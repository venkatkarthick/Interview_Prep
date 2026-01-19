package Revision.LLD;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Y_RateLimiter {

    static class User{
        int UserId;
        UserType userType;
        public User(int userId, UserType userType) {
            UserId = userId;
            this.userType = userType;
        }
    }
    enum UserType {
        FREE_USER, PREMIUM_USER
    }

    static abstract class RateLimiter{
        RateLimitConfig rateLimitConfig;
        RateLimitType rateLimitType;
        public RateLimiter(RateLimitConfig rateLimitConfig, RateLimitType rateLimitType) {
            this.rateLimitConfig = rateLimitConfig;
            this.rateLimitType = rateLimitType;
        }
        public abstract boolean allowRequest(Integer userId);
    }
    static class TokenBucketRateLimiter extends RateLimiter {
        private final Map<Integer, Integer> tokens = new ConcurrentHashMap<>(); //Concurrent hashmap is used to handle/synchronize same user requests coming simultaneously
        private final Map<Integer, Long> lastRefillTime = new HashMap<>();
        public TokenBucketRateLimiter(RateLimitConfig rateLimitConfig, RateLimitType rateLimitType) {
            super(rateLimitConfig, rateLimitType);
        }
        @Override
        public boolean allowRequest(Integer userId) {
            AtomicBoolean allowed=new AtomicBoolean(false); //Inside lambda functions, only atomic values or final boolean[] can be used.
            long now = System.currentTimeMillis();
            //synchronization logic
            tokens.compute(userId, (id, availableTokens) -> {
                int currentTokens = refillTokens(userId, now);
                if(currentTokens>0) {
                    allowed.set(true);
                    return currentTokens-1;
                } else {
                    return currentTokens;
                }
            });
            return allowed.get();
        }
        //0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
        //Refill rate = 60/10 = 6 token per minute.
        //say last time is 2min and current time is 14min. We need to find how many tokens got refilled in that interval.
        //So it is (elapsed time/Refilled rate) => ((14-2)/6) = 2. So 2 tokens are added in that elapsed time.
        private int refillTokens(int userId, long now) {
            double refillRate = (double) rateLimitConfig.windowInSeconds/rateLimitConfig.maxRequests;
            long lastRefill = lastRefillTime.getOrDefault(userId, now);
            long elapsedSeconds = (now - lastRefill)/1000;
            int refillTokens = (int) (elapsedSeconds/refillRate);

            int currentTokens = tokens.getOrDefault(userId, rateLimitConfig.maxRequests);
            currentTokens = Math.min(rateLimitConfig.maxRequests, currentTokens+refillTokens);
            if(refillTokens>0) lastRefillTime.put(userId, now);
            return currentTokens;
        }
    }
    static class SlidingWindowLogRateLimiter extends RateLimiter {
        private final Map<Integer, Queue<Long>> requestLog = new ConcurrentHashMap<>();
        public SlidingWindowLogRateLimiter(RateLimitConfig rateLimitConfig, RateLimitType rateLimitType) {
            super(rateLimitConfig, rateLimitType);
        }
        @Override
        public boolean allowRequest(Integer userId) {
            AtomicBoolean allowed=new AtomicBoolean();
            long now = System.currentTimeMillis()/1000;
            requestLog.compute(userId, (id, log) -> {
                if(log==null) log=new ArrayDeque<>();
                while(!log.isEmpty() && (now-log.peek())>=rateLimitConfig.windowInSeconds) {
                    log.poll();
                }
                if(log.size()<rateLimitConfig.maxRequests) {
                    log.add(now);
                    allowed.set(true);
                }
                return log;
            });
            return allowed.get();
        }
    }
    static class RateLimitConfig {
        int maxRequests;
        int windowInSeconds;
        public RateLimitConfig(int maxRequests, int windowInSeconds) {
            this.maxRequests = maxRequests;
            this.windowInSeconds = windowInSeconds;
        }
    }
    enum RateLimitType{
        TOKEN_BUCKET, SLIDING_WINDOW, FIXED_WINDOW
    }
    static class RateLimiterFactory {
        public static RateLimiter createRatelimiter(RateLimitType rateLimitType, RateLimitConfig rateLimitConfig) {
            switch (rateLimitType) {
                case TOKEN_BUCKET : return new TokenBucketRateLimiter(rateLimitConfig, rateLimitType);
                case SLIDING_WINDOW: return new SlidingWindowLogRateLimiter(rateLimitConfig, rateLimitType);
                default: return null;
            }
        }
    }

    static class RateLimiterService {
        private final Map<UserType, RateLimiter> ratelimiters = new HashMap<>();
        RateLimiterService() {
            ratelimiters.put(UserType.FREE_USER,
                    RateLimiterFactory.createRatelimiter(RateLimitType.TOKEN_BUCKET, new RateLimitConfig(10, 60))); //10req/min
            ratelimiters.put(UserType.PREMIUM_USER,
                    RateLimiterFactory.createRatelimiter(RateLimitType.SLIDING_WINDOW, new RateLimitConfig(30, 60))); //100req/min
        }
        public boolean allowRequest(User user) throws Exception {
            RateLimiter rateLimiter=ratelimiters.get(user.userType);
            if(rateLimiter==null) throw new Exception("No limiter configured for tier");
            return rateLimiter.allowRequest(user.UserId);
        }
    }

    public static void main(String[] args) {
        User user1=new User(1, UserType.FREE_USER);
        User user2=new User(2, UserType.PREMIUM_USER);

        RateLimiterService rateLimiterService=new RateLimiterService();

        try {
            for (int i = 0; i <20; i++) {
                boolean allowed = rateLimiterService.allowRequest(user1);
                System.out.println("User 1 : Request " +i+ " : " +(allowed?"ALLOWED":"BLOCKED"));
                Thread.sleep(100);
            }
            for (int i = 0; i <40; i++) {
                boolean allowed = rateLimiterService.allowRequest(user2);
                System.out.println("User 2 : Request " +i+ " : " +(allowed?"ALLOWED":"BLOCKED"));
                Thread.sleep(100);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

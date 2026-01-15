package Revision.LLD;

public class F_ChainOfResponsibilityPattern {

    enum LogStatus { INFO, DEBUG, ERROR };
    //Logger Example
    static abstract class Logger{
        Logger nextLogger;
        Logger(Logger nextLogger) {
            this.nextLogger=nextLogger;
        }
        public void log(LogStatus logLevel, String message) {
            if(nextLogger!=null) {
                nextLogger.log(logLevel, message);
            }
        }
    }
    static class InfoLogger extends Logger{
        InfoLogger(Logger logger) {
            super(logger);
        }
        public void log(LogStatus logLevel, String message) {
            if(logLevel==LogStatus.INFO) {
                System.out.println("INFO : " +message);
            } else {
                super.log(logLevel, message);
            }
        }
    }
    static class DebugLogger extends Logger{
        DebugLogger(Logger logger) {
            super(logger);
        }
        public void log(LogStatus logLevel, String message) {
            if(logLevel==LogStatus.DEBUG) {
                System.out.println("DEBUG : " +message);
            } else {
                super.log(logLevel, message);
            }
        }
    }
    static class ErrorLogger extends Logger{
        ErrorLogger(Logger logger) {
            super(logger);
        }
        public void log(LogStatus logLevel, String message) {
            if(logLevel==LogStatus.ERROR) {
                System.out.println("ERROR : " +message);
            } else {
                super.log(logLevel, message);
            }
        }
    }

    public static void main(String[] args) {
        Logger logger=new InfoLogger(new DebugLogger(new ErrorLogger(null)));

        logger.log(LogStatus.INFO, "Statement is correct");
        logger.log(LogStatus.DEBUG, "Statement needs to be analysed");
        logger.log(LogStatus.ERROR, "Statement is wrong");
    }

}

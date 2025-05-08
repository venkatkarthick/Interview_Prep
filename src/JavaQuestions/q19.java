package JavaQuestions;

public class q19 {
        public static int testMethod() {
            try{
                return 10;
            } finally {
                return 20;
            }
        }
        public static void main(String[] args) {
            System.out.println(testMethod());

            //Finally call overrides the return
        }
    }
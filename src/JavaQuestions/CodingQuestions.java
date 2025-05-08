package JavaQuestions;

import java.util.Arrays;

public class CodingQuestions {

    public static void q1() {
        int x=10;
        int y=20;
        System.out.println(x+y+"Hello"); //30Hello
        System.out.println("Hello"+x+y); //Hello1020

        //Inside sout, After a string is seen, all other objects are treated as strings
    }

    public static void q3() {
        System.out.println("Hello World");
        String str="RRR";
        String[] result = str.split("");
        //Splits char by char as string. Use toCharArray for char[]
        System.out.println(Arrays.toString(result));
    }

    public static void q4() {
        int x=10;
        x= x++ + ++x;
        System.out.println(x);

        //22
    }

    public static void q6() {
        int a=5;
        int b=2;
        double ans=a/b;
        System.out.println(ans);

        //2.0 not 2.5 because inputs are int
    }

    public static void q7() {
        String s = "Java";
        s.replace('J', 'B');
        System.out.println(s);

        //return java not bava since strings are immutable
        // and replace will give a return value to store the updated value
    }

    public static void q8() {
        String s1 = "abc";
        String s2="def";
        String s3=s1+s2;
        String s4="abcdef";
        System.out.println(s3==s4);

        //false. Here == compares references. S3 is stored in heap as it is dynamically created,
        // it will be internally created with new String() but S4 is created at compile time and
        //stored in string pool.
    }

    public static void q9() {
        String s = null;
        System.out.println(String.valueOf(s).concat("Test"));

        //nullTest. If we pass null obj, valueOf method in string class returns "null"
    }

    public static void q10() {
        String s = " ";
        System.out.println(s.isBlank()+", "+s.isEmpty());

        //true, false. isBlank return true if string is empty or contains only whitespace characters
        //isEmpty return true if string's length is 0
    }

    public static void q11() {
        String s="Java";
        System.out.println(s.substring(3,3).length());

        //0. Since substring loops from begInd to endInd-1. It will empty String "" and its length is 0
    }

    public static void q13() {
        String s="J";
        System.out.println(s.repeat(3));
    }

    public static class q14 {
        //sequence of initialization
        static{
            System.out.println("1. Static block");
        }
        {
            System.out.println("2. Instance initializer block");
        }
        q14() {
            System.out.println("3. Constructor");
        }

        public static void main(String[] args) {
            System.out.println("4. Main method started");
            q14 ins=new q14();
            System.out.println("5. Main method ended");
        }
    }

    public static class q21 {
        int x;

        public q21(int x) {
            x=x;
        }
        public void display() {
            System.out.println("x: "+x);
        }

        public static void main(String[] args) {
            q21 q=new q21(6);
            q.display();
        }
    }

    public static class q22 {
        //This keyword usage
        q22() {
            this(10);
        }
        q22(int x) {
            System.out.println("x: "+x);
        }

        public static void main(String[] args) {
            new q22();
        }
    }

    public static class q24 {
        public static void main(String[] args) {
            System.out.println(outerMethod());
        }

        private static int outerMethod() {
            try {
                return innerMethod();
            } catch (ArithmeticException e) {
                return 2;
            }
        }

        private static int innerMethod() {
            try {
                return 10/0;
            } catch (ArithmeticException e) {
                return 3;
            }
        }
    }

    public static class q25 {
        static class MyThread extends Thread {
            public void run() {
                System.out.println("Thread started");
            }
        }
        public static void main(String[] args) {
            MyThread myThread=new MyThread();
            myThread.start();
            myThread.start();
        }
    }

    public static class q26 {
        static class MyThread extends Thread {
            public void run() {
                System.out.println("Thread started");
            }
        }
        public static void main(String[] args) {
            MyThread myThread=new MyThread();
            myThread.run();  //Executes in current thread
            myThread.start(); //Executes in a new thread created
        }
    }


    public static void main(String[] args) {
        q13();
    }

}

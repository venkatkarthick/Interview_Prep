package JavaQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingQuestions {

    public static void q1() {
        int x=10;
        int y=20;
        System.out.println(x+y+"Hello"); //30Hello
        System.out.println("Hello"+x+y); //Hello1020

        //Inside sout, After a string is seen, all other objects are treated as strings
    }

    static class q3{
        public static void main(String[] args) {
            System.out.println("Hello World");
            String str="RRR";
            String[] result = str.split("");
            //Splits char by char as string. Use toCharArray for char[]
            System.out.println(Arrays.toString(result));
            System.out.println(Arrays.toString(str.toCharArray()));
        }
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

    static class q7{
        public static void main(String[] args) {
                String s = "Java";
                String newS=s.replace('J', 'B');
                System.out.println(s);
                System.out.println(newS);

                //return java not bava since strings are immutable
                // and replace will give a return value to store the updated value
        }
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

    static class q13 {
        public static void main(String[] args) {
            String s="J";
            System.out.println(s.repeat(3));
        }
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

    public static class q27 {
        public static void main(String[] args) {
            List<String> list = new ArrayList<>();
            list.add("A");
            list.add("B");
            list.add("C");
            list.remove("B");
            list.remove(1);
            System.out.println(list);
        }
    }

    public static class q28 {
        public static void main(String[] args) {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
            list.remove(2);
            list.remove(Integer.valueOf(2));
            System.out.println(list);
        }
        //[1,2] -> here primitive integer is passed so remove from index method invoked
        // We need to pass Integer object as Integer.valueOf(2) to invoke remove an element method
    }

    public static class q29 {
        public static void main(String[] args) {
            List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
            for (String s : list) {
                if (s.equals("A")) {
                    list.remove(s);
                }
            }
            System.out.println(list);
        }
        //throws concurrent modification exception.
        // Use structural modifications inside for loop
    }

    public static void q30() {
        String s1="abc";
        String s2=s1;
        s1+="d";
        System.out.println(s1==s2);
        //false, Since strings are immutable a new String is formed in the string pool for s1 and thus both points to different address

        StringBuffer s3=new StringBuffer("abc");
        StringBuffer s4=s3;
        s3.append("d");
        System.out.println(s3==s4);
        //true, String buffer is mutable and both objects are pointed to same reference
    }

    public static void main(String[] args) {
        q30();
    }

}

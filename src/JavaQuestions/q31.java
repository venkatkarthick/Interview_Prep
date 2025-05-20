package JavaQuestions;

class Parent {
    final public void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
//    public void show() {
//        System.out.println("Child");
//    }
}

public class q31 {
    public static void main(String[] args) {
        Parent b=new Child();
        b.show();

        //Error since final methods cannot be overriden in child classes
    }
}

package LLD.HeadFirstDesignPattern.B_ObserverPattern;

import javax.swing.*;

public class SwingObserverExample {
    JFrame frame;

    public static void main(String[] args) {
        SwingObserverExample swingObserverExample=new SwingObserverExample();
        swingObserverExample.go();
    }

    private void go() {
        frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);

        JButton button=new JButton("Should I click it?");
        button.addActionListener(e->System.out.println("Don't click it, it's a trap!")); //DevilListener
        button.addActionListener(e->System.out.println("Come on, click it!")); //AngelListener

        frame.add(button);
    }
}

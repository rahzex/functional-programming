package com.rahzex.functionalProg.advanceLambda;



@FunctionalInterface
interface Variable {
    void doIt();
}

public class VariableCapture {
    //instance variable
    private int counter;

    //capturing instance variable inside lambda
    public void increment() {
        Variable variable = () -> {
            //instance variable is captured
            System.out.println(++counter);
        };
        variable.doIt();
    }

    public static void main(String[] args) {
        new VariableCapture().increment();
        //local variable
        String name="Subramanian";
        //Capturing a local variable inside lambda
        Variable variable = ()->{
            System.out.println(name);
        };
        variable.doIt();

    }

}


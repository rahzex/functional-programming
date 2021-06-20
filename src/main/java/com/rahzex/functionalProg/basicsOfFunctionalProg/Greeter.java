package com.rahzex.functionalProg.basicsOfFunctionalProg;


//SAM : Single abstract method: interface having only one abstract method
interface Greeter {
    String greet();
}
//ways to implement/use this interface

//way -1 : declare class which implements Greeter
class GreeterImpl implements Greeter{
    @Override
    public String greet() {
        return "Hello";
    }
}
class GreeterImplMain{
    public static void main(String[] args) {
        //create object for GreeterImpl
        Greeter g = new GreeterImpl();
        System.out.println(g.greet());
        //Way 2 : loosly coupled ; inner classes ; anonymous inner class
        Greeter greetre = new Greeter() {
            @Override
            public String greet() {
                return "Hi, use and throe this and don't maintain a seperate class just to return a string";
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


        //problems with above code:
        // 1. Not declarative
        // how to covert the above code to declarative ?????????? LAMBDA or FUNCTIONAL

        //Way3 :LAMBDA
        //Way 3 : lambda style
        Greeter mygreeter = () -> {
            return "Hello Lambda Vertx Application";
        };
        System.out.println(mygreeter.greet());

        Runnable runner = () -> {
            System.out.println(" Lambda thread " + Thread.currentThread().getName());
        };
        thread = new Thread(runner);
        thread.start();
    }
}


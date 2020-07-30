package com.corejava.thread;

public class ThreadTest{

    public static class MyThread extends Thread{
        public Printer ref;

        public MyThread(){super();}

        public MyThread(Printer ref){
            super();
            this.ref = ref;
        }

        @Override
        public void run() {
            ref.print(5,"From MyThread, Printing Paul's Document");
        }
    }

    public static class YourThread extends Thread{
        public Printer ref;

        public YourThread(){super();}

        public YourThread(Printer ref){
            super();
            this.ref = ref;
        }

        @Override
        public void run() {

            ref.print(5,"From YourThread, Printing Peter's Document");
        }
    }

    public static class Printer{

        int n;
        String statement;

        public void print(int n, String statement){
            for(int i=1; i<=n;i++){
                System.out.println(statement+ " "+ i);
            }
        }
    }

    public static void main(String[] args) {

        Printer newPrinter = new Printer();


        MyThread thread1 = new MyThread(newPrinter);
        YourThread thread2 = new YourThread(newPrinter);

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }



}


package com.rymar.Lab4;

import javafx.scene.control.TextArea;

public class Lab4 {

    public static void main(String[] args, TextArea textArea) {
        //Проверка ввода
        if (args.length!=1){
            textArea.appendText("Неверное кол-во чисел\n");
            return;
        }
        int argument;
        try {
            argument=Integer.parseInt(args[0]);
        } catch (Exception e){
            textArea.appendText("неудалось распарсить параметр!\n");
            return;
        }
        textArea.appendText("Введённый параметр равен :"+argument+"\n");
        if (argument<=0){
            textArea.appendText("неверный ввод!!\n");
            return;
        }

        Work1 work1=new Work1(argument,textArea);
        Work2 work2=new Work2(argument,textArea);
        Work3 work3=new Work3(argument,textArea);

        Thread thread1=new Thread(new DiggingHole(work1,work2,textArea));
        Thread thread2=new Thread(new SqueezeWood(work2,work3,textArea));
        Thread thread3=new Thread(new TiesSedling(work3,work1,textArea));
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textArea.appendText("Вуаля!)\n");
    }
}
class DiggingHole implements Runnable {
    TextArea textArea;
    Work1 work1;
    Work2 work2;

    DiggingHole(Work1 work1, Work2 work2,TextArea textArea) {
        this.work1 = work1;
        this.work2 = work2;
        this.textArea=textArea;
    }

    @Override
    public void run() {
        synchronized (this.work1) {
            while (work1.count <= work1.arg) {

//            System.out.println(Thread.currentThread().getName()+"взял монитор 1");
                work1.textArea.appendText(Thread.currentThread().getName() + " копаю яму номер " + work1.count+"\n");
                synchronized (this.work2) {
//                System.out.println(Thread.currentThread().getName()+"взял монитор 2");
                    try {
//                    System.out.println(Thread.currentThread().getName() + " бужу поток 1");
                        work2.notify();
                    } catch (Exception e) {
                        work2.textArea.appendText("Исключение типа InterruptedException перехвачено\n");
                    }
                }
                if (work1.count <= work1.arg) {
                    work1.count++;
//                    System.out.println(Thread.currentThread().getName() + " ложусь спать в мониторе 1 ");
                    try {
                        work1.wait();
                    } catch (InterruptedException e) {
                        work1.textArea.appendText("Исключение типа InterruptedException перехвачено\n");
                    }
                }
            }
        }
    }

}


class SqueezeWood implements Runnable{
    TextArea textArea;
    Work2 work2;
    Work3 work3;

    SqueezeWood(Work2 work2,Work3 work3,TextArea textArea) {
        this.work2 = work2;
        this.work3 = work3;
        this.textArea=textArea;
    }

    @Override
    public void run() {
        synchronized (work2) {
            while (work2.count <= work2.arg) {
                if (work2.count <= work2.arg) {
//                    System.out.println(Thread.currentThread().getName() + " ложусь спать ");
                    try {
                        work2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(Thread.currentThread().getName() + " разбудили " );
                    work2.textArea.appendText(Thread.currentThread().getName() + " сажу дерево номер  " + work2.count+"\n");
                    work2.count++;
                }
                synchronized (this.work3) {
//                    System.out.println(Thread.currentThread().getName()+"взял монитор 3");
                    try {
//                        System.out.println(Thread.currentThread().getName() + " бужу поток 2");
                        work3.notify();
                    } catch (Exception e) {
                        work3.textArea.appendText("Исключение типа InterruptedException перехвачено\n");
                    }
                }
            }
        }
    }
}

class TiesSedling implements Runnable{
    TextArea textArea;
    Work3 work3;
    Work1 work1;

    TiesSedling(Work3 work3,Work1 work1,TextArea textArea) {
        this.work3 = work3;
        this.work1 = work1;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        synchronized (work3) {
            while (work3.count <= work3.arg) {
                if (work3.count <= work3.arg) {
                    try {
                        work3.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    work3.textArea.appendText(Thread.currentThread().getName() + " подвязываю дерево номер " + work3.count + "\n");
                    work3.count++;

                    synchronized (this.work1) {
                        try {
                            work1.notify();
//                            System.out.println(Thread.currentThread().getName() + " разбудил  поток 0");
                        } catch (Exception e) {
                            work1.textArea.appendText("Исключение типа InterruptedException перехвачено\n");
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        }

    }
}

class Work1 {
    TextArea textArea;
    int arg;
    int count=1;
    Work1(int arg,TextArea ta) {
        this.arg = arg;
        this.textArea = ta;
    }
}
class Work2 {
    TextArea textArea;
    int arg;
    int count=1;
    Work2(int arg,TextArea ta) {
        this.arg = arg;
        this.textArea = ta;
    }
}

class Work3 {
    TextArea textArea;
    int arg;
    int count=1;
    Work3(int arg,TextArea ta) {
        this.arg = arg;
        this.textArea = ta;
    }
}
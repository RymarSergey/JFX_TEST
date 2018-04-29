package com.rymar.Lab3;

import javafx.scene.control.TextArea;

public class Lab3 {

    public static void main(String[] args,TextArea textArea) {
        //Проверка ввода
        if (args.length!=1){
            textArea.appendText("Неверное кол-во чисел\n");
            return;
        }
        int argument=0;
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
//      Инициализация и запуск потоков
	    Resource resource=new Resource(textArea);
        Thread thread1=new Thread(new PutNumber(resource,argument,textArea));
        Thread thread2=new Thread(new GetNumber(resource,argument,textArea));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textArea.appendText("Вуаля!)\n");
    }
}

class PutNumber implements Runnable{
    TextArea textArea;
     private Resource resource;
     private int arg;
     PutNumber(Resource resource,int arg,TextArea ta) {
        this.resource = resource;
        this.arg=arg;
        this.textArea=ta;
    }

    @Override
    public void run() {
       int i=0;
       synchronized (resource) {
           while (i < arg) {
               i++;
               resource.putResource(i);
           }
           textArea.appendText(Thread.currentThread().getName() + " закончился!)\n");
       }
    }
}


class GetNumber implements Runnable{
    TextArea textArea;
   private Resource resource;
   private int arg;
     GetNumber(Resource resource,int arg,TextArea ta) {
        this.resource = resource;
        this.arg = arg;
         this.textArea=ta;
     }
    @Override
    public void run(){
         int count=1;
         synchronized (resource) {
             while (count <= arg) {
                 resource.getResource(arg);
                 count++;
             }
             textArea.appendText(Thread.currentThread().getName() + " закончился!!\n");
         }
    }
}
class Resource {
    TextArea textArea;
     int i;

    Resource(TextArea textArea) {
        this.textArea = textArea;
    }

     void getResource(int arg) {

        textArea.appendText(Thread.currentThread().getName()+" получено : "+i+"\n");
            try {
//               System.out.println(Thread.currentThread().getName()+"  бужу");
                notify();

                if (i<arg){
//                    System.out.println(Thread.currentThread().getName()+"  ложусь спать");
                    wait();
                }

            } catch (InterruptedException e) {
                textArea.appendText("Исключение типа InterruptedException перехвачено\n");
            }
    }

     void putResource(int i) {

        this.i = i;
        textArea.appendText(Thread.currentThread().getName()+" отправленно : "+i+"\n");

            try {
//                System.out.println(Thread.currentThread().getName()+" бужу");
                notify();
//                System.out.println(Thread.currentThread().getName()+" ложусь спать");
                wait();
            } catch (InterruptedException e) {
                textArea.appendText("Исключение типа InterruptedException перехвачено\n");
            }
    }

}

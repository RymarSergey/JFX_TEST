package com.rymar.Lab2;

import javafx.scene.control.TextArea;

public class Lab2 {

    public static void main(String[] args,TextArea textArea) {
        int countOfSnakes=Integer.parseInt(args[0]);
        int countOfDogs=Integer.parseInt(args[1]);
        if ((countOfDogs<0)||(countOfSnakes<0)){
            textArea.appendText("неверный ввод!\n");
            return;
        }
        //создание массива
        int lenght=countOfDogs+countOfSnakes;
        Creep[] creeps=new Creep[lenght];
        for (int index = 0; index < lenght; index++) {
            if (index <countOfSnakes) {
                creeps[index] = new Snake();
            } else {
                creeps[index] = new Dog();
            }
        }
        //выведение массива и методов
      /*  for (int index = 0; index <lenght ; index++) {
            if (creeps[index] instanceof Snake){
                textArea.appendText(((Snake)creeps[index]).whoAmI());
                textArea.appendText(((Snake)creeps[index]).creep());
                textArea.appendText(((Snake)creeps[index]).wriggle());
            } else {
                textArea.appendText(creeps[index].whoAmI());
                textArea.appendText(creeps[index].creep());
            }
        }*/
        for (int index = 0; index <lenght ; index++) {
            textArea.appendText(creeps[index].whoAmI()+"\n");
            textArea.appendText(creeps[index].creep()+"\n");
            if (creeps[index] instanceof Snake){
                textArea.appendText(((Snake)creeps[index]).wriggle()+"\n");
            }
        }
    }
}

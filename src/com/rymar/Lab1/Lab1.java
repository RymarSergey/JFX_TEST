package com.rymar.Lab1;


import javafx.scene.control.TextArea;

public class Lab1 {

    public static void main(String[] args,TextArea textArea) {
        int countOfArguments=args.length;
        textArea.appendText(" аргумента "+countOfArguments+" : \n");
        if (args.length!=3){
            textArea.appendText("Неверное кол-во чисел\n");
            return;
        }
        int[] arguments= new int[countOfArguments];

        for (int index = 0; index < countOfArguments; ++index) {
            try {
                arguments[index] = Integer.parseInt(args[index]);//переводим аргументы в int
            } catch (Exception e){
                textArea.appendText("Неправильный ввод!\n");
                return;
            }
            textArea.appendText( arguments[index]+" ");
            }

        textArea.appendText(" НОД "+NodFor_3_Nums(arguments)+"\n");
}
    static int NodFor_3_Nums(int [] num)
    {
        int min=num[0];
        int nod =1;
        for (int index = 0; index < num.length; ++index) {
        if(min > num[index])
            min = num[index];
        }

        for(int index = min;index >1;--index)
        {
            if(num[0] % index == 0 && num[1] % index == 0 && num[2] % index == 0){
                if(index>nod)
                    nod = index;
            }
        }
        return nod;
    }
}

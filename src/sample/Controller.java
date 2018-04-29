package sample;

import com.rymar.Lab1.Lab1;
import com.rymar.Lab2.Lab2;
import com.rymar.Lab3.Lab3;
import com.rymar.Lab4.Lab4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.PatternSyntaxException;

public class Controller implements Initializable{
    private int index;
    private Lab1 lab1;
    private Lab2 lab2;
    private Lab3 lab3;
    private Lab4 lab4;
    private ObservableList<String > choice= FXCollections.observableArrayList("Лабораторная 1","Лабораторная 2","Лабораторная 3","Лабораторная 4");
    @FXML
    private ComboBox comboBox ;
    @FXML
    private TextArea descriptionText;
    @FXML
    private TextArea resultText;
    @FXML
    private TextField parametrs;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(choice);
        lab1 = new Lab1();
        lab2 = new Lab2();
        lab3 = new Lab3();
        lab4 = new Lab4();
        parametrs.setEditable(false);
        descriptionText.setEditable(false);
        resultText.setEditable(false);
    }
    public void comboChanged(javafx.event.ActionEvent actionEvent) {
        switch (comboBox.getValue().toString()){
            case "Лабораторная 1" :
                index=1;
                descriptionText.setText("Написать приложение которое вводит\n" +
                        " 3 целых положительных числа\n" +
                        "  из командной строки и находит \n" +
                        "наибольший общий делитель. \n" +
                        "Вывести на дисплей введенные \n" +
                        "числа и полученный результат.");
                parametrs.setEditable(true);
                resultText.setText("");
                parametrs.setText("");
                break;
            case "Лабораторная 2" :
                index=2;
                descriptionText.setText("Есть класс Creature c подклассами Snake, Dog \n" +
                        " и есть интерфейс Creep c методом \n" +
                        "creep(например метод выводит:I can creep)\n" +
                        " и методом whoAmI. Создать унаследованный от\n" +
                        " Creep  подинтерфейс Wriggle c методом wriggle.\n" +
                        " Класс Snake реализует интерфейс Wriggle, а класс\n" +
                        " Dog - Creep. \n" +
                        "  Создать массив объектов Сreep, как представителей\n" +
                        " классов Snake , Dog,состоящий из количества  элементов,\n" +
                        " заданных параметром. Вывести его на дисплей.\n" +
                        " Для каждого объекта Сreep выполнить все  методы, которые\n" +
                        " реализованы в соответствующих классах.Вывод на дисплей\n" +
                        " результатов выполнения.");
                parametrs.setEditable(true);
                resultText.setText("");
                parametrs.setText("");
                break;
            case "Лабораторная 3" :
                index=3;
                descriptionText.setText("Создать 2 потока, один из которых записывает любое число  \n" +
                        "в разделенную между потоками переменную , а другой считывает это число.\n" +
                        "Параметр приложения задаст количество выполнений.  \n" +
                        "Нужно выводить имя работающего потока и - записываемое число   для первого \n" +
                        "потока или считанное число для второго потока  .\n" +
                        "Выполнить задание   с использованием конструкции synchronized . \n" +
                        "Не использовать в этом задании флаги для синхронизации потоков, а только методы\n" +
                        "wait и notify.Также не использовать любые задержки для потоков после начала их \n" +
                        "работы в виде методов sleep, yield или wait c параметром.");
                parametrs.setEditable(true);
                resultText.setText("");
                parametrs.setText("");
                break;
            case "Лабораторная 4" :
                index=4;
                descriptionText.setText("Создать приложение с 3 потоками для следующей задачи: 3 работника выполняют следующую\n" +
                        " работу: 1-ый копает яму, 2-ой сажает дерево,  3-ий подвязывает саженец к кольям. \n" +
                        "Работа идет строго по очереди: пока не подвязан саженец 1 -ый рабочий отдыхает\n" +
                        "(т.е. поток находится в состоянии ожидания), пока не вскопана яма отдыхает 2 рабочий , \n" +
                        "и пока не посажено дерево 3 -й работник отдыхает. Число саженцев задается параметром.  \n" +
                        "Использовать ограничения из задания 3. \n" +
                        "Выводить на дисплей номер работника и номер саженца.");
                parametrs.setEditable(true);
                resultText.setText("");
                parametrs.setText("");
                break;
        }
    }

    public String[] argsParce1() {
        String textLine = parametrs.getText();
        String[] args={};
        try{
        args = textLine.split(" ");
        } catch (PatternSyntaxException e){

        }
        return args;
    }

    public void StartButtonPushed(ActionEvent actionEvent) {
            switch (index) {
                case 1:
                    Lab1.main(argsParce1(), resultText);
                    break;
                case 2:
                    Lab2.main(argsParce1(), resultText);
                    break;
                case 3:
                    Lab3.main(argsParce1(), resultText);
                    break;
                case 4:
                    Lab4.main(argsParce1(), resultText);
                    break;
                default:
                    resultText.appendText("Выбирите номер лабораторной\n");
            }
    }
}


package ru.kazanin.lab4;

import ru.kazanin.lab4.entities.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Главный класс приложения.
 * @author Andrey Kazanin
 * @version 1.0
 */
public class Main {

    /**
     * Точка входа в программу.<br>
     * Реализует взаимодействие с пользователем.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        String pathToCSV = System.getProperty("user.dir") + "\\src\\main\\resources\\foreign_names.csv";
        ArrayList<Person> cont = new ArrayList<>();
        // из за ограничений IDE может вывести не весь список людей
        // но в cont он хранится целиком!!!
        try {
            CSV_Parser reader = new CSV_Parser(pathToCSV, ';', cont);
            reader.print();
            cont = new ArrayList<>(reader.getPersons());
        }
        catch (FileNotFoundException except){
            System.out.println("Таблица не найдена");
        }
    }
}
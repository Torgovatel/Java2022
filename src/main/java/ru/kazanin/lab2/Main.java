package ru.kazanin.lab2;

import java.util.Scanner;

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
        String str = "";
        Scanner in = new Scanner(System.in);

        while(!str.equals("stop")) {

            System.out.print("Введите выражение: ");
            str = in.nextLine();

            if (!str.equals("stop")) {
                Expression expr = new Expression(str);
                if (!expr.checkCorrect()) {
                    System.out.println("Выражение задано некорректно");
                } else {
                    System.out.print(str + " = ");
                    System.out.println(expr.calculate());
                    System.out.println("Вычислено по постфиксной форме " + expr.getPostfix());
                }
            }
        }
    }
}
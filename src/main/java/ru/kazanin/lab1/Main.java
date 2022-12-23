package ru.kazanin.lab1;

import java.util.Scanner;

/**
 * Вспомогательное перечисление для определения типа команды пользователя.
 */
enum CommandType{
    stop,
    pop,
    push,
    help,
    peek,
    clear,
    print,
    undefined
}

/**
 * Главный класс приложения.
 * @author Andrey Kazanin
 * @version 1.0
 */
public class Main {
    /**
     * Преобразование строковой команды к перечислению для дальнейшей обработки.
     * @param command строка, содержащая команду пользователя.
     * @return {@link CommandType}
     */
    public static CommandType parseCommand(String command) {
        if (command.substring(0, 3).equals("pop"))
            return CommandType.pop;
        if (command.substring(0, 4).equals("push"))
            return CommandType.push;
        if (command.substring(0, 4).equals("help"))
            return CommandType.help;
        if (command.substring(0, 4).equals("stop"))
            return CommandType.stop;
        if (command.substring(0, 4).equals("peek"))
            return CommandType.peek;
        if (command.substring(0, 5).equals("clear"))
            return CommandType.clear;
        if (command.substring(0, 5).equals("print"))
            return CommandType.print;
        return CommandType.undefined;
    }

    /**
     * Точка входа в программу.<br>
     * Реализует взаимодействие с пользователем.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        test();
    }

    /**
     * Метод для пользовательского взаимодействия со списком.<br>
     * Позволяет пользователю путем ввода команд выполнять простейшие действия со списком.
     */
    private static void test() {
        String commandString = "";
        CommandType command = CommandType.undefined;
        Scanner input = new Scanner(System.in);
        help();
        MyList<Integer> cont = new MyList<Integer>();

        while (command != CommandType.stop) {
            commandString = input.nextLine().toLowerCase().strip();
            command = parseCommand(commandString);
            switch (command) {
                case pop:
                    cont.pop();
                    break;

                case push:
                    cont.push(Integer.parseInt(commandString.substring(4).strip()));
                    break;

                case help:
                    help();
                    break;

                case stop:
                    break;

                case peek:
                    try {
                        System.out.println("Первый элемент списка: " + cont.peek());
                    } catch (IndexOutOfBoundsException except) {
                        System.out.println("В списке еще нет элементов");
                    }
                    break;

                case clear:
                    cont.clear();
                    break;

                case print:
                    System.out.println(cont.toString());
                    break;

                case undefined:
                    System.out.println("Неопределённая команда, повторите ввод");
                    break;
            }
        }
    }
    /**
     * Метод вывода справки help (по командам) для пользователя.
     */
    private static void help() {
        System.out.println("""
                Командное меню:
                    push <value> - вставить <value> в начало списка
                    pop - удалить элемент из начала списка
                    peek - получить первый элемент списка
                    clear - удалить все элементы списка
                    print - вывести содержимое списка
                                
                    help - справка
                    stop - завершить работу
                """);
    }
}
package ru.kazanin.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.zip.Adler32;

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
    public static void main(String[] args){
        Caller check = new Caller();
        int start = 6;
        int end = 14;
        LinkedList<Integer> list = new LinkedList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        System.out.println("===========================================================");
        for (int n = start; n <= end; n++) {
            check.callsCount = (int) Math.pow(2, n);
            System.out.println("Для количества операций = " + check.callsCount + ":");
            System.out.printf("ArrayList.add() : %d мс\t\t\tLinkedList.add() : %d мс\n",
                    check.timeForMethod(arr, Caller.MethodType.Add), check.timeForMethod(list, Caller.MethodType.Add));
            System.out.printf("ArrayList.get() : %d мс\t\t\tLinkedList.get() : %d мс\n",
                    check.timeForMethod(arr, Caller.MethodType.Get), check.timeForMethod(list, Caller.MethodType.Get));
            System.out.printf("ArrayList.remove() : %d мс\t\tLinkedList.remove() : %d мс\n",
                    check.timeForMethod(arr, Caller.MethodType.Delete), check.timeForMethod(list, Caller.MethodType.Delete));
            System.out.println("===========================================================");
        }
    }
}
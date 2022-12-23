package ru.kazanin.lab5;

import ru.kazanin.lab5.injection.InjectableTemplate;
import ru.kazanin.lab5.injection.Injector;

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
        String basicPath = System.getProperty("user.dir") + "\\src\\main\\resources\\";
        String[] testPaths = {
                basicPath + "test1.properties",
                basicPath + "test2.properties",
                basicPath + "test3.properties",
                basicPath + "test4.properties"
        };

        System.out.println("===================================");
        for (int i = 0; i < testPaths.length; i++) {
            System.out.println("" + (i + 1) + " тест : ");
            System.out.println("-----------------------------------");
            try {
                InjectableTemplate test = (new Injector(testPaths[i])).inject(new InjectableTemplate());
                test.printSomething();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            System.out.println("===================================");
        }
    }
}

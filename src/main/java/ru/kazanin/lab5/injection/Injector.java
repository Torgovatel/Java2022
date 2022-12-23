package ru.kazanin.lab5.injection;

import ru.kazanin.lab5.annotations.AutoInjectable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для рефлексии из задания по полям, помеченным {@link AutoInjectable}
 * @author Andrey Kazanin
 * @version 1.0
 */
public class Injector {

    /**
     * Property файл для рефлексивных связей.
     */
    private File dataFile;

    /**
     * Конструктор по пути к Property файлу {@link #dataFile}.
     */
    public Injector(String filePath) {
        dataFile = new File(filePath);
    }

    /**
     * Метод внедрения зависимостей
     * @param obj объект для внедрения зависимости
     * @param <T> object class
     * @return экземпляр объекта после внедрения зависимостей и приведения к исходному классу
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T inject (T obj) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(new FileReader(dataFile));                  // считываем нужные зависимости
        Class classOfObject = obj.getClass();                       // создаем объект класса в runtime
        Field[] fields = classOfObject.getDeclaredFields();         // получаем список полей
        for (Field f: fields) {
            Annotation a = f.getAnnotation(AutoInjectable.class);
            f.setAccessible(true);                                  // обходим модификаторы доступа
            // если это поле аннотировано
            if (a != null) {
                // получаем имя типа по имени типа аннотированного поля
                String typeName = properties.getProperty(f.getType().getName());
                // создаем экземпляр нужного класса
                Object classObject = Class.forName(typeName).newInstance();
                // устанавливаем его в поле
                f.set(obj, classObject);
            }
        }
        // возвращаем измененный объект
        return obj;
    }

    /**
     * Устанавливает {@link #dataFile}.
     * @param filePath путь к файлу Propetry.
     */
    public void setFileByPath(String filePath) {
        dataFile = new File(filePath);
    }
}

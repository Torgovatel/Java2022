package ru.kazanin.lab3;

import java.util.List;

/**
 * Класс обработки вызова методов стандартных контейнеров.<br>
 * @author Andrey Kazanin
 * @version 1.0
 */
public class Caller {
    /**
     * Количество внутренних вызовов.
     */
    public int callsCount;

    /**
     * Конструктор по умолчанию.<br>
     * Устанавливает {@link #callsCount} = 1000.
     */
    public Caller() {
        callsCount = 1000;
    }

    public enum MethodType {
        Add,
        Delete,
        Get
    }
    /**
     * Конструктор по вызовам.
     * @param cntCalls данные для записи в {@link #callsCount}
     */
    public Caller(int cntCalls){
        callsCount = cntCalls;
    }

    /**
     * Метод измерения времени по заданным настройкам.<br>
     * Warning:
     * <ol>
     *      <li>Переданный контейнер обязательно должен быть наследником {@link List<Integer>}</li>
     *      <li>Метод может изменять содержимое переданного контейнера!</li>
     * </ol>
     * @param method тип метода, который будет тестироваться.
     * @param basicContainer контейнер, у которого будет вызываться заданный метод.<br>
     * @return Возвращает время выполнения теста в мс.
     * @throws IllegalArgumentException в случае если переданный контейнер не наследник {@link List<Integer>}
     */
    public Long timeForMethod(Object basicContainer, MethodType method) throws IllegalArgumentException {
        List<Integer> cont = null;
        try {
            cont = (List<Integer>) basicContainer;
        }
        catch(ClassCastException except){
            throw new IllegalArgumentException("obj was bad type");
        }
        int trash;
        switch (method) {
            case Delete:
            case Get:
                while (cont.size() < callsCount)
                    cont.add(7);
                break;
        }
        long startTime = System.currentTimeMillis();
        switch (method) {
            case Add :
                for (int i = 0; i < callsCount; i++)
                    cont.add(i);
                break;

            case Delete:
                for(int i = callsCount - 1; i >= 0; i--)
                    trash = cont.remove(i);
                break;

            case Get:
                for (int i = 0; i < callsCount; i++)
                    trash = cont.get(i);
                break;
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}

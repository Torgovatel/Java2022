package ru.kazanin.lab4.entities;

/**
 * Класс подразделения.
 * @author Andrey Kazanin
 * @version 1.0
 */
public class Department {
    /**
     * Внутренний счетчик для генерации id.
     */
    private static int idCounter = 0;
    /**
     * ID текущего подразделения.
     */
    private int ID;
    /**
     * Наименование подразделения.
     */
    private String title;

    /**
     * Конструктор по умолчанию.
     */
    public Department(){
        this.ID = ++idCounter;
        this.title = "noTitle";
    }

    /**
     * Конструктор по подразделению.
     * @param departmentName наименование подразделения.
     */
    public Department(String departmentName){
        this.ID = ++idCounter;
        this.title = departmentName;
    }

    @Override
    public String toString(){
        return "" + title + " (ID: " + ID + ")";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
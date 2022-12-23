package ru.kazanin.lab4;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import ru.kazanin.lab4.entities.Department;
import ru.kazanin.lab4.entities.Person;
import java.io.*;
import java.util.List;


/**
 * Класс для обработки CSV файлов.
 * @author Andrey Kazanin
 * @version 1.0
 */
public class CSV_Parser{
    /**
     * Путь к CSV файлу в системе.
     */
    private String path;
    /**
     * Символ-разделитель, используемый в CSV файле.
     */
    private char separator;
    /**
     * Список считанных людей.
     */
    private List<Person> container;

    /**
     * Конструктор класса.
     * @param path полный путь к CSV файлу в системе.
     * @param separator символ-разделитель, используемый в этом файле.
     * @param persons ссылка на контейнер людей, подлежащий заполнению.
     * @throws FileNotFoundException если файл не найден.
     */
    public CSV_Parser(String path, char separator, List<Person> persons) throws FileNotFoundException{
        this.path = path;
        this.separator = separator;
        container = persons;
        read();
    }

    /**
     * Метод считывания данных CSV файла.
     * @throws FileNotFoundException если файл не найден.
     */
    public void read() throws FileNotFoundException {
        try (FileReader in = new FileReader(path)) {
            CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
            CSVReader reader = new CSVReaderBuilder(in).withCSVParser(parser).withSkipLines(1).build();
            if (reader == null) {
                throw new FileNotFoundException(path);
            }
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Person person = new Person(Integer.valueOf(nextLine[0]),    // ID
                        nextLine[1],                                        // Имя
                        nextLine[2],                                        // Пол
                        new Department(nextLine[4]),                        // Подразделение
                            Integer.valueOf(nextLine[5]),                   // Зарплата
                            nextLine[3]);                                   // Дата рождения
                container.add(person);
            }
        } catch (IOException | CsvValidationException except) {
            throw new FileNotFoundException(path);
        }
    }

    /**
     * Метод печати информации о всех записанных объектах.
     */
    public void print() {
        for (int i = 0; i < container.size(); i++) {
            System.out.print("" + (i + 1) + " человек: ");
            System.out.print(container.get(i).getID() + "\t");
            System.out.print(container.get(i).getName() + "\t");
            System.out.print(container.get(i).getGender() + "\t");
            System.out.print(container.get(i).getDate() + "\t");
            System.out.print("Подразделение: ");
            System.out.print(container.get(i).getDepartment().getID() + "\t");
            System.out.print(container.get(i).getDepartment().getTitle() + "\t");
            System.out.println(container.get(i).getSalary() + "\t");
        }
    }

    public String getFilePath() {
        return path;
    }

    public void setFilePath(String filePath) throws FileNotFoundException {
        path = filePath;
        read();
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public List<Person> getPersons() {
        return container.subList(0, container.size());
    }

    public void setPersons(List<Person> persons) {
        this.container = persons;
    }
}

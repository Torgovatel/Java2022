package ru.kazanin.lab4;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import ru.kazanin.lab4.entities.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования CSV_Parser (4 Лаба).
 */
class CSV_ParserTest {

    /**
     * unit-тест метода {@link CSV_Parser#read()}.<br>
     * Проверяет наименование подразделения со считанного файла и факт выбрасывания исключения.
     */
    @Test
    void readFromCSV() {
        ArrayList<Person> arr = new ArrayList<>();
        boolean wasException = false;
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\foreign_names.csv";
        try{
            CSV_Parser helper = new CSV_Parser(path, ';', arr);
            boolean catchExcept = false;
            String d1 = helper.getPersons().get(7).getDepartment().getTitle();
            String d2 = "I";
            assertEquals(d1, d2);
            path = System.getProperty("user.dir");
            helper.setFilePath(path);
            helper.read();
        }
        catch(FileNotFoundException except){
            wasException = true;
        }
        assertTrue(wasException);
    }
}
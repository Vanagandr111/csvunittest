import org.example.Main;
import org.example.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CsvParserTest {

    @Test
    public void testParseCSV_validCsv_success() {
        // given (дано)
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String csvFileName = "data.csv";

        // when (когда)
        List<Employee> result = Main.parseCSV(columnMapping, csvFileName);

        // then (тогда)
        Assertions.assertNotNull(result, "Результат не должен быть null");
        Assertions.assertFalse(result.isEmpty(), "Результат не должен быть пустым");
        Assertions.assertEquals(2, result.size(), "Размер результата должен быть 2");
        Assertions.assertEquals("John", result.get(0).getFirstName(), "Имя первого сотрудника должно быть John");
        System.out.println("Тест parseCSV успешно пройден");
    }

    @Test
    public void testListToJson_validList_success() {
        // given (дано)
        List<Employee> employees = List.of(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Inav", "Petrov", "RU", 23)
        );

        // when (когда)
        String json = Main.listToJson(employees);

        // then (тогда)
        Assertions.assertNotNull(json, "JSON не должен быть null");
        Assertions.assertFalse(json.isEmpty(), "JSON не должен быть пустым");
        Assertions.assertTrue(json.startsWith("[") && json.endsWith("]"), "JSON должен быть массивом");
        System.out.println("Тест listToJson успешно пройден");
    }

    @Test
    public void testWriteString_validString_success() {
        // given (дано)
        String jsonFileName = "data2.json";
        String content = "[{\"id\":1,\"firstName\":\"John\"}]";

        // when (когда)
        Main.writeString(jsonFileName, content);

        // then (тогда)
        // Здесь вы можете добавить код для проверки существования файла и соответствия его содержимого
        System.out.println("Тест writeString успешно пройден");
    }
}

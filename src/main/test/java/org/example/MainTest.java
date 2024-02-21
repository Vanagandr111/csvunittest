package org.example;

import org.example.Main;
import org.example.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MainTest {

    @Test
    public void testParseCSV_validCsv_success() {
        // given
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String csvFileName = "data.csv";

        // when
        List<Employee> result = Main.parseCSV(columnMapping, csvFileName);

        // then
        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertFalse(result.isEmpty(), "Result should not be empty");
        Assertions.assertEquals(2, result.size(), "Result size should be 2");
        // Directly comparing expected and actual first employee's firstName
        Assertions.assertEquals("John", result.get(0).getFirstName(), "First employee's name should be John");
    }

    @Test
    public void testListToJson_validList_success() {
        // given
        List<Employee> employees = List.of(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Inav", "Petrov", "RU", 23)
        );
        String expectedJson = "[{\"id\":1,\"firstName\":\"John\"..."; // Simplified for brevity

        // when
        String actualJson = Main.listToJson(employees);

        // then
        Assertions.assertNotNull(actualJson, "JSON should not be null");
        Assertions.assertFalse(actualJson.isEmpty(), "JSON should not be empty");
        // Assume you compare the actual JSON string to a partial expected value for simplicity
        Assertions.assertTrue(actualJson.startsWith("[") && actualJson.endsWith("]"), "JSON should be an array");
    }

    @Test
    public void testWriteString_validString_success() throws Exception {
        // given
        String jsonFileName = "data2.json";
        String content = "[{\"id\":1,\"firstName\":\"John\"}]";

        // when
        Main.writeString(jsonFileName, content);

        // then
        // Check file exists
        Assertions.assertTrue(Files.exists(Paths.get(jsonFileName)), "File should exist");
        // Check file content
        String fileContent = new String(Files.readAllBytes(Paths.get(jsonFileName)));
        Assertions.assertEquals(content, fileContent, "File content should match the expected content");
    }
}

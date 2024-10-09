package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonParser {

    private ObjectMapper objectMapper = new ObjectMapper();  // Создаем ObjectMapper

    public <T> T fromJsonFile(String filePath, Class<T> valueType) throws IOException {
        // Парсинг JSON файла в объект указанного типа
        return objectMapper.readValue(new File(filePath), valueType);
    }

    public <T> void toJsonFile(String filePath, T object) throws IOException {
        // Сериализация объекта в JSON и запись в файл
        objectMapper.writeValue(new File(filePath), object);
    }
}
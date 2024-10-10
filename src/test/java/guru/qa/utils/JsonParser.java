package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonParser {

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> void toJsonFile(String filePath, T object) throws IOException {
        objectMapper.writeValue(new File(filePath), object);
    }

    public <T> T fromJson(InputStream inputStream, Class<T> valueType) throws IOException {
        return objectMapper.readValue(inputStream, valueType);
    }
}
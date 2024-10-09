package guru.qa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

public class FilesParsingTest {
    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void xlsParsingTests() throws Exception{

    }
    @Test
    void csvParsingTests() throws Exception{

    }
    @Test
    void jsonParsingTests() throws Exception {
        utils.JsonParser jsonParser = new utils.JsonParser();

        try {
            // Чтение и парсинг JSON-файла
            Fruit fruit = jsonParser.fromJsonFile("fruit.json", Fruit.class);

            // Вывод данных на экран
            System.out.println(fruit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void pdfParsingTests() throws Exception{

    }
    @Test
    void zipParsingTests() throws Exception{

    }
}

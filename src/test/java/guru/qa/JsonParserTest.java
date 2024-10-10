package guru.qa;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.InputStream;
import model.Fruit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonParserTest {
    utils.JsonParser jsonParser = new utils.JsonParser();

    @Test
    void jsonParsingTests() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample1.json")) {
            if (inputStream == null) {
                throw new FileNotFoundException("Не удается найти указанный файл: sample1.json");
            }

            Fruit fruit = jsonParser.fromJson(inputStream, Fruit.class);

            assertNotNull(fruit);


            assertEquals("Apple", fruit.getFruit());
            assertEquals("Large", fruit.getSize());
            assertEquals("Red", fruit.getColor());


            System.out.println(fruit);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

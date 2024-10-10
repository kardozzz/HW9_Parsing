package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipParserTest {

    private static final String ZIP_FILE_NAME = "sampleFilesForHW.zip";

    @Test
    void testCsvFile() throws Exception {
        InputStream inputStream = getFileFromZip("sample4.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] header = reader.readNext();
            assertArrayEquals(new String[]{"Game Number", "Game Length"}, header);

            String[] firstLine = reader.readNext();
            assertArrayEquals(new String[]{"1", " 30"}, firstLine);
        }
    }

    @Test
    void testXlsxFile() throws Exception {
        InputStream inputStream = getFileFromZip("sample3.xlsx");
        XLS xls = new XLS(inputStream);
        assertEquals("ID", xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
        assertEquals("January", xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue());
    }

    @Test
    void testPdfFile() throws Exception {
        InputStream inputStream = getFileFromZip("sample3.pdf");
        PDF pdf = new PDF(inputStream);
        String expectedContent = "Get Lost!";
        assertTrue(pdf.text.contains(expectedContent), "PDF должен содержать'" + expectedContent + "'");
        assertTrue(pdf.text.trim().contains("Document: Sample #1"), "PDF должен содержать'" + "Document: Sample #1" + "'");
        assertTrue(pdf.text.trim().contains("Document file name"), "PDF должен содержать'" + "Document file name" + "'");
    }

    private InputStream getFileFromZip(String fileName) throws IOException {
        URL resource = getClass().getClassLoader().getResource(ZIP_FILE_NAME);
        assertNotNull(resource, "Файл архива не найден в ресурсах: " + ZIP_FILE_NAME);

        try (ZipInputStream zipIn = new ZipInputStream(resource.openStream())) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    return new ByteArrayInputStream(zipIn.readAllBytes());
                }
                zipIn.closeEntry();
            }
        }
        fail("Файл " + fileName + " не найден в ZIP-архиве.");
        return null; // unreachable, but needed for compilation
    }
}



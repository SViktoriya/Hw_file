package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipTest {

    ClassLoader zp = ZipTest.class.getClassLoader();

    @DisplayName("Тест для csv")
    @Test
    void zipCsvTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/Архив.zip"))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(zp.getResourceAsStream("Архив.zip")));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(0);
                        assertThat(row[0]).isEqualTo("Помидор");
                    }
                }
            }
        }
    }

    @DisplayName("Тест для pdf")
    @Test
    void zipPdfTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/Архив.zip"))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(zp.getResourceAsStream("Архив.zip")));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.text).contains("Список покупок!");
                    }
                }
            }
        }
    }

    @DisplayName("Тест для xlsx")
    @Test
    void zipXlsxTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/Архив.zip"))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(zp.getResourceAsStream("Архив.zip")));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(1)
                                        .getCell(1)
                                        .getStringCellValue()
                        ).isEqualTo("Тестов");
                    }
                }
            }
        }
    }
}
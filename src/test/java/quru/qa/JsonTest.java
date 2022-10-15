package quru.qa;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import quru.qa.model.PersonalInfo;

import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class JsonTest {

    ClassLoader js = JsonTest.class.getClassLoader();

    @DisplayName("Тест по парсингу Json")
    @Test
    void jsonTestWithModel() throws Exception {
        InputStream is = js.getResourceAsStream("test.json");
        ObjectMapper objectMapper = new ObjectMapper();

        PersonalInfo personalInfo = objectMapper.readValue(is, PersonalInfo.class);

        assertThat(personalInfo.name).isEqualTo("John");
        assertThat(personalInfo.fullName).isEqualTo("John Smith");
        assertThat(personalInfo.age).isEqualTo(30);
        assertThat(personalInfo.address.city).isEqualTo("Moscow");
        assertThat(personalInfo.address.street).isEqualTo("Arbat");
        assertThat(personalInfo.cars.get(0)).isEqualTo("Ford");
        assertThat(personalInfo.cars.get(1)).isEqualTo("BMW");
        assertThat(personalInfo.cars.get(2)).isEqualTo("Fiat");


    }
}

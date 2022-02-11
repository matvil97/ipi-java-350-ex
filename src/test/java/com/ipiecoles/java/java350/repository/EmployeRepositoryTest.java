package com.ipiecoles.java.java350.repository;
import com.ipiecoles.java.java350.model.Employe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;


@DataJpaTest
public class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    @Test
    public void findLastMatriculeTestEmptyList() {

        employeRepository.deleteAll();

        String result = employeRepository.findLastMatricule();

        Assertions.assertThat(result).isEqualTo(null);

    }

    @Test
    public void findLastMatriculeFullWithLetter() {

        employeRepository.deleteAll();

        Employe employeMessi = employeRepository.save(new Employe("Messi", "Lionel", "T12345", LocalDate.now(), 1000d, 1, 1.0));
        Employe employeRonaldo = employeRepository.save(new Employe("Ronaldo", "Cristiano", "M15697", LocalDate.now(), 1000d, 1, 1.0));
        Employe employeMbappe = employeRepository.save(new Employe("Mbappe", "Kylian", "T64978", LocalDate.now(), 1000d, 1, 1.0));
        Employe employeVeratti = employeRepository.save(new Employe("Veratti", "Marco", "C49785", LocalDate.now(), 1000d, 1, 1.0));

        String result = employeRepository.findLastMatricule();

        Assertions.assertThat(result).isEqualTo("64978");

    }

    @Test()
    public void avgPerformanceWhereMatriculeStartsWithEmptyList() {

        employeRepository.deleteAll();

        Double result = employeRepository.avgPerformanceWhereMatriculeStartsWith("T");

        Assertions.assertThat(result).isNull();

    }

    @ParameterizedTest(name = "La moyenne de perf des matricule {0} est de {1}")
    @CsvSource({
            "T, 10.5",
            "M, 15",
            "C, 25.5",
            ","
    })
    public void avgPerformanceWhereMatriculeStartsWithTestRegularList(String letter, Double resultat) {

        employeRepository.deleteAll();

        Employe employeRonaldo = employeRepository.save(new Employe("Ronaldo", "Cristiano", "M15697", LocalDate.now(), 1000d, 5, 1.0));
        Employe employeFernandes = employeRepository.save(new Employe("Fernandes", "Bruno", "M54973", LocalDate.now(), 1000d, 25, 1.0));
        Employe employeMessi = employeRepository.save(new Employe("Messi", "Lionel", "T12345", LocalDate.now(), 1000d, 9, 1.0));
        Employe employeMbappe = employeRepository.save(new Employe("Mbappe", "Kylian", "T64978", LocalDate.now(), 1000d, 12, 1.0));
        Employe employeVeratti = employeRepository.save(new Employe("Veratti", "Marco", "C49785", LocalDate.now(), 1000d, 3, 1.0));
        Employe employeHaaland = employeRepository.save(new Employe("Haaland", "Erling", "C94678", LocalDate.now(), 1000d, 48, 1.0));

        Double functionResult = employeRepository.avgPerformanceWhereMatriculeStartsWith(letter);

        Assertions.assertThat(functionResult).isEqualTo(resultat);

    }

    @ParameterizedTest(name = "La moyenne de perf des matricule {0} est de {1}")
    @CsvSource({
            "T, 9",
            "M, 5",
            "C, 3",
            ","
    })
    public void avgPerformanceWhereMatriculeStartsWithSoloList(String letter, Double resultat) {

            employeRepository.deleteAll();

            Employe employeRonaldo = employeRepository.save(new Employe("Ronaldo", "Cristiano", "M15697", LocalDate.now(), 1036d, 5, 1.0));
            Employe employeMessi = employeRepository.save(new Employe("Messi", "Lionel", "T12345", LocalDate.now(), 1036d, 9, 1.0));
            Employe employeVeratti = employeRepository.save(new Employe("Veratti", "Marco", "C49785", LocalDate.now(), 1036d, 3, 1.0));

            Double functionResult = employeRepository.avgPerformanceWhereMatriculeStartsWith(letter);

            Assertions.assertThat(functionResult).isEqualTo(resultat);

    }

    @Test
    public void avgPerformanceWhereMatriculeStartsWithTestWithoutLetterList() {

        employeRepository.deleteAll();

        Employe employeRonaldo = employeRepository.save(new Employe("Ronaldo", "Cristiano", "M15697", LocalDate.now(), 1000d, 5, 1.0));
        Employe employeMessi = employeRepository.save(new Employe("Messi", "Lionel", "M12345", LocalDate.now(), 1000d, 9, 1.0));
        Employe employeVeratti = employeRepository.save(new Employe("Veratti", "Marco", "M49785", LocalDate.now(), 1000d, 3, 1.0));

        Double result = employeRepository.avgPerformanceWhereMatriculeStartsWith("T");

        Assertions.assertThat(result).isNull();

    }
}
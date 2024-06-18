package pro.sky.List.Homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;
import pro.sky.List.Homework.exceptions.EmployeeStorageIsFullException;
import pro.sky.List.Homework.services.EmployeeService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    List<Employee> employees = List.of(
            new Employee("Милана", "Малышева", 1, 34281),
            new Employee("Михаил", "Агеев", 2, 49693),
            new Employee("Елизавета", "Ларионова", 4, 38840),
            new Employee("Максим", "Андреев", 5, 36242),
            new Employee("Каролина", "Пахомова", 3, 41043),
            new Employee("Александр", "Смирнов", 2, 37320),
            new Employee("Максим", "Крючков", 5, 48716)
    );
    @Test
    void addTest() {
        Employee expected = new Employee("Иван","Иванов", 2, 50000);
        Employee actual = employeeService.add("Иван","Иванов", 2, 50000);
//        Assertions.assertEquals(expected, actual);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Иван","Иванов"));
        //assertThat(actual).isEqualTo(employeeService.print());

    }
    @Test
    void negativeTest() {
        employeeService.add("Иван","Иванов", 1, 50000);
        employeeService.add("Сергей","Иванов", 2, 50000);
        employeeService.add("Дмитрий","Иванов", 3, 50000);

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Александр","Иванов", 4, 50000));
    }

    @Test
    void negativeTestFullStorage() {
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Иван","Иванов", 1, 50000));
    }

    @Test
    void findTest() {
        Employee expected = new Employee("Милана", "Малышева", 2, 50000);
        assertThat(employeeService.print().contains(expected));
        Employee actual = employeeService.find("Милана", "Малышева");
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    void findNegativeTest() {
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.find("Милана", "Малышева"));
    }

    @Test
    void removeTest() {
        Employee expected = new Employee("Милана", "Малышева", 2, 50000);
        assertThat(employeeService.print().contains(expected));
        Employee actual = employeeService.remove("Милана", "Малышева");
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotIn(employeeService.print());

    }
    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Милана", "Малышева"));
    }
    @Test
    void printTest() {
        assertThat(employeeService.print())
                .containsExactlyInAnyOrderElementsOf(employees);
    }
}

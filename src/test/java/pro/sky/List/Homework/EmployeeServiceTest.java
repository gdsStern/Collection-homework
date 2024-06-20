package pro.sky.List.Homework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.List.Homework.exceptions.EmployeeAlreadyAddedException;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;
import pro.sky.List.Homework.exceptions.EmployeeStorageIsFullException;
import pro.sky.List.Homework.services.EmployeeService;
import pro.sky.List.Homework.services.ValidationService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidationService());

    List<Employee> employees = List.of(
            new Employee("Милана", "Малышева", 1, 34281),
            new Employee("Михаил", "Агеев", 2, 49693),
            new Employee("Елизавета", "Ларионова", 4, 38840),
            new Employee("Максим", "Андреев", 5, 36242),
            new Employee("Каролина", "Пахомова", 3, 41043),
            new Employee("Александр", "Смирнов", 2, 37320),
            new Employee("Максим", "Крючков", 5, 48716)
    );
    @BeforeEach
    void beforeEach() {
        employees.forEach(employee -> employeeService.add(employee.getFirstName(),employee.getLastName(),employee.getDepartmentID(),employee.getSalary()));
    }

    @AfterEach
    void afterEach() {
        employeeService.print().forEach(employee -> employeeService.remove(employee.getFirstName(),employee.getLastName()));
    }


    @Test
    void addTest() {
        Employee expected = new Employee("Илья","Иванов", 2, 50000);
        Employee actual = employeeService.add("Илья","Иванов", 2, 50000);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Илья","Иванов"));
        assertThat(actual).isIn(employeeService.print());

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
    void negativeTestAlready() {
        employeeService.add("Иван","Иванов", 1, 50000);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Иван","Иванов", 1, 50000));
    }

    @Test
    void findTest() {
        Employee expected = new Employee("Милана", "Малышева", 1, 34281);
        assertThat(employeeService.print().contains(expected));
        Employee actual = employeeService.find("Милана", "Малышева");
        assertThat(actual).isEqualTo(expected);

    }
    @Test
    void findNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Милана", "Иванова"));
    }

    @Test
    void removeTest() {
        Employee expected = new Employee("Милана", "Малышева", 1, 34281);
        assertThat(employeeService.print().contains(expected));
        Employee actual = employeeService.remove("Милана", "Малышева");
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotIn(employeeService.print());

    }
    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Милана", "Иванова"));
    }
    @Test
    void printTest() {
        assertThat(employeeService.print())
                .containsExactlyInAnyOrderElementsOf(employees);
    }
}

package pro.sky.List.Homework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;
import pro.sky.List.Homework.services.DepartmentsService;
import pro.sky.List.Homework.services.EmployeeService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentsServiceTest {
    @Mock
    private EmployeeService employeeService = new EmployeeService();

    @InjectMocks
    private DepartmentsService departmentsService = new DepartmentsService();

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
    public void beforeEach() {
        when(employeeService.print()).thenReturn(employees);
    }
    @Test
    void findTest() {
        assertThat(departmentsService.employeesByDepartments(2))
                .containsExactlyInAnyOrder(
                        new Employee("Михаил", "Агеев", 2, 49693),
                        new Employee("Александр", "Смирнов", 2, 37320)
                );
    }
    @Test
    void sumTest() {
        assertThat(departmentsService.sumSalary(1))
                .isEqualTo(34281);
    }
    @Test
    void sumNegativeTest() {
        assertThat(departmentsService.sumSalary(6))
                .isEqualTo(0);
    }
//
    @Test
    void findMaxSalaryTest() {
        assertThat(departmentsService.maxSalary(1))
                .isEqualTo(34281);
    }

    @Test
    void findMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentsService.maxSalary(6));
    }
    @Test
    void findMinSalaryTest() {
        assertThat(departmentsService.minSalary(1))
                .isEqualTo(34281);
    }

    @Test
    void findMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentsService.minSalary(6));
    }

    @Test
    void groupEmployeesTest() {
        assertThat(departmentsService.groupEmployees())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Милана", "Малышева", 1, 34281)),
                                2, List.of(
                                        new Employee("Михаил", "Агеев", 2, 49693),
                                        new Employee("Александр", "Смирнов", 2, 37320) ),
                                3, List.of(new Employee("Каролина", "Пахомова", 3, 41043)),
                                4, List.of(new Employee("Елизавета", "Ларионова", 4, 38840)),
                                5, List.of(
                                        new Employee("Максим", "Андреев", 5, 36242),
                                        new Employee("Максим", "Крючков", 5, 48716))
                        )
                );
    }

}

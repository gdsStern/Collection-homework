package pro.sky.List.Homework.services;

import org.springframework.stereotype.Service;
import pro.sky.List.Homework.Employee;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentsService {
    private final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee maxSalary(int departmentsID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentsID)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee minSalary(int departmentsID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentsID)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Collection<Employee> employeesByDepartments(int departmentsID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentsID)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupEmployees() {
        return employeeService.print().stream()
                .collect(groupingBy(Employee::getDepartmentID));
    }
}

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
    private final EmployeeService employeeService = new EmployeeService();

//    public DepartmentsService(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    public Integer maxSalary(int departmentID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentID)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Integer minSalary(int departmentsID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentsID)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Collection<Employee> employeesByDepartments(int departmentID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentID)
                .collect(Collectors.toList());
    }

    public int sumSalary(int departmentID) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartmentID() == departmentID)
                .map(Employee::getSalary)
                .reduce(0,Integer::sum);
    }

    public Map<Integer, List<Employee>> groupEmployees() {
        return employeeService.print().stream()
                .collect(groupingBy(Employee::getDepartmentID));
    }
}

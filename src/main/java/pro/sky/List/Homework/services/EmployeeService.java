package pro.sky.List.Homework.services;

import org.springframework.stereotype.Service;
import pro.sky.List.Homework.Employee;
import pro.sky.List.Homework.exceptions.EmployeeAlreadyAddedException;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;
import pro.sky.List.Homework.exceptions.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    private Map<String, Employee> employeesMap = new HashMap<>(Map.of(
            "Милана Малышева", new Employee("Милана", "Малышева", 1, 34281),
            "Михаил Агеев", new Employee("Михаил", "Агеев", 2, 49693),
            "Елизавета Ларионова", new Employee("Елизавета", "Ларионова", 4, 38840),
            "Максим Андреев", new Employee("Максим", "Андреев", 5, 36242),
            "Каролина Пахомова", new Employee("Каролина", "Пахомова", 3, 41043),
            "Александр Смирнов", new Employee("Александр", "Смирнов", 2, 37320),
            "Максим Крючков", new Employee("Максим", "Крючков", 5, 48716)

    ));

    private final int maxEmployees = 10;
    private final ValidationService validationService;

    public EmployeeService(ValidationService validationService) {
        this.validationService = validationService;
    }


//    public EmployeeService(Map<String, Employee> employeesMap) {
//        this.employeesMap = employeesMap;
//    }

    public Employee add(String firstName, String lastName, int departmentID, int salary)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        firstName = validationService.validation(firstName);
        lastName = validationService.validation(lastName);
        Employee employee = new Employee(firstName, lastName, departmentID, salary);
        if (employeesMap.size() == maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        if (employeesMap.containsKey(Employee.getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesMap.put(Employee.getKey(firstName, lastName), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) throws EmployeeNotFoundException {
        //Employee employee = new Employee(firstName, lastName);
        if (!employeesMap.containsKey(Employee.getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }

        return employeesMap.remove(Employee.getKey(firstName, lastName));
    }

    public Employee find(String firstName, String lastName) throws EmployeeNotFoundException {
        //Employee employee = new Employee(firstName, lastName);
        if (!employeesMap.containsKey(Employee.getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException();
        }

        return employeesMap.get(Employee.getKey(firstName, lastName));
    }

    public Collection<Employee> print() {
        return employeesMap.values();
    }
}

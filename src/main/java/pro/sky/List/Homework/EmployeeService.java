package pro.sky.List.Homework;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private Map<String, Employee> employeesMap = new HashMap<>(Map.of(
            "Милана Малышева", new Employee("Милана", "Малышева"),
            "Михаил Агеев", new Employee("Михаил", "Агеев"),
            "Елизавета Ларионова", new Employee("Елизавета", "Ларионова"),
            "Максим Андреев", new Employee("Максим", "Андреев"),
            "Каролина Пахомова", new Employee("Каролина", "Пахомова"),
            "Александр Смирнов", new Employee("Александр", "Смирнов"),
            "Максим Крючков", new Employee("Максим", "Крючков")

    ));

    private final int MAX_EMPLOYEES = 10;

//    public EmployeeService(Map<String, Employee> employeesMap) {
//        this.employeesMap = employeesMap;
//    }

    public Employee add(String firstName, String lastName)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employeesMap.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        if (employeesMap.containsKey(employee.getKey())) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesMap.put(employee.getKey(), employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employeesMap.containsKey(employee.getKey())) {
            throw new EmployeeNotFoundException();
        }

        return employeesMap.remove(employee.getKey());
    }

    public Employee find(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employeesMap.containsKey(employee.getKey())) {
            throw new EmployeeNotFoundException();
        }

        return employeesMap.get(employee.getKey());
    }

    public Collection<Employee> print() {
        return employeesMap.values();
    }
}

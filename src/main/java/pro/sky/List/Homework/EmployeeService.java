package pro.sky.List.Homework;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class EmployeeService {
    private Map<String, Employee> employeesMap;
    private final int MAX_EMPLOYEES = 5;

    public EmployeeService(Map<String, Employee> employeesMap) {
        this.employeesMap = employeesMap;
    }

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

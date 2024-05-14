package pro.sky.List.Homework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.List.Homework.Employee;
import pro.sky.List.Homework.exceptions.EmployeeAlreadyAddedException;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;
import pro.sky.List.Homework.services.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam int departmentID,
                        @RequestParam int salary) {
        return employeeService.add(firstName, lastName, departmentID, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam(required = false) String firstName,
                         @RequestParam(required = false) String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping()
    public Collection<Employee> print() {
        return employeeService.print();
    }
}

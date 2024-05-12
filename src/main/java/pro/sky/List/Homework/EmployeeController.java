package pro.sky.List.Homework;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam(required = false) String firstName,
                        @RequestParam(required = false) String lastName) {
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
            throw new EmployeeAlreadyAddedException();
        }
        return employeeService.add(firstName, lastName);
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

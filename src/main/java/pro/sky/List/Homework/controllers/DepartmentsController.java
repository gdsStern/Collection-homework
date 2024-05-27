package pro.sky.List.Homework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.List.Homework.exceptions.EmployeeNotFoundException;
import pro.sky.List.Homework.services.DepartmentsService;
import pro.sky.List.Homework.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping("/max-salary")
    public Employee max(@RequestParam int departmentID) {
        return departmentsService.maxSalary(departmentID);
    }

    @GetMapping("/min-salary")
    public Employee min(@RequestParam int departmentID) {
        return departmentsService.minSalary(departmentID);
    }

    @GetMapping(value = "/all", params = {"departmentID"})
    public Collection<Employee> all(@RequestParam int departmentID) {
        return departmentsService.employeesByDepartments(departmentID);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> group() {
        return departmentsService.groupEmployees();
    }


}

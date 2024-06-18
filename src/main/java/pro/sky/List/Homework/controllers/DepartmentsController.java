package pro.sky.List.Homework.controllers;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}/salary/max")
    public Integer max(@RequestParam int departmentID) {
        return departmentsService.maxSalary(departmentID);
    }

    @GetMapping("/{id}/salary/min")
    public Integer min(@PathVariable("id") int departmentID) {
        return departmentsService.sumSalary(departmentID);
    }

    @GetMapping("/{id}/salary/sum")
    public Integer sum(@PathVariable("id") int departmentID) {
        return departmentsService.minSalary(departmentID);
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> all(@PathVariable("id") int departmentID) {
        return departmentsService.employeesByDepartments(departmentID);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> group() {
        return departmentsService.groupEmployees();
    }


}

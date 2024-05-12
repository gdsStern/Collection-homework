package pro.sky.List.Homework;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Employee max(@RequestParam(required = false) int departmentID) {
        if (departmentID  == 0) {
            throw new EmployeeAlreadyAddedException();
        }
        return departmentsService.maxSalary(departmentID);
    }

    @GetMapping("/min-salary")
    public Employee min(@RequestParam(required = false) int departmentID) {
        if (departmentID  == 0) {
            throw new EmployeeAlreadyAddedException();
        }
        return departmentsService.minSalary(departmentID);
    }

    @GetMapping(value = "/all", params = {"departmentID"})
    public Collection<Employee> all(@RequestParam(required = false) int departmentID) {
        if (departmentID  == 0) {
            throw new EmployeeAlreadyAddedException();
        }
        return departmentsService.employeesByDepartments(departmentID);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> group() {
        return departmentsService.groupEmployees();
    }


}

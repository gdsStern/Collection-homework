package pro.sky.List.Homework;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EmployeeStorageIsFullException extends  RuntimeException {
}

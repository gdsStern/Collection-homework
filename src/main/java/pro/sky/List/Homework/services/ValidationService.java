package pro.sky.List.Homework.services;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.List.Homework.exceptions.ValidationFailedException;

@Service
public class ValidationService {
    public String validation(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new ValidationFailedException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }
}

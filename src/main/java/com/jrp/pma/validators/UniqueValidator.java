package com.jrp.pma.validators;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository empRepo;

    public void initialize(UniqueValue constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        System.out.println("Entered validation method...");
        Employee emp = empRepo.findByEmail(value);

        return emp == null;
    }
}

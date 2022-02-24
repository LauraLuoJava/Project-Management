package com.jrp.pma.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    ProjectRepository proRepo;

    @GetMapping
    public String displayEmployees(Model model){
        Iterable<Employee> employees = empRepo.findAll();
        model.addAttribute("employees",employees);
        return "employees/list-employees";
    }

    @RequestMapping("/new")
    public String displayEmployeeForm(Model model){
        model.addAttribute("employee",new Employee());
        List<Project> projects = proRepo.findAll();
        model.addAttribute("allProjects",projects);
        return "employees/new-employee";
    }
    @PostMapping("/save")
    public String createEmployee(@Valid Employee employee, Errors errors){
        if(errors.hasErrors())
            return "employees/new-employee";

        empRepo.save(employee);

        return "redirect:/employees/new";
    }
    @GetMapping("/update")
    public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model){
        Employee theEmp = empRepo.findById(theId).get();
        model.addAttribute("employee",theEmp);
        List<Project> projects = proRepo.findAll();
        model.addAttribute("allProjects",projects);
        return "employees/new-employee";
    }
    @GetMapping("/delete")
    public String deleteEmployeeForm(@RequestParam("id") long id){
        empRepo.deleteById(id);
        return "redirect:/employees";
    }
}

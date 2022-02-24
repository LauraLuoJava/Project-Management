package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public String displayProjects(Model model){
        List<Project> projects = proRepo.findAll();
        model.addAttribute("projects",projects);
        return "projects/list-projects";
    }

    @RequestMapping("/new")
    public String displayProjectForm(Model model){
        model.addAttribute("project",new Project());
        Iterable<Employee> employees = empRepo.findAll();
        model.addAttribute("allEmployees",employees);
        return "projects/new-project";
    }
    @PostMapping("/save")
    public String createProject(Project project, Model model){
        proRepo.save(project);

        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    public String displayProjectTimelines(Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimelineString = objectMapper.writeValueAsString(proRepo.getTimeData());
        model.addAttribute("projectTimeList",jsonTimelineString);

        return "projects/project-timelines";
    }
}

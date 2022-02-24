package com.jrp.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Value("${version}")
    private String ver;

    @Autowired
    ProjectRepository proRepo;

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {
        model.addAttribute("versionNumber",ver);

        List<Project> projects = proRepo.findAll();
        model.addAttribute("projects",projects);

        List<ChartData> projectData = proRepo.getProjectStatus();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatusCnt",jsonString);

        List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
        model.addAttribute("employeeListProjectCnt",employeesProjectCnt);
        return "main/home";
    }
}

package com.jrp.pma.api.controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository proRepo;

    @GetMapping
    public Iterable<Project> getProjects(){
        return proRepo.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id){
        return proRepo.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody @Valid Project project){
        return proRepo.save(project);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project){
        return proRepo.save(project);
    }

    @PatchMapping("/{id}")
    public Project partialUpdate(@PathVariable("id") long id,@RequestBody Project patchProject){
        Project pro = proRepo.findById(id).get();

        if(patchProject.getName() !=null){
            pro.setName(patchProject.getName());
        }
        if(patchProject.getStage() !=null){
            pro.setStage(patchProject.getStage());
        }
        if(patchProject.getDescription() !=null){
            pro.setDescription(patchProject.getDescription());
        }

        return proRepo.save(pro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        try {
            proRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            System.out.println("there is no project " + id + " exist");
        }
    }
}

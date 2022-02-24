package com.jrp.pma.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "project_seq")
    @SequenceGenerator(name = "project_seq", allocationSize = 1)
    private long projectId;
    private String name;
    private String Stage; //NOTSTARTED, COMPLETED; INPROGRESS
    private String description;

    @NotBlank
    private Date startDate;

    @NotBlank
    private Date endDate;

    public Project(String name, String stage, String description) {
        this.name = name;
        Stage = stage;
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_Id"),
            inverseJoinColumns = @JoinColumn(name = "employee_Id"))

    @JsonIgnore
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Project() {

    }

    public Project(String name, String stage, String description,Date startDate,Date endDate) {
        this.name = name;
        Stage = stage;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addEmployee(Employee emp) {
        if(employees== null){
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }
}

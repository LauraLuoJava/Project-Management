package com.jrp.pma.dao;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apiemployees",path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Query(nativeQuery = true,value = "SELECT e.first_name as firstName, e.last_name as lastName, " +
            "COUNT(pe.employee_id) as projectCount FROM EMPLOYEE  e left join project_employee pe " +
            "on pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

    public Employee findByEmail(String value);
}

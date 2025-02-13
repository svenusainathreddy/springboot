package com.vcube.sbjpaapp01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.sbjpaapp01.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

	Employee findByFname(String fname);

}

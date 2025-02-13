package com.vcube.sbjpaapp01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.sbjpaapp01.model.Employee;
import com.vcube.sbjpaapp01.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController123 {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/get/employees")
	List<Employee> getAllEmpInfo() {
		return employeeRepository.findAll();

	}
	@GetMapping("/getempByName/{fname}")
	  public Employee getEmployeeByFname(@PathVariable String fname){
		 Employee employee = employeeRepository.findByFname(fname);
				 return employee;
	 }

//	@GetMapping("/getemp/{eid}")
//	Optional<Employee> getEmpId(@PathVariable long eid){
//		 
//		return employeeRepository.findById(eid);
//	}
	@GetMapping("/getemp/{eid}")
	public ResponseEntity<Employee> getEmpId(@PathVariable long eid) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found for thid eid:" + eid));
		return ResponseEntity.ok().body(employee);

	}
	@PutMapping("/update/{eid}")
	 
	public ResponseEntity<Employee> updateEmployee(@PathVariable long eid, @RequestBody Employee empReq) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("employee not found for thid eid:" + eid));
//		 employee.setEid(empReq.getEid());
		employee.setFname(empReq.getFname());
		employee.setLname(empReq.getLname());
		employee.setPhone(empReq.getPhone());
		employee.setAge(empReq.getAge());
		employee.setSalary(empReq.getSalary());
		
		
		Employee updatedEmp=employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmp);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/employees/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
}

package com.revature.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.api.daos.IEmployeeDao;
import com.revature.api.models.Employee;
import com.revature.api.models.EmployeeNotFoundException;

@RestController
public class EmployeeController {
	
	private final IEmployeeDao eDao;
	
	EmployeeController(IEmployeeDao eDao){
		this.eDao = eDao;
	}
	
	//Create
	@PostMapping("/employees")
	public Employee newEmployee(@RequestBody Employee newEmployee) {
		return eDao.save(newEmployee);
	}
	
	//Read
	@GetMapping("/employees")
	public List<Employee> getAll(){
		return eDao.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public Employee findById(@PathVariable Integer id) {
		return eDao.findById(id)
				.orElseThrow(()->new EmployeeNotFoundException(id));
	}
	
	//Update
	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee updatedEmployee, @PathVariable Integer id){
		return eDao.findById(id)
				.map(employee -> { //update if exists
					employee.setName(updatedEmployee.getName());
					employee.setRole(updatedEmployee.getRole());
					return eDao.save(employee);
				})
				.orElseGet(() -> { //create if it doesn't
					updatedEmployee.setId(id);
					return eDao.save(updatedEmployee);
				});
	}
	
	//Delete
	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Integer id) {
		eDao.deleteById(id);
	}
}


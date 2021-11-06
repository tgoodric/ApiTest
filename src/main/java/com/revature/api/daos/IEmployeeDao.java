package com.revature.api.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.api.models.Employee;

public interface IEmployeeDao extends JpaRepository<Employee, Integer> {

}

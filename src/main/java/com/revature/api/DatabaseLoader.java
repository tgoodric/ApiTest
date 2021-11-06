package com.revature.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.api.daos.IEmployeeDao;
import com.revature.api.models.Employee;

@Configuration
public class DatabaseLoader {
	private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);
	
	@Bean 
	CommandLineRunner initDatabase(IEmployeeDao eDao) {
		return args -> {
			log.info("Preloading " + eDao.save(new Employee("Tristan Goodrich", "Associate")));
			log.info("Preloading " + eDao.save(new Employee("Ben Petruziello", "Supreme Leader")));
		};
	}
}

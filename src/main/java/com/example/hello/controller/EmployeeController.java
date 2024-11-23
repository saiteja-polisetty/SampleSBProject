package com.example.hello.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.dto.EmployeeDTO;
import com.example.hello.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
		List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployees();
		return new ResponseEntity<List<EmployeeDTO>>(employeeDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id){
		EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTOrequest){
		EmployeeDTO employeeDTOresponse = employeeService.saveEmployee(employeeDTOrequest);
		return new ResponseEntity<EmployeeDTO>(employeeDTOresponse, HttpStatus.CREATED);
	}

}

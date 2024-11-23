package com.example.hello.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello.dto.EmployeeDTO;
import com.example.hello.entity.Employee;
import com.example.hello.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeDTO> employeeDTOList = new ArrayList<>();
		List<Employee> employeeList = employeeRepository.findAll();

		employeeList.forEach((employee) -> {
			EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getName(), employee.getAge());

			employeeDTOList.add(employeeDTO);
		});
		return employeeDTOList;

	}

	public EmployeeDTO getEmployeeById(Integer id) {

		EmployeeDTO employeeDTO = new EmployeeDTO();
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent()) {
			Employee employee = employeeOptional.get();
			employeeDTO.setId(employee.getId());
			employeeDTO.setName(employee.getName());
			employeeDTO.setAge(employee.getAge());
		}

		return employeeDTO;
	}
	
	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setName(employeeDTO.getName());
		employee.setAge(employeeDTO.getAge());
		employee = employeeRepository.save(employee);
		
		return new EmployeeDTO(employee.getId(), employee.getName(), employee.getAge());
	}
}

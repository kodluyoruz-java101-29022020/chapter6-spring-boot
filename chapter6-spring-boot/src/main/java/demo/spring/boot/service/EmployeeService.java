package demo.spring.boot.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.dao.jpa.repository.EmployeeRepository;
import demo.spring.boot.dao.mongo.entity.EmployeeDraft;
import demo.spring.boot.dao.mongo.repository.EmployeeDraftRepository;
import demo.spring.boot.service.model.EmployeeContext;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDraftRepository employeeDraftRepository;
	
	
	public Employee findByEmpNo(Long empNo) {
		
		return employeeRepository.findWithEmpNo(empNo);
	}
	
	@Transactional
	public Long save(EmployeeContext employeeContext) {
		
		Long maxId = employeeRepository.findMaxId() + 1;
		
		Employee employee = new Employee();
		employee.setEmpNo(maxId);
		employee.setName(employeeContext.getName());
		employee.setLastName(employeeContext.getLastName());
		employee.setGender(employeeContext.getGender());
		employee.setBirthDate(employeeContext.getBirthDate());
		employee.setHireDate(employeeContext.getHireDate());
		
		employee = employeeRepository.save(employee);
		return employee.getEmpNo();
	}
	
	public Long saveAsDraft(Long empNo) {
		
		Employee employee = employeeRepository.findWithEmpNo(empNo);
		
		if(employee == null) {
			return -1L;
		}
		
		EmployeeDraft draft = new EmployeeDraft();
		draft.setBirthDate(employee.getBirthDate());
		draft.setHireDate(employee.getHireDate());
		draft.setEmpNo(BigInteger.valueOf(employee.getEmpNo()));
		draft.setGender(employee.getGender());
		draft.setLastName(employee.getLastName());
		draft.setName(employee.getName());
		
		employeeDraftRepository.save(draft);
		return employee.getEmpNo();
	}
}

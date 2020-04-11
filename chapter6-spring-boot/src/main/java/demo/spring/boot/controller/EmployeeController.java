package demo.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.dao.entity.Employee;
import demo.spring.boot.service.EmployeeService;
import demo.spring.boot.service.model.EmployeeContext;

@RestController
@RequestMapping("/company")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee findByEmpNo(@PathVariable("id") Long id) {
		
		return employeeService.findByEmpNo(id);
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public Long save(@RequestBody EmployeeContext employeeContext) {
		
		return employeeService.save(employeeContext);
	}
	
	@RequestMapping(value = "/employee/draft", method = RequestMethod.POST)
	public Long saveAsDraft(@RequestParam("id") Long id) {
		
		return employeeService.saveAsDraft(id);
	}
	
}

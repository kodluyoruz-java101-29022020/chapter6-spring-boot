package demo.spring.boot.dao.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import demo.spring.boot.dao.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query(value = "FROM Employee e WHERE e.empNo = :empNo")
	public Employee findWithEmpNo(@Param("empNo") Long empNo);
	
	@Query(value = "SELECT MAX(e.empNo) FROM Employee e")
	public Long findMaxId();
	
}

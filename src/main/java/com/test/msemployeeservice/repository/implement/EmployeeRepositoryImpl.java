package com.test.msemployeeservice.repository.implement;

import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final String CREATE_EMPLOYEE_QUERY = "INSERT INTO employee(id, first_name, last_name, birth_date, gender, hire_date, total_working_days, total_salary, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE_QUERY = "UPDATE employee SET first_name = ?, last_name = ?, birth_date = ?, gender = ?, hire_date = ?, total_working_days = ?, total_salary = ?, created_at = ?, updated_at = ? WHERE id = ?";
    private static final String GET_LIST_EMPLOYEE_QUERY = "SELECT * FROM employee";
    private static final String GET_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM employee WHERE id = ?";
    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employee WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        Employee employee = Employee.builder()
                .id(rs.getString("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .birthDate(rs.getDate("birth_date"))
                .gender(rs.getString("gender"))
                .hireDate(rs.getDate("hire_date"))
                .totalWorkingDays(rs.getLong("total_working_days"))
                .totalSalary(rs.getLong("total_salary"))
                .createdAt(rs.getTimestamp("created_at"))
                .updateAt(rs.getTimestamp("updated_at"))
                .build();
        return employee;
    };

    @Override
    public Employee save(Employee employee) {
        jdbcTemplate.update(CREATE_EMPLOYEE_QUERY, employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getBirthDate(), employee.getGender(), employee.getHireDate(), employee.getTotalWorkingDays(), employee.getTotalSalary(), employee.getCreatedAt(), employee.getUpdateAt());
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        jdbcTemplate.update(UPDATE_EMPLOYEE_QUERY, employee.getFirstName(), employee.getLastName(),  employee.getBirthDate(), employee.getGender(), employee.getHireDate(), employee.getTotalWorkingDays(), employee.getTotalSalary(), employee.getCreatedAt(), employee.getUpdateAt(), employee.getId());
        return employee;
    }

    @Override
    public Optional<Employee> findById(String id) {
        Employee employee = null;
        try {
            employee = jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID_QUERY, new Object[]{id}, rowMapper);
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception);
        }
        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> findAll() {

        return jdbcTemplate.query(GET_LIST_EMPLOYEE_QUERY, rowMapper);
    }

    @Override
    public void delete(String id) {

        jdbcTemplate.update(DELETE_EMPLOYEE_QUERY, id);
    }
}

package com.test.msemployeeservice.repository.implement;


import com.test.msemployeeservice.entity.Salary;
import com.test.msemployeeservice.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class SalaryRepositoryImpl implements SalaryRepository {

    private static final String CREATE_SALARY_QUERY = "INSERT INTO salary(id, salary) VALUES (?, ?)";
    private static final String UPDATE_SALARY_QUERY = "UPDATE salary SET salary = ? WHERE id = ?";
    private static final String GET_SALARIES_QUERY = "SELECT * FROM salary";
    private static final String GET_SALARY_BY_ID_QUERY = "SELECT * FROM salary WHERE id = ?";
    private static final String DELETE_SALARY_QUERY = "DELETE FROM salary WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Salary> rowMapper = (rs, rowNum) -> {
        Salary salary = Salary.builder()
                .id(rs.getString("id"))
                .salary(rs.getLong("salary"))
                .build();
        return salary;
    };

    @Override
    public Salary save(Salary salary) {
        jdbcTemplate.update(CREATE_SALARY_QUERY, salary.getId(), salary.getSalary());
        return salary;
    }

    @Override
    public Salary update(Salary salary) {
        jdbcTemplate.update(UPDATE_SALARY_QUERY, salary.getSalary(), salary.getId());
        return salary;
    }

    @Override
    public Optional<Salary> findById(String id) {
        Salary salary = null;
        try {
            salary = jdbcTemplate.queryForObject(GET_SALARY_BY_ID_QUERY, new Object[]{id}, rowMapper);
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception);
        }
        return Optional.ofNullable(salary);
    }

    @Override
    public List<Salary> findAll() {

        return jdbcTemplate.query(GET_SALARIES_QUERY, rowMapper);
    }

    @Override
    public void delete(String id) {

        jdbcTemplate.update(DELETE_SALARY_QUERY, id);
    }
}

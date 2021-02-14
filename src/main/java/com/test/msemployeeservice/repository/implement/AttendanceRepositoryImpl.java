package com.test.msemployeeservice.repository.implement;

import com.test.msemployeeservice.entity.Attendance;

import com.test.msemployeeservice.repository.AttendanceRepository;
import org.dom4j.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AttendanceRepositoryImpl implements AttendanceRepository {

    private static final String CREATE_ATTENDANCE_QUERY = "INSERT INTO attendance(id, employee_id, salary_id, date_of_work, first_name, last_name, check_in, check_out) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ATTENDANCE_QUERY = "UPDATE attendance SET employee_id = ?, salary_id = ?, date_of_work = ?, first_name = ?, last_name = ?, check_in = ?, check_out = ? WHERE id = ?";
    private static final String GET_ATTENDANCES_QUERY = "SELECT * FROM employee AS e JOIN attendance a ON e.id = a.employee_id JOIN salary s ON a.salary_id = s.id";
    private static final String GET_ATTENDANCE_BY_ID_QUERY = "SELECT * FROM attendance WHERE id = ?";
    private static final String GET_COUNT_ATTENDANCE_QUERY = "SELECT COUNT(id) FROM attendance WHERE employee_id = ?";
    private static final String DELETE_ATTENDANCE_QUERY = "DELETE FROM attendance WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Attendance> rowMapper = (rs, rowNum) -> {
        Attendance attendance = Attendance.builder()
                .id(rs.getString("id"))
                .employeeId(rs.getString("employee_id"))
                .salaryId(rs.getString("salary_id"))
                .dateOfWork(rs.getDate("date_of_work"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .checkIn(rs.getTimestamp("check_in"))
                .checkOut(rs.getTimestamp("check_out"))
                .build();
        return attendance;
    };


    @Override
    public Attendance save(Attendance attendance) {
        jdbcTemplate.update(CREATE_ATTENDANCE_QUERY, attendance.getId(), attendance.getEmployeeId(), attendance.getSalaryId(), attendance.getDateOfWork(), attendance.getFirstName(), attendance.getLastName(), attendance.getCheckIn(), attendance.getCheckOut());
        return attendance;
    }

    @Override
    public Attendance update(Attendance attendance) {
        jdbcTemplate.update(UPDATE_ATTENDANCE_QUERY, attendance.getEmployeeId(), attendance.getSalaryId(), attendance.getDateOfWork(), attendance.getFirstName(), attendance.getLastName(), attendance.getCheckIn(), attendance.getCheckOut(), attendance.getId());
        return attendance;
    }

    @Override
    public Optional<Attendance> findById(String id) {
        Attendance attendance = null;
        try {
            attendance = jdbcTemplate.queryForObject(GET_ATTENDANCE_BY_ID_QUERY, new Object[]{id}, rowMapper);
        } catch (DataAccessException exception) {
            throw new RuntimeException(exception);
        }
        return Optional.ofNullable(attendance);
    }

    @Override
    public List<Attendance> findAll() {
        return jdbcTemplate.query(GET_ATTENDANCES_QUERY, rowMapper);
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update(DELETE_ATTENDANCE_QUERY, id);
    }

    @Override
    public int count(String id) {
        int value = jdbcTemplate.queryForObject(GET_COUNT_ATTENDANCE_QUERY, new Object[]{id}, Integer.class);
        return value;
    }

}

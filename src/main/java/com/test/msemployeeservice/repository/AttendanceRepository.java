package com.test.msemployeeservice.repository;

import com.test.msemployeeservice.entity.Attendance;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {

    @Query(
            value = "SELECT * FROM employee AS e JOIN attendance_employee a ON e.id = a.employee_id JOIN salary s ON a.salary_id = s.id",
            nativeQuery = true)
    List<Attendance> findAllAttendance();

    @Query(
            value = "SELECT COUNT(date_of_work) FROM attendance_employee a WHERE a.id = ?1",
            nativeQuery = true
    )
    Attendance findAttendanceEmployee(@RequestParam("id") String id);

}

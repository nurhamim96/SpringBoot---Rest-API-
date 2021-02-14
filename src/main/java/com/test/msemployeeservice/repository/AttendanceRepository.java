package com.test.msemployeeservice.repository;

import com.test.msemployeeservice.entity.Attendance;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository {

    Attendance save(Attendance attendance);

    Attendance update(Attendance attendance);

    Optional<Attendance> findById(String id);

    List<Attendance> findAll();

    void delete(String id);

    int count(String id);
}

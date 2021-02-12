package com.test.msemployeeservice.service.implement;

import com.test.msemployeeservice.entity.Attendance;
import com.test.msemployeeservice.entity.Salary;
import com.test.msemployeeservice.model.request.CheckInAttendanceRequest;
import com.test.msemployeeservice.model.request.CheckoutAttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import com.test.msemployeeservice.repository.AttendanceRepository;
import com.test.msemployeeservice.repository.SalaryRepository;
import com.test.msemployeeservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryServiceImpl salaryService;

    @Override
    public AttendanceResponse checkIn(CheckInAttendanceRequest request) {

        var date = new Date();

        Attendance attendance = Attendance.builder()
                .dateOfWork(date)
                .salaryId(request.getSalaryId())
                .employeeId(request.getEmployeeId())
                .createdAt(date)
                .updateAt(null)
                .build();

        attendance = attendanceRepository.save(attendance);

        return AttendanceResponse.<AttendanceResponse>builder()
                .id(attendance.getId())
                .dateOfWork(attendance.getDateOfWork())
                .createdAt(attendance.getCreatedAt())
                .updateAt(attendance.getUpdateAt())
                .salaryId(attendance.getSalaryId())
                .employeeId(attendance.getEmployeeId())
                .build();
    }

    @Override
    public AttendanceResponse get(String id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        return AttendanceResponse.builder()
                .id(attendance.get().getId())
                .employeeId(attendance.get().getEmployeeId())
                .salaryId(attendance.get().getSalaryId())
                .dateOfWork(attendance.get().getDateOfWork())
                .salary(attendance.get().getSalary())
                .totalSalary(attendance.get().getTotalSalary())
                .firstName(attendance.get().getFirstName())
                .lastName(attendance.get().getLastName())
                .createdAt(attendance.get().getCreatedAt())
                .updateAt(attendance.get().getUpdateAt())
                .build();
    }

    @Override
    public AttendanceResponse checkOut(CheckoutAttendanceRequest request) {

        var created = get(request.getId());

        var date = new Date();

        var temp = salaryService.get(request.getSalaryId());

        Attendance attendance = Attendance.builder()
                .id(request.getId())
                .dateOfWork(created.getDateOfWork())
                .salaryId(request.getSalaryId())
                .employeeId(request.getEmployeeId())
                .createdAt(created.getCreatedAt())
                .updateAt(date)
                .build();

        attendance = attendanceRepository.save(attendance);

        var weekdaysInAMonth = 22;

        Salary salary = Salary.builder()
                .id(attendance.getSalaryId())
                .salary(temp.getSalary())
                .totalSalary((temp.getSalary() / weekdaysInAMonth) * 22)
                .build();
        salaryRepository.save(salary);

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .dateOfWork(attendance.getDateOfWork())
                .createdAt(attendance.getCreatedAt())
                .updateAt(attendance.getUpdateAt())
                .salaryId(attendance.getSalaryId())
                .employeeId(attendance.getEmployeeId())
                .build();
    }

    @Override
    public List<AttendanceResponse> list() {
        return attendanceRepository.findAllAttendance().stream()
                .map(attendanceEmployee -> AttendanceResponse.builder()
                        .id(attendanceEmployee.getId())
                        .employeeId(attendanceEmployee.getEmployeeId())
                        .firstName(attendanceEmployee.getFirstName())
                        .lastName(attendanceEmployee.getLastName())
//                        .birthDate(attendanceEmployee.getBirthDate())
//                        .gender(attendanceEmployee.getGender())
//                        .hireDate(attendanceEmployee.getHireDate())
                        .dateOfWork(attendanceEmployee.getDateOfWork())
//                        .salary(attendanceEmployee.getSalary())
//                        .totalSalary(attendanceEmployee.getTotalSalary())
                        .salaryId(attendanceEmployee.getSalaryId())
                        .employeeId(attendanceEmployee.getEmployeeId())
                        .createdAt(attendanceEmployee.getCreatedAt())
                        .updateAt(attendanceEmployee.getUpdateAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        attendanceRepository.delete(attendance.get());
    }

}

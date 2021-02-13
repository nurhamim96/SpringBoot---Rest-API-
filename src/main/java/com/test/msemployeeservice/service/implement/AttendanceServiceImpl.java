package com.test.msemployeeservice.service.implement;

import com.test.msemployeeservice.entity.Attendance;
import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.entity.Salary;
import com.test.msemployeeservice.model.request.CheckInAttendanceRequest;
import com.test.msemployeeservice.model.request.CheckoutAttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import com.test.msemployeeservice.model.response.CheckInAndCheckOutResponse;
import com.test.msemployeeservice.model.response.ListAttendanceResponse;
import com.test.msemployeeservice.repository.AttendanceRepository;
import com.test.msemployeeservice.repository.EmployeeRepository;
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

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public CheckInAndCheckOutResponse checkIn(CheckInAttendanceRequest request) {

        var date = new Date();

        var temp = salaryService.get(request.getSalaryId());

        Attendance attendance = Attendance.builder()
                .dateOfWork(date)
                .salaryId(request.getSalaryId())
                .employeeId(request.getEmployeeId())
                .salary(temp.getSalary())
                .checkIn(date)
                .checkOut(null)
                .build();

        attendance = attendanceRepository.save(attendance);

        return CheckInAndCheckOutResponse.<CheckInAndCheckOutResponse>builder()
                .id(attendance.getId())
                .dateOfWork(attendance.getDateOfWork())
                .checkIn(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
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
                .checkIn(attendance.get().getCheckIn())
                .checkOut(attendance.get().getCheckOut())
                .build();
    }

    @Override
    public CheckInAndCheckOutResponse checkOut(CheckoutAttendanceRequest request) {

        var created = get(request.getId());

        var date = new Date();

        var temp = salaryService.get(request.getSalaryId());

        var requestEmployee = employeeService.get(request.getEmployeeId());

        var workingDays = 22;

        var totalWorkingDays = attendanceRepository.count(created.getEmployeeId());

        Long calculate = temp.getSalary() / workingDays * totalWorkingDays;
        System.out.println("INI LOG TOTAL WORKING DAYS " + calculate);

        Attendance attendance = Attendance.builder()
                .id(request.getId())
                .dateOfWork(created.getDateOfWork())
                .salaryId(request.getSalaryId())
                .employeeId(request.getEmployeeId())
                .totalSalary(calculate)
                .totalWorkingDays((int) totalWorkingDays)
                .checkIn(created.getCheckIn())
                .checkOut(date)
                .build();

        attendance = attendanceRepository.save(attendance);

        Employee employee =Employee.builder()
                .id(requestEmployee.getId())
                .birthDate(requestEmployee.getBirthDate())
                .firstName(requestEmployee.getFirstName())
                .lastName(requestEmployee.getLastName())
                .gender(requestEmployee.getGender())
                .hireDate(requestEmployee.getHireDate())
                .totalWorkingDays(totalWorkingDays)
                .totalSalary(calculate)
                .createdAt(requestEmployee.getCreatedAt())
                .updateAt(requestEmployee.getUpdateAt())
                .build();

        employeeRepository.save(employee);

        return CheckInAndCheckOutResponse.builder()
                .id(attendance.getId())
                .dateOfWork(attendance.getDateOfWork())
                .checkIn(attendance.getCheckIn())
                .checkOut(attendance.getCheckOut())
                .salaryId(attendance.getSalaryId())
                .employeeId(attendance.getEmployeeId())
                .build();
    }

    @Override
    public List<ListAttendanceResponse> list() {
        return attendanceRepository.findAllAttendance().stream()
                .map(attendance -> ListAttendanceResponse.builder()
                        .id(attendance.getId())
                        .employeeId(attendance.getEmployeeId())
                        .dateOfWork(attendance.getDateOfWork())
                        .firstName(attendance.getFirstName())
                        .lastName(attendance.getLastName())
                        .checkIn(attendance.getCheckIn())
                        .checkOut(attendance.getCheckOut())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Optional<Attendance> attendance = attendanceRepository.findById(id);
        attendanceRepository.delete(attendance.get());
    }

}

package com.test.msemployeeservice.service.implement;

import com.test.msemployeeservice.entity.Attendance;
import com.test.msemployeeservice.entity.Employee;
import com.test.msemployeeservice.model.request.AttendanceRequest;
import com.test.msemployeeservice.model.response.AttendanceResponse;
import com.test.msemployeeservice.model.response.ListAttendanceResponse;
import com.test.msemployeeservice.repository.AttendanceRepository;
import com.test.msemployeeservice.repository.EmployeeRepository;
import com.test.msemployeeservice.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SalaryServiceImpl salaryService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    private UUID uuid = UUID.randomUUID();

    @Override
    public AttendanceResponse checkIn(AttendanceRequest request) {

        var date = new Date();

        var temp = salaryService.get(request.getSalaryId());

        Attendance attendance = Attendance.builder()
                .id(uuid.toString())
                .employeeId(request.getEmployeeId())
                .salaryId(request.getSalaryId())
                .dateOfWork(date)
//                .salary(temp.getSalary())
                .checkIn(date)
                .checkOut(null)
                .build();

        attendance = attendanceRepository.save(attendance);

        return AttendanceResponse.<AttendanceResponse>builder()
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
                .checkIn(attendance.get().getCheckIn())
                .checkOut(attendance.get().getCheckOut())
                .build();
    }

    @Override
    public AttendanceResponse checkOut(AttendanceRequest request) {

        var created = get(request.getId());

        var date = new Date();

        var temp = salaryService.get(request.getSalaryId());

        var requestEmployee = employeeService.get(request.getEmployeeId());

        var workingDays = 22;

        var totalWorkingDays = attendanceRepository.count(created.getEmployeeId());

        Long calculate = temp.getSalary() / workingDays * totalWorkingDays;
        System.out.println("INI LOG TOTAL WORKING DAYS " + totalWorkingDays);

        Attendance attendance = Attendance.builder()
                .id(request.getId())
                .dateOfWork(created.getDateOfWork())
                .salaryId(request.getSalaryId())
                .employeeId(request.getEmployeeId())
                .totalSalary(calculate)
                .totalWorkingDays((long) totalWorkingDays)
                .checkIn(created.getCheckIn())
                .checkOut(date)
                .build();

        attendance = attendanceRepository.update(attendance);

        Employee employee = Employee.builder()
                .id(requestEmployee.getId())
                .birthDate(requestEmployee.getBirthDate())
                .firstName(requestEmployee.getFirstName())
                .lastName(requestEmployee.getLastName())
                .gender(requestEmployee.getGender())
                .hireDate(requestEmployee.getHireDate())
                .totalWorkingDays((long) totalWorkingDays)
                .totalSalary(calculate)
                .createdAt(requestEmployee.getCreatedAt())
                .updateAt(requestEmployee.getUpdateAt())
                .build();

        employeeRepository.update(employee);

        return AttendanceResponse.builder()
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
        return attendanceRepository.findAll().stream()
                .map(attendance -> ListAttendanceResponse.builder()
                        .id(attendance.getId())
                        .salaryId(attendance.getSalaryId())
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
        if (!id.isEmpty()) {
            attendanceRepository.delete(id);
        }
    }

}

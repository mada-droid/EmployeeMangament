package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.exception.UserNotFoundException;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService implements  EmployeeServiceInterface{

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDTO addEmployeeDTO(EmployeeDTO employeeDTO) {

        employeeDTO.setEmployeeCode(UUID.randomUUID().toString());

        Employee employee = new Employee(employeeDTO.getName(),
                employeeDTO.getEmail(),employeeDTO.getJobTitle(),employeeDTO.getPhone(),
                employeeDTO.getImageUrl(),employeeDTO.getEmployeeCode());

        employee.setId(employeeDTO.getId());

       employeeRepo.save(employee);

       return employeeDTO;
    }

    public List<EmployeeDTO> findAllEmployeesDto() {

        return employeeRepo.findAll().stream().map(
                x -> new EmployeeDTO(x.getId(), x.getName(), x.getEmail(),
                        x.getJobTitle(), x.getPhone(), x.getEmployeeCode(),
                        x.getImageUrl())).collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployeeDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getName(),
                employeeDTO.getEmail(),employeeDTO.getJobTitle(),employeeDTO.getPhone(),
                employeeDTO.getImageUrl(),employeeDTO.getEmployeeCode());
        employee.setId(employeeDTO.getId());

        employeeRepo.save(employee);

        return employeeDTO;
    }

    public EmployeeDTO findEmployeeDTOById(Long id) {

        Employee employee = employeeRepo.findEmployeeById(id).orElseThrow
                (() -> new UserNotFoundException
                        ("User by id" + id + "was not found"));

        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),
                employee.getName(), employee.getEmail(), employee.getJobTitle(),
                employee.getPhone(),employee.getEmployeeCode(),employee.getImageUrl());

        return employeeDTO;
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }


    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(UUID.randomUUID().toString());

        Employee employee = new Employee(employeeDTO.getName(),
                employeeDTO.getEmail(),employeeDTO.getJobTitle(),employeeDTO.getPhone(),
                employeeDTO.getImageUrl(),employeeDTO.getEmployeeCode());

        employee.setId(employeeDTO.getId());
        employeeRepo.save(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }
}

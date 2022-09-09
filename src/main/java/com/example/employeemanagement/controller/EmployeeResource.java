package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeDTO;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

    @Autowired
    private EmployeeServiceInterface employeeServiceInterface;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        List<EmployeeDTO> employeeDTOS = employeeService.findAllEmployeesDto();
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") Long id) {

        EmployeeDTO employeeDTO = employeeService.findEmployeeDTOById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO employeeDTO1 = employeeService.addEmployeeDTO(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO employeeDTO2 = employeeService.updateEmployeeDTO(employeeDTO);
        return new ResponseEntity<>(employeeDTO2, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee with id: " + id + "was found and deleted", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO>saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDTO1 = employeeServiceInterface.save(employeeDTO);
        return new ResponseEntity<>(employeeDTO1, HttpStatus.CREATED);

    }


}

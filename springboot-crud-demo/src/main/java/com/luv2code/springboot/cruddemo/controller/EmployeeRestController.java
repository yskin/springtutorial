package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.data.Employee;
import com.luv2code.springboot.cruddemo.data.EmployeeGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeGateway gateway;

    public EmployeeRestController(EmployeeGateway gateway) {
        this.gateway = gateway;
    }

    @GetMapping
    public List<Employee> findAll() {
        return gateway.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {
        Employee e = gateway.findById(id);
        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(e, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return new ResponseEntity(gateway.save(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        Employee e = gateway.update(employee);
        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(e, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable int id) {
        int row = gateway.deleteById(id);
        if (row == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

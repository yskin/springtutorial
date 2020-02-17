package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.data.Employee;
import com.luv2code.springboot.cruddemo.data.EmployeeGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/employees")
public class EmployeeUiController {

    private final EmployeeRestController controller;
    private final EmployeeGateway gateway;

    public EmployeeUiController(EmployeeRestController controller, EmployeeGateway gateway) {
        this.controller = controller;
        this.gateway = gateway;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", gateway.findAllOrderByLastName());
        return "employees/list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/form";
    }

    @PostMapping
    public String save(@ModelAttribute("employee") Employee employee) {
        if (employee.getId() == -1) {
            controller.save(employee);
        } else {
            controller.update(employee);
        }
        return "redirect:/ui/employees";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
        model.addAttribute("employee", gateway.findById(id));
        return "employees/form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id) {
        controller.delete(id);
        return "redirect:/ui/employees";
    }

}

package Lesson6.Controller;

import Lesson6.Service.EmployeeService;
import Lesson6.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees(Model model){

        List<Employee> allEmployee = employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployee);

        return "showAllEmployees";
    }

    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model){

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "EmployeeInfo";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @RequestMapping("updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        return "EmployeeInfo";
    }

    @RequestMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id){
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}

package Lesson6.Service;

import Lesson6.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public  Employee getEmployeeById(int id);

    public void deleteEmployee(int id);

}

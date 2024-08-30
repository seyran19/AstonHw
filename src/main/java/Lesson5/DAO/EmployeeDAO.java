package Lesson5.DAO;

import Lesson5.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> getAllEmployee();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);

    public void deleteEmployee(int id);
}
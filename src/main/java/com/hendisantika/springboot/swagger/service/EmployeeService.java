package com.hendisantika.springboot.swagger.service;

import com.hendisantika.springboot.swagger.model.Employee;
import org.jfairy.Fairy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hendisantika on 4/24/17.
 */
@Service
public class EmployeeService {
    private static Map<Integer, Employee> employeeDB;
    private Fairy fairy = Fairy.create();

    @PostConstruct
    public void init() throws Exception {
        employeeDB = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee(i, fairy.person());
            employeeDB.put(new Integer(i), employee);
        }
    }

    public List<Employee> getAll(){
        List<Employee> employeeList = employeeDB.entrySet().stream()
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return employeeList;
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeDB.get(employeeId);
    }

    public List<Employee> filterByAge(Integer age) {
        List<Employee> employeeList = employeeDB.entrySet().stream().filter(e -> e.getValue().getAge() > age)
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return employeeList;
    }

    public List<Employee> filterByCity(String cityName) {
        List<Employee> employeeList = employeeDB.entrySet().stream()
                .filter(e -> e.getValue().getAddress().getCity().equals(cityName)).map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return employeeList;
    }

    public List<Employee> filterByAgeAndCity(Integer age, String cityName) {
        List<Employee> employeeList = employeeDB.entrySet().stream()
                .filter(e -> e.getValue().getAddress().getCity().equals(cityName) && e.getValue().getAge() > age)
                .map(Map.Entry::getValue).collect(Collectors.toList());
        return employeeList;
    }
}

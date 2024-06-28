package com.hendisantika.springboot.swagger.controller;

import com.hendisantika.springboot.swagger.model.Employee;
import com.hendisantika.springboot.swagger.service.EmployeeService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(value = "/employees")
@Api(value = "API to search Employee from a Employee Directory by different search parameters",
        description = "This API provides the capability to search Employee from a Employee Directory", produces = "application/json")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Get All Employee", produces = "application/json")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployees() {
        logger.debug("Getting All employees ......");
        List<Employee> employee = null;
        try {
            employee = employeeService.getAll();
            logger.debug("Getting All employees ...... ::");
        } catch (Exception ex) {
            logger.error("Error occurred in searchEmployeeById >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Search Employee by employeeId", produces = "application/json")
    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Object> searchEmployeeById(
            @ApiParam(name = "employeeId",
                    value = "The Id of the Employee to be viewed",
                    required = true)
            @PathVariable Integer employeeId) {
        logger.debug("Searching for employee with employeeId ::" + employeeId);
        Employee employee = null;
        try {
            employee = employeeService.getEmployeeById(employeeId);
            logger.debug("Employee found with employeeId ::" + employeeId);
        } catch (Exception ex) {
            logger.error("Error occurred in searchEmployeeById >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(employee, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for all Employees whose age is greater than input age", produces = "application/json")
    @RequestMapping(value = "/greaterThanAge/{age}", method = RequestMethod.GET)
    public ResponseEntity<Object> filterEmployeesByAge(
            @ApiParam(name = "age",
                    value = "filtering age",
                    required = true) @PathVariable Integer age) {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeService.filterByAge(age);
        } catch (Exception ex) {
            logger.error("Error occurred in filterEmployeesByAge >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(employeeList, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for all Employees who are from input city", produces = "application/json")
    @RequestMapping(value = "/fromCity/{cityName}", method = RequestMethod.GET)
    public ResponseEntity<Object> filterEmployeesByCity(
            @ApiParam(name = "cityName", value = "filtering city name", required = true)
            @PathVariable String cityName) {
        List<Employee> employeeList = null;
        try {
            employeeList = employeeService.filterByCity(cityName);
        } catch (Exception ex) {
            logger.error("Error occurred in filterEmployeesByCity >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(employeeList, HttpStatus.OK);
    }

    @ApiOperation(value = "Search for all employees who are from given city and "
            + "whose age are greater than input age", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "schoolId", value = "School Id", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "age", value = "Age of Employee", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "cityName", value = "City of Employee", required = true, dataType = "String", paramType = "query") })
    @RequestMapping(value = "/filterByAgeAndCity", method = RequestMethod.GET)
    public ResponseEntity<Object> filterEmployeesByAgeAndCity(@RequestHeader(name = "schoolId") String userId,
                                                             @RequestParam Integer age,@RequestParam String cityName) {

        List<Employee> employeeList = null;
        try {
            employeeList = employeeService.filterByAgeAndCity(age, cityName);
        } catch (Exception ex) {
            logger.error("Error occurred in filterEmployeesByAgeAndCity >>", ex, ex.getMessage());
            return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(employeeList, HttpStatus.OK);
    }
}

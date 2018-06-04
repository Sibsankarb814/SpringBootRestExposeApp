package com.pwc.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import com.pwc.springboot.dao.EmployeeDAO;
import com.pwc.springboot.model.BagicPojo;
import com.pwc.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
 
public class MainRestController {
 
    @Autowired
    private EmployeeDAO employeeDAO;
 
    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to PwC Employee Management System.";
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employees
    // http://localhost:8080/SomeContextPath/employees.xml
    // http://localhost:8080/SomeContextPath/employees.json
    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    		  
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    // http://localhost:8080/SomeContextPath/employee/{empNo}.xml
    // http://localhost:8080/SomeContextPath/employee/{empNo}.json
    @RequestMapping(value = "/employee/{empNo}", 
            method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
            //produces = { MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
 
    @RequestMapping(value = "/employee", 
            method = RequestMethod.POST, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {
 
        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
 
        return employeeDAO.addEmployee(emp);
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
    @RequestMapping(value = "/employee", 
            method = RequestMethod.PUT, 
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {
 
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
 
        return employeeDAO.updateEmployee(emp);
    }
 
    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{empNo}", 
            method = RequestMethod.DELETE, 
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
 
        System.out.println("(Service Side) Deleting employee: " + empNo);
 
        employeeDAO.deleteEmployee(empNo);
    }
    
//============================================================================================= 
    @RequestMapping(value = "/bagicDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveBagicDeatils(@RequestBody BagicPojo bgicPojo) throws Exception 
    {
    	 System.out.println(" In saveBagicDeatils method .....................!!!!!!!!!!!");
    	 System.out.println("============================= Bagic Details :: Start :: =============================");
    	 
    try {
    	 System.out.println("Fist Name : "+bgicPojo.getFirstName());
    	 System.out.println("Middle Name : "+bgicPojo.getMiddleName());
    	 System.out.println("Phone No : "+bgicPojo.getPhone());
    	 System.out.println("Sur Name : "+bgicPojo.getSurName());
    	 System.out.println("Proposal Type : "+bgicPojo.getProposalType());
      	 System.out.println("Vechile Type : "+bgicPojo.getVechileTyp());
      	 System.out.println("Existing Policy No : "+bgicPojo.getExistingPolicyNo());
    	 System.out.println("============================= Bagic Details :: End :: =============================");
    	 }
    catch(Exception e) {    		 
    		 e.printStackTrace();
    		 return "Request Status : Error";
    	 }
    	 return "Request Status : Success";

    }
 
}
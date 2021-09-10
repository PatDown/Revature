package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.models.Employee;
import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.repository.EmployeeRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class EmployeeService {
    private EmployeeRepository employeeRepo;
    private ReimbursementService reimbursementService;
    public static final Map<Integer, Employee> employees = new HashMap<>();
    
    public EmployeeService(){
        super();
    }//EmployeeService()
    
    public EmployeeService(Connection conn){
        super();
        employeeRepo = new EmployeeRepository(conn);
        reimbursementService = new ReimbursementService();
    }//EmployeeService(Connection)
    
    public Employee create(Employee employee){
        try {
            employeeRepo.save(employee);
            employees.put(employee.getId(), employee);
            Employee.getEmployees().put(employee.getId(), employee);
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        return employee;
    }//create(Employee)
    
    public List<Employee> getEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        try {
            employeeList = employeeRepo.getAll();
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        
        for(Employee em : employeeList)
            if (!employees.containsValue(em))
                employees.put(em.getId(), em);
        
        return employeeList;
    }//getEmployees()
    
    public int login(String username, String password){
        for (var entry : employees.entrySet())
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)){
                Account.currentAccount = entry.getValue();
                return entry.getKey();
            }//if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password))
        return 0;
    }//login(String, String)
    
    public void logout(){
        Account.currentAccount = null;
    }//logout()
    
    public boolean isLoggedIn(){
        return Account.currentAccount != null;
    }//isLoggedIn()
    
    public void createRequest(Reimbursement r){
        Employee employee = (Employee) Account.currentAccount;
        employee.getReimbursements().add(reimbursementService.create(r));
    }//createRequest(Reimbursement)
    
    public List<Reimbursement> getReimbursements(){
        List<Reimbursement> reimbursements = reimbursementService.getAll();
        reimbursements.stream().filter(r -> (r.getEmployeeId() != Account.currentAccount.getId())).forEachOrdered(r -> {
            reimbursements.remove(r);
        });
        return reimbursements;
    }//getReimbursements()
}//EmployeeService

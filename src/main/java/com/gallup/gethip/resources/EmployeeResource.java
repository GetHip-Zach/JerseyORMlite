
package com.gallup.gethip.resources;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.gallup.gethip.DataSourceManager;
import com.gallup.gethip.model.Employee;
import com.j256.ormlite.dao.Dao;

// The Java class will be hosted at the URI path "/employee"
@Path("/employee")
public class EmployeeResource {

    // TODO: update the class to suit your needs
    
    // The Java method will process HTTP GET requests
    @GET 
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/html")
    public String getIt(@QueryParam("empno") String empno) {
    	Employee emp = null;
    	try {
			emp = getDao().queryForId(empno);
			if(emp == null){
				return "No employee found with that id";
			}else{
				return "<h1>" + emp.getFirstName() + " " + emp.getLastName() + "</h1>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error finding employee";
		}

        
    }
    
    private Dao<Employee, String> getDao(){
    	Dao<Employee, String> dao = DataSourceManager.getInstance().getDao(Employee.class);
    	return dao;
    }
}

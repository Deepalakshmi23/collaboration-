package com.niit.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.Jobdao;
import com.niit.dao.UserDao;
import com.niit.model.Error;
import com.niit.model.User;
import com.niit.model.job;

@Controller
public class JobController {

	@Autowired
	private UserDao userdao;
	@Autowired
	private Jobdao jobdao;
	
    @RequestMapping(value="/savejob", method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody job job,HttpSession session){

	  if(session.getAttribute("username") == null){
		  Error error=new Error(5,"Unauthorized Access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	  }
	  String username=(String) session.getAttribute("username");
	  User user = userdao.getUserByUsername(username);
	  if(user.getRole().equals("ADMIN")){
		  try{
			  job.setPostedOn(new Date());
			  jobdao.saveJob(job);
			  return new ResponseEntity<job>(job,HttpStatus.OK);
			  
		  }catch(Exception e){
			  Error error = new Error (7,"Unable to insert job details");
			  return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		  }
	  }
	  else{
			  Error error = new Error(6,"ACCESS DENIED");
			  return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			  
		  }
	
	}
    @RequestMapping(value="/getalljobs",method=RequestMethod.GET)
	public ResponseEntity<?>  getAllJobs(HttpSession session){
		if(session.getAttribute("username")==null){
			 Error error=new Error(5,"Unauthorized Access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		  }
		List<job> jobs=jobdao.getAllJobs();
		 return new ResponseEntity<List<job>>(jobs,HttpStatus.OK);
		  
	}
    @RequestMapping(value="/getjobbyid/{id}",method=RequestMethod.GET)
    public ResponseEntity<?> getJobById(@PathVariable int id,HttpSession session){
    	if(session.getAttribute("username")==null){
			 Error error=new Error(5,"Unauthorized Access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		  }
    job job=jobdao.getJobById(id);
    return new ResponseEntity<job>(job,HttpStatus.OK);
    
    }
    
}

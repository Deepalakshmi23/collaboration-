package com.niit.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.UserDao;
import com.niit.model.User;
import com.niit.model.Error;

@Controller
public class UserController {
	
	public UserController()
	{
		System.out.println("creating Instance for usercontroller");
	}

	
	
	@Autowired
	private UserDao userdao;
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	 public ResponseEntity<?> registration(@RequestBody User user){
		 if(!userdao.isEmailValid(user.getEmail())){
			 Error error=new Error(2,"Duplicate email address.. ");
			 return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);
		 }
		 if(!userdao.isUsernameValid(user.getUsername())){
			 Error error=new Error(3,"Username already exists..please enter different username");
				return new ResponseEntity<Error>(error,HttpStatus.NOT_ACCEPTABLE);//error -function
			}
		 try{
			 userdao.registration(user);
				return new ResponseEntity<User>(user,HttpStatus.OK);//200-299 - success function
				}catch(Exception e){
					Error error=new Error(1,"Unable to register.." + e.getMessage());
					return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);//error -function
				}
	 }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)	
public ResponseEntity<?> login(@RequestBody User user,HttpSession session)
{
	User validUser=userdao.login(user);
	if(validUser==null){
		Error error=new Error(4,"INVALID USERNAME/PASSWORD");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	validUser.setOnline(true);
	userdao.updateuser(validUser);
	session.setAttribute("username", validUser.getUsername());
	return new ResponseEntity<User>(validUser,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.PUT)
	public ResponseEntity<?> logout(HttpSession session){
		
		String username=(String)session.getAttribute("username");
		if(username==null){
			Error error=new Error(5,"please login...");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
		}
		
		User user=userdao.getUserByUsername(username);
		user.setOnline(false);
		userdao.updateuser(user);
		session.removeAttribute("username");
		session.invalidate();
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	@RequestMapping(value="/updateprofile", method=RequestMethod.PUT)
	public ResponseEntity<?> updateuser(@RequestBody User user,HttpSession session){
		String username=(String) session.getAttribute("username");
		if(username==null){
			Error error=new Error(5,"Unauthorized Access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			userdao.updateuser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(6,"Unable to edit"+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}



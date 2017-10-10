package com.niit.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.model.Error;
import com.niit.model.ProfilePicture;
import com.niit.model.User;

@Controller
public class ProfilePictureController {
	@Autowired
private ProfilePictureDao profilePictureDao;
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
public ResponseEntity<?>saveProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
	
	/*User user=(User)session.getAttribute("user");
	System.out.println(user.getUsername());*/
	if( session.getAttribute("username")==null){
		    Error error=new Error(3,"UnAuthorized acess");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	} 
	String username=(String) session.getAttribute("username");
	
	ProfilePicture profilepicture=new ProfilePicture();
	profilepicture.setUsername(username);
	profilepicture.setImage(image.getBytes());
	profilePictureDao.saveProfilePicture(profilepicture);
	return new  ResponseEntity<ProfilePicture>(profilepicture,HttpStatus.OK);
}
	
	@RequestMapping(value="/getprofilepic/{username}", method=RequestMethod.GET)
	public @ResponseBody byte[]getProfilePicture(@PathVariable String username,HttpSession session){
		if( session.getAttribute("username")==null)
			return null;
		ProfilePicture profilepicture=profilePictureDao.getProfilePicture(username);
		if(profilepicture==null)
			return null;
		else
			return profilepicture.getImage();
	}
		
}

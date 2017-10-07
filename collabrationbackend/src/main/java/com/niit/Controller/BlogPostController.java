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

import com.niit.dao.BlogPostdao;
import com.niit.dao.UserDao;
import com.niit.model.BlogPost;
import com.niit.model.User;
import com.niit.model.Error;
@Controller
public class BlogPostController {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private BlogPostdao blogpostdao;
	
	@RequestMapping(value="/saveBlogpost" , method=RequestMethod.POST)
	public ResponseEntity<?> saveblogpost(@RequestBody BlogPost blogpost,HttpSession session){
		if( session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String) session.getAttribute("username");
		User user=userdao.getUserByUsername(username);
		try{
			Date date=new Date();
			blogpost.setPostedOn(date);
			blogpost.setPostedby(user);
			blogpostdao.saveblogpost(blogpost);
			return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(7,"Unable to enter blog details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/getAllblogpost/{approved}" , method=RequestMethod.GET)
	public ResponseEntity<?> getallblogpost(@PathVariable int approved,HttpSession session){
	if( session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogPost> blogposts=blogpostdao.getallblogposts(approved);
		return new ResponseEntity<List<BlogPost>>(blogposts,HttpStatus.OK);
}
	
	@RequestMapping(value="/getBlogpostByid/{id}" , method=RequestMethod.GET)
	public ResponseEntity<?> getblogpostbyid(@PathVariable int id,HttpSession session){
		if( session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		BlogPost blogpost=blogpostdao.getblogpostbyid(id);
		return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateBlogpost" , method=RequestMethod.PUT)
	public ResponseEntity<?> updateblogpost(@RequestBody BlogPost blogpost,HttpSession session){
	
		if( session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try{
			if(!blogpost.isApproved() && blogpost.getRejectionReason() ==null)
				blogpost.setRejectionReason("Not Mentioned");//blogpost is reject but reason not mentioned
			blogpostdao.updateblogpost(blogpost);
			return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
		}
		catch(Exception e){
			Error error=new Error(7,"Unable to enter blog details");
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value="/blogpostapprovalstatus",method=RequestMethod.GET)
	public ResponseEntity<?> getBlogPostApprovalStatus(HttpSession session){
		if(session.getAttribute("username")==null){
			Error error=new Error(5,"Unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		String username=(String)session.getAttribute("username");
		List<BlogPost> blogPosts=blogpostdao.getApprovalStatus(username);
		return new ResponseEntity<List<BlogPost>>(blogPosts, HttpStatus.OK);
	}
	
	
}

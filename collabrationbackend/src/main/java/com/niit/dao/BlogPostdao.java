package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostdao {
	void saveblogpost(BlogPost blogpost);
	List<BlogPost> getallblogposts(int approved);
	BlogPost getblogpostbyid(int id);
	void updateblogpost(BlogPost blogpost);
	List<BlogPost> getApprovalStatus(String username);
	void saveblogcomment(BlogComment blogcomment);
	List<BlogComment> getallcomments(int blogid);

}

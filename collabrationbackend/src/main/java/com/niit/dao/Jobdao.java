package com.niit.dao;

import java.util.List;

import com.niit.model.job;

public interface Jobdao {
	void saveJob(job job);
	List<job> getAllJobs();
	job getJobById(int id);
	

}

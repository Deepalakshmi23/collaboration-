package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.job;
@Repository
@Transactional
public class jobdaoimpl implements Jobdao
{

	@Autowired
	private SessionFactory sessionFactory;

	public void saveJob(job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);
}

	public List<job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from job");
		
		List<job> jobs=query.list();
		int size=jobs.size();
		System.out.println("Number of object in the job");
		return jobs;
	}

	public job getJobById(int id) {
		Session session=sessionFactory.getCurrentSession();
		job job=(job) session.get(job.class, id);
		
		// TODO Auto-generated method stub
		return job;
	}
}
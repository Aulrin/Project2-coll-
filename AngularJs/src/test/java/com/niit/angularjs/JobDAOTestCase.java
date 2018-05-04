package com.niit.angularjs;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.angular.dao.JobDAO;
import com.niit.angular.model.Job;
import com.niit.angular.model.JobApplication;

public class JobDAOTestCase {

private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static JobDAO jobDAO;
	
	@Autowired
	private static Job job;
	
	@Autowired
	private static JobApplication jobApplication;
	
	
	//Need create instance of AnnotationConfigApplicationContext only once
	@BeforeClass
	public static void init()
	{
		context= new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
		jobApplication = (JobApplication) context.getBean("jobApplication");
	}
	@Test
	public void saveJobTestCase()
	{
		job.setNo_of_openings(10);
		job.setQualification("B.E");
		job.setSalary(20000);
		job.setTitle("Programmer");
		job.setDescription("This is program job");
		Assert.assertEquals("Save Job Test Case",true,jobDAO.save(job));
	}
	@Test
	public void updateJobTestCase()
	{
		job = jobDAO.get(101);
		job.setStatus('N');
		job.setQualification("B.Sc");
		Assert.assertEquals("Update Job Test Case",true,jobDAO.update(job));
	}
}
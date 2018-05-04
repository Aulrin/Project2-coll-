package com.niit.angular.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.angular.dao.JobDAO;
import com.niit.angular.model.Job;
import com.niit.angular.model.JobApplication;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//return max job id of all the records
	//if record are exits else return 100 
	private int getMaxValue()
	{
		int maxValue =100;
		try {
			maxValue = (Integer) getCurrentSession().createQuery("max(id)  from job").uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		return maxValue;
	}
	
	public boolean save(Job job) {
		try {
			job.setId(getMaxValue()+1);
			job.setStatus('N');
			getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Job job) {
		try {
			getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Job get(int jobID) {
		return (Job) getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("id", jobID)).uniqueResult();
	}

	public List<Job> list() {
		return getCurrentSession().createQuery("from Job").list();
	}

	public List<Job> list(char status) {
		return getCurrentSession().createCriteria(Job.class).add(Restrictions.eq("status", status)).list();

	}

	public boolean save(JobApplication jobApplication) {
		try {
			getCurrentSession().save(jobApplication);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(JobApplication jobApplication) {
		try {
			getCurrentSession().update(jobApplication);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<JobApplication> list(int jobID) {
		return getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("id", jobID)).list();

	}

	public List<JobApplication> list(int jobID, char status) {
		return getCurrentSession().createCriteria(JobApplication.class).add(Restrictions.eq("id", jobID))
				.add(Restrictions.eq("status", status)).list();

	}


}

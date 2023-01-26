package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

public class CourseService implements CourseI  {

	@Override
	public void createCourse(Course course) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx=session.beginTransaction();
			session.merge(course);
			tx.commit();
			
		}catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
		
	}

	@Override
	public Course getCourseById(int courseId) {
		Transaction tx= null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Course course = new Course();
		
		try {
			tx= session.beginTransaction();
			Query<Course> query = session.createQuery("from Course where id =:id", Course.class)
					.setParameter("id", courseId);
			course = query.getSingleResult();
			tx.commit();
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		List<Course> listOfCourses = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Course> query = session.createQuery("from Course ", Course.class);
			listOfCourses = query.getResultList();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		
		return listOfCourses;
	}

}

package sba.sms.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

public class StudentService implements StudentI {

	@Override
	public List<Student> getAllStudents() {
		List<Student> listOfStudents = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Student> query = session.createQuery("from Student ", Student.class);
			listOfStudents = query.getResultList();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		}
		
		return listOfStudents;
	}

	@Override
	public void createStudent(Student student) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx=session.beginTransaction();
			session.merge(student);
			tx.commit();
			
		}catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}		
		
	}

	@Override
	public Student getStudentByEmail(String email) {
		Transaction tx = null;
		Student student = new Student();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx = session.beginTransaction();
			Query<Student> query = session.createQuery("from student where email = :email", Student.class)
					.setParameter("email", email);
			student = query.getSingleResult();
			tx.commit();
			
			
		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		return student;
	}

	@Override
	public boolean validateStudent(String email, String password) {
		Student student = getStudentByEmail(email);
		boolean res = student.getPassword().equals(password) && student != null;
			
			return res;
		
	}

	
	private static final CourseService courseService = new CourseService();
	
	@Override
	public void registerStudentToCourse(String email, int courseId) {
		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			tx=session.beginTransaction();
			Student student = getStudentByEmail(email);
			student.addCourse(courseService.getCourseById(courseId));
			session.merge(student);
			tx.commit();
	
			
		}catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Course> getStudentCourses(String email) {
		List<Course> studentsCourses = new ArrayList<>();
		
		
		return null;
	}

}

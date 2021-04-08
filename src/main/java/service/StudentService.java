package service;


import dao.HibernateSessionFactoryUtil;
import dao.StudentDAO;
import dop.DaoException;
import dto.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class StudentService implements StudentDAO {
    SessionFactory factory;

    @Override
    public void add(Student student) throws DaoException {
        Session session=null;
        try {
        session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(student);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Student findStudent( int id)throws DaoException {
        Session session=null;Student student=null;
        try {
            session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            student=session.get(Student.class,id);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return student;
      //  return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Student.class,id);
    }

    @Override
    public Collection<Student> findStudentMore( String secondName) throws DaoException {
        List<Student> listOfStudents=null;
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            listOfStudents = session.createQuery("from Student where secondName = 'Bra'").getResultList();
            System.out.println(listOfStudents);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return listOfStudents;
    }

    @Override
    public void updateStudent( Student student)throws DaoException  {
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            session.update(student);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void removeStudent( int id)  throws DaoException{
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            Query<Student> query=session.createQuery("delete from Student where id =:studentId");
            query.setParameter("studentId",id);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Collection<Student> selectStudents(String firstName, String secondName, String birthDay, String enterYear) throws DaoException {
        List<Student> listOfStudents=null;
        Session session=null;
        if (firstName == null||firstName=="") {
            firstName = ""+"%";
        }
        if(secondName==null||secondName=="") {
            secondName = ""+"%";
        }
        if(birthDay==null||birthDay==""){
            birthDay=""+"%";
        }
        if(enterYear==null||enterYear==""){
            enterYear=""+"%";
        }
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
           String sqlQuerySelectStudent = "SELECT ID, FIRST_NAME, SECOND_NAME, BIRTH_DAY,ENTER_YEAR FROM STUDENT WHERE FIRST_NAME LIKE  ?  AND SECOND_NAME LIKE ? AND BIRTH_DAY LIKE ? AND ENTER_YEAR LIKE ? ";
            Query<Student> query = session.createNativeQuery(sqlQuerySelectStudent).addEntity(Student.class);
            query.setParameter(1, firstName);
            query.setParameter(2, secondName);
            query.setParameter(3, birthDay);
            query.setParameter(4, enterYear);
            listOfStudents=query.getResultList();
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return listOfStudents;
    }

    @Override
    public Collection<Student> selectGroupOfStudents( String firstName, String secondName, String birthDay, String enterYear, int n, int m)  throws DaoException{
        Session session=null;List results=null;n=(n-1)*m;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
        String hql = "FROM Student";
        Query query = session.createQuery(hql);
        query.setFirstResult(n);
        query.setMaxResults(m);
         results = query.list();
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return results;
    }

    @Override
    public int countLines( String firstName, String secondName, String birthDay, String enterYear)throws DaoException {
        Session session=null;Transaction transaction=null;
       int count = 0;
        if (firstName == null || firstName == "") {
            firstName = ""+"%";
        }
        if (secondName == null || secondName == "") {
            secondName = ""+"%";
        }
        if (birthDay == null || birthDay == "") {
            birthDay = ""+"%";
        }
        if (enterYear == null || enterYear == "") {
            enterYear = ""+"%";
        }

        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
             transaction=session.beginTransaction();
            String sqlQueryCountStudents = "SELECT ID, FIRST_NAME, SECOND_NAME, BIRTH_DAY,ENTER_YEAR FROM STUDENT  WHERE FIRST_NAME LIKE  ?  AND SECOND_NAME LIKE ? AND BIRTH_DAY LIKE ? AND ENTER_YEAR LIKE ? ";
            Query<Student> query = session.createNativeQuery(sqlQueryCountStudents).addEntity(Student.class);
            query.setParameter(1, firstName);
            query.setParameter(2, secondName);
            query.setParameter(3, birthDay);
            query.setParameter(4, enterYear);
            count=query.getResultList().size();
            transaction.commit();
        }catch (Exception e) {
            transaction.commit();
        } finally {
            session.close();

        }

        return count;
    }
}

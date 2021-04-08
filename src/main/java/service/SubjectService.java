package service;

import dao.HibernateSessionFactoryUtil;
import dao.SubjectDAO;
import dop.DaoException;
import dto.Student;
import dto.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.List;

public class SubjectService implements SubjectDAO {
    @Override
    public void addSubject(Subject subject) throws DaoException{
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        } catch (Exception e) {
            throw new DaoException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public void updateSubject(Subject subject) throws DaoException{
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(subject);
            transaction.commit();
        }catch (Exception e) {
                throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public void removeSubject(int id) throws DaoException{
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query<Student> query = session.createQuery("delete from Subject where id =:subjectId");
            query.setParameter("subjectId", id);
            query.executeUpdate();
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Subject findSubject(int id)throws DaoException {
        Session session=null; Subject subject=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
           subject= session.get(Subject.class,id);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return subject;
        //return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Subject.class,id);
    }

    @Override
    public Collection<Subject> findSubjectName(String nameSubject) throws DaoException{
        List<Subject> listOfSubject=null;
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            listOfSubject = session.createQuery("from Subject where name_subject = :subject").setParameter("subject", nameSubject).getResultList();
            System.out.println(listOfSubject);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return listOfSubject;

    }

    @Override
    public Collection<Subject> readAllSubject() throws DaoException{
        List<Subject> listOfSubject=null;
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            listOfSubject = session.createQuery("from Subject ").getResultList();
            System.out.println(listOfSubject);
            transaction.commit();
        }catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return listOfSubject;

    }



}

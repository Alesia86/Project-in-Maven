package service;

import dao.HibernateSessionFactoryUtil;
import dao.MarkDAO;
import dop.DaoException;
import dto.Mark;
import dto.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MarkService implements MarkDAO {
    @Override
    public void addMark(Mark mark)throws DaoException {
        Session session=null;
        try{
       session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(mark);
        tx1.commit();
    } catch (Exception e) {
            throw new DaoException(e);
        }finally {
            session.close();
        }
    }

    @Override
    public void updateMark(Mark mark) throws DaoException {
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            session.update(mark);
            transaction.commit();
        } catch (Exception e) {
            throw new DaoException(e);
    } finally {
            session.close();
        }
    }

    @Override
    public void removeMark(int id) throws DaoException{
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            Query<Student> query=session.createQuery("delete from Mark where id =:markId");
            query.setParameter("markId",id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
    }


    @Override
    public Mark findMark(int id)throws DaoException {
        Session session=null;Mark mark=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            mark = session.get(Mark.class, id);
        }catch(Exception e){
            throw new DaoException(e);
    } finally {
        session.close();
    }
        return mark;
  //  return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Mark.class, id);
    }

    @Override
    public Collection<Mark> findMarkStudent(int idStudent) throws DaoException{
        List<Mark> listOfMark=new ArrayList<>();
        Session session=null;
        try {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            listOfMark = session.createQuery("from Mark where studentId = :idStudent").setParameter("idStudent",idStudent).getResultList();
            System.out.println(listOfMark);
            transaction.commit();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            session.close();
        }
        return listOfMark;
    }
}

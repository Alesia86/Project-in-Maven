package dto;

import dao.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Mark.class).addAnnotatedClass(Subject.class).buildSessionFactory();

        Session session = null;
        try {


            /*Student student = session.get(Student.class, 31);
Subject subject=session.get(Subject.class, 6);
Mark mark1=new Mark(student.getId(),student.getSecondName(),subject.getId(),subject.getName_subject(),4);
student.addMarkToStudent(mark1);
Subject subject1=new Subject("Fizra","Smirnov","Fizicheskaia kultura");
session.save(subject1);*/
            /*session = factory.getCurrentSession();
            session.beginTransaction();*/
/*Student student=new Student("Alis","Bra", Date.valueOf("2002-11-11"),4);
            session.save(student);
            System.out.println(student.toString());
            session.getTransaction().commit();
            System.out.println("Done!");*/

           // session = factory.getCurrentSession();
            session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();int id= 44;
            /*Query<Student> query = session.createSQLQuery("from Student where firstName like :studentName and secondName like :studentSecondName and  birthDay like :studentBirthDay and enterYear like :studentEnterYear");
            query.setParameter("studentName", "Alis");
            query.setParameter("studentSecondName", "%");
            query.setParameter("studentBirthDay", "%");
            query.setParameter("studentEnterYear", "4");
            listOfStudents= query.list();*/List<Student> listOfStudents=null;
           // String sqlQuerySelectStudent = "SELECT ID, FIRST_NAME, SECOND_NAME, BIRTH_DAY,ENTER_YEAR FROM STUDENT  WHERE FIRST_NAME LIKE :a AND SECOND_NAME LIKE :b AND BIRTH_DAY LIKE :c AND ENTER_YEAR LIKE :d ";
            /*String sqlQuerySelectStudent = "SELECT ID, FIRST_NAME, SECOND_NAME, BIRTH_DAY,ENTER_YEAR FROM STUDENT  WHERE FIRST_NAME LIKE ? AND SECOND_NAME LIKE ? AND BIRTH_DAY LIKE ? AND ENTER_YEAR LIKE ? ";

            Query<Student> query = session.createNativeQuery(sqlQuerySelectStudent).addEntity(Student.class);
            query.setParameter(1, "Serafima");
            query.setParameter(2, ""+"%");
            query.setParameter(3, ""+"%");
            query.setParameter(4, ""+"%");
            System.out.println(" %");
            listOfStudents=query.getResultList();*/
            /*int n=9; int m=10;
            String hql = "FROM Student";
            Query query = session.createQuery(hql);
            query.setFirstResult(n);
            query.setMaxResults(m);
            listOfStudents = query.getResultList();
            System.out.println(listOfStudents.size());
            for (Student student1:listOfStudents){
                System.out.println(student1.toString());
            }
            System.out.println(listOfStudents);*/


            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            /*String sqlQueryCountStudents = "SELECT COUNT(ID) FROM STUDENT WHERE FIRST_NAME LIKE  ?  AND SECOND_NAME LIKE ? AND BIRTH_DAY LIKE ? AND ENTER_YEAR LIKE ? ";
            Query<Student> query = session.createNativeQuery(sqlQueryCountStudents).addEntity(Student.class);*/
            String sqlQueryCountStudents = "SELECT ID, FIRST_NAME, SECOND_NAME, BIRTH_DAY,ENTER_YEAR FROM STUDENT  WHERE FIRST_NAME LIKE  ?  AND SECOND_NAME LIKE ? AND BIRTH_DAY LIKE ? AND ENTER_YEAR LIKE ? ";
            Query<Student> query = session.createNativeQuery(sqlQueryCountStudents).addEntity(Student.class);
            query.setParameter(1, ""+"%");
            query.setParameter(2, ""+"%");
            query.setParameter(3, ""+"%");
            query.setParameter(4, ""+"%");
            int count=query.getResultList().size();
            System.out.println(count);
            transaction.commit();
        }catch (Exception e) {
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }


    }
}

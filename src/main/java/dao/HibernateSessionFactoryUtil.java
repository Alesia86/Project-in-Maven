package dao;

import dto.Mark;
import dto.Student;
import dto.Subject;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            /*sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Mark.class)
                    .addAnnotatedClass(Subject.class)
                    .buildSessionFactory();*/
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Mark.class);
            configuration.addAnnotatedClass(Subject.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }

}

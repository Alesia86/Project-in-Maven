package Test;

import dao.StudentDAO;
import dop.DaoException;
import dop.DatabaseConnection;
import dop.GeneralConnectionPool;
import dto.Student;
import org.testng.Assert;
import org.testng.annotations.*;
import service.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentDaoTest {
    private GeneralConnectionPool generalConnectionPool;
private  Student student ;
private int idStudent;

    @BeforeClass
    public void getGeneralPool() throws DaoException {

        try {
            generalConnectionPool=new GeneralConnectionPool();
            generalConnectionPool.init("db_unit.properties");
            StudentDAO studentDAO=new StudentService(generalConnectionPool.getConnection());
            String [] spisok={"Masha","Sasha","Vera","Olia","Katia","Vlada","Victoria","Ulia","Ilona","Vasia"};
            List<Student> listStudent=new ArrayList<>();
            for(int i=0;i<spisok.length;i++){
                Student student1 = new Student();
                student1.setFirstName(spisok[i]);
                student1.setSecondName("Sokolova");
                student1.setBirthDay(Date.valueOf("2005-06-21"));
                student1.setEnterYear(2);
                listStudent.add(student1);
                studentDAO.add(generalConnectionPool.getConnection(),student1);
                Student student2 = new Student();
                student2.setFirstName(spisok[i]);
                student2.setSecondName("Petrova");
                student2.setBirthDay(Date.valueOf("1997-07-30"));
                student2.setEnterYear(4);
                listStudent.add(student2);
                studentDAO.add(generalConnectionPool.getConnection(),student2);
            }
            Student student3 = new Student();
            student3.setFirstName("Misha");
            student3.setSecondName("Sidorov");
            student3.setBirthDay(Date.valueOf("2005-06-21"));
            student3.setEnterYear(2);
            studentDAO.add(generalConnectionPool.getConnection(), student3);
            Student student4 = new Student();
            student4.setFirstName("Vova");
            student4.setSecondName("Kolosok");
            student4.setBirthDay(Date.valueOf("2002-06-21"));
            student4.setEnterYear(3);
            studentDAO.add(generalConnectionPool.getConnection(), student4);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

@AfterClass
public void cleanTable() throws DaoException {
    Connection connection=null;
    Statement statement=null;
    String sqlQueryDelete = "DELETE FROM STUDENT";
    connection=generalConnectionPool.getConnection();
    try {
        statement = connection.createStatement();
        statement.executeUpdate(sqlQueryDelete);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            generalConnectionPool.returnConnection((DatabaseConnection) connection);
        }
    }
}


    @BeforeMethod
    public void writeInTable() throws DaoException {
        student = new Student();
        student.setFirstName("Misha");
        student.setSecondName("Sidorov");
        student.setBirthDay(Date.valueOf("2005-06-21"));
        student.setEnterYear(2);
        StudentDAO studentDAO = new StudentService(generalConnectionPool.getConnection());
        studentDAO.add(generalConnectionPool.getConnection(), student);
        idStudent=student.getId();
    }

    @AfterMethod
    public void cleanDatabase() throws DaoException {
        StudentDAO studentDAO=new StudentService(generalConnectionPool.getConnection());
        Collection<Student>collection=studentDAO.findStudentMore(generalConnectionPool.getConnection(),"Sidorov");
        if(collection.size()!=0){
            for(Student student:collection){
                studentDAO.removeStudent(generalConnectionPool.getConnection(),student.getId());
            }
        }
    }

   @Test
    public void testAddStudent() throws DaoException {
       StudentDAO studentDAO=new StudentService(generalConnectionPool.getConnection());
       Student studentFromDB=studentDAO.findStudent(generalConnectionPool.getConnection(), idStudent);
       Assert.assertEquals(student,studentFromDB);

    }

    @Test
    public void testDeleteStudent() throws DaoException {
        StudentDAO studentDAO = new StudentService(generalConnectionPool.getConnection());
        studentDAO.removeStudent(generalConnectionPool.getConnection(), idStudent);
        Assert.assertEquals(new Student(), studentDAO.findStudent(generalConnectionPool.getConnection(), idStudent));
    }
    @Test
    public void testFindStudentFromSecondName() throws DaoException {
        StudentDAO studentDAO = new StudentService(generalConnectionPool.getConnection());
        Collection<Student>collection=studentDAO.findStudentMore(generalConnectionPool.getConnection(),"Sokolova");
        int numberOfStudent=10;
        System.out.println(numberOfStudent);
        int numberFoundStudent=collection.size();
        System.out.println(numberFoundStudent);
        Assert.assertEquals(numberOfStudent,numberFoundStudent);
    }
    @Test
    public void testUpdateStudent() throws DaoException {
        StudentDAO studentDAO = new StudentService(generalConnectionPool.getConnection());
        Student studentFromDB=studentDAO.findStudent(generalConnectionPool.getConnection(), idStudent);
        int enterYearBefore=studentFromDB.getEnterYear();
        studentFromDB.setEnterYear(enterYearBefore+1);
        studentDAO.updateStudent(generalConnectionPool.getConnection(),studentFromDB);
        Student student1=studentDAO.findStudent(generalConnectionPool.getConnection(),idStudent);
        Assert.assertSame(studentFromDB,student1);
    }

}

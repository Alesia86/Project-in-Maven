package dao;


import dop.DaoException;
import dto.Student;

import java.util.Collection;

public interface StudentDAO {
    public void add( Student student)throws DaoException;
    public Student findStudent( int id) throws DaoException;
    public Collection<Student> findStudentMore(String secondName)throws DaoException ;
    public void updateStudent( Student student)throws DaoException;
    public void removeStudent( int id) throws DaoException;
    public Collection<Student> selectStudents( String firstName, String secondName, String birthDay, String enterYear) throws DaoException ;
    public Collection<Student>  selectGroupOfStudents( String firstName, String secondName, String birthDay, String enterYear, int n, int m) throws DaoException;
    public int  countLines(String firstName, String secondName, String birthDay, String enterYear) throws DaoException;
}

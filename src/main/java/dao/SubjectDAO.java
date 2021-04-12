package dao;


import dop.DaoException;
import dto.Subject;

import java.util.Collection;

public interface SubjectDAO {
    public void addSubject(Subject subject)throws DaoException;
    public void updateSubject(Subject subject)throws DaoException;
    public void removeSubject(int id)throws DaoException;
    public Subject findSubject(int id)throws DaoException;
    public Collection<Subject> findSubjectName(String nameSubject)throws DaoException;
    public Collection<Subject> readAllSubject()throws DaoException;
}

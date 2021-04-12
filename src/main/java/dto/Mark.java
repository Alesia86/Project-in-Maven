package dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mark")
public class Mark {

    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name="STUDENT_ID")
    private int studentId;

    @Column(name="SECONDNAME_STUDENT")
    private String secondNameOfStudent;

    @Column(name="SUBJECT_ID")
    private int subjectId;

    @Column(name="NAME_SUBJECT")
    private String nameOfSubject;

    @Column(name="MARK")
    private int mark;

    public Mark(int studentId, String secondNameOfStudent, int subjectId, String nameOfSubject, int mark) {
        this.studentId = studentId;
        this.secondNameOfStudent = secondNameOfStudent;
        this.subjectId = subjectId;
        this.nameOfSubject = nameOfSubject;
        this.mark = mark;
    }
    public Mark(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getSecondNameOfStudent() {return secondNameOfStudent;}

    public String getNameOfSubject() {return nameOfSubject; }

    public void setSecondNameOfStudent(String secondNameOfStudent) {this.secondNameOfStudent = secondNameOfStudent;}

    public void setNameOfSubject(String nameOfSubject) {this.nameOfSubject = nameOfSubject;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark1 = (Mark) o;
        return
                studentId == mark1.studentId &&
                        subjectId == mark1.subjectId &&
                        mark == mark1.mark &&
                        Objects.equals(secondNameOfStudent, mark1.secondNameOfStudent) &&
                        Objects.equals(nameOfSubject, mark1.nameOfSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, secondNameOfStudent, subjectId, nameOfSubject, mark);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", secondNameOfStudent='" + secondNameOfStudent + '\'' +
                ", subjectId=" + subjectId +
                ", nameOfSubject='" + nameOfSubject + '\'' +
                ", mark=" + mark +
                '}';
    }
}

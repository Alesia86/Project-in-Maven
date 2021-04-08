package dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="subject")
public class Subject {

    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name="name_subject")
    private String name_subject;

    @Column(name="name_teacher")
    private String name_teacher;

    @Column(name="kafedra")
    private String kafedra;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="SUBJECT_ID")
    private List<Mark> listOfMarkFromSubject;

    public Subject(String name_subject, String name_teacher, String kafedra) {
        this.name_subject = name_subject;
        this.name_teacher = name_teacher;
        this.kafedra = kafedra;
    }
    public Subject() {
    }

    public void addMarkToSubject(Mark mark){
        if(listOfMarkFromSubject==null){
            listOfMarkFromSubject=new ArrayList<>();
        }
        listOfMarkFromSubject.add(mark);
        mark.setSubjectId(this.id);
    }
    public List<Mark> getListOfMarkFromSubject() {
        return listOfMarkFromSubject;
    }

    public void setListOfMarkFromSubject(List<Mark> listOfMarkFromSubject) {
        this.listOfMarkFromSubject = listOfMarkFromSubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }
    public String getName_teacher() {
        return name_teacher;
    }

    public void setName_teacher(String name_teacher) {
        this.name_teacher = name_teacher;
    }

    public String getKafedra() {
        return kafedra;
    }

    public void setKafedra(String kafedra) {
        this.kafedra = kafedra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return
                Objects.equals(name_subject, subject.name_subject) &&
                        Objects.equals(name_teacher, subject.name_teacher) &&
                        Objects.equals(kafedra, subject.kafedra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_subject, name_teacher, kafedra);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name_subject='" + name_subject + '\'' +
                ", name_teacher='" + name_teacher + '\'' +
                ", kafedra='" + kafedra + '\'' +
                '}';
    }
}

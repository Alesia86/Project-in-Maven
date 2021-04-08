package dto;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name="student")
public class Student {

@Column(name="id")
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Id
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="second_name")
    private String secondName;

    @Column(name="birth_day")
    private Date birthDay;

    @Column(name="enter_year")
    private int enterYear;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="STUDENT_ID")
    private List<Mark> listOfMark;


    public Student(String firstName, String secondName, Date birthDay, int enterYear) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDay = birthDay;
        this.enterYear = enterYear;
    }
    public Student(){
    }

    public List<Mark> getListOfMark() {
        return listOfMark;
    }

    public void setListOfMark(List<Mark> listOfMark) {
        this.listOfMark = listOfMark;
    }

    public void addMarkToStudent(Mark mark){
        if(listOfMark==null){
            listOfMark=new ArrayList<>();
        }
        listOfMark.add(mark);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(int enterYear) {
        this.enterYear = enterYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return
                enterYear == student.enterYear &&
                        Objects.equals(firstName, student.firstName) &&
                        Objects.equals(secondName, student.secondName) &&
                        Objects.equals(birthDay, student.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, birthDay, enterYear);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDay=" + birthDay +
                ", enterYear=" + enterYear +
                '}';
    }
}

package co.edu.udea.compumovil.studentapp.models;

/**
 * Created by jaiber on 10/7/17.
 */

public class Student {
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String photo;

    public Student(String firstName, String lastName, String photo) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.photo = photo;
    }

    public Student(String firstName, String lastName, String gender, String photo) {
        this.firstname = firstName;
        this.lastname = lastName;
        this.gender = gender;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

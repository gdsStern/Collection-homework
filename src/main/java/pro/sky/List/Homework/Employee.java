package pro.sky.List.Homework;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;
import java.util.Random;

public class Employee {
    private String firstName;
    private String lastName;

    private int salary;
    private int departmentID;


    public Employee(String firstName, String lastName, int departmentID, int salary) {
        Random random = new Random();
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departmentID = departmentID;
    }

//    public Employee() {
//
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    @JsonIgnore
    public static String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}

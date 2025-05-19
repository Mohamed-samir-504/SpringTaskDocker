package org.example.springtaskdocker.Model.DTO;

public class CourseXSDDTO {
    private String name;
    private String description;


    private int credit;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCredit() {
        return credit;
    }
}

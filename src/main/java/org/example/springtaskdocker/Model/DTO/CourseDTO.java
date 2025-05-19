package org.example.springtaskdocker.Model.DTO;

public class CourseDTO {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public CourseDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CourseDTO() {}

    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}

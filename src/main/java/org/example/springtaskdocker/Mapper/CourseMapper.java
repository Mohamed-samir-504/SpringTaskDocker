package org.example.springtaskdocker.Mapper;

import org.example.springtaskdocker.Model.DTO.CourseDTO;
import org.example.springtaskdocker.Model.Entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CourseDTO toDto(Course entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Course toEntity(CourseDTO dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    List<CourseDTO> toDtoList(List<Course> courses);
}

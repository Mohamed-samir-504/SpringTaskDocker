package org.example.springtaskdocker.Mappers;

import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.Models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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

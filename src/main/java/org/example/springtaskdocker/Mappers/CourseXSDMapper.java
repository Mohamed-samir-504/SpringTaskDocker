package org.example.springtaskdocker.Mappers;

import generated.AdvancedCourseXSD;
import generated.CourseXSD;
import org.example.springtaskdocker.DTO.CourseDTO;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
import org.example.springtaskdocker.Models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseXSDMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "prerequisites", target = "prerequisites")
    CourseXSDDTO toDto(AdvancedCourseXSD entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "prerequisites", target = "prerequisites")
    AdvancedCourseXSD toEntity(CourseXSDDTO dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "prerequisites", target = "prerequisites")
    List<CourseXSDDTO> toDtoList(List<AdvancedCourseXSD> advCourses);
}

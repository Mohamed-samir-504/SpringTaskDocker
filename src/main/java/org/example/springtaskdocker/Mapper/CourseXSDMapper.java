package org.example.springtaskdocker.Mapper;

import generated.CoursesXSD;
import org.example.springtaskdocker.Model.DTO.CourseXSDDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseXSDMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CourseXSDDTO toDto(CoursesXSD.CourseXSD entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CoursesXSD.CourseXSD toEntity(CourseXSDDTO dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    List<CourseXSDDTO> toDtoList(List<CoursesXSD.CourseXSD> courses);
}

package org.example.springtaskdocker.Mapper;

import generated.AdvancedCourseXSD;
import org.example.springtaskdocker.DTO.CourseXSDDTO;
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

package com.udacity.jdnd.course3.critter.util;

import java.util.stream.Collectors;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;

public class ScheduleMapper {
    public static Schedule ScheduleDTOToEntity(ScheduleDTO dto) {
        return Schedule.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .activities(dto.getActivities())
                .build();
    }

    public static ScheduleDTO ScheduleToDTO(Schedule entity) {
        return ScheduleDTO.builder()
                .id(entity.getId())
                .date(entity.getDate())
                .activities(entity.getActivities())
                .employeeIds(entity.getEmployee().stream().map(x -> x.getId()).collect(Collectors.toList()))
                .petIds(entity.getPets().stream().map(x -> x.getId()).collect(Collectors.toList()))
                .build();
    }
}

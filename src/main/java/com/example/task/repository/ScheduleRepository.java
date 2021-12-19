package com.example.task.repository;

import com.example.task.model.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.task.context.Context.SCHEDULE_FILE_PATH;

@Slf4j
@Repository
public class ScheduleRepository {

    private static List<Schedule> validScheduleList;

    @PostConstruct
    public void setUp() {
        try{
            validScheduleList = Files.readAllLines(Paths.get(SCHEDULE_FILE_PATH)).stream()
                    .map(validSchedule ->
                            Schedule
                                    .builder()
                                    .route(validSchedule.substring(0, 2))
                                    .departureStation(validSchedule.substring(0, 1))
                                    .destinationStation(validSchedule.substring(1, 2))
                                    .distance(Integer.parseInt(validSchedule.substring(2)))
                                    .build())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("read schedule file failed");
        }
    }

    public List<Schedule> getValidScheduleList() {
        return validScheduleList;
    }

    public List<Schedule> getValidScheduleByStation(String stationName) {
        return validScheduleList
                .stream()
                .filter(s -> s.getDepartureStation().equals(stationName))
                .collect(Collectors.toList());
    }
}

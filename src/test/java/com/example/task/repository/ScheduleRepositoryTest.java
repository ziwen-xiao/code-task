package com.example.task.repository;

import com.example.task.model.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ScheduleRepositoryTest {
    private  ScheduleRepository scheduleRepository;

    @BeforeEach
    void setup() {
        this.scheduleRepository = new ScheduleRepository();
    }

    @Test
    void should_return_correct_valid_schedule_list() {
        List<Schedule> actualScheduleList = scheduleRepository.getValidScheduleList();
        Schedule expectedSchedule = Schedule
                .builder()
                .route("AB")
                .departureStation("A")
                .destinationStation("B")
                .distance(5)
                .build();
        assertEquals(expectedSchedule, actualScheduleList.get(0));
    }

    @Test
    void should_return_correct_valid_schedule_list_according_to_departure_station() {
        List<Schedule> result = scheduleRepository.getValidScheduleByStation("A");
        Schedule expect = Schedule
                .builder()
                .route("AB")
                .departureStation("A")
                .destinationStation("B")
                .distance(5)
                .build();
        assertEquals(3, result.size());
        assertEquals(expect, result.get(0));
    }
}

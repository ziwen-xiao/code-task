package com.example.task.repository;

import com.example.task.model.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


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
        Assertions.assertEquals(expectedSchedule, actualScheduleList.get(0));
    }
}

package com.example.task.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.task.model.Schedule;
import com.example.task.repository.ScheduleRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SolutionServiceTest {

    @Mock
    ScheduleRepository mockScheduleRepository;

    @InjectMocks
    SolutionService solutionService;

    private final Schedule schedule1 = Schedule
        .builder()
        .departureStation("A")
        .destinationStation("B")
        .route("AB")
        .distance(5)
        .build();
    private final Schedule schedule2 = Schedule
        .builder()
        .departureStation("B")
        .destinationStation("C")
        .route("BC")
        .distance(4)
        .build();
    private final Schedule schedule3 = Schedule
        .builder()
        .departureStation("C")
        .destinationStation("D")
        .route("CD")
        .distance(8)
        .build();
    private final Schedule schedule4 = Schedule
        .builder()
        .departureStation("D")
        .destinationStation("E")
        .route("DE")
        .distance(6)
        .build();
    private final Schedule schedule5 = Schedule
        .builder()
        .departureStation("A")
        .destinationStation("E")
        .route("AE")
        .distance(18)
        .build();

    List<Schedule> mockScheduleListStartWithA = List.of(schedule1, schedule5);
    List<Schedule> mockScheduleListStartWithB = List.of(schedule2);
    List<Schedule> mockScheduleListStartWithC = List.of(schedule3);
    List<Schedule> mockScheduleListStartWithD = List.of(schedule4);

    @Test
    void should_get_trips_and_trips_number() {
        Mockito
            .when(mockScheduleRepository.getValidScheduleByStation("A"))
            .thenReturn(mockScheduleListStartWithA);
        Mockito
            .when(mockScheduleRepository.getValidScheduleByStation("B"))
            .thenReturn(mockScheduleListStartWithB);
        Mockito
            .when(mockScheduleRepository.getValidScheduleByStation("C"))
            .thenReturn(mockScheduleListStartWithC);
        Mockito
            .when(mockScheduleRepository.getValidScheduleByStation("D"))
            .thenReturn(mockScheduleListStartWithD);
        String tripsNumberWithinLimitStations = solutionService.getTripsNumberWithinLimitStations(
            "A",
            "E",
            4
        );
        String tripsNumberOfExactStations = solutionService.getTripsNumberOfExactStations(
            "A",
            "E",
            4
        );
        assertEquals(
            "THE NUMBER OF TRIPS BETWEEN A AND E IS: 2",
            tripsNumberWithinLimitStations
        );
        assertEquals(
            "THE NUMBER OF TRIPS BETWEEN A AND E IS: 1",
            tripsNumberOfExactStations
        );
    }
}

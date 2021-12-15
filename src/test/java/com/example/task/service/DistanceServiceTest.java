package com.example.task.service;

import com.example.task.model.Schedule;
import com.example.task.repository.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DistanceServiceTest {
    @Mock
    ScheduleRepository mockScheduleRepository;

    @InjectMocks
    DistanceService distanceService;

    List<Schedule> mockScheduleList = List.of(
            Schedule
                    .builder()
                    .departureStation("A")
                    .destinationStation("B")
                    .route("AB")
                    .distance(5)
                    .build(),
            Schedule
                    .builder()
                    .departureStation("B")
                    .destinationStation("C")
                    .route("BC")
                    .distance(4)
                    .build(),
            Schedule
                    .builder()
                    .departureStation("C")
                    .destinationStation("D")
                    .route("CD")
                    .distance(8)
                    .build()
    );

    @Test
    void should_deal_with_route_correctly() {
        Mockito.when(mockScheduleRepository.getValidScheduleList()).thenReturn(mockScheduleList);
        String validResult = distanceService.dealWithRoute("ABCD");
        String invalidResult = distanceService.dealWithRoute("AEF");
        assertEquals("FOLLOW THE ROUTE AND THE DISTANCE IS: 17", validResult);
        assertEquals("NO SUCH ROUTE: AEF", invalidResult);

    }
}

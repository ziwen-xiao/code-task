package com.example.task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private String route;
    private String departureStation;
    private String destinationStation;
    private Integer distance;
}

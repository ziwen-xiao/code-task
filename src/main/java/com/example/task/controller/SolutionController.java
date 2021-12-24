package com.example.task.controller;

import com.example.task.dto.SolutionRequest;
import com.example.task.service.SolutionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("route/solution")
public class SolutionController {

    private final SolutionService solutionService;

    @PostMapping("/limit")
    @ResponseStatus(HttpStatus.OK)
    public String getSolutionsWithinLimitStations(
        @RequestBody SolutionRequest solutionRequest
    ) {
        return solutionService.getTripsNumberWithinLimitStations(
            solutionRequest.getStart(),
            solutionRequest.getEnd(),
            solutionRequest.getLimit()
        );
    }

    @PostMapping("/exact")
    @ResponseStatus(HttpStatus.OK)
    public String getSolutionsOfExactStations(
        @RequestBody SolutionRequest solutionRequest
    ) {
        return solutionService.getTripsNumberOfExactStations(
            solutionRequest.getStart(),
            solutionRequest.getEnd(),
            solutionRequest.getExact()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<List<String>> getSolutions(
        @RequestBody SolutionRequest solutionRequest
    ) {
        return solutionService.getAllTrips(
            solutionRequest.getStart(),
            solutionRequest.getEnd()
        );
    }
}

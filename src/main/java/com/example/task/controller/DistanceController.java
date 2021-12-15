package com.example.task.controller;

import com.example.task.service.DistanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("route")
public class DistanceController {
    private final DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @PostMapping("/distance")
    @ResponseStatus(HttpStatus.OK)
    public String calculateDistance(@RequestParam String route) throws IOException {
        return distanceService.dealWithRoute(route);
    }
}

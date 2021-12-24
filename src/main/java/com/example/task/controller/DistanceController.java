package com.example.task.controller;

import com.example.task.service.DistanceService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("route")
public class DistanceController {

    private final DistanceService distanceService;

    @PostMapping("/distance")
    @ResponseStatus(HttpStatus.OK)
    public String calculateDistance(@RequestParam String route) {
        return distanceService.dealWithRoute(route);
    }
}

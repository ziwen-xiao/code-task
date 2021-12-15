package com.example.task.service;

import com.example.task.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DistanceService {

    private final ScheduleRepository scheduleRepository;

    // 判断路线是否存在
    private Boolean isValid(String route) {
        return scheduleRepository.getValidScheduleList()
                .stream()
                .anyMatch(trainSchedule ->
                        route.equals(trainSchedule.getRoute())
                );
    }

    // 获取单条路线距离
    private Integer getDistance(String route)  {
        return scheduleRepository.getValidScheduleList().stream()
                        .filter(trainSchedule ->
                                route.equals(trainSchedule.getRoute()))
                        .collect(Collectors.toList())
                        .get(0)
                        .getDistance();
    }

    // 计算总路程距离
    private Integer calculateTotalDistance(String route) {
        return getRouteList(route).stream()
                .map(this::getDistance)
                .reduce(Integer::sum)
                .orElseThrow();
    }

    public String dealWithRoute(String route) {
        for (String s : getRouteList(route)) {
            if(!isValid(s)) {
                return "NO SUCH ROUTE: " + route;
            }
        }
        return "FOLLOW THE ROUTE AND THE DISTANCE IS: " + calculateTotalDistance(route);
    }

    // 路线转为List
    private List<String> getRouteList(String route) {
        List<String> routeList = new ArrayList<>();
        for (int i = 0; i < route.length() - 1; i++) {
            String substring = route.substring(i, i + 2);
            routeList.add(substring);
        }
        return routeList;
    }
}

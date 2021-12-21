package com.example.task.service;

import com.example.task.repository.ScheduleRepository;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SolutionService {

    private final ScheduleRepository scheduleRepository;

    // 获取所有路线
    public List<List<String>> getAllTrips(String start, String end) {
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        List<List<String>> solutionList = new ArrayList<>();

        stack.push(start);
        visited.add(start);

        depthFirstSearch(start, end, stack, visited, solutionList);
        return solutionList;
    }

    // 深度优先搜索
    private void depthFirstSearch(
        String start,
        String end,
        Stack<String> stack,
        Set<String> visited,
        List<List<String>> solutionList
    ) {
        scheduleRepository
            .getValidScheduleByStation(start)
            .forEach(
                schedule -> {
                    if (schedule.getDestinationStation().equals(end)) {
                        stack.push(schedule.getDestinationStation());
                        List<String> solution = new ArrayList<>(stack);
                        solutionList.add(solution);
                        stack.pop();
                    }
                    if (!visited.contains(schedule.getDestinationStation())) {
                        stack.push(schedule.getDestinationStation());
                        visited.add(schedule.getDestinationStation());
                        depthFirstSearch(
                            schedule.getDestinationStation(),
                            end,
                            stack,
                            visited,
                            solutionList
                        );
                    }
                }
            );
        visited.remove(start);
        stack.pop();
    }

    // N站以内所有路线
    public String getTripsNumberWithinLimitStations(
        String start,
        String end,
        int limit
    ) {
        return (
            "THE NUMBER OF TRIPS BETWEEN " +
            start +
            " AND " +
            end +
            " IS: " +
            (int) getAllTrips(start, end)
                .stream()
                .filter(s -> s.size() <= limit + 1)
                .count()
        );
    }

    // 恰好等于N站所有路线
    public String getTripsNumberOfExactStations(
        String start,
        String end,
        int exact
    ) {
        return (
            "THE NUMBER OF TRIPS BETWEEN " +
            start +
            " AND " +
            end +
            " IS: " +
            (int) getAllTrips(start, end)
                .stream()
                .filter(s -> s.size() == exact + 1)
                .count()
        );
    }
}

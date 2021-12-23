package com.example.task.service;

import com.example.task.repository.ScheduleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DistanceService {

    //TODO 1: 一般ScheduleRepository应该为接口，而不是具体的实现类。从而实现业务和底层数据的存取隔离开
    private final ScheduleRepository scheduleRepository;

    // 判断路线是否存在
    private Boolean isValid(String route) {
        return scheduleRepository
            .getValidScheduleList()
            .stream()
            .anyMatch(trainSchedule -> route.equals(trainSchedule.getRoute()));
    }

    // 获取单条路线距离
    private Integer getDistance(String route) {
        return scheduleRepository
            .getValidScheduleList()
            .stream()
            .filter(trainSchedule -> route.equals(trainSchedule.getRoute()))
                //TODO 3: 可以在这里处理路径不存在的情况
            .collect(Collectors.toList())
            .get(0)
            .getDistance();
    }

    // 计算总路程距离
    private Integer calculateTotalDistance(String route) {
        return getRouteList(route)
            .stream()
            .map(this::getDistance)
            .reduce(Integer::sum)
            .orElseThrow();
    }

    //TODO 2: 这个方法返回字符串不合理，应该返回的是 int 类型的距离，或者抛异常。
    // "FOLLOW THE ROUTE AND THE DISTANCE IS" 属于程序跟用户交互的内容细节，应该交给更上层的controller去处理，
    // service只负责计算结果
    public String dealWithRoute(String route) {
        //TODO 3: 这里没有必要专门通过isValid 检查是否存在。可以直接在计算路径时判断是否存在
        for (String s : getRouteList(route)) {
            if (!isValid(s)) {
                return "NO SUCH ROUTE: " + route;
            }
        }
        return (
            "FOLLOW THE ROUTE AND THE DISTANCE IS: " +
            calculateTotalDistance(route)
        );
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

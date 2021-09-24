package com.example.managerserver.server.controller;

import com.example.managerserver.server.entity.TrafficLightState;
import com.example.managerserver.server.entity.TrafficLightEnum;
import com.example.managerserver.server.service.TrafficLightService;
import com.pi4j.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 红绿灯
 */
@RestController
@RequestMapping("/trafficLight")
public class TrafficLightController {
    @Autowired
    TrafficLightService trafficLightService;
    @Autowired
    public Context pi4j;

    @GetMapping("/controlLight")
    public boolean controlLight(TrafficLightEnum light, boolean state) {
        trafficLightService.controlLightState(light, state);
//        pi4j.dout().create(23).setState(state);
        return true;
    }

    @GetMapping("/lightState")
    public TrafficLightState controlLight() {
        TrafficLightState trafficLight = trafficLightService.lightState();
        return trafficLight;
    }
}

package com.example.managerserver.server.service;

import com.example.managerserver.server.entity.TrafficLightEnum;
import com.example.managerserver.server.entity.TrafficLightOperator;
import com.example.managerserver.server.entity.TrafficLightState;
import com.pi4j.io.gpio.digital.DigitalOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrafficLightService {
    @Autowired
    public TrafficLightOperator trafficLightOperator;


    /**
     * 获取红绿灯状态
     *
     * @return
     */
    public TrafficLightState lightState() {
        var trafficLight = new TrafficLightState();
        trafficLight.setRstate(trafficLightOperator.lightInputR.state().isHigh());
        trafficLight.setYstate(trafficLightOperator.lightInputY.state().isHigh());
        trafficLight.setGstate(trafficLightOperator.lightInputG.state().isHigh());
        return trafficLight;
    }

    /**
     * 设置红绿灯状态
     *
     * @param b
     */
    public void controlLightState(TrafficLightEnum light, boolean b) {
        DigitalOutput lightOutput = null;
        System.out.println(light + "" + b);
        trafficLightOperator.lightOutputR.high();
        switch (light) {
            case R:
                lightOutput = trafficLightOperator.lightOutputR;
                break;
            case Y:
                lightOutput = trafficLightOperator.lightOutputY;
                break;
            case G:
                lightOutput = trafficLightOperator.lightOutputG;
                break;
        }
        if (lightOutput != null) {
            lightOutput.setState(b);
        }
    }

}

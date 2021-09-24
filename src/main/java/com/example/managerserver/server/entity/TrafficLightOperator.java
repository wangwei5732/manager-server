package com.example.managerserver.server.entity;

import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;

public class TrafficLightOperator {
    public DigitalOutput lightOutputR;
    public DigitalInput lightInputR;
    public DigitalOutput lightOutputY;
    public DigitalInput lightInputY;
    public DigitalOutput lightOutputG;
    public DigitalInput lightInputG;
}

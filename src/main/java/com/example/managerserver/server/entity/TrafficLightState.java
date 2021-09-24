package com.example.managerserver.server.entity;

import lombok.Data;

@Data
public class TrafficLightState {
    /**
     * 红灯状态
     */
    public boolean rstate;
    /**
     * 黄灯状态
     */
    public boolean ystate;
    /**
     * 绿灯状态
     */
    public boolean gstate;
}

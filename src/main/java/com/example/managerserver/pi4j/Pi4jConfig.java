package com.example.managerserver.pi4j;

import com.example.managerserver.server.entity.TrafficLightOperator;
import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.library.pigpio.PiGpio;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalInputProvider;
import com.pi4j.plugin.pigpio.provider.gpio.digital.PiGpioDigitalOutputProvider;
import com.pi4j.plugin.pigpio.provider.i2c.PiGpioI2CProvider;
import com.pi4j.plugin.pigpio.provider.pwm.PiGpioPwmProvider;
import com.pi4j.plugin.pigpio.provider.serial.PiGpioSerialProvider;
import com.pi4j.plugin.pigpio.provider.spi.PiGpioSpiProvider;
import com.pi4j.plugin.raspberrypi.platform.RaspberryPiPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pi4jConfig extends RaspberryPiPlatform {
    @Value("${pi4j.trafficLight.r}")
    public int R;
    @Value("${pi4j.trafficLight.y}")
    public int Y;
    @Value("${pi4j.trafficLight.g}")
    public int G;
    @Autowired
    public Context pi4j;
    @Autowired
    public TrafficLightOperator trafficLightOperator;

    /**
     * 获取pi4j上下文
     *
     * @return
     */
    @Bean
    public Context pi4j() {

//        // Build Pi4J context with this platform and PiGPIO providers
//        return Pi4J.newAutoContext();
        // Initialize PiGPIO
        final var piGpio = PiGpio.newNativeInstance();

        // Build Pi4J context with this platform and PiGPIO providers
        return Pi4J.newContextBuilder()
                .noAutoDetect()
                .add(new Pi4jConfig())
                .add(
                        PiGpioDigitalInputProvider.newInstance(piGpio),
                        PiGpioDigitalOutputProvider.newInstance(piGpio),
                        PiGpioPwmProvider.newInstance(piGpio),
                        PiGpioI2CProvider.newInstance(piGpio),
                        PiGpioSerialProvider.newInstance(piGpio),
                        PiGpioSpiProvider.newInstance(piGpio)
                )
                .build();
    }

    /**
     * 红绿灯操作bean
     *
     * @return
     */
    @Bean("trafficLightOperator")
    public TrafficLightOperator trafficLightOperator() {
        TrafficLightOperator trafficLightOperator = new TrafficLightOperator();

        trafficLightOperator.lightInputR = pi4j.din().create(R);
        trafficLightOperator.lightOutputR = pi4j.dout().create(R);
        trafficLightOperator.lightOutputY = pi4j.dout().create(Y, "lightOutputY", "lightOutputY");
        trafficLightOperator.lightInputY = pi4j.din().create(Y, "lightInputY", "lightInputY");
        trafficLightOperator.lightOutputG = pi4j.dout().create(G, "lightOutputG", "lightOutputG");
        trafficLightOperator.lightInputG = pi4j.din().create(G, "lightInputG", "lightInputG");
        return trafficLightOperator;
    }

    @Override
    protected String[] getProviders() {
        return new String[]{};
    }
}

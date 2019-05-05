package com.cloud.example.customBindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface DeviceSink {
    String INPUT = "input-device";

    @Input(INPUT)
    SubscribableChannel recieve();
}

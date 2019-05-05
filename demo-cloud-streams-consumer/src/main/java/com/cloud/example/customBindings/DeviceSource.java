package com.cloud.example.customBindings;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeviceSource {
    String OUTPUT = "output-device";

    @Output(OUTPUT)
    MessageChannel create();
}

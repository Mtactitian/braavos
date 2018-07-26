package com.braavos.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "braavos")
@Data
public class ReceiverProperties {
    private final List<String> receivers;
}

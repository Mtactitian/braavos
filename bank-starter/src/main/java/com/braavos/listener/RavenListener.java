package com.braavos.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RavenListener {

    private final List<String> receivers;

    @EventListener(classes = ContextRefreshedEvent.class)
    @Async
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        receivers.stream()
                .filter(s -> !s.isEmpty())
                .forEach(r -> log.info("Raven is send to  " + r));
    }
}

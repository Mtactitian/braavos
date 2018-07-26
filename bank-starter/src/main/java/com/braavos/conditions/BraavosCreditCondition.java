package com.braavos.conditions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class BraavosCreditCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();

        try {
            String[] bannedUsers = environment.getProperty("braavos.credit.banned-users", String[].class);
            if (bannedUsers != null && bannedUsers.length == 0) {
                return false;
            }

            Long amount = environment.getProperty("braavos.balance.amount", Long.class);
            return amount != null && amount >= 0;

        } catch (RuntimeException ex) {
            log.error("Wrong property format");
            return false;
        }
    }
}

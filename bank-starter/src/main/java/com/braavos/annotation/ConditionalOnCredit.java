package com.braavos.annotation;

import com.braavos.conditions.BraavosCreditCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Conditional(value = BraavosCreditCondition.class)
public @interface ConditionalOnCredit {
}

package com.braavos.configuration;

import com.braavos.annotation.ConditionalOnCredit;
import com.braavos.listener.RavenListener;
import com.braavos.service.CreditService;
import com.braavos.util.ReceiverProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(ReceiverProperties.class)
@ComponentScan("com.braavos.controller")
public class IronBankConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "braavos", name = "receivers")
    public RavenListener ravenListener(@Value("${braavos.receivers}") final List<String> receivers) {
        return new RavenListener(receivers);
    }

    @Bean
    @ConditionalOnCredit
    public CreditService creditService(@Value("${braavos.credit.banned-users}") final List<String> bannedUsers,
                                       @Value("${braavos.balance.amount}") final long balance) {
        return new CreditService(bannedUsers, balance);
    }

}

package com.tprm.ism.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IssueConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

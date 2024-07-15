package com.example.passwordchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class Settings{

        @Bean
        public PasswordStrengthChecker passwordStrengthChecker() {
            return new PasswordStrengthChecker();
        }

        @Bean
        public TimeToCrack timeToCrack() {
            return new TimeToCrack();
        }
    }


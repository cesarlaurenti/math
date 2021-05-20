package com.tenpo.math;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MathApplication {

    public static void main(String[] args) {
        SpringApplication.run(MathApplication.class, args);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public CommandLineRunner setupDefaultUser(CustomUserDetailsService service) {
        return args -> {
            service.registerUser(new AppUser(
                    "user", //username
                    "user", //password
                    Arrays.asList(new Role("USER"), new Role("ACTUATOR")),//roles
                    true//Active
            ));
        };
    }*/
}

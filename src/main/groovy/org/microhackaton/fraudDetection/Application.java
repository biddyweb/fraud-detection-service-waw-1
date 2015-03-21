package org.microhackaton.fraudDetection;

import com.ofg.infrastructure.environment.EnvironmentSetupVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

import static com.ofg.config.BasicProfiles.*;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class Application {

    static public void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new EnvironmentSetupVerifier(Arrays.asList(DEVELOPMENT, PRODUCTION, TEST)));
        application.run(args);
    }

}

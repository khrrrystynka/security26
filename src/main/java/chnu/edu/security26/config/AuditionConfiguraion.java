package chnu.edu.security26.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class AuditionConfiguraion
 * @since 06.10.2026 - 17.48
 */
@EnableMongoAuditing
@Configuration
public class AuditionConfiguraion {

    @Bean
    public AuditorAware<String> auditorAware() {return new AuditorAwarelmpl(); }
}

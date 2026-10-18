package chnu.edu.security26.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class AuditorAwarelmpl
 * @since 06.10.2026 - 17.45
 */
public class AuditorAwarelmpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //   return Optional.of(System.getProperty("user.name"));
        return Optional.of("Admin");
    }
}

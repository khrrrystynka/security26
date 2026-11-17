package chnu.edu.security26.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class AuthenticationResponse
 * @since 17.11.2026 - 14.12
 */
@Data
@Builder
public class AuthenticationResponse {
    private String token;
}
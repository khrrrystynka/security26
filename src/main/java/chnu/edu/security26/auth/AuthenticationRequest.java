package chnu.edu.security26.auth;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class AuthenticationRequest
 * @since 17.11.2026 - 14.12
 */
import lombok.Data;
import lombok.NonNull;
@Data
public class AuthenticationRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}

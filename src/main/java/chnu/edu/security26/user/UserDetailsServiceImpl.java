package chnu.edu.security26.user;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class UserDetailsServiceImpl
 * @since 17.11.2026 - 14.22
 */
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {

        User admin = User.builder()
                .id("1")
                .firstName("John")
                .lastName("Smith")
                .email("john@mail.com")
                .password(passwordEncoder.encode("admin"))
                .enabled(true)
                .accountLocked(false)
                .roles(List.of(Role.ADMIN))
                .build();

        User user = User.builder()
                .id("2")
                .firstName("Anna")
                .lastName("Brown")
                .email("user@mail.com")
                .password(passwordEncoder.encode("user"))
                .enabled(true)
                .accountLocked(false)
                .roles(List.of(Role.USER))
                .build();

        User superadmin = User.builder()
                .id("3")
                .firstName("Alex")
                .lastName("Green")
                .email("superadmin@mail.com")
                .password(passwordEncoder.encode("superadmin"))
                .enabled(true)
                .accountLocked(false)
                .roles(List.of(Role.SUPERADMIN))
                .build();

        repository.saveAll(List.of(admin, user, superadmin));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}

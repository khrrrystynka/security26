package chnu.edu.security26.user;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class UserRepository
 * @since 17.11.2026 - 14.22
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}

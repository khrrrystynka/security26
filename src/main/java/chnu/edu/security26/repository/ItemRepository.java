package chnu.edu.security26.repository;

import chnu.edu.security26.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class ItemRepository
 * @since 06.10.2026 - 17.11
 */

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
}

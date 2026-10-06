package chnu.edu.security26.service;

import chnu.edu.security26.model.Item;
import chnu.edu.security26.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PostConstruct
    void init() {
        itemRepository.deleteAll();
        itemRepository.save(new Item("1", "Cure Skin AHA Exfoliating Toner", "Chemical exfoliating toner to remove dead cells and brighten skin tone."));
        itemRepository.save(new Item("2", "Cure Skin Soothing Facial Serum", "Lightweight calming serum designed to reduce redness and irritation."));
        itemRepository.save(new Item("3", "Cure Skin Mineral Sunscreen SPF 50", "Protective daily sunscreen offering broad-spectrum UV defense."));
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItem(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
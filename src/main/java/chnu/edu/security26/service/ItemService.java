package chnu.edu.security26.service;

import chnu.edu.security26.model.Item;
import chnu.edu.security26.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemService {

    private final ItemRepository itemRepository;

    private List<Item> items = new ArrayList<>();

    {
        items.add(new Item("1", "name1", "description1"));
        items.add(new Item("2", "name2", "description2"));
        items.add(new Item("3", "name3", "description3"));
    }

    @PostConstruct
    void init(){
        itemRepository.deleteAll();
        items.forEach(this::createItem);

    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public Item getItem(String id){
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Item item){
        Item existing = itemRepository.findById(item.getId()).orElse(null);
        if (existing != null) {
            existing.setName(item.getName());
            existing.setDescription(item.getDescription());
            return itemRepository.save(existing);
        }
        return null;
    }

    public void deleteItem(String id){
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            username = "unknown";
        }
        username = authentication.getName();
        Item item = this.getItem(id);
        log.info(item.toString() + " was deleted by " + username);
        itemRepository.deleteById(id);
    }
}
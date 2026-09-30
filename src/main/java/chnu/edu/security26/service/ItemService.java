package chnu.edu.security26.service;

import chnu.edu.security26.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private List<Item> items = new ArrayList<>();


    {
        items.add(new Item("1", "Cure Skin AHA Exfoliating Toner","Chemical exfoliating toner to remove dead cells and brighten skin tone."));
        items.add(new Item("2", "Cure Skin Soothing Facial Serum","Lightweight calming serum designed to reduce redness and irritation."));
        items.add(new Item("3", "Cure Skin Mineral Sunscreen SPF 50","Protective daily sunscreen offering broad-spectrum UV defense."));
    }

    public List<Item> getAllItems() {
        return items;
    }
    public Item createItem(Item item) {
        items.add(item);
        return item;
    }

    public Item getItem(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Item updateItem(Item item) {
        Item oldItem = getItem(item.getId());
        items.remove(oldItem);
        items.add(item);
        return oldItem;
    }

    public void deleteItem(String id) {
        Item item = getItem(id);
        items.remove(getItem(id));
    }
}

package chnu.edu.security26.controller;

import chnu.edu.security26.model.Item;
import chnu.edu.security26.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author user
 * @version 1.0.0
 * @project security26
 * @class ItemControler
 * @since 01.10.2026 - 00.13
 */

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemControler {
    private final ItemService itemService;

    @GetMapping
    public List<Item> getAll(String id) {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable String id) {
        return itemService.getItem(id);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
    }

    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }

    @GetMapping("/hello/user")
    public String hello() { return "Hello!, user"; }

    @GetMapping("/hello/man")
    public String helloMan() { return "Hello, man!"; }





}

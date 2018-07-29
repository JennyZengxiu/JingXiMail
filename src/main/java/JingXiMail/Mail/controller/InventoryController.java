package JingXiMail.Mail.controller;

import JingXiMail.Mail.entity.Inventory;
import JingXiMail.Mail.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @PostMapping
    public void saveInventory(@ModelAttribute Long productId){
        inventoryRepository.saveByProductId(productId);
    }

    @GetMapping
    public List<Inventory> GetInventories(){
        return inventoryRepository.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id,@RequestBody Inventory inventory){
        inventoryRepository.updateCountById(id, inventory.getCount());
    }
}

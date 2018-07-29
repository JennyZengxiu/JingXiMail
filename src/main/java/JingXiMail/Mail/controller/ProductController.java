package JingXiMail.Mail.controller;

import JingXiMail.Mail.entity.Product;
import JingXiMail.Mail.repository.InventoryRepository;
import JingXiMail.Mail.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryRepository inventoryRepository;
    
    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        Long id = productRepository.save(product).getId();
        inventoryRepository.saveByProductId(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create("http://192.168.1.102:8083/products/" + id));
        return new ResponseEntity<Product>(productRepository.findProductById(id),responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updataProduct(@PathVariable Long id,@RequestBody Product product){
        productRepository.updateById(id, product.getName(), product.getDescription(), product.getPrice());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id){
        return productRepository.findProductById(id);
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        if (!name.isEmpty() && !description.isEmpty()) {
            return productRepository.findByNameAndDescriptionContaining(name, description);
        } else if (!name.isEmpty()) {
            return productRepository.findByName(name);
        } else {
            return productRepository.findAll();
        }
    }
    /*@GetMapping
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping
    public List<Product> getProductByName(@RequestParam(value = "name") String name){
        return productRepository.findByName(name);
    }

    @GetMapping
    public List<Product> getProductByDescriptionAndName(@RequestParam(value = "name") String name, @RequestParam(value = "description") String description) {
        return productRepository.findByNameAndDescriptionContaining(name, description);
    }*/
}

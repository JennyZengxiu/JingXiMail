package JingXiMail.Mail.repository;

import JingXiMail.Mail.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product save(Product product);

    @Modifying
    @Transactional
    @Query("update Product u set u.name = ?2, u.description = ?3, u.price = ?4 where u.id = ?1")
    int updateById(Long id, String name, String description, Integer price);

    Product findProductById(Long id);

    @Override
    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByNameAndDescriptionContaining(String name, String description);
}

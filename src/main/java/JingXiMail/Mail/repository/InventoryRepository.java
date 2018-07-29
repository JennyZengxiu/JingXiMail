package JingXiMail.Mail.repository;

import JingXiMail.Mail.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into Inventory(id,count,lockedCount) values(?1,0,0)", nativeQuery = true)
    int saveByProductId(Long id);

    @Modifying
    @Transactional
    @Query("update Inventory u set u.count = u.count + ?2 where u.id = ?1")
    int updateCountById(Long productId, Integer count);

    @Modifying
    @Transactional
    @Query("update Inventory u set u.lockedCount = u.lockedCount + ?2 where u.id = ?1")
    int updateLockedCount(Long productId, Integer lockedCount);

    Inventory findInventoryById(Long id);

    @Override
    List<Inventory> findAll();
}

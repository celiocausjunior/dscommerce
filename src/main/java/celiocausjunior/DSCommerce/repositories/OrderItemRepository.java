package celiocausjunior.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import celiocausjunior.DSCommerce.models.OrderItem;
import celiocausjunior.DSCommerce.models.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
    
}

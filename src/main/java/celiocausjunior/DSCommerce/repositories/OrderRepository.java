package celiocausjunior.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import celiocausjunior.DSCommerce.models.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Long>{
    
}

package celiocausjunior.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import celiocausjunior.DSCommerce.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    
}

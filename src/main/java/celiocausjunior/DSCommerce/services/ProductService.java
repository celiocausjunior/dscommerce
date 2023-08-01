package celiocausjunior.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import celiocausjunior.DSCommerce.models.dtos.ProductDTO;
import celiocausjunior.DSCommerce.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return productRepository.findById(id).map(product -> new ProductDTO(product)).orElse(null);
    }


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<ProductDTO> page = productRepository.findAll(pageable).map(product -> new ProductDTO(product));
        return page;
    }
}

package celiocausjunior.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import celiocausjunior.DSCommerce.models.ProductModel;
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

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        ProductModel product = new ProductModel();
        copyDtoToEntity(productDTO, product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {

        ProductModel product = productRepository.getReferenceById(id);
        copyDtoToEntity(productDTO, product);
        product = productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public void delete(Long id) {
        ProductModel product = productRepository.getReferenceById(id);
        if (product != null) {
            productRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Product not found");
        }
    }

    private void copyDtoToEntity(ProductDTO productDTO, ProductModel product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }

}

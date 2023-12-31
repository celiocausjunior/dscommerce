package celiocausjunior.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import celiocausjunior.DSCommerce.models.CategoryModel;
import celiocausjunior.DSCommerce.models.ProductModel;
import celiocausjunior.DSCommerce.models.dtos.CategoryDTO;
import celiocausjunior.DSCommerce.models.dtos.ProductDTO;
import celiocausjunior.DSCommerce.models.dtos.ProductMinDTO;
import celiocausjunior.DSCommerce.repositories.ProductRepository;
import celiocausjunior.DSCommerce.services.exceptions.DataIntegrityException;
import celiocausjunior.DSCommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return productRepository.findById(id).map(product -> new ProductDTO(product))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        Page<ProductModel> page = productRepository.findByNameContainingIgnoreCase(name, pageable);
        return page.map(product -> new ProductMinDTO(product));
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

        try {
            ProductModel product = productRepository.getReferenceById(id);
            copyDtoToEntity(productDTO, product);
            product = productRepository.save(product);
            return new ProductDTO(product);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO productDTO, ProductModel product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());

        product.getCategories().clear();
        for (CategoryDTO catDTO : productDTO.getCategories()) {
            CategoryModel cat = new CategoryModel();
            cat.setId(catDTO.getId());

            product.getCategories().add(cat);
        }

    }

}

package celiocausjunior.DSCommerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import celiocausjunior.DSCommerce.models.dtos.ProductDTO;
import celiocausjunior.DSCommerce.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO productDTO) {
        return productService.insert(productDTO);
    }
}
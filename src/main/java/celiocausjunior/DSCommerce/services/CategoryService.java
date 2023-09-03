package celiocausjunior.DSCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import celiocausjunior.DSCommerce.models.CategoryModel;
import celiocausjunior.DSCommerce.models.dtos.CategoryDTO;
import celiocausjunior.DSCommerce.repositories.CategoryRepository;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<CategoryModel> category = categoryRepository.findAll();
        return category.stream().map(x -> new CategoryDTO(x)).toList();
    }
}

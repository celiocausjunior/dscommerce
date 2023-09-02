package celiocausjunior.DSCommerce.models.dtos;

import java.util.ArrayList;
import java.util.List;

import celiocausjunior.DSCommerce.models.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
    private Long id;

    @NotBlank(message = "O campo nome não pode ser vazio")
    @Size(min = 3, max = 80, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String name;
    
    @Size(min=10, message = "O campo descrição deve ter no mínimo 10 caracteres")
    private String description;
    @Positive(message = "O preço deve ser um valor positivo")
    private Double price;
    private String imgUrl;

    @NotEmpty(message = "O produto deve ter pelo menos uma categoria")
    private List<CategoryDTO> categories = new ArrayList<>();



    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(ProductModel entity){
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        entity.getCategories().forEach(category -> this.categories.add(new CategoryDTO(category)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
    
}

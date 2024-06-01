package com.smitjdev.demospringbootone.dto;


import com.smitjdev.demospringbootone.model.Category;
import com.smitjdev.demospringbootone.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    //DTO: Data Transfer Object
    //fakestore api: having product is in different format
    //compared to our product class

    //so we have to get those values from api, and match it to the product class
    //in this case DTO's comes into picture

    //so in this dto class
    //all fields would be as same as fields in product of fakestore api

    private Long id;
    private String title;
    private Double price;       //why double used by dheeraj?
    private String category;
    private String description;
    private String image;

    public Product toProduct()
    {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category cat = new Category();
        cat.setTitle(category);

        product.setCategory(cat);

        return product;
    }
}

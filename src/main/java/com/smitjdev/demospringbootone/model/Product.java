package com.smitjdev.demospringbootone.model;

import com.smitjdev.demospringbootone.dto.FakeStoreProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;

    public FakeStoreProductDTO toFakeStoreProductDto()
    {
        FakeStoreProductDTO fs = new FakeStoreProductDTO();

        fs.setTitle(title);
        fs.setPrice(price);
        fs.setCategory(category.getTitle());
        fs.setDescription(description);
        fs.setImage(imageUrl);

        return fs;
    }
}

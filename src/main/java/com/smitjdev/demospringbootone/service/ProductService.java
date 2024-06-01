package com.smitjdev.demospringbootone.service;

//public class ProductService {
//}

//currently we are learning and doing our work with fakestore api
//as currently we do not have database

//so for now to handle all the requests of productController
//here we have to provide those services

//but today if we do with fakestore and implement things
//two days after, if we want to do with database
//then we would be requiring many changes to be made

//to solve this issue
//instead of making ProductService as a class
//we would be having ProductService as an "INTERFACE"

import com.smitjdev.demospringbootone.model.Category;
import com.smitjdev.demospringbootone.model.Product;

import java.util.List;

public interface ProductService
{
    //and as we know: in case of interface
    //we only have method signatures, and not implementations

    //implementation of all those methods would be made, in a class which uses this interface

    //now as defined in ProductController, for now we are having 3 methods/functions

    //getAllProducts, getSpecificProduct, createProduct

    //define those methods here, just signatures, and NOT implementation

    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    List<Category> getAllCategories();

    List<Product> getInCategory(String categoryName);

    //updating a product via patch
    //so in patch: we just need to say id of the product, and
    //to send just only the fields which we need to update on that product
    //as opposed to put (in put: all the fields needs to sent irrelevant of whether we want to update 1 field or
    //all fields

    Product updateProductViaPatch(Long productId, Product product);

    void updateProductViaPut(Long productId, Product product);


}
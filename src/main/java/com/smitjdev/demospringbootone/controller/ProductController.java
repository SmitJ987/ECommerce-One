package com.smitjdev.demospringbootone.controller;

import com.smitjdev.demospringbootone.model.Category;
import com.smitjdev.demospringbootone.model.Product;
import com.smitjdev.demospringbootone.service.FakeStoreProductService;
import com.smitjdev.demospringbootone.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //this annotation: @RestController: will tell springboot that this class is having some endpoints
public class ProductController {

    //POST request
    //api name should be around resources or model name
    //i.e. product

    //and this is request is for creating a product

    //also to pass all data of product like name, id, category and all
    //we have to pass that data through
    //request body
    /*
    {
        "id":"airpods123",
        "title":"airpods",
        "category":"accessory",
        "price":25000
    }
     */

    //now we have to link the below defined API's in ProductController
    //with the functions defined in the FakeStoreProductService class

    //way 1
    //ProductService productService = new FakeStoreProductService();
    //but we need not do it ourselves, let springboot do it
    //which is called: dependency Injection, and spring way it is: Inversion of Control
    //Inversion of control: Iam not going to create and init the dependency of
    //FakeStorePS, instead I will tell springBoot to do it, so it is IOC

    //with this way, we just declared productService object here
    //and we will write @Service annotation in the FakeStoreProductService class
    //to tell jvm, that for this below defined productService, it will initialise object of
    //FakeStoreProductService, and when hitting these API endpoints,
    //inside these endpoint definition we are calling productService.method
    //remember: here we just call product service interface methods
    //and whatever is assigned to that interface
    //like fakestorePS or DBStorePS, that class method would running...


    private ProductService productService;

    //now this below constructor
    //spring would automatically take FakeStorePS class, as we have written @Service on that class
    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }


    //for post request: we need @PostMapping annotation
    @PostMapping(value = "/product")
    public Product createProduct(@RequestBody Product product)
    {
        //whenever someone hits post request on "/product"
        //please execute this method : "createProduct"
//        productService.createProduct();

        Product postRequestResponse = productService.createProduct(product);

        return postRequestResponse;
    }



    //method to fetch a particular product
    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable("id") Long productId)
    {
//        return id;

        //here we are telling to spring that
        //if someone hits 'GET' request on "products/{id}"
        //then execute this method...

        Product currentProduct = productService.getSingleProduct(productId);
        return currentProduct;
    }


//    @GetMapping("/products")
//    public void getAllProducts()
//    {
//        productService.getAllProducts();
//    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }


    @GetMapping("/categories")
    public List<Category> getAllCategories()
    {
        List<Category> allCategories = productService.getAllCategories();
        return allCategories;
    }

    //@GetMapping("/products/{category}")
//    @GetMapping("getProductsByCategory/{category}")
    //but we should not use verbs in api end points: so cant name it as getProductsBycategory
    //remove get
    //name it as maybe: productsByCategory or productsInCategory
    @GetMapping("productsByCategory/{category}")
    public List<Product> getInCategory(@PathVariable("category") String category)
    {
        List<Product> productList = productService.getInCategory(category);
        return productList;
    }

    //patch  me pass karenge kya vo baat main hai
    //adha product pass karenge json me shayad:  sirf kuch fields ke sath maybe
    @PatchMapping("product/{id}")
    public Product updateProductViaPatch(@PathVariable("id") Long id, @RequestBody Product product)
    {
        Product updatedProduct = productService.updateProductViaPatch(id,product);
        return updatedProduct;
    }

    @PutMapping("product/{id}")
    public void updateProductViaPut(@PathVariable("id") Long id, @RequestBody Product product)
    {
        productService.updateProductViaPut(id,product);
    }

}

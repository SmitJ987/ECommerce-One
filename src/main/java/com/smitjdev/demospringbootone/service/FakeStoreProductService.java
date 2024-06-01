package com.smitjdev.demospringbootone.service;

import com.smitjdev.demospringbootone.dto.FakeStoreProductDTO;
import com.smitjdev.demospringbootone.model.Category;
import com.smitjdev.demospringbootone.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    //injecting the rest template
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId)
    {
        //return new Product();

        //here we will call fakestore product service: in order to fetch a particular product

        //then what we get we will assign it to fakestoreProductDto object

        //and then we will make object of Product

        //and map the FakeStoreProductDto to Product object
        //and return the Product object.

        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+productId,FakeStoreProductDTO.class);

        Product product = fakeStoreProductDTO.toProduct();

        return product;
    }


    @Override
    public List<Product> getAllProducts()
    {
        //fakestore response will give list of FSPDTO type
//        List<FakeStoreProductDTO> fsList = restTemplate.getForObject("https://fakestoreapi.com/products",List.class);
        //above way is not working

        //two ways are there from baeldung website
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("http://fakestoreapi.com/products",FakeStoreProductDTO[].class);

        //now convert this list to list of Product type
        //and return that

        List<Product> productList = new ArrayList<Product>();

        for(FakeStoreProductDTO fsProductDto : response.getBody())
        {
            Product thisProduct = fsProductDto.toProduct();
            productList.add(thisProduct);
        }

        return productList;
    }

    @Override
    public Product createProduct(Product product)
    {
        //here we have to contact 3rd party API: fakestore
        //now we do not have to reinvent the wheel
        //and do all the nuances end to end by ourselves

        //springboot provides us "Rest Template"
        //we will use that to contact fakestore
        //and get response

        //also fakestore expects data to be according to their model
        //as defined in fakestoreProductDTO

        //and on our side we have class Product

        //in create product we are receiving Product object from controller

        //in here service: we have to convert it to FSPDTO (fakeStoreProductDTO)
        //and send hit the fs
        //then fs returns response in FSPDTO only

        //again we have to convert that to Product object and then return that product object

        //step 1: convert from product to FSPDTO object
        FakeStoreProductDTO fs = product.toFakeStoreProductDto();

        //step 2: with help of rest template: call fakestore
        //3 args: url, fsPDTO object, class definition: in this case FSPDTO

        //and the returns us also FSPDTO

        FakeStoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products",fs,FakeStoreProductDTO.class);
        //step 3: response we got is in FSPDTO type
        //convert that again to Product object
        //and return it

        Product p = response.toProduct();
        return p;
    }

    @Override
    public List<Category> getAllCategories()
    {
        ResponseEntity<String[]> response = restTemplate.getForEntity("http://fakestoreapi.com/products/categories", String[].class);

        List<Category> catList = new ArrayList<Category>();

        for(String title : response.getBody())
        {
            Category cat = new Category();
            cat.setTitle((title));

            catList.add(cat);
        }

        return catList;
    }

    @Override
    public List<Product> getInCategory(String categoryName)
    {
        //way 1:
        //don't again write all code with rest to fetch all products and then doing things
        // instead use our earlier made method of getAllProducts
        List<Product> allProducts =  getAllProducts();
        List<Product> requiredProducts = new ArrayList<>();

        for(Product p : allProducts)
        {
            if(p.getCategory().getTitle().equals(categoryName))
            {
                requiredProducts.add(p);
            }
        }



        //way 2:
        //fakestore is having separate api for category related search using that only
//        ResponseEntity<FakeStoreProductDTO[]>  getRequestResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/category/"+categoryName, FakeStoreProductDTO[].class);
//
//        List<Product> requiredProducts = new ArrayList<>();
//
//        for(FakeStoreProductDTO fspdto : getRequestResponse.getBody())
//        {
//            Product product = fspdto.toProduct();
//            requiredProducts.add(product);
//        }

        //both the ways are working fine.
        return requiredProducts;
    }

    @Override
    public Product updateProductViaPatch(Long productId, Product product)
    {
        //fakestore works in terms on fakestoreProductDto

        //step 1:
        //convert Product to fakestoreDto
        //our product which is coming here in arg: will be containing newer fields to be updated
        FakeStoreProductDTO newDetails = product.toFakeStoreProductDto();

        //now we are having productId also
        //we will tell fakestore
        //to go on that id
        //and there: keep remaining fields as it is, and CHANGE the given fields only

        //like in this request if someone wanted to change only title of the product

        //then rest all fields would not be passed in the body from postman
        //so those fields would be default values only
        //so only title will be updated for the product with given productId
        //and rest all fields would be same only as old

        FakeStoreProductDTO patchRequestResponse = restTemplate.patchForObject("https://fakestoreapi.com/products/"+productId,newDetails,FakeStoreProductDTO.class);

//        System.out.println((patchRequestResponse==null)+" ask patchResp null");
        Product updatedProduct = patchRequestResponse.toProduct();
        return updatedProduct;
    }

    @Override
    public void updateProductViaPut(Long productId, Product product)
    {
        //step 1: product to fakestoreProductDto conversion
        FakeStoreProductDTO fakeStoreProductDto = product.toFakeStoreProductDto();

        restTemplate.put("https://fakestoreapi.com/products/"+productId, fakeStoreProductDto);
    }


}

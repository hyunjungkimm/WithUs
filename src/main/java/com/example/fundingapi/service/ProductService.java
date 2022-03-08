package com.example.fundingapi.service;

import com.example.fundingapi.domain.Product;
import com.example.fundingapi.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(ProductService.class);

    private final String FILE_INIT_PRODUCT = "config/init_product.json";

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> productList = new ArrayList<>();
        productList = getInitProductsFromFile();
        if(productList == null || productList.size() == 0){
            throw new IllegalArgumentException("no data in init data file");
        }

        for(Product product : productList){
            productRepository.save(product);
        }
    }

    private List<Product> getInitProductsFromFile() throws IOException {
        List<Product> productList = new ArrayList<>();
        try(InputStream is = getStreamFromResource(FILE_INIT_PRODUCT)){
            JsonNode productNode = getProductNode(is);
            productList = getProductListFromNode(productNode);
        }
        return productList;
    }


    private InputStream getStreamFromResource(String fileLocation) {
        ClassLoader classLoader = ProductService.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileLocation);

        if(inputStream == null){
            throw new IllegalArgumentException("init data file \"" + fileLocation + "\" not found.");
        }else{
            return inputStream;
        }
    }

    private JsonNode getProductNode(InputStream is) {
        ObjectMapper objcetMapper = new ObjectMapper();
        JsonNode productNode = null;
        try(DataInputStream dis = new DataInputStream(is)){
            productNode = objcetMapper.readTree(dis).path("product");
        }catch (IOException io){
            io.printStackTrace();
        }
        return productNode;
    }

    private List<Product> getProductListFromNode(JsonNode productNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> productList = objectMapper.convertValue(productNode, new TypeReference<List<Product>>() {
        }).stream().collect(Collectors.toList());
        return productList;
    }



}

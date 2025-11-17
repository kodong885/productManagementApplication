package koDong.testSpringBoot.application;


import koDong.testSpringBoot.domain.Product;
import koDong.testSpringBoot.infrastructure.DatabaseProductRepository;
import koDong.testSpringBoot.infrastructure.ListProductRepository;
import koDong.testSpringBoot.presentation.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class SimpleService {

    private DatabaseProductRepository databaseProductRepository;
    private ValidationService validationService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public SimpleService(
            DatabaseProductRepository databaseProductRepository,
            ValidationService validationService
    ) {
        this.databaseProductRepository = databaseProductRepository;
        this.validationService = validationService;
    }

    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        validationService.checkValid(product);

        Product savedProduct = databaseProductRepository.add(product);
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        return savedProductDto;
    }

    public ProductDto findProductById(Long id) {
        Product foundProduct = databaseProductRepository.findById(id);
        ProductDto foundProductDto = modelMapper.map(foundProduct, ProductDto.class);
        return foundProductDto;
    }

    public List<ProductDto> getAllProduct() {
        List<Product> productList = databaseProductRepository.findAll();
        List<ProductDto> productDtoList = productList.stream()
                .map(value -> modelMapper.map(value, ProductDto.class))
                .toList();
        return productDtoList;
    }

    public List<ProductDto> findProductsByName(String name) {
        List<Product> productsFoundList = databaseProductRepository.findByNameContaining(name);
        List<ProductDto> productDtoFoundList = productsFoundList.stream()
                .map(value -> modelMapper.map(value, ProductDto.class))
                .toList();
        return productDtoFoundList;
    }

    public void deleteProduct(Long id) {
        databaseProductRepository.delete(id);
    }

    public ProductDto updateProduct(ProductDto productDtoForUpdate) {

        Product productForUpdate = modelMapper.map(productDtoForUpdate, Product.class);
        Product productUpdated = databaseProductRepository.update(productForUpdate);
        ProductDto productDtoUpdated = modelMapper.map(productUpdated, ProductDto.class);
        return productDtoUpdated;
    }

}

package koDong.productManagementApplication.application;


import koDong.productManagementApplication.domain.Product;
import koDong.productManagementApplication.infrastructure.ListProductRepository;
import koDong.productManagementApplication.presentation.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {

    private ListProductRepository listProductRepository;
    private ModelMapper modelMapper;
    private ValidationService validationService;

    @Autowired
    SimpleProductService(
            ListProductRepository listProductRepository,
            ModelMapper modelMapper,
            ValidationService validationService
    ) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
        this.validationService = validationService;
    }


    public ProductDTO add(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        validationService.checkValid(product);

        Product savedProduct = listProductRepository.add(product);
        ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);
        return savedProductDTO;

    }

    public ProductDTO findById(Long id) {
        Product product = listProductRepository.findById(id);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    public List<ProductDTO> findAll() {
        List<Product> products = listProductRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        return productDTOS;
    }

    public List<ProductDTO> findByNameContaining(String name) {
        List<Product> products = listProductRepository.findByNameContaining(name);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        return productDTOS;
    }

    public ProductDTO update(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product updatedProduct = listProductRepository.update(product);
        ProductDTO updatedProductDTO = modelMapper.map(updatedProduct, ProductDTO.class);
        return updatedProductDTO;

    }

    public void delete(Long id) {
        listProductRepository.delete(id);
    }


}

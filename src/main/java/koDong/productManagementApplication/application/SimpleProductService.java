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

    @Autowired
    SimpleProductService(
            ListProductRepository listProductRepository,
            ModelMapper modelMapper
    ) {
        this.listProductRepository = listProductRepository;
        this.modelMapper = modelMapper;
    }


    public ProductDTO add(ProductDTO productDTO) {
        // 1. ProductDto를 Product로 변환하는 코드;
        // +) DTO는 "표현", "응용"계층에만 존재, 이상은 나아가지 않음!!!!ㅣ;
        Product product = modelMapper.map(productDTO, Product.class);
        // 2. 레포지토리를 호출하는 코드;
        Product savedProduct = listProductRepository.add(product);
        // 3. Product를 ProductDTO로 변환하는 코드;
        ProductDTO savedProductDTO = modelMapper.map(savedProduct, ProductDTO.class);
        // 4. DTO를 반환하는 코드;
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

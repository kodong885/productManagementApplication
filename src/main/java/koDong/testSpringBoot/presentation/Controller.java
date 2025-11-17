package koDong.testSpringBoot.presentation;

import jakarta.validation.Valid;
import koDong.testSpringBoot.application.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private SimpleService simpleService;

    @Autowired
    public Controller(SimpleService service) {
        this.simpleService = service;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDto createProduct(
            @Valid @RequestBody ProductDto productDto
    ) {
        return simpleService.addProduct(productDto);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDto findProductById(
            @PathVariable Long id
    ) {
        return simpleService.findProductById(id);
    }

    @RequestMapping(value = "/products/all", method = RequestMethod.GET)
    public List<ProductDto> getAllProduct() {
        return simpleService.getAllProduct();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> searchProductsByName(
            @RequestParam String name
    ) {
        // name에 전혀 없는 값을 입력하였을 때의 경우, 빈 리스트를 그대로 반환함...
        return simpleService.findProductsByName(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(
            @PathVariable Long id
    ) {
        simpleService.deleteProduct(id);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDto updateProduct(
            @RequestBody ProductDto productDto,
            @PathVariable Long id
    ) {
        productDto.setId(id);
        return simpleService.updateProduct(productDto);
    }


}

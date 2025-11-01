package koDong.productManagementApplication.presentation;

import jakarta.validation.Valid;
import koDong.productManagementApplication.application.SimpleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated // 이게 필요없다고 하는데, 왜?????
@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    @Autowired
    ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(
            @Valid @RequestBody ProductDTO productDTO
    ) {
        return simpleProductService.add(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDTO findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> findProducts(@RequestParam(required = false) String name) {
        if (name == null || name.isBlank()) {
            // name이 없으면 전체 상품 조회
            return simpleProductService.findAll();
        } else {
            // name이 있으면 이름 포함 검색
            return simpleProductService.findByNameContaining(name);
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        productDTO.setId(id);
        return simpleProductService.update(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }

}



package koDong.productManagementApplication.presentation;

import koDong.productManagementApplication.application.SimpleProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    private SimpleProductService simpleProductService;

    @Autowired
    ProductController(SimpleProductService simpleProductService) {
        this.simpleProductService = simpleProductService;
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDTO createProduct(
            @RequestBody ProductDTO productDTO
    ) {
        return simpleProductService.add(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDTO findProductById(@PathVariable Long id) {
        return simpleProductService.findById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> findAllProduct() {
        return simpleProductService.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDTO> findProducts(
            @RequestParam(required = false) String name
            // @RequestParam은 기본적으로 파라미터를 필수로 받도록되어있음
            // → required = false;
    ) {
        if (null == name) {
            return simpleProductService.findAll(); // 이건 뭐하는 코드지....??
        } else {
            return simpleProductService.findByNameContaining(name);
        }

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO
    ) {
        productDTO.setId(id);
        // 이거 왜 씀? ( name, price, amount를 받아 productDTO로 받아온 후, 해당 DTO의 id를 세팅함.
        // 그리고는 service에 넘겨줌;
        return simpleProductService.update(productDTO);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        simpleProductService.delete(id);
    }

}

package koDong.testSpringBoot.infrastructure;


import koDong.testSpringBoot.domain.EntityNotFoundException;
import koDong.testSpringBoot.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRepository {

    private List<Product> productList = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);

    public Product addProduct(Product product) {
        product.setId(sequence.getAndAdd(1L));
        productList.add(product);
        return product;
    }

    public Product findProductById(Long id) {
        Product foundProduct = productList.stream()
                .filter(product -> product.isSameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product 못찾음 ㅋ"));  // ★★★ 커스텀 에외 처리!!!
        // 에러가 남! → ConstraintViolationException(?) 예외 처리해야함!! ( by 커스텀 예외..? )
        return foundProduct;
    }

    public List<Product> getALlProduct() {
        return productList;
    }

    public List<Product> findProductsByName(String name) {
        List<Product> productsFound = productList.stream()
                .filter(product -> product.isNameContaining(name))
                .toList();
        return productsFound;
    }

    public Product deleteProduct(Long id) {
        Product productForDelete = this.findProductById(id);
        productList.remove(productForDelete);
        return productForDelete;
    }

    public Product updatedProduct(Product productForUpdate) {
        Integer indexForUpdate = productList.indexOf(productForUpdate);
        productList.set(indexForUpdate, productForUpdate);
        return productForUpdate;
    }

    // 리스트에서 컨트롤러로 넘겨저 오는 예외 처리해야함!!!!; ★★★ and 멱등성.....?
    // → ConstraintViolationException 공부하기!!


}



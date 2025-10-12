package koDong.productManagementApplication.infrastructure;

import koDong.productManagementApplication.domain.EntityNotFoundException;
import koDong.productManagementApplication.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class ListProductRepository {

    private List<Product> products = new CopyOnWriteArrayList<>();
    private AtomicLong sequence = new AtomicLong(1L);


    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product; // 왜 반환하지...???
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product를 찾지 못했습니다!"));
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        // indexOf : 리스트(products)의 요소 중, 매개변수로 받은 인스턴스와 동일한 인스턴스의 index를 반환함;
        products.set(indexToModify, product);
        return product;
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }




}


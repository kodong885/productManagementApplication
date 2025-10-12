package koDong.productManagementApplication.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Product {
    private Long id;

    @Size(min = 1, max = 100)
    private String name;

    @Max(1_000_000)
    @Min(0)
    private Integer price;

    @Max(9_999)
    @Min(0)
    private Integer amount;


    public Boolean sameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean containsName(String name) {
        return this.name.contains(name); // 어떤 값이 포함되어있는지 확인!;
    }


    @Override // id만 같으면 같은 인스턴스라고 인식하기 위해서 오버라이딩 함!!!!;
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id);

    }

    // ★ Product는 HTTP 응답을 주는데 사용하지 않으므로(DTO가 이 역할 수행!), getter 는 제거할 수 있다!!!;
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(Integer price) {
        this.price = price;
    }


    public void setAmount(Integer amount) {
        this.amount = amount;
    }


}

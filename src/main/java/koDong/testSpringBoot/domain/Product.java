package koDong.testSpringBoot.domain;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
public class Product {

    private Long id;

    @Getter
    @Size(min = 1, max = 100)
    private String name;

    @Getter
    @Max(1_000_000)
    @Min(0)
    private Integer price;

    @Getter
    @Max(9_999)
    @Min(0)
    private Integer amount;

    public Boolean isSameId(Long id) {
        return this.id.equals(id);
    }

    public Boolean isNameContaining(String name) {
        return this.name.contains(name); // 여기서 왜 에러가 나는거지...?
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Product) {
            return this.id.equals(((Product) object).id);
        } else {
            return this == object;
        }
    }


}

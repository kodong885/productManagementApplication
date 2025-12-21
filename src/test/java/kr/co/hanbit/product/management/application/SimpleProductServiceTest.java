package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.EntityNotFoundException;
import kr.co.hanbit.product.management.presentation.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // "스프링 컨테이너가 뜨는 통합 테스트를 위해 사용하는 애너테이션..... 뭔 소리야!!;
@ActiveProfiles("prod") // 테스트 코드에 사용할 ProfileDMF 지정함;
class SimpleProductServiceTest {

    @Autowired // Test코드에서는 애플리케이션에서와 달리(=생성자 주입), 필드 주입을 해도 상관 X;
    SimpleProductService simpleProductService;

    @Test // @Test가 붙은 해당 메서드는 테스트 코드라는 것을 의미함;
    @DisplayName("상품을 추가한 후 id로 조회하면 해당 상품이 조회되어야 한다.") // 해당 테스트 코드의 이름 지정(for 테스트 코드의 사용목적 정보 표시)
    void productAddAndFindByIdTest() {
        ProductDto productDto = new ProductDto("연필", 300, 20);

        ProductDto savedProductDto = simpleProductService.add(productDto);
        Long savedProductId = savedProductDto.getId();

        ProductDto foundProductDto = simpleProductService.findById(savedProductId);

//        아래와 같이, 단순히 true or false로만 테스트 코드를 판단하면,
//        코드가 잘못 수정이 되었다는 사실을 "테스트 코드의 로그"를 "직접"확인하여 판단해야하는 문제가 생긴다.
//        ( 그래서, "Assertion을" 활용하면 테스트 코드가 실패해야 되는 상황에서 실패했다고 알려줄 수 있다. )
//        System.out.println(savedProductDto.getId() == foundProductDto.getId());
//        System.out.println(savedProductDto.getName() == foundProductDto.getName());
//        System.out.println(savedProductDto.getPrice() == foundProductDto.getPrice());
//        System.out.println(savedProductDto.getPrice() == foundProductDto.getPrice());

        assertTrue(savedProductDto.getId() == foundProductDto.getId());
        assertTrue(savedProductDto.getName() == foundProductDto.getName());
        assertTrue(savedProductDto.getPrice() == foundProductDto.getPrice());
        assertTrue(savedProductDto.getPrice() == foundProductDto.getPrice());

    }

    @Test
    @DisplayName("존재하지 않는 상품 id로 조회하면 EntityNotFoundException이 발생해야한다.")
    void findProductNotExistIdTest() {
        Long notExistId = -1L;

        assertThrows(
                EntityNotFoundException.class,
                () -> simpleProductService.findById(notExistId)
        );

    }


}


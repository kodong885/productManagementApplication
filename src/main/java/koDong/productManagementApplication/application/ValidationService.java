package koDong.productManagementApplication.application;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated // @Validated가 달려있는 클래스에 있는 메서드 중, @Valid가 붙은 메서드 매개변수를 유효성 검사하겠다는 의미;
public class ValidationService {
    public <T> void checkValid(@Valid T validationTarget) {
        // do nothing; → checkValid로 인자를 담아 호출하는 것만으로도 유효성 검증이 이루어지기 떄문에 do nothing!!;
    }


}

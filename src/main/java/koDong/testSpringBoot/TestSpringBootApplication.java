package koDong.testSpringBoot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class TestSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}


    @Bean
    public ApplicationRunner applicationRunner(DataSource dataSource) {
        return args -> {
            // 이 부분에 실행할 코드 넣기;
            Connection connection = dataSource.getConnection();
            // dataSource : 데이터베이스와의 연결을 담당하는 인터페이스로,
            // 해당 인터페이스를 통해 데이터베이스와의 커넥션을 가져올 수 있다.

            /*
            1. JDBC API의 규칙에 따라
            2. "MySQL JDBC 드라이버"가 Connection 객체를 만든다
            3. 그리고 DB에 연결한다
            4. DB는 세션을 만든다
            5. 둘이 묶여서 커넥션 인스턴스가 된다
            6. 커넥션 풀은 이걸 미리 여러 개 만들어둔다
             */
        };
    }
}



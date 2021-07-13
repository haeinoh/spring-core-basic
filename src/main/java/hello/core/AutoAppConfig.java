package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 이 위치에서부터 하위 패키지로 스캔,,,
        // basePackages = "hello.core.member",
        // basePackageClasses = AutoAppConfig.class, //지정한 클래스 패키지부터 (hello.core)
        // @Configuration이 붙은 설정정보 자동 등록되서,, 기존 예제와 충돌 될거라서 제외 시키는 !
        // 이미 @Component가 붙어있어서,,,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}

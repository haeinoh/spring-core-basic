package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {
        @Autowired(required = false) // true ->  오류,, false -> 애초에 수정자 메서드 호출 안
        public void setNoBean1(Member noBean1) { // spring container에 관리 되지 않는것
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2 (@Nullable Member noBean2){ // 호출은 되지만,, null로 들어옴
            System.out.println("noBean2 = " + noBean2);
        }
        
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // 값이 있으면 값이 출력 없으면, Optional.empty
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
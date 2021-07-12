package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 만원을 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB : B사용자가 2만원을 주문 <-- 중간에 끼어들었다!
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        //int price = statefulService1.getPrice();
        //(같은 객체를 사용하므로 10000 -> 20000)
//        System.out.println("price = " + price); // (중간에 끼어들어서) 결과 :: 20000!
        System.out.println("price = " + userAPrice); // (중간에 끼어들어서) 결과 :: 20000!

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}

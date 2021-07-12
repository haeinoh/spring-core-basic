package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 애플리케이션 설정/구성정보
public class AppConfig {
    // appliction 환경 구성은 여기서 다 한다.

    // 현재 나의 어플리케이션에서 뭐를 어떻게 쓸건지 한 눈에 볼 수 있다

    // ex. 이 어플리케이션에서 memberservice는 memberserviceimpl을 쓸거다!∂
    @Bean // 각 메서드에 적어준다. 이러면 다 각각 스프링컨테이너에 올라간다.
    public MemberService memberService() { // ctrl + alt + m
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자를 통해서 객체가 들어간다 :: 생성자 주입
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 할인정책을 변경 할 때 이 부분만 변경하면 된다.
    }
}

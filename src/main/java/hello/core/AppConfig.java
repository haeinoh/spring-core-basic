package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // appliction 환경 구성은 여기서 다 한다.

    // 현재 나의 어플리케이션에서 뭐를 어떻게 쓸건지 한 눈에 볼 수 있다

    // ex. 이 어플리케이션에서 memberservice는 memberserviceimpl을 쓸거다!
    public MemberService memberService() { // ctrl + alt + m
        return new MemberServiceImpl(memberRepository()); // 생성자를 통해서 객체가 들어간다 :: 생성자 주입
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}

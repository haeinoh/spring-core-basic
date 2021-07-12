package hello.core.order;

import hello.core.discount.DiscountPolicy;
//import hello.core.discount.FixDiscountPolicy;
//import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // dip 지키는 중 - 구체적인 클래스에대해서 전혀 모른다.
    private final MemberRepository memberRepository;// = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // 기존 고정 할인 금액 -> DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 변경하면 여기 코드도 변경 필요 -> OCP 위반
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하게 된다. (dip 지키지만 null pointer exception 뜬다)

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 여기서는 fix인지 rate인지도 모른다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 변경은 order쪽에서 알아서, 단일 체계 원칙을 잘 지킨 것이다.

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // TEST (<=> MemberServiceImpl)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

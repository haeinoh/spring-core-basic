package hello.core.member;

public interface MemberService {

    void join(Member member); // 회원 가입

    hello.core.member.Member findMember(Long memberId); // 회원 조회
}

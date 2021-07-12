package hello.core.member;


public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;// = new MemoryMemberRepository(); // dip 위반 : MemberRepository, MemoryMemberRepository 2개에 의존중
    // 추상화에만 의존할 수 있게 되었다.
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자를 통해서
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //TEST
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

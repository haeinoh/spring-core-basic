package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); // memberServiceImpl이 들어가 있다.

        // annotation 기반으로 config
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class); // appconfig에 있는 환경 설정 정보를 가지고 spring 컨테이너에 다 넣어서 관리 해준다.
        // spring container에 bean에 있는 애들이 메서드 이름이 key값, 객체를 value값으로 spring container에 올라간다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // 메서드 이름으로 등록되어 있다.

        Member member = new Member(1L, "memberA", Grade.VIP); // ctrl + alt + V
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}

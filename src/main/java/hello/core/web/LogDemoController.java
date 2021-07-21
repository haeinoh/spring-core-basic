package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // @Autowired  의존관계 주입 받는 ,,, (생성자도 생략되어 있는 상태)
    // 스프링이 빈 등록을 할 때, mylogger 한테 달라고 하고 싶은데 스프링을 띄우는 상태에서는
    // httprequest가 안 들어온 상태여서 (httprequest가 들어오고 나올 때까지,,,) 요청이 들어왔을 때
    // 아무것도 없어서,,
    //Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'myLogger': Scope 'request' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton; nested exception is java.lang.IllegalStateException: No thread-bound request found: Are you referring to request attributes outside of an actual web request, or processing a request outside of the originally receiving thread? If you are actually operating within a web request and still receive this message, your code is probably running outside of DispatcherServlet: In this case, use RequestContextListener or RequestContextFilter to expose the current request.
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody // 화면 없이 바로 문자 그대로 반환
    public String logDemo(HttpServletRequest request) { // 고객 요청 정보 받기
        String requestURL = request.getRequestURL().toString();
        // 껍데기 가짜 myLogger
        System.out.println("myLogger = " + myLogger.getClass()); // 스프링이 조작해서 만든 스프링 빈이 등록되어 있다...
        // 기능을 실제로 사용할 때 "진짜"빈을 찾아서 Provider처럼 사용한다.
        myLogger.setRequestURL(requestURL); // 출력 될 때 url출력하기 위해서
//        MyLogger myLogger = myLoggerProvider.getObject(); // 만들어진다 -> init()호출, request연결
//        myLogger.setRequestURL(requestURL);

        myLogger.log("Controller Test");
        // Thread.sleep(1000); // 요청이 중간중간 들어와도 괜찮다. 각각 빈을 할당해준다.
        logDemoService.logic("testID");

        return "OK";
    }
}

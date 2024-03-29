package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 프록시 추가
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct // 고객 최초 요청
    public void init() {
        uuid = UUID.randomUUID().toString(); // 절대로 겹칠 일 없다
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy // 소멸
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}

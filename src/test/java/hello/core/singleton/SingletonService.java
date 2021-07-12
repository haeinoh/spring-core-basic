package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부에 private static으로 만든다 -> 딱 1개 생성
    private static final SingletonService instance = new SingletonService();

    // getInstance() 메서드를 통해서만 호출 -> 항상 같은 인스턴스 반환!
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 막아서,,, new 키워드로 객체 인스턴스가 생성되는 것을 막는다..
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

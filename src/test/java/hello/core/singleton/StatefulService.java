package hello.core.singleton;

public class StatefulService {

//    private int price ; // 상태를 유지하는 필드 -> 유지할 필요가 없다! (공유로 문제 생김

    public int /*void*/ order(String name, int price) {
        System.out.println("name = " + " price = " + price);
//        this.price = price; // 여기가 문제!
        return price;
    }
//
//    public int getPrice() {
//        return price;
//    }
}

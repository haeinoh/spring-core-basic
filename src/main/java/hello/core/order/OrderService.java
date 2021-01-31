package hello.core.order;

public interface OrderService {

    // 1. 클라이언트 -> 주문 생성
    Order createOrder(Long memberId, String itemName, int itemPrice);

}

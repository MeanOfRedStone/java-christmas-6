package christmas.view;

import christmas.domain.OrderList;
import christmas.domain.ReservationDate;

import java.text.DecimalFormat;

public class OutputView {
    public void printReservationDate(ReservationDate reservationDate) {
        System.out.println("12월 " + reservationDate.getDate() +"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printTotalPrice(OrderList orderList) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(splitMoneyBy1000(orderList.checkOut()) + "원");
        System.out.println();
    }

    private String splitMoneyBy1000(int money) {
        DecimalFormat moneyConverter = new DecimalFormat("###,###");

        return moneyConverter.format(money);
    }
}

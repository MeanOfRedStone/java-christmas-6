package christmas.view;

import christmas.domain.ChristmasEvent;
import christmas.domain.OrderList;
import christmas.domain.ReservationDate;

import java.text.DecimalFormat;

public class OutputView {
    private final OrderList orderList;
    private final ReservationDate reservationDate;
    private final ChristmasEvent christmasEvent;

    public OutputView(OrderList orderList, ReservationDate reservationDate) {
        this.orderList = orderList;
        this.reservationDate = reservationDate;
        this.christmasEvent = new ChristmasEvent(orderList, reservationDate);
    }

    public void printReservationDate() {
        System.out.println("12월 " + reservationDate.getDate() +"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printTotalPrice() {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(splitMoneyBy1000(orderList.checkOut()) + "원");
        System.out.println();
    }

    private String splitMoneyBy1000(int money) {
        DecimalFormat moneyConverter = new DecimalFormat("###,###");

        return moneyConverter.format(money);
    }

    //TODO : (2)주문 메뉴 출력  (5) 혜택 내역 출력 (6) 총혜택 금액 출력 (7) 할인 후 예상 결제 금액 출력 (8) 이벤트 배지 출력
    //TODO : (4) 증정 메뉴 출력
    public void printPresentation() {
        System.out.println("<증정 메뉴>");

        boolean hasPresentation = christmasEvent.presentation();
        if(hasPresentation) {
            System.out.println("샴페인 1개");
            System.out.println();
            return;
        }

        System.out.println("없음");
    }

    //TODO : (5) 혜택 내역 출력
    public void printDiscount() {
        System.out.println("<혜택 내역>");

        printAppliedDiscount();

        if(!hasAppliedDiscount()) {
            System.out.println("없음");
        }
    }

    private void printAppliedDiscount() {
        if(christmasEvent.dDayDiscount() != 0) {
            System.out.println("크리스마스 디데이 할인: -" + splitMoneyBy1000(christmasEvent.dDayDiscount()));
        }
        if(christmasEvent.weekDayDiscount() != 0) {
            System.out.println("평일 할인: -" + splitMoneyBy1000(christmasEvent.weekDayDiscount()));
        }
        if(christmasEvent.weekendDiscount() != 0) {
            System.out.println("주말 할인: -" + splitMoneyBy1000(christmasEvent.weekendDiscount()));
        }
        if(christmasEvent.specialDiscount() != 0) {
            System.out.println("특별 할인: -" + splitMoneyBy1000(christmasEvent.specialDiscount()));
        }
    }

    private boolean hasAppliedDiscount() {
        if(totalDiscount() == 0) {
            return false;
        }

        return true;
    }

    private int totalDiscount() {
        return christmasEvent.dDayDiscount() + christmasEvent.weekDayDiscount() + christmasEvent.weekendDiscount() + christmasEvent.specialDiscount();
    }
}

package christmas;

import christmas.domain.OrderList;
import christmas.domain.ReservationDate;
import christmas.view.OutputView;

public class ChristmasService {
    public void showReservation(OrderList orderList, ReservationDate reservationDate) {
        OutputView outputView = new OutputView(orderList, reservationDate);
        outputView.printReservationDate();
        outputView.printOrders();
        outputView.printTotalPrice();
        outputView.printPresentation();
        outputView.printDiscount();
        outputView.printTotalDiscount();
        outputView.printTotalPriceAfterDiscount();
        outputView.printBadge();
    }
}

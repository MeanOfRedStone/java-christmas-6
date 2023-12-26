package christmas;

import christmas.domain.OrderList;
import christmas.domain.ReservationDate;
import christmas.view.InputView;

public class ChristmasController {
    InputView inputView = new InputView();
    ChristmasService christmasService = new ChristmasService();

    public void run(){
        ReservationDate reservationDate = inputView.readDate();
        OrderList orderList = inputView.readOrder();
        christmasService.showReservation(orderList, reservationDate);
    }
}

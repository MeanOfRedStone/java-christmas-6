package christmas.domain;

public class ChristmasEvent {
    static final int BASIC_D_DAY_DISCOUNT = 1000;
    static final int ADDITIONAL_DISCOUNT = 100;
    static final int CHRISTMAS_MINUS_ONE = 24;
    private final OrderList orderList;
    private final ReservationDate reservationDate;

    public ChristmasEvent(OrderList orderList, ReservationDate reservationDate){
        this.orderList = orderList;
        this.reservationDate = reservationDate;
    }

    public int dDayDiscount() {
        int totalPrice = orderList.checkOut();
        int discount = 0;

        if(isPriceMoreThanTenThousand(totalPrice)){
            int dDay = reservationDate.christmasCountdown();
            int discountEffectiveDate = CHRISTMAS_MINUS_ONE - dDay;

            discount = BASIC_D_DAY_DISCOUNT +  ADDITIONAL_DISCOUNT * discountEffectiveDate;
            if(dDay < 0){
                discount = 0;
            }
        }
        return discount;
    }

    private boolean isPriceMoreThanTenThousand(int price) {
        if(price >= 10_000) {
            return true;
        }

        return false;
    }

    public int weekDayDiscount() {
        int totalPrice = orderList.checkOut();
        int discount = 0;

        if(!reservationDate.isWeekDay()) {
            return discount;
        }

        if(isPriceMoreThanTenThousand(totalPrice)){
            discount = 2023 * orderList.findTotalDessertQuantity();
        }

        return discount;
    }

    public int weekendDiscount() {
        if(reservationDate.isWeekDay()) {
            return 0;
        }

        return 2023 * orderList.findTotalMainQuantity();
    }
}

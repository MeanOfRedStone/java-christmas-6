package christmas;

import christmas.domain.ChristmasEvent;
import christmas.domain.OrderList;
import christmas.domain.ReservationDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChristmasEventTest {
    @Test
    void orderBeforeDecemberTwentySixth() {
        int expectedDDayDiscount1 = 1000;
        assertDDayDiscount(new OrderList(new String[] {"해산물파스타-1"}), new ReservationDate(1), expectedDDayDiscount1);
        int expectedDDayDiscount2 = 2000;
        assertDDayDiscount(new OrderList(new String[] {"크리스마스파스타-1"}), new ReservationDate(11), expectedDDayDiscount2);
    }

    @Test
    void orderAfterChristmas() {
        int expectedDDayDiscount = 0;
        assertDDayDiscount(new OrderList(new String[] {"해산물파스타-1"}), new ReservationDate(26), expectedDDayDiscount);
    }

    @Test
    void orderMoreThanTenThousandBeforeChristmas() {
        int expectedDDayDiscount1 = 1000;
        assertDDayDiscount(new OrderList(new String[] {"해산물파스타-1"}), new ReservationDate(1), expectedDDayDiscount1);
    }

    @Test
    void orderUnderTenThousandBeforeChristmas() {
        int expectedDDayDiscount1 = 0;
        assertDDayDiscount(new OrderList(new String[] {"타파스-1"}), new ReservationDate(1), expectedDDayDiscount1);
    }

    private void assertDDayDiscount(OrderList orderList, ReservationDate reservationDate, int expectedDiscount) {
        ChristmasEvent christmasEvent = new ChristmasEvent(orderList, reservationDate);
        int realDiscount = christmasEvent.dDayDiscount();
        assertEquals(expectedDiscount, realDiscount);
    }

    @Test
    void doNotGetWeekDayDiscountWhenOrderListContainsOneDessertAndThatOfPriceIsIsUnderTenThousandOnWeekDays() {
        int expectedWeekDayDiscount = 0;
        assertWeekDayDiscount(new OrderList(new String[] {"아이스크림-1", "제로콜라-1"}), new ReservationDate(3), expectedWeekDayDiscount);
    }

    @Test
    void getWeekDayDiscountWhenOrderListContainsOneDessertAndThatOfPriceIsMoreThanTenThousandOnWeekDays() {
        int expectedWeekDayDiscount = 2023;
        assertWeekDayDiscount(new OrderList(new String[] {"아이스크림-1", "제로콜라-2"}), new ReservationDate(3), expectedWeekDayDiscount);
    }

    @Test
    void getWeeDayDiscountWhenOrderListContainsMoreThanTwoDessertsAndThatOfPriceIsMorThanTenThousandOnWeekDays() {
        int expectedWeekDayDiscount = 4046;
        assertWeekDayDiscount(new OrderList(new String[] {"아이스크림-2", "제로콜라-1"}), new ReservationDate(3), expectedWeekDayDiscount);
    }

    @Test
    void doNotGetWeekDayDiscountWhenOrderListContainsDessertAndThatOfPriceIsMoreThanTenThousandOnWeekend() {
        int expectedWeekDayDiscount = 0;
        assertWeekDayDiscount(new OrderList(new String[] {"아이스크림-2", "제로콜라-1"}), new ReservationDate(1), expectedWeekDayDiscount);
    }

    private void assertWeekDayDiscount(OrderList orderList, ReservationDate reservationDate, int expectedWeekDayDiscount) {
        ChristmasEvent christmasEvent = new ChristmasEvent(orderList, reservationDate);
        int realWeekDayDiscount = christmasEvent.weekDayDiscount();
        assertEquals(expectedWeekDayDiscount, realWeekDayDiscount);
    }

    @Test
    void doNotGetWeekendDiscountWhenOrderListDoesNotContainMainOnWeekend() {
        int expectedWeekendDiscount = 0;
        assertWeekendDiscount(new OrderList(new String[] {"아이스크림-2", "제로콜라-1"}), new ReservationDate(8), expectedWeekendDiscount);
    }

    @Test
    void getWeekendDiscountWhenOrderListContainsMainOnWeekend() {
        int expectedWeekendDiscount1 = 2023;
        assertWeekendDiscount(new OrderList(new String[] {"해산물파스타-1", "제로콜라-1"}), new ReservationDate(15), expectedWeekendDiscount1);

        int expectedWeekendDiscount2 = 4046;
        assertWeekendDiscount(new OrderList(new String[] {"바비큐립-2", "제로콜라-2"}), new ReservationDate(22), expectedWeekendDiscount2);
    }

    @Test
    void doNotGetWeekendDiscountWhenOrderListDoesNotContainMainOnWeekDays() {
        int expectedWeekendDiscount = 0;
        assertWeekendDiscount(new OrderList(new String[] {"아이스크림-2", "제로콜라-1"}), new ReservationDate(10), expectedWeekendDiscount);
    }

    @Test
    void doNotGetWeekendDiscountWhenOrderListContainMainOnWeekDays() {
        int expectedWeekendDiscount = 0;
        assertWeekendDiscount(new OrderList(new String[] {"바비큐립-2", "제로콜라-1"}), new ReservationDate(17), expectedWeekendDiscount);
    }

    @Test
    void getWeekendDiscountWhenOrderListContainMainOnWeekend() {
        int expectedWeekendDiscount = 4046;
        assertWeekendDiscount(new OrderList(new String[] {"바비큐립-2", "제로콜라-1"}), new ReservationDate(16), expectedWeekendDiscount);
    }

    private void assertWeekendDiscount(OrderList orderList, ReservationDate reservationDate, int expectedWeekendDiscount) {
        ChristmasEvent christmasEvent = new ChristmasEvent(orderList, reservationDate);
        int realWeekendDiscount = christmasEvent.weekendDiscount();

        assertEquals(expectedWeekendDiscount, realWeekendDiscount);
    }

    @Test
    void specialDiscount_totalPriceUnderTenThousandAndReservationDateIsSpecialEventDay_0() {
        int expectedSpecialDiscount = 0;
        assertSpecialDiscount(new OrderList(new String[] {"아이스크림-1", "제로콜라-1"}), new ReservationDate(25), expectedSpecialDiscount);
    }

    @Test
    void specialDiscount_totalPriceMoreThanTenThousandAndReservationDateIsSpecialEventDay_1000() {
        int expectedSpecialDiscount = 1000;
        assertSpecialDiscount(new OrderList(new String[] {"바비큐립-1", "제로콜라-1"}), new ReservationDate(25), expectedSpecialDiscount);
    }

    @Test
    void specialDiscount_totalPriceMoreThanTenThousandAndReservationDateIsNotSpecialEventDay_0() {
        int expectedSpecialDiscount = 0;
        assertSpecialDiscount(new OrderList(new String[] {"바비큐립-1", "제로콜라-1"}), new ReservationDate(1), expectedSpecialDiscount);
    }

    private void assertSpecialDiscount(OrderList orderList, ReservationDate reservationDate, int expectedSpecialDisount) {
        ChristmasEvent christmasEvent = new ChristmasEvent(orderList, reservationDate);
        int realSpecialDiscount = christmasEvent.specialDiscount();

        assertEquals(expectedSpecialDisount, realSpecialDiscount);
    }

    @Test
    void presentation_totalPriceIsUnderOneHundredTwentyThousand_false() {
        OrderList orderList1 = new OrderList(new String[] {"바비큐립-1", "제로콜라-1"});
        ReservationDate reservationDate1 = new ReservationDate(5);
        ChristmasEvent christmasEvent1 = new ChristmasEvent(orderList1, reservationDate1);

        boolean realExpectation1 = christmasEvent1.presentation();
        boolean expectation1 = false;

        assertEquals(expectation1, realExpectation1);
    }

    @Test
    void presentation_totalPriceIsMoreThanOneHundredTwentyThousand_true() {
        boolean expectation1 = true;
        assertPresentation(new OrderList(new String[] {"바비큐립-2", "해산물파스타-1", "제로콜라-3"}), new ReservationDate(8), expectation1);

        boolean expectation2 = true;
        assertPresentation(new OrderList(new String[] {"티본스테이크-2", "아이스크림-2"}), new ReservationDate(28), expectation2);
    }

    private void assertPresentation(OrderList orderList, ReservationDate reservationDate, boolean expectation) {
        ChristmasEvent christmasEvent = new ChristmasEvent(orderList, reservationDate);
        boolean realExpectation = christmasEvent.presentation();

        assertEquals(expectation, realExpectation);
    }
}

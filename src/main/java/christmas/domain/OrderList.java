package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private final List<Order> orders;

    public OrderList(String[] inputOrders) throws IllegalArgumentException{
        List<Order> orders = new ArrayList<>();

        for(String inputOrder : inputOrders){
            Order order = new Order(inputOrder);
            checkDuplicatedOrder(order, orders);
            orders.add(order);
        }
        checkHasOnlyDrink(orders);
        checkTotalCountLessThanTwenty(orders);
        this.orders = orders;
    }

    private void checkDuplicatedOrder(Order order, List<Order> orders) throws IllegalArgumentException {
        for(Order orderInList : orders){
            if(orderInList.isSame(order)){
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkHasOnlyDrink(List<Order> orders) throws IllegalArgumentException {
        boolean isOnlyDrink = true;

        for(Order order : orders){
            if(!order.menuType().equals("drink")) {
                isOnlyDrink = false;
                break;
            }
        }

        if(isOnlyDrink) {
            throw new IllegalArgumentException();
        }
    }

    private void checkTotalCountLessThanTwenty(List<Order> orders) throws IllegalArgumentException {
        int totalCount = 0;
        for(Order order : orders){
            totalCount = order.addQuantity(totalCount);
        }

        if(totalCount > 20) {
            throw new IllegalArgumentException();
        }
    }

    public int checkOut() {
        int price = 0;
        for(Order orderInList : orders){
            price += orderInList.calculate();
        }

        return price;
    }

    public int findTotalDessertQuantity() {
        int totalDessertQuantity = 0;
        for(Order order : orders) {
            totalDessertQuantity += order.findDessertQuantity();
        }

        return totalDessertQuantity;
    }

    public int findTotalMainQuantity() {
        int totalMainQuantity = 0;
        for(Order order : orders) {
            totalMainQuantity += order.findMainQuantity();
        }
        return totalMainQuantity;
    }

    public List<String> request() {
        List<String> orderList = new ArrayList<>();
        for(Order order : orders) {
            orderList.add(order.check());
        }

        return orderList;
    }
}

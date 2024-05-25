package main.java.com.lld.pathfinder.models;

public class Order {
    Integer orderId;
    Consumer drop;
    Restaurant pickUp;

    public Order(Integer orderId, Consumer drop, Restaurant pickUp){
        this.orderId = orderId;
        this.drop = drop;
        this.pickUp = pickUp;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Consumer getDrop() {
        return drop;
    }

    public Restaurant getPickUp() {
        return pickUp;
    }
}

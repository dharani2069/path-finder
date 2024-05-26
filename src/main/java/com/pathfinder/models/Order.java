package com.pathfinder.models;

public class Order {

    private Integer orderId;
    private Consumer drop;
    private Restaurant pickUp;

    public Order(Integer orderId, Consumer drop, Restaurant pickUp){
        this.orderId = orderId;
        this.drop = drop;
        this.pickUp = pickUp;
    }

    public Order() {
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

    public void setPickUp(Restaurant pickUp) {
        this.pickUp = pickUp;
    }

    public void setDrop(Consumer drop) {
        this.drop = drop;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}

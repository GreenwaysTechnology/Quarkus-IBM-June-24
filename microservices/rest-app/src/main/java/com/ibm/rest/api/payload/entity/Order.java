package com.ibm.rest.api.payload.entity;

public class Order {
    private Integer orderId;
    private String orderStatus;
    private Double orderValue;

    public Order() {
    }

    public Order(Integer orderId, String orderStatus, Double orderValue) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderValue = orderValue;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Double orderValue) {
        this.orderValue = orderValue;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderValue=" + orderValue +
                '}';
    }
}

package com.ibm.rest.response.enity;

public class Order {
    private Integer orderId;
    private String orderStatus;
    private Double orderValue;
    private String description;

    public Order() {
    }

    public Order(Integer orderId, String orderStatus, Double orderValue, String description) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderValue = orderValue;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderValue=" + orderValue +
                ", description='" + description + '\'' +
                '}';
    }
}

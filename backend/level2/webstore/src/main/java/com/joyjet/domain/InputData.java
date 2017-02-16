package com.joyjet.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jackson on 14/02/17.
 */
public class InputData implements Serializable {

    private List<Article> articles;
    private List<Cart> carts;

    @JsonProperty("delivery_fees")
    private List<DeliveryFees> deliveryFees;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<DeliveryFees> getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(List<DeliveryFees> deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

}

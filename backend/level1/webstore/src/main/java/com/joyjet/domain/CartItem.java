package com.joyjet.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jackson on 14/02/17.
 */
public class CartItem implements Serializable {

    @JsonProperty("article_id")
    private Integer article;

    private Integer quantity;

    public CartItem() {
    }

    public CartItem(Integer article, Integer quantity) {
        this.article = article;
        this.quantity = quantity;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

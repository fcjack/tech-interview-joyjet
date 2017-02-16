package com.joyjet.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * This class is the model representation for the JSON received related with Discounts to be applied
 * on products.
 * <p>
 * Created by Jackson Coelho on 14/02/17.
 */
public class Discount implements Serializable {

    public enum DiscountType {
        @JsonProperty("amount")
        AMOUNT,
        @JsonProperty("percentage")
        PERCENTAGE
    }

    @JsonProperty("article_id")
    private Integer articleId;

    private DiscountType type;

    private Double value;

    public Discount() {
    }

    public Discount(Integer articleId, DiscountType type, Double value) {
        this.articleId = articleId;
        this.type = type;
        this.value = value;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}

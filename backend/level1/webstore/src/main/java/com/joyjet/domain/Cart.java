package com.joyjet.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jackson on 14/02/17.
 */
public class Cart implements Serializable {

    private Integer id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CartItem> items;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer total;

    public Cart() {}

    public Cart(Integer id, List<CartItem> items) {
        this.id = id;
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void addValueToTotal(Integer value) {
        this.total += value;
    }
}

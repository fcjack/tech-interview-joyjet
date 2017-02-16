package com.joyjet.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jackson on 14/02/17.
 */
public class OutputData {

    private List<Cart> carts;

    public OutputData() {
        this.carts = new ArrayList<>();
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}

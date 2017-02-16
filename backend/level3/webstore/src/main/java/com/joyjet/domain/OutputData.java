package com.joyjet.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the model representation for the JSON related with Output Data.
 * This class is the result of the operation to checkout the cart and will be returned by API.
 * <p>
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

}

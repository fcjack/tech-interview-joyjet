package com.joyjet;

import com.joyjet.domain.*;
import com.joyjet.service.CartService;
import com.joyjet.service.CartServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jackson on 16/02/17.
 */
public class CarServiceTest {

    private CartService cartService;

    private Article article = new Article(1, "Java book", 10000);
    private Article article2 = new Article(2, "JS book", 20000);
    private Article article3 = new Article(3, "Node.JS book", 10500);
    private Article article4 = new Article(4, "Spring book", 12300);


    @Test
    public void testCheckout() {
        cartService = new CartServiceImpl();
        InputData inputData = new InputData();
        inputData.setArticles(Arrays.asList(article, article2, article3, article4));

        CartItem cartItem = new CartItem(1, 5);//50_000
        CartItem cartItem2 = new CartItem(2, 4);//80_000
        Cart cart = new Cart(1, Arrays.asList(cartItem, cartItem2));

        inputData.setCarts(Collections.singletonList(cart));
        cartService.calculate(inputData);

        Assert.assertEquals(130_000L, cart.getTotal().longValue());
    }

    @Test
    public void testWithDeliveryFees() {
        cartService = new CartServiceImpl();
        InputData inputData = new InputData();
        inputData.setArticles(Arrays.asList(article, article2, article3, article4));

        CartItem cartItem = new CartItem(1, 5);
        CartItem cartItem2 = new CartItem(2, 4);

        CartItem cartItem3 = new CartItem(1, 5);
        CartItem cartItem4 = new CartItem(2, 4);
        CartItem cartItem5 = new CartItem(3, 4);
        CartItem cartItem6 = new CartItem(4, 4);

        Cart cart = new Cart(1, Arrays.asList(cartItem, cartItem2));
        Cart cart2 = new Cart(2, Arrays.asList(cartItem3, cartItem4, cartItem5, cartItem6));

        EligibleTransactionVolume transactionVolume = new EligibleTransactionVolume(5000, 10000);
        EligibleTransactionVolume transactionVolume2 = new EligibleTransactionVolume(10000, 15000);
        EligibleTransactionVolume transactionVolume3 = new EligibleTransactionVolume(15000, null);

        DeliveryFees deliveryFees = new DeliveryFees(transactionVolume, 1000);
        DeliveryFees deliveryFees2 = new DeliveryFees(transactionVolume2, 800);
        DeliveryFees deliveryFees3 = new DeliveryFees(transactionVolume3, 0);

        inputData.setDeliveryFees(Arrays.asList(deliveryFees, deliveryFees2, deliveryFees3));
        inputData.setCarts(Arrays.asList(cart, cart2));
        cartService.calculate(inputData);

        Assert.assertEquals(130000, cart.getTotal().longValue());
        Assert.assertEquals(221200, cart2.getTotal().longValue());
    }

}

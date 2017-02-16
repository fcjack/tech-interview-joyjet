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

}

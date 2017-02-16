package com.joyjet.controller;

import com.joyjet.domain.InputData;
import com.joyjet.domain.OutputData;
import com.joyjet.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jackson on 14/02/17.
 */
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;


    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    public OutputData checkoutCart(@RequestBody InputData inputData) {
        return cartService.calculate(inputData);
    }

}

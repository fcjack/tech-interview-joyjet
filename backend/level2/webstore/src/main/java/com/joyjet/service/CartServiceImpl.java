package com.joyjet.service;

import com.joyjet.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by jackson on 14/02/17.
 */
@Component("basicCartService")
public class CartServiceImpl implements CartService {

    @Override
    public OutputData calculate(InputData inputData) {
        OutputData outputData = basicCalculate(inputData);

        if (inputData.getDeliveryFees() != null && !inputData.getDeliveryFees().isEmpty()) {
            calculateWithDeliveryFees(outputData, inputData.getDeliveryFees());
        }

        return outputData;
    }

    private void calculateWithDeliveryFees(OutputData outputData, List<DeliveryFees> deliveryFees) {
        for (Cart cart : outputData.getCarts()) {
            for (DeliveryFees deliveryFee : deliveryFees) {
                Integer total = cart.getTotal();
                EligibleTransactionVolume eligibleTransactionVolume = deliveryFee.getEligibleTransactionVolume();

                if ((eligibleTransactionVolume.getMaxPrice() != null &&
                        total < eligibleTransactionVolume.getMaxPrice()) &&
                        total >= eligibleTransactionVolume.getMinPrice()) {
                    cart.addValueToTotal(deliveryFee.getPrice());
                    break;
                }
            }
        }
    }

    private OutputData basicCalculate(InputData inputData) {
        Map<Integer, Integer> articles = inputData.getArticles().stream()
                .collect(Collectors.toMap(Article::getId, Article::getPrice));

        OutputData outputData = new OutputData();
        for (Cart cart : inputData.getCarts()) {
            Integer total = 0;
            for (CartItem cartItem : cart.getItems()) {
                total += cartItem.getQuantity() * articles.get(cartItem.getArticle());
            }

            cart.setTotal(total);
            outputData.getCarts().add(cart);
        }

        return outputData;
    }
}

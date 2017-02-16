package com.joyjet.service;

import com.joyjet.domain.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This service is responsible to apply the rules about the calculation operation for the cart.
 * Here we can calculate the cart total, the delivery fees and the discounts in the products.
 * <p>
 * Created by Jackson Coelho on 14/02/17.
 */
@Component("basicCartService")
public class CartServiceImpl implements CartService {

    @Override
    public OutputData calculate(InputData inputData) {
        OutputData outputData = basicCalculate(inputData);

        /*
        * if we have data about delivery fees, this values have to be included inside the calculate
        * operation.
        * */
        if (inputData.getDeliveryFees() != null && !inputData.getDeliveryFees().isEmpty()) {
            calculateWithDeliveryFees(outputData, inputData.getDeliveryFees());
        }

        return outputData;
    }

    /**
     * This method will calculate the delivery fees and apply to cart.
     * This method analyze every cart and verify if the cart is between a category
     * of delivery fee, if there is some category for this cart, the value will be applied
     * for the cart.
     *
     * @param outputData   {@link OutputData}
     * @param deliveryFees {@link DeliveryFees}
     */
    private void calculateWithDeliveryFees(OutputData outputData, List<DeliveryFees> deliveryFees) {
        for (Cart cart : outputData.getCarts()) {
            for (DeliveryFees deliveryFee : deliveryFees) {
                Integer total = cart.getTotal();
                EligibleTransactionVolume eligibleTransactionVolume = deliveryFee.getEligibleTransactionVolume();

                if ((eligibleTransactionVolume.getMaxPrice() == null ||
                        total < eligibleTransactionVolume.getMaxPrice()) &&
                        total >= eligibleTransactionVolume.getMinPrice()) {
                    cart.addValueToTotal(deliveryFee.getPrice());
                    break;
                }
            }
        }
    }

    /**
     * This simple method has the responsability to analyze each item in the cart and sum
     * the prices to put in total value.
     *
     * @param inputData {@link InputData}
     * @return outputData {@link OutputData}
     */
    private OutputData basicCalculate(InputData inputData) {
        Map<Integer, Double> articles = processCurrentItemPrice(inputData);

        /* As we have a map, the access to the prices is O(1).
           So, we have a better performance to calculate this carts total.
         */
        OutputData outputData = new OutputData();
        for (Cart cart : inputData.getCarts()) {
            Double total = 0D;
            for (CartItem cartItem : cart.getItems()) {
                total += cartItem.getQuantity() * articles.get(cartItem.getArticle());
            }

            cart.setTotal(total.intValue());
            outputData.getCarts().add(cart);
        }

        return outputData;
    }

    /**
     * This method will analyze every article and verify if the item has discount {@link Discount}
     * to be applied.
     * <p>
     * As a result, we have a {@link Map} where the key is the ID of the article and the value is
     * the price that the product has after discount or the original price if it doesn`t have discount.
     *
     * @param inputData {@link InputData}
     * @return Map
     */
    private Map<Integer, Double> processCurrentItemPrice(InputData inputData) {
        Map<Integer, Double> articlePricesMap = new HashMap<>();
        for (Article article : inputData.getArticles()) {
            Double price = Double.valueOf(article.getPrice());
            if (inputData.getDiscounts() != null) {
                for (Discount discount : inputData.getDiscounts()) {
                    if (discount.getArticleId().equals(article.getId())) {
                        switch (discount.getType()) {
                            case AMOUNT:
                                price = price - discount.getValue();
                                break;
                            case PERCENTAGE:
                                price = price - ((price * discount.getValue()) / 100);
                                break;
                        }

                        break;
                    }
                }
            }

            articlePricesMap.put(article.getId(), price);
        }

        return articlePricesMap;
    }
}

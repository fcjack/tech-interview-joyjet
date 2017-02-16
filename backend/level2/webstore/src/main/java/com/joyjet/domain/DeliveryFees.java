package com.joyjet.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jackson on 14/02/17.
 */
public class DeliveryFees implements Serializable {

    @JsonProperty("eligible_transaction_volume")
    private EligibleTransactionVolume eligibleTransactionVolume;
    private Integer price;

    public DeliveryFees() {
    }

    public DeliveryFees(EligibleTransactionVolume eligibleTransactionVolume, Integer price) {
        this.eligibleTransactionVolume = eligibleTransactionVolume;
        this.price = price;
    }

    public EligibleTransactionVolume getEligibleTransactionVolume() {
        return eligibleTransactionVolume;
    }

    public void setEligibleTransactionVolume(EligibleTransactionVolume eligibleTransactionVolume) {
        this.eligibleTransactionVolume = eligibleTransactionVolume;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

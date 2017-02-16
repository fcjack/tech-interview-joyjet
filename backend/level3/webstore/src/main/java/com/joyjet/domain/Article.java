package com.joyjet.domain;

import java.io.Serializable;

/**
 * This class is the model representation for the JSON received related with Articles
 * <p>
 * Created by Jackson Coelho on 14/02/17.
 */
public class Article implements Serializable {

    private Integer id;
    private String name;
    private Integer price;

    public Article() {
    }

    public Article(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

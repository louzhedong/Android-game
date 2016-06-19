package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/27.
 */
public class Product {
    private int head;
    private String name;
    private int price;
    private String description;
    private String buy;

    public int getHead() {
        return head;
    }

    public String getName() {
        return  name;
    }

    public int getPrice(){
        return price;
    }

    public String getDescription(){
        return description;
    }

    public String getBuy() {
        return buy;
    }


    public Product(int head, String name, int price, String description,String buy) {
        super();
        this.head = head;
        this.name = name;
        this.price = price;
        this.description = description;
        this.buy = buy;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }
}

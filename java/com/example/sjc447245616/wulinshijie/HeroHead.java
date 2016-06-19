package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/25.
 */
public class HeroHead {

    private int head;
    private String name;

    public int getHead() {
        return head;
    }

    public String getName() {
        return  name;
    }

    public HeroHead(int head, String name) {
        super();
        this.head = head;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }

}
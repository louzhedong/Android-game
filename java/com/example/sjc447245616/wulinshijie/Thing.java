package com.example.sjc447245616.wulinshijie;

/**
 * Created by sjc447245616 on 15/11/25.
 */
public class Thing {

    public int head;
    public String name;
    public int number;
    public String email;
    public String address;

    public int getHead() {
        return head;
    }

    public String getName() {
        return  name;
    }

    public int getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Thing(){
        super();
    }

    public Thing(int head, String name,int number, String email, String address) {
        super();
        this.head = head;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", number=" + number + ", email=" + email
                + ", address=" + address + "]";
    }

}

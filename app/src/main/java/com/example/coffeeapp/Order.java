package com.example.coffeeapp;

public class Order {

    //    Attributes of every order
    private int _id;
    private String _custName;
    private int _saleAmount;

    //    Constructor
    public Order(String custName, int saleAmount){
        _custName = custName;
        _saleAmount = saleAmount;
    }

    //    Get methods
    public int get_id() { return _id; }
    public String get_custName() { return _custName; }
    public int get_saleAmount() { return _saleAmount; }

    //    Set methods
    public void set_id(int id) {_id = id; }
    public void set_custName(String custName) { _custName = custName; }
    public void set_saleAmount(int saleAmount) { _saleAmount = saleAmount; }
}

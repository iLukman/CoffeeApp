package com.example.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int noOfCoffee = 0;
    int costOfCoffee = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //submit Order
    public void submitOrder(View view) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        int totalPrice = noOfCoffee * costOfCoffee;
//        priceTextView.setText("Total: $" + totalPrice + "\n" + "Thank you!");

//        1. Get the user's name
//        Create an object of EditText
        EditText nameText = (EditText) findViewById(R.id.name_field);
        String name = nameText.getText().toString();

//        2. Check if the user wants Whipped Cream or Chocolate
        CheckBox wCreamCB = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = wCreamCB.isChecked();

        CheckBox ChocolateCB = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = ChocolateCB.isChecked();

//        3. Calculate the price of the coffee
        int price = calculatePrice(hasWhippedCream, hasChocolate);

//        4. Create a message that can be send as an intent
        String message = createOrderSummary(name, hasWhippedCream, hasChocolate, noOfCoffee, price);

//        5. Start a new intent and pass the message to the new activity
        Intent intent = new Intent(this, com.example.coffeeapp.DisplayOrderDetails.class);
        intent.putExtra("name",name);
        intent.putExtra("message",message);

        intent.putExtra("totalPrice",Integer.toString(totalPrice));
        startActivity(intent);
    }

    public String createOrderSummary(String name, boolean addWhippedCream, boolean addChocolate, int totalCoffee, int price) {
        String priceMessage = "Name: " + name + "\n" + "Add Whipped Cream: " + addWhippedCream + "\n" + "Add Chocolate: " + addChocolate + "\n" + "Quantity: " + totalCoffee + "\n" + "Total Price: " + price + "\n" + "Thank-you";
        return priceMessage;
    }

    //    Method to calculate the price of coffee
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
//        Price of a single coffee
        if (addWhippedCream == true) {
            costOfCoffee = costOfCoffee + 1;
        }
        if (addChocolate == true) {
            costOfCoffee = costOfCoffee + 2;
        }
        return (noOfCoffee * costOfCoffee);
    }

    //return quantity
    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void plusCoffee(View view) {
        noOfCoffee++;
        if ((noOfCoffee < 0) || (noOfCoffee > 15)) {
            noOfCoffee = 0;
        }
        display(noOfCoffee);
    }

    public void minusCoffee(View view) {
        noOfCoffee--;
        if ((noOfCoffee < 0) || (noOfCoffee > 15)) {
            noOfCoffee = 0;
        }
        display(noOfCoffee);
    }
}

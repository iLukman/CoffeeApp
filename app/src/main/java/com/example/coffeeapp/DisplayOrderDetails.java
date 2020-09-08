package com.example.coffeeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayOrderDetails extends AppCompatActivity {
    String message;
    String name;
    String totalPrice;
    CoffeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        dbHandler = new CoffeeDBHandler(this, null, null, 1);

//         Get the intent from the MainActivity and extract the Strings
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        totalPrice = intent.getStringExtra("totalPrice");

//        Capturing the TextView and set is as a String
        TextView tv = (TextView) findViewById(R.id.message);
        tv.setText(message);
    }

    //                Create a method that will send a message to Gmail
    public void sendEmail(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
//         Only email apps should handle this
//         intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(intent.EXTRA_SUBJECT, "Coffee Order for " +name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    //    Method to save data to SQLite database
    public void addButtonClicked(View view){
        Order order = new Order(name,Integer.parseInt(totalPrice));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(), "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    //    Method to display the details from the database into a new activity
    public void salesReport(View view){
//        Read the details from the database to produce the report
        String dbString = dbHandler.databaseToString();
//        Start the new intent here
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db",dbString);
        startActivity(salesIntent);
    }
}

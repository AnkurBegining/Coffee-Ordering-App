package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int num = 0, totalSum = 0, priceWhiped = 0, priceChoco = 0, totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        display(++num);
    }

    public void decrement(View view) {
        if (num <= 0) {
            Toast.makeText(this, "You can't order this quntity", Toast.LENGTH_SHORT).show();
            return;
        }
        display(--num);
    }

    public void summitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_description_view);
        String value = text.getText().toString();
        String message = "Name: " + value;
        message = message + "\nQuantity: " + num;
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();
        CheckBox chocoCream = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChocoCream = chocoCream.isChecked();
        if (hasWhippedCream) {
            priceWhiped++;
            totalPrice = totalPrice + priceWhiped;
        }
        if (hasChocoCream) {
            priceChoco += 2;
            totalPrice = totalPrice + priceChoco;
        }
        totalPrice=totalPrice +(num*20);
        message = message + "\nPrice of coffee:" + (num * 20);
        message = message + "\nWhipped cream cost: $" + 1;
        message = message + "\nChoco cream cost: $" + 2;
        message = message + "\nWhipped cream added: " + priceWhiped;
        message = message + "\nChoco cream added:" + (priceChoco / 2);
        message = message + "\nTotal cost: " + (totalPrice);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee for " +value);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Log.e("print",intent.toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        //displayMessage(message, hasWhippedCream, hasChocoCream);
    }

    private void displayMessage(String message, boolean hasWhippedCream, boolean hasChocoCream) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText("" + message + "\nWhipped Cream: " + hasWhippedCream + "\nChocolate: " + hasChocoCream + "\nThank you! Visit again");
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText("$ " + number);

    }
}
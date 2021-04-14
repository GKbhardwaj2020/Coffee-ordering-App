package com.androidexample.coffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox chk = (CheckBox) findViewById(R.id.whipped_Cream);
        boolean chkbox = chk.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.ChocoLate_checkBox);
        boolean hasChocolate = chocolate.isChecked();
        String Message=OrderSummary(5, chkbox, hasChocolate);
        String Address="gkjha20016@gmail.com";
        Intent SendEmail = new Intent(Intent.ACTION_SENDTO);
        SendEmail.setData(Uri.parse("mailto:")); //only email apps should handle this
        SendEmail.putExtra(SendEmail.EXTRA_EMAIL,Address);
        SendEmail.putExtra(SendEmail.EXTRA_SUBJECT,"coffee order for" + name);
        SendEmail.putExtra(SendEmail.EXTRA_TEXT,Message);
        if (SendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(SendEmail);
        }

    }

    private void display(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.textView1);
        quantityTextView.setText("" + quantity);
    }

    public String getName() {
        EditText Name = (EditText) findViewById(R.id.editTextName);
        name = String.valueOf(Name.getText());
        return name;
    }

    public String OrderSummary(int number, boolean addWhippedCream, boolean hasChocolate) {

        if (addWhippedCream == true && hasChocolate == true) {
            int Totalprice = quantity * 50;
            String price = "Name:" + getName();
            price += "\nAdd Whipped Cream";
            price += "\nAdd Chocolate";
            price += "\nTotal:" + " " + Totalprice;
            price += "\nThank you!";
            TextView Price = (TextView) findViewById(R.id.PriceValue);
            Price.setText(price);
            return price;
        } else if (hasChocolate == true) {
            int Totalprice = quantity * 20;
            String price = "Name:" + getName();
            price += "\nAdd Chocolate";
            price += "\nTotal:" + " " + Totalprice;
            price += "\nThank you!";
            TextView Price = (TextView) findViewById(R.id.PriceValue);
            Price.setText(price);
            return price;
        } else if (addWhippedCream == true) {
            int Totalprice = quantity * 20;
            String price = "Name:" + getName();
            price += "\nAdd Whipped Cream";
            price += "\nTotal:" + " " + Totalprice;
            price += "\nThank you!";
            TextView Price = (TextView) findViewById(R.id.PriceValue);
            Price.setText(price);
            return price;
        } else {
            int Totalprice = quantity * 10;
            String price = "Name:" + getName();
            price += "\nTotal:" + " " + Totalprice;
            price += "\nThank you!";
            TextView Price = (TextView) findViewById(R.id.PriceValue);
            Price.setText(price);
            return price;
        }

    }

    public void increment(View view) {
        quantity = quantity + 1;
        TextView quantityIncValue = (TextView) findViewById(R.id.textView1);
        quantityIncValue.setText(String.valueOf(quantity));
    }

    public void decrement(View view) {
        if (quantity >= 1 && quantity > 100) {
            quantity = quantity - 1;
            TextView quantityDecValue = (TextView) findViewById(R.id.textView1);
            quantityDecValue.setText(String.valueOf(quantity));
        }
    }
}
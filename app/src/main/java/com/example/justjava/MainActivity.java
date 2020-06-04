/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.JustJava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


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

import java.text.NumberFormat;

;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 10) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 10 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);;
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
    /**
     * This method is called to select toppings in the order.
     */
    public void onCheckboxClicked(View view){
        // Is the view now checked?

        boolean iswhipped= true;

        // Check which checkbox was clicked

                if (iswhipped) {
                    Log.v("whippedcream", "Whipped cream is used");
                }
                    // Put some cream on the coffee
                else {
                    Log.v("whippedcream", "whipped cream is not used");
                }
                    // Remove the cream

        boolean ischocochip= true;

        // Check which checkbox was clicked

        if (ischocochip) {
            Log.v("chocchip", "Chocochip is used");
        }
        // Put some cream on the coffee
        else {
            Log.v("chocochip", "Chocochip is not used");
        }


        }

    private int calculatePrice(boolean hasWhippedCream, boolean haschocochip){
        int baseprice= quantity* 50;
        if (hasWhippedCream){
            baseprice= baseprice+10;
        }
        if(haschocochip){
            baseprice= baseprice+5;
        }


        return baseprice;
    }
    /**
     * This method is called to display message along with order.
     */
    public void submitOrder(View view)
    {//Input name
        EditText nameField =(EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("MainActivity","Name"+ name);
        CheckBox whippedcreamcheckbox = (CheckBox) findViewById(R.id.checkbox_WhippedCream);
        CheckBox chocochip = (CheckBox) findViewById(R.id.checkbox_choco);
        boolean hasWhippedCream = whippedcreamcheckbox.isChecked();
        boolean haschocochip = chocochip.isChecked();
        int baseprice= calculatePrice(hasWhippedCream,haschocochip);
displayOrderSummary(baseprice);
       calculatePrice(hasWhippedCream,haschocochip);
        String priceMessage =(createOrderSummary(name,hasWhippedCream, haschocochip));
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Day Order For :"+ name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(priceMessage);
}


    private String createOrderSummary(String Name ,Boolean hasWhippedCream, Boolean haschocochip){
        int baseprice = calculatePrice(hasWhippedCream,haschocochip);
        String priceMessage  = "Name:"+ Name+"\nAdd whippedCream - "+hasWhippedCream + "\nAdd chocochip - "+haschocochip + "\nquantity:"+ quantity;
        priceMessage = priceMessage + "\nTotal: Rs. " + baseprice + "\nThank you and Have a Nice Day!" ;
        return priceMessage;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayOrderSummary(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }private void displayMessage(String message){
        TextView OrderSummaryTextView = findViewById(R.id.order_summary_text_view);
        OrderSummaryTextView.setText(message);

    }



}

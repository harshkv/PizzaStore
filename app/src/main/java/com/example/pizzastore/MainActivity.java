package com.example.pizzastore;

/*
Group 15
Sahil Sood - 801082267
Harshitha Keshavaraju Vijayalakshmi - 801084151
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FlexboxLayout flexboxLayout;
    CheckBox checkBoxDelivery;
    Button addToppingB, clearButton, checkoutButton;
    public static String Item_Key = "item";
    CharSequence[] toppings = {"Bacon", "Cheese", "Garlic", "Green Pepper", "Mushroom", "Olive", "Onion", "Red Pepper"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flexboxLayout = (FlexboxLayout) findViewById(R.id.flexLayout);
        addToppingB = (Button) findViewById(R.id.addToppingB);
        clearButton = (Button) findViewById(R.id.clearButton);
        checkoutButton = (Button) findViewById(R.id.checkoutButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        checkBoxDelivery = (CheckBox) findViewById(R.id.checkBoxDelivery);
        progressBar.setMax(10);
        final ArrayList<CharSequence> myToppingList = new ArrayList<CharSequence>(10);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Select Topping!")
                .setItems(toppings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final int viewCount = flexboxLayout.getChildCount();
                        if (viewCount < 10) {
                            myToppingList.add(toppings[which]);
                            final String selection = (String) toppings[which];
                            int result = setImageFunction(selection);
                            final ImageButton imageButton = new ImageButton(MainActivity.this);

                            imageButton.setBackgroundResource(result);
                            imageButton.setTag(selection);
                            flexboxLayout.addView(imageButton);

                            progressFunction(viewCount + 1);
                            imageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    flexboxLayout.removeView(v);
                                    int removeCount = flexboxLayout.getChildCount();
                                    String removalItem = (String) v.getTag();
                                    progressFunction(removeCount);
                                    myToppingList.remove(removalItem);
                                }

                            });

                        } else {
                            Toast.makeText(MainActivity.this, "Maximum Topping capacity reached!", Toast.LENGTH_LONG).show();
                        }

                    }

                });


        final AlertDialog alert = builder.create();
        addToppingB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressFunction(0);
                myToppingList.clear();
                flexboxLayout.removeAllViews();
                checkBoxDelivery.setChecked(false);


            }
        });


        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean delivery;
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, second_Activity.class);
                if (checkBoxDelivery.isChecked()) {
                    delivery = true;
                } else {
                    delivery = false;
                }
                if (myToppingList.size() < 1) {

                    builder1.setTitle("Please select atleast 1 topping")
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });


                    final AlertDialog alert1 = builder1.create();
                    alert1.show();


                } else {

                    Items itemsPassed = new Items(myToppingList, delivery);
                    intent.putExtra(Item_Key, itemsPassed);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }


    public void progressFunction(int current) {
        progressBar.setProgress(current);

    }

    public int setImageFunction(String selected) {
        Log.i("demo", selected);
        switch (selected) {
            case "Bacon":
                return R.drawable.bacon;
            case "Cheese":
                return R.drawable.cheese;
            case "Garlic":
                return R.drawable.garlic;
            case "Green Pepper":
                return R.drawable.green_pepper;
            case "Mushroom":
                return R.drawable.mushroom;
            case "Olive":
                return R.drawable.olive;
            case "Onion":
                return R.drawable.onion;
            case "Red Pepper":
                return R.drawable.red_pepper;
        }
        return 0;
    }


}








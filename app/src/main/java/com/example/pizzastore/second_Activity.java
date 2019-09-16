package com.example.pizzastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class second_Activity extends AppCompatActivity {
    TextView tv_bpValues, tv_topValues, tv_Delivery, tv_totalCost, tv_displayTopping;
    double basePrice, toppingPrice, deliveryCost, totalCost;
    Button b_finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);
        setTitle("Pizza Store");

        tv_bpValues = (TextView) findViewById(R.id.tv_bpValues);
        tv_topValues = (TextView) findViewById(R.id.tv_topValues);
        tv_Delivery = (TextView) findViewById(R.id.tv_Delivery);
        tv_displayTopping = (TextView) findViewById(R.id.tv_displayTopping);
        tv_totalCost = (TextView) findViewById(R.id.tv_totalCost);
        b_finish = (Button) findViewById(R.id.b_finish);


        if (getIntent() != null && getIntent().getExtras() != null) {
            Items items = (Items) getIntent().getExtras().getSerializable(MainActivity.Item_Key);
            basePrice = 6.50;
            toppingPrice = 1.50 * items.myToppingList.size();
            if (items.delivery == true) {
                deliveryCost = 4.0;
            } else {
                deliveryCost = 0.0;
            }
            totalCost = basePrice + toppingPrice + deliveryCost;

            tv_bpValues.setText("$ " + Double.toString(basePrice));
            tv_topValues.setText("$ " + Double.toString(toppingPrice));
            String finalResult = items.myToppingList.toString().replace("[", "").replace("]", "");
            tv_displayTopping.setText(finalResult);
            tv_Delivery.setText("$ " + Double.toString(deliveryCost));
            tv_totalCost.setText("$ " + Double.toString(totalCost));

        }
        b_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(second_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}

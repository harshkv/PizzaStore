package com.example.pizzastore;

import java.io.Serializable;
import java.util.ArrayList;

class Items  implements Serializable {
        ArrayList<CharSequence> myToppingList = new java.util.ArrayList<CharSequence>(10);
        Boolean delivery;

        public Items(ArrayList<CharSequence> myToppingList, Boolean delivery) {
            this.myToppingList = myToppingList;
            this.delivery = delivery;
        }

        @Override
        public String toString() {
            return "items{" +
                    "myToppingList=" + myToppingList +
                    ", delivery=" + delivery +
                    '}';
        }
    }




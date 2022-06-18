package com.example.shadow.foodfast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.shadow.foodfast.Adapters.OrdersAdapter;
import com.example.shadow.foodfast.Models.OrdersModel;
import com.example.shadow.foodfast.databinding.ActivityOrderListBinding;


import java.util.ArrayList;

/*
 * @Abhishek Goyal
 *  OrderListActivity shows the orders been placed along with the facilities like updating and deleting the order.
 */

public class OrderListActivity extends AppCompatActivity
{
    ActivityOrderListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding= ActivityOrderListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper=new DBHelper(this);
        ArrayList<OrdersModel> list=helper.getOrders();



        OrdersAdapter adapter=new OrdersAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(manager);
    }
}

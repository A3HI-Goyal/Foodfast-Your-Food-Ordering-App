package com.example.shadow.foodfast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shadow.foodfast.Adapters.MainAdapter;
import com.example.shadow.foodfast.Models.MainModel;
import com.example.shadow.foodfast.databinding.ActivityMainBinding;

import java.util.ArrayList;

/*
 * @Abhishek Goyal
 *  This activity contain the menu of the food availiable as well as intent to go to details activity for placing an order.
 *  you can also click Three dots in toggle bar for checking out your order
 */

public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list=new ArrayList<>();
        list.add(new MainModel(R.drawable.food1,"Burger","5","Aloo Tikki Burger with Extra chese"));
        list.add(new MainModel(R.drawable.food2,"French fries","5","Fries Sprinkled with Peri Peri Spice Mix and Cheese Sause"));
        list.add(new MainModel(R.drawable.food3,"Jumbo pack","10","Jumbo Pack including Aloo Tikki Burger, French fries and Coke"));
        list.add(new MainModel(R.drawable.food4,"Pizza","8","Medium Size Corn Pizza with Extra Mozzarella Chese"));
        list.add(new MainModel(R.drawable.food5,"Raj Kachori","6","Raj Kachori with Chickpeas, Yogurt, Chutneys, Spices and Fruits "));
        list.add(new MainModel(R.drawable.food6,"Paneer Pizza","8","Pizza with Extra Paneer Topings and Extra Cheese"));
        list.add(new MainModel(R.drawable.food7,"Ice-cream","6","Vanilla Flavoured Ice-cream Full of nuts and Choco chips"));
        list.add(new MainModel(R.drawable.food8,"Sundae Dessert","10","Mixed Flavour Ice-cream with syrups, whipped cream,fruits and nuts"));
        list.add(new MainModel(R.drawable.food9,"Donuts","5","Donuts made with fine Butter, Fruits and chocolates"));

        MainAdapter adapter=new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderListActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
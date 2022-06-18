package com.example.shadow.foodfast;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shadow.foodfast.databinding.ActivityDetailsBinding;

/*
 * @Abhishek Goyal
 *  DetailsActivity is responsible for showing further details on any particular food in the menu.
 *  This activity is also used for Ordering the order and Updating the placed order.
 */

public class DetailsActivity extends AppCompatActivity
{
    ActivityDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        binding.subtract.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int q = Integer.parseInt(binding.quantity.getText().toString());
                if (q > 1)
                {
                    binding.quantity.setText(Integer.toString(q - 1));
                }
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                binding.quantity.setText(Integer.toString(Integer.parseInt(binding.quantity.getText().toString()) + 1));
            }
        });

        if (getIntent().getIntExtra("type", 0) == 1)
        {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String foodname = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");

            binding.detailImage.setImageResource(image);
            binding.priceOfOrder.setText(String.format("%d", price));
            binding.detailDescription.setText(description);
            binding.foodname.setText(foodname);

            binding.insertButton.setOnClickListener(view -> {
                boolean isInserted = helper.insertOrder(
                        binding.personName.getText().toString(),
                        binding.personPhone.getText().toString(),
                        price * Integer.parseInt(binding.quantity.getText().toString()),
                        image,
                        Integer.parseInt(binding.quantity.getText().toString()),
                        description,
                        foodname

                );

                if (isInserted)
                {
                    Toast.makeText(DetailsActivity.this, "Order is Placed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DetailsActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }

            });
        }

        else
        {
            int id=getIntent().getIntExtra("id",0);
            Cursor cursor= helper.getOrderById(id);
            int image=cursor.getInt(4);
            String quan=cursor.getString(5);
            binding.detailImage.setImageResource(image);
            binding.priceOfOrder.setText(String.format("%d", cursor.getInt(3)));
            binding.detailDescription.setText(cursor.getString(6));
            binding.foodname.setText(cursor.getString(7));

            binding.personName.setText(cursor.getString(1));
            binding.personPhone.setText(cursor.getString(2));
            binding.quantity.setText(quan);
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    int price=Integer.parseInt(binding.priceOfOrder.getText().toString());
                    int quant=Integer.parseInt(binding.quantity.getText().toString());
                    int ppp=price/Integer.parseInt(quan);
                    boolean isUpdated=helper.updateOrder
                    (

                            binding.personName.getText().toString(),
                            binding.personPhone.getText().toString(),
                            ppp*quant,
                            image,
                            quant,
                            binding.detailDescription.getText().toString(),
                            binding.foodname.getText().toString(),
                            id

                    );
                    if(isUpdated)
                    {
                        Toast.makeText(DetailsActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(DetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
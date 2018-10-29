package com.example.s531378.admin_smart_inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdminView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
    }

    public void AddItem(View v){

        Intent init = new Intent(this,AddItem.class);
        startActivity(init);
    }

    public void EditItem(View v){

        Intent init = new Intent(this,EditItem.class);
        startActivity(init);
    }

    public void SupplyList(View v){

        Intent init = new Intent(this,SupplyListItem.class);
        startActivity(init);
    }

    public void NewUsers(View v){

        Intent init = new Intent(this,AcceptnewUSers.class);
        startActivity(init);
    }
}

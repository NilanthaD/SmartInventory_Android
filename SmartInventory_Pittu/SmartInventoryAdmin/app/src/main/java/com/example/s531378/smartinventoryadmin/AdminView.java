package com.example.s531378.smartinventoryadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminView extends Activity {

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

        Intent init = new Intent(this,SupplyListItems.class);
        startActivity(init);
    }

    public void NewUsers(View v){

        Intent init = new Intent(this,AcceptNewUsers.class);
        startActivity(init);
    }
}

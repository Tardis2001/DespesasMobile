package com.matheus.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class remove extends AppCompatActivity {
    EditText removefield;
    Button removebt,back;
    SQLiteDatabase db;
    Animation topanim,bottomanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        removefield = findViewById(R.id.removefield);
        removebt = findViewById(R.id.removebt);
        back = findViewById(R.id.back);


        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        removefield.setAnimation(topanim);
        removebt.setAnimation(bottomanim);
        back.setAnimation(bottomanim);
        removebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(remove.this,Options.class);
                startActivity(intent);

            }
        });
    }
    private void delete(){
        try {
            db = openOrCreateDatabase("Expensedb", MODE_PRIVATE, null);
            db.delete("expense", "id =" + String.valueOf(removefield.getText()), null);

            db.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
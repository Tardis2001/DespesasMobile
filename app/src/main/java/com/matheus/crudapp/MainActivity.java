package com.matheus.crudapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    public ListView listViewdados;
    public Button button;

    Animation topAnim,bottomAnim;
    ImageView image;
    TextView logo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.Logo);

      /*  image.setAnimation(topAnim);*//**/
        logo.setAnimation(bottomAnim);

        createdatabase();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Options.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
    public void createdatabase() {
        try{
            db = openOrCreateDatabase("Expensedb",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS expense("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT"+
                    ",name VARCHAR NOT NULL"+
                    ",amount REAL NOT NULL" +
                    ",date VARCHAR NOT NULL" +
                    ",description VARCHAR)");
            db.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    /*For test purposes */
    public void dropTable(){
        try{
            db = openOrCreateDatabase("Expensedb", MODE_PRIVATE, null);
            db.execSQL("DROP TABLE expense");
            db.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
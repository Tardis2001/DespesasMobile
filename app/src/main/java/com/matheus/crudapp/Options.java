package com.matheus.crudapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Options extends AppCompatActivity {

    Button add,remove,update,list;
    Animation topAnim,bottomAnim;
    TextView selecione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        add =findViewById(R.id.Add);
        remove = findViewById(R.id.remove);
        update = findViewById(R.id.update);
        list = findViewById(R.id.List);
        selecione = findViewById(R.id.selecionetextview);

        add.setAnimation(bottomAnim);
        remove.setAnimation(bottomAnim);
        update.setAnimation(bottomAnim);
        list.setAnimation(bottomAnim);

        selecione.setAnimation(topAnim);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options.this,Adicionar.class);
                startActivity(intent);

            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options.this,remove.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options.this,updateActivity.class);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Options.this,List.class);
                startActivity(intent);
            }
        });

    }
}
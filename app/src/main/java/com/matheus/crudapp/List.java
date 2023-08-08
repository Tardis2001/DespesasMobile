package com.matheus.crudapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class List<E> extends AppCompatActivity {
    SQLiteDatabase db;
    Button search,back;
    ListView list;
    RecyclerView recyclerView;
    ArrayList<String> name,description,id,amount,date;
    MyAdapter adapter;
    Animation topAnim,bottomAnim;
    EditText searchfield;
    TextView textviewdespesa,despesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        searchall();


        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        back = findViewById(R.id.back);
        search = findViewById(R.id.search);
        searchfield = findViewById(R.id.searchfield);
        recyclerView = findViewById(R.id.list);
        textviewdespesa = findViewById(R.id.textviewdespesa);
        despesa =(TextView) findViewById(R.id.total);

        back.setAnimation(topAnim);
        despesa.setAnimation(topAnim);
        textviewdespesa.setAnimation(topAnim);
        search.setAnimation(topAnim);
        recyclerView.setAnimation(bottomAnim);
        searchfield.setAnimation(topAnim);
        adapter = new MyAdapter(this,name,id,description,amount,date);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(List.this,Options.class);
                startActivity(intent);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                search();
                adapter.setAmount(amount);
                adapter.setDate(date);
                adapter.setDescription(description);
                adapter.setId(id);
                adapter.setName(name);

                recyclerView.setAdapter(adapter);
            }
        });
    }
    private void searchall() {

        double total = 0;
        try {
            name = new ArrayList<>();
            description = new ArrayList<>();
            id = new ArrayList<>();
            amount = new ArrayList<>();
            date = new ArrayList<>();
            despesa =(TextView) findViewById(R.id.total);
            db = openOrCreateDatabase("Expensedb", MODE_PRIVATE, null);
            Cursor meuCursor = db.rawQuery("SELECT * FROM expense", null);
            meuCursor.moveToFirst();
            if (meuCursor.moveToFirst()) {
                do {
                    id.add(meuCursor.getString(0));
                    name.add(meuCursor.getString(1));
                    description.add(meuCursor.getString(4));
                    amount.add(meuCursor.getString(2));
                    date.add(meuCursor.getString(3));

                    total += Double.valueOf(meuCursor.getString(2));
                } while (meuCursor.moveToNext());
            }
            despesa.setText(String.valueOf(total));
            meuCursor.close();
            db.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void search() {
        name.clear();
        description.clear();
        amount.clear();
        id.clear();
        date.clear();

        double total = 0;
        try {
            db = openOrCreateDatabase("Expensedb", MODE_PRIVATE, null);

            if(String.valueOf(searchfield.getText()).equals("")) searchall();
            else {
                Log.d("Teste", "teste 2");
                String sql = "SELECT * FROM expense WHERE name = '" + searchfield.getText() + "'";
                Cursor meuCursor = db.rawQuery(sql, null);
                meuCursor.moveToFirst();
                if (meuCursor.getCount() > 0 && meuCursor != null) {
                    do {
                        id.add(meuCursor.getString(0));
                        name.add(meuCursor.getString(1));
                        description.add(meuCursor.getString(2));
                        amount.add(meuCursor.getString(3));
                        date.add(meuCursor.getString(4));

                        total += Double.valueOf(meuCursor.getString(2));
                    } while (meuCursor.moveToNext());

                    despesa.setText(String.valueOf(total));
                    meuCursor.close();
                }
            }
            db.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
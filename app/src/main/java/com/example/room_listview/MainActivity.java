package com.example.room_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnSave, btnCancel;
    private TextView tvAdd;
    private List<Address> listAddress=new ArrayList<Address>();;
    private AddressAdapter adapter;
    private ListView listView;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        tvAdd=findViewById(R.id.tvAdd);
        listView=findViewById(R.id.listView);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Addressdb")
                .allowMainThreadQueries()
                .build();
        AddressDao addressDao = db.addressDao();

//        addressDao.addAddress(new Address("Hồ Chí Minh"));
//        addressDao.addAddress(new Address("Hà Nội"));

        listAddress = addressDao.getAll();

        adapter=new AddressAdapter(MainActivity.this, listAddress, db);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=tvAdd.getText().toString();
                addressDao.addAddress(new Address(name));
                listAddress=addressDao.getAll();
                adapter=new AddressAdapter(MainActivity.this, listAddress, db);
                listView.setAdapter(adapter);
                tvAdd.setText("");
            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                tvAdd.setText("");
            }
        });

    }

}
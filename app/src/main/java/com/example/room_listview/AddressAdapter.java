package com.example.room_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<Address> listAddress;
    private AppDatabase appDatabase;

    public AddressAdapter(Context context, List<Address> listAddress, AppDatabase appDatabase) {
        this.context = context;
        this.listAddress = listAddress;
        this.appDatabase = appDatabase;
    }


    @Override
    public int getCount() {
        return listAddress.size();
    }

    @Override
    public Object getItem(int position) {
        return listAddress.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listAddress.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Address address=listAddress.get(position);
        convertView=LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        TextView tvName = convertView.findViewById(R.id.tvName);
        ImageButton imgbtnDelete = convertView.findViewById(R.id.imgbtnDelete);

        imgbtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.addressDao().deleteAddress(address);
                notifyDataSetChanged();
            }
        });

        tvName.setText(position+1 +". "+address.getAddress());
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        listAddress = appDatabase.addressDao().getAll();
        super.notifyDataSetChanged();
    }
}

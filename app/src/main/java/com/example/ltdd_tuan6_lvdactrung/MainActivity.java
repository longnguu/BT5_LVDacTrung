package com.example.ltdd_tuan6_lvdactrung;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.ltdd_tuan6_lvdactrung.Model.Adapter;
import com.example.ltdd_tuan6_lvdactrung.Model.Items;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<Items> items = new ArrayList<>();
    Adapter adapter;
    Boolean kt=false;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        Button btthem = (Button) findViewById(R.id.bt_them);
        EditText item_ten = (EditText) findViewById(R.id.edt_them);
        EditText item_mota = (EditText) findViewById(R.id.edt_hint);
        items.add(new Items("Quảng Trị","Information of item 1",R.drawable.img)) ;
        items.add(new Items("Hà Nội","Information of item 2",R.drawable.img_1)) ;
        items.add(new Items("Đà Nẵng","Information of item 3",R.drawable.img_2)) ;
        items.add(new Items("TP Hồ Chí Minh","Information of item 4",R.drawable.img_3)) ;
        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new Items(item_ten.getText().toString(),item_mota.getText().toString(),0));
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new Adapter(MainActivity.this,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Detail.class);
                intent.putExtra("dulieu",items.get(i).getTen());
                if (kt!=true)
                    startActivity(intent);
                kt=false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                kt=true;
                Xacnhanxoa(i);
                return false;
            }
        });
    }

    public void Xacnhanxoa(final int pos){
        AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(MainActivity.this);
        alertDiaLog.setTitle("Thong bao");
        alertDiaLog.setIcon(R.mipmap.ic_launcher);
        alertDiaLog.setMessage("Ban co muon xoa");
        alertDiaLog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                items.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        alertDiaLog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDiaLog.show();

    }
}
package com.example.coffeeGongbang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.example.coffeeGongbang.R.layout.list_item;

public class tomntoms_list extends AppCompatActivity {
    private ListView list;
    String[] name_list = new String[82];
    String[] price_list = new String[82];
    int [] draw = new int[82];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomntoms_list);

        String tmp = makeDataString(R.raw.tomtom_menu);
        makeClass(tmp);

        list = (ListView)findViewById(R.id.tomntoms);

        CustomList adapter = new CustomList(tomntoms_list.this);
        list.setAdapter(adapter);
    }

    class CustomList extends ArrayAdapter<String>{
        private final Activity context;
        public CustomList(Activity context){
            super(context, R.layout.list_item,name_list);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(list_item, null,true);
            ImageView image = (ImageView) rowView.findViewById(R.id.image);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView price = (TextView) rowView.findViewById(R.id.price);

            image.setImageResource(draw[position]);
            name.setText(name_list[position]);
            price.setText(price_list[position]);

            return rowView;
        }

    }

    public String makeDataString(int menu){
        InputStream inputStream = getResources().openRawResource(Integer.parseInt(String.valueOf(menu)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i=0;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp = byteArrayOutputStream.toString();

        return tmp;
    }

    public void makeClass(String tmp){
        String[] arr_t;
        arr_t = tmp.split("\n");

        int count=0;
        int n=0;
        while(n<(arr_t.length)){
            if(n>=902) break;
            name_list[count] = arr_t[n+1];
            price_list[count] = arr_t[n+4];
            int t = count+200;
            String a = "tomtom"+t;
            int res = getResources().getIdentifier(a, "drawable", getPackageName());
            draw[count] = res;
            n += 11;
            count++;
        }
    }
}


package com.example.w9_lab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static class Data {
        public String name;
        public int photo;
    }

    public class MainAdapter extends BaseAdapter {
        private Data[] data;
        private int view;

        public MainAdapter(Data[] data, int view) {
            this.data = data;
            this.view = view;
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(view, parent, false);

            TextView name = convertView.findViewById(R.id.name);
            name.setText(data[position].name);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setImageResource(data[position].photo);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner setup
        String[] transNameArray = new String[]{"腳踏車", "機車", "汽車", "巴士", "飛機", "輪船"};
        int[] transPhotoIdArray = new int[]{R.drawable.trans1, R.drawable.trans2,
                R.drawable.trans3, R.drawable.trans4,
                R.drawable.trans5, R.drawable.trans6};

        Data[] transData = new Data[transNameArray.length];
        for (int i = 0; i < transData.length; i++) {
            transData[i] = new Data();
            transData[i].name = transNameArray[i];
            transData[i].photo = transPhotoIdArray[i];
        }

        MainAdapter transAdapter = new MainAdapter(transData, R.layout.trans_list);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(transAdapter);

        // GridView setup
        String[] cubeeNameArray = new String[]{"哭哭", "發抖", "再見", "生氣", "暈倒", "飄笑", "很棒", "你好", "驚嚇", "大笑"};
        int[] cubeePhotoIdArray = new int[]{R.drawable.cubee1, R.drawable.cubee2, R.drawable.cubee3, R.drawable.cubee4,
                R.drawable.cubee5, R.drawable.cubee6, R.drawable.cubee7, R.drawable.cubee8,
                R.drawable.cubee9, R.drawable.cubee10};

        Data[] cubeeData = new Data[cubeeNameArray.length];
        for (int i = 0; i < cubeeData.length; i++) {
            cubeeData[i] = new Data();
            cubeeData[i].name = cubeeNameArray[i];
            cubeeData[i].photo = cubeePhotoIdArray[i];
        }

        MainAdapter cubeeAdapter = new MainAdapter(cubeeData, R.layout.cubee_list);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(cubeeAdapter);
        gridView.setNumColumns(3);

        // ListView setup
        String[] messageArray = new String[]{"訊息 1", "訊息 2", "訊息 3", "訊息 4", "訊息 5", "訊息 6"};
        ArrayAdapter<String> messageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageArray);

        ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(messageAdapter);
    }
}

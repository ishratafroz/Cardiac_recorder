package com.example.cardiacrecorder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {

    private Activity context;
    List<cardiaclist> cardiacList;

    public CustomAdapter(Activity context, List<cardiaclist> cardiacList) {
        super(context, R.layout.sample_layout,cardiacList);
        this.context = context;
        this.cardiacList = cardiacList;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);
        cardiaclist students = cardiacList.get(position);
        TextView t1 = view.findViewById(R.id.up1);
        t1.setText(students.getSys_v());
        TextView t2 = view.findViewById(R.id.up2);
        t2.setText(students.getDias_v());
        TextView t3 =view.findViewById(R.id.up3);
        t3.setText(students.getPulse_v());
        TextView t4 =view.findViewById(R.id.up4);
        t4.setText(students.getPulse_status());
        TextView t5 =view.findViewById(R.id.up51);TextView t6 =view.findViewById(R.id.up52);
        TextView t7 =view.findViewById(R.id.up5);
        t5.setText(students.getPressure_status());
        t6.setText(students.getD()); t7.setText(students.getT());
        return view;
    }
}

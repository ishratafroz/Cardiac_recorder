package com.example.cardiacrecorder;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Record extends AppCompatActivity {

    SimpleCursorAdapter simpleCursorAdapter;
   // DatabaseReference databaseReference;
    ListView listView;
   // List<cardiaclist> cardiacList;
    private CustomAdapter customAdapter;
    List<cardiaclist> mexampleitemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = findViewById(R.id.listview1);
       // cardiacList=new ArrayList<>();
        loaddata();

        customAdapter=new CustomAdapter(Record.this,mexampleitemlist);
        listView.setAdapter(customAdapter);
     //   databaseReference = FirebaseDatabase.getInstance().getReference("Resources");


//load();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardiaclist c=mexampleitemlist.get(position);
               // String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                AlertDialog.Builder builder = new AlertDialog.Builder(Record.this);
                View view2 = getLayoutInflater().inflate(R.layout.cust, null);
                Button update, delete, nothing;
                update = view2.findViewById(R.id.custom1);
                delete = view2.findViewById(R.id.custom2);
                nothing = view2.findViewById(R.id.custom3);
                builder.setView(view2);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(Record.this);

                        View view2 = getLayoutInflater().inflate(R.layout.custom_dialog, null);

                        EditText sys = view2.findViewById(R.id.syst);
                        EditText dias = view2.findViewById(R.id.diast);
                        EditText date = (EditText) view2.findViewById(R.id.date);
                        EditText time = (EditText) view2.findViewById(R.id.time);
                        EditText comment = (EditText) view2.findViewById(R.id.comments);
                        EditText pulse = (EditText) view2.findViewById(R.id.pulse_rate);

                        Button yes = (Button) view2.findViewById(R.id.yes_btn);
                        Button no = (Button) view2.findViewById(R.id.no_btn);
                        Calendar calendar = Calendar.getInstance();

                        Date currentDate = calendar.getTime();
                        String date_v = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
                        date.setText(date_v);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                        String time_v = simpleDateFormat.format(calendar.getTime());
                        time.setText(time_v);

                        builder.setView(view2);

                        AlertDialog alertDialog1 = builder.create();
                        alertDialog1.setCanceledOnTouchOutside(false);

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String sys_v = sys.getText().toString();
                                String dias_v = dias.getText().toString();
                                String comments_v = comment.getText().toString();
                                String pulse_v = pulse.getText().toString();
                                String d = date.getText().toString();
                                String t = time.getText().toString();

                                String pulse_status = "", pressure_status = "";
                                sys.setText("");
                                dias.setText("");
                                pulse.setText("");
                                date.setText("");
                                time.setText("");
                                comment.setText("");
                                if (TextUtils.isEmpty(sys_v)) {
                                    sys.setError("Required");
                                    return;
                                } else if (TextUtils.isEmpty(dias_v)) {
                                    dias.setError("Required");
                                    return;
                                } else if (TextUtils.isEmpty(pulse_v)) {
                                    pulse.setError("Required");
                                    return;
                                }
                                int v = Integer.parseInt(pulse_v);
                                if (v > 150) {
                                    v = 150;
                                }
                                if (Integer.parseInt(pulse_v) >= 60 && Integer.parseInt(pulse_v) <= 80) {
                                    pulse_status += "normal";
                                } else {
                                    pulse_status += "exceptional";
                                }

                                int x = Integer.parseInt(sys_v);
                                int y = Integer.parseInt(dias_v);

                                if ((x >= 90 && x <= 140) && (y >= 60 && y <= 90)) {
                                    pressure_status += "normal";
                                } else if (x > 140 || y > 90) {
                                    pressure_status += "high";
                                } else if (x < 90 || y < 60) {
                                    pressure_status += "low";
                                }
                                cardiaclist student = new cardiaclist(sys_v, dias_v, pulse_v, pulse_status, pressure_status,d,t);
                            //    databaseReference.child(userid).setValue(student);
                    //new
                                mexampleitemlist.set(position,student);
             PreferenceManager.getDefaultSharedPreferences(Record.this).edit().clear().commit();
                                savedata();
                                Toast.makeText(Record.this, "Record updated.", Toast.LENGTH_LONG).show();

//load();
                                alertDialog.dismiss();
                                alertDialog1.dismiss();
                            }
                        });
                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(getActivity(),"no",Toast.LENGTH_SHORT).show();
                                 alertDialog1.dismiss();
                            }
                        });
                        alertDialog1.show();

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // databaseReference.child(userid).removeValue();
                        mexampleitemlist.remove(position);
                        savedata();
             PreferenceManager.getDefaultSharedPreferences(Record.this).edit().clear().commit();
                        Toast.makeText(Record.this, "Record deleted.", Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                });
                nothing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }});  }

   /* @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                cardiacList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                { cardiaclist student=dataSnapshot.getValue(cardiaclist.class);cardiacList.add(student); }
                listView.setAdapter(customAdapter);
                 }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) { }});super.onStart();
    } */
   private void savedata()
   {
       SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
       SharedPreferences.Editor editor=sharedPreferences.edit();
       Gson gson=new Gson();
       String json=gson.toJson(mexampleitemlist);
       editor.putString("task list",json);
       editor.apply();

   }
   private void loaddata()
   {
       SharedPreferences sharedPreferences=getSharedPreferences("shared preferences",MODE_PRIVATE);
       Gson gson=new Gson();
       String json=sharedPreferences.getString("task list",null);
       Type type=new TypeToken<ArrayList<cardiaclist>>() {}.getType();
       mexampleitemlist=gson.fromJson(json,type);
       if(mexampleitemlist==null)
       {mexampleitemlist=new ArrayList<>();}
   }

}
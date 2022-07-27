package com.example.cardiacrecorder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class home extends AppCompatActivity {
    TextView t1,t2,t3; Button b1,b2;
    Dialog d1;
    ActivityHomeBinding binding;
    //notun
    List<cardiaclist> mexampleitemlist;
    private ListView mRecyclerview;
    private CustomAdapter madapter;
    //notun shesh
    //DatabaseReference databaseReference;   //cnt2 100; cnt3 add,cnt5 normal naki exc,cnt4record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        d1 = new Dialog(home.this);  //custom dialog box
        loaddata();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(view);
       // mRecyclerview=findViewById(R.id.recyclerview);

        binding.cnt2.setText("0");   //bpm dekhay
        binding.cnt5.setText("");  //normal naki exceptional
       binding.cnt6.setText("");
       // databaseReference = FirebaseDatabase.getInstance().getReference("Resources");
        binding.cnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(home.this);

                View view2 = getLayoutInflater().inflate(R.layout.custom_dialog,null);

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
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sys_v = sys.getText().toString();
                        String dias_v = dias.getText().toString();
                        String comments_v = comment.getText().toString();
                        String pulse_v = pulse.getText().toString();
                        String d=date.getText().toString(); String t=time.getText().toString();

                        String pulse_status="",pressure_status="";
                        sys.setText("");
                        dias.setText("");
                        pulse.setText("");
                        date.setText("");
                        time.setText("");
                        comment.setText("");

                        if(TextUtils.isEmpty(sys_v))
                        {
                            sys.setError("Required");
                            return;
                        }
                        else if(TextUtils.isEmpty(dias_v))
                        {
                            dias.setError("Required");
                            return;
                        }
                        else if(TextUtils.isEmpty(pulse_v))
                        {
                            pulse.setError("Required");
                            return;
                        }
                        int v = Integer.parseInt(pulse_v);
                        if(v>150)
                        {
                            v=150;
                        } //binding.cnt2.setText(pulse_v);

                        if(v>=60 && v<=80)
                        {
                            //binding.cnt5.setText("Normal"); //pulse
                            pulse_status+="Normal";
                        }
                        else
                        {
                            //binding.cnt5.setText("Exceptional");
                            pressure_status+="Exceptional";
                        }
                        int x = Integer.parseInt(sys_v);
                        int y = Integer.parseInt(dias_v);

                        if((x>=90 && x<=140) && (y>=60 && y<=90))
                        {
                            pressure_status+="normal";
                        }
                        else if(x>140 || y>90)
                        {
                            pressure_status+="high";
                        }
                        else if(x<90 || y<60)
                        {
                            pressure_status+="low";
                        }
                        binding.cnt6.setText("you have a "+pressure_status+" pressure");
            // String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();

              cardiaclist student = new cardiaclist(sys_v, dias_v, pulse_v, pulse_status, pressure_status,d,t);
                      //  databaseReference.child(t).setValue(student);
//notun
                          mexampleitemlist.add(student);
                        PreferenceManager.getDefaultSharedPreferences(home.this).edit().clear().commit();
                        savedata();
                        //notun
                        Toast.makeText(home.this, "New record added.", Toast.LENGTH_LONG).show();
                       alertDialog.dismiss();

                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(getActivity(),"no",Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
        binding.cnt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //notun
               // loaddata();
                Intent intent=new Intent(getApplicationContext(),Record.class);
                startActivity(intent);
            }
        });

    }
    //notun
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

    //notun

}
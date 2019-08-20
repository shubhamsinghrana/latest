package com.example.slumsurvey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Infoshow extends AppCompatActivity {
    DatabaseReference db;
    FirebaseAuth firebaseAuth;
    TextView t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19;
    ArrayList<String> m1,m2,m3,m4,m5,m6,mid;
    ListView mylist1;
    Infoshow.myhelperclass obj;
    String d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoshow);
        t0=findViewById(R.id.infoslumane);
        t1=findViewById(R.id.infoheadoffamily);
        t2=findViewById(R.id.infogender);
        t3=findViewById(R.id.infofathersname);
        t4=findViewById(R.id.infoage);
        t5=findViewById(R.id.infocategory);
        t6=findViewById(R.id.inforeligion);
        t7=findViewById(R.id.infomobilenumber);
        t8=findViewById(R.id.infoaddress);
        t9=findViewById(R.id.infofamincome);
        t10=findViewById(R.id.infonationality);
        t11=findViewById(R.id.infoaadhar);
        t12=findViewById(R.id.infoareaoccupy);
        t13=findViewById(R.id.infoareaconstructed);
        t14=findViewById(R.id.infoconditionofhouse);
        t15=findViewById(R.id.inforoom);
        t16=findViewById(R.id.infotoilet);
        t17=findViewById(R.id.infokitchen);
        t18=findViewById(R.id.infoyearofstaying);
        t19=findViewById(R.id.infoconsentsigned);
        mylist1=findViewById(R.id.mylistmember);


        m1=new ArrayList<>();
        m2=new ArrayList<>();
        m3=new ArrayList<>();
        m4=new ArrayList<>();
        m5=new ArrayList<>();
        m6=new ArrayList<>();
        mid=new ArrayList<>();





        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            d= (String) b.get("id");
           // Toast.makeText(this, (String) b.get("id"), Toast.LENGTH_SHORT).show();
        }

        fetchvalues();


        obj=new Infoshow.myhelperclass(this,android.R.layout.simple_list_item_1,mid);
         mylist1.setAdapter(obj);


        db= FirebaseDatabase.getInstance().getReference().child("forms").child(d);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t0.setText(dataSnapshot.child("headoffamily").child("nameofslum").getValue().toString());
                t1.setText(dataSnapshot.child("headoffamily").child("headoffamily").getValue().toString());
                t2.setText(dataSnapshot.child("headoffamily").child("gender").getValue().toString());
                t3.setText(dataSnapshot.child("headoffamily").child("fathername").getValue().toString());
                t4.setText(dataSnapshot.child("headoffamily").child("hofage").getValue().toString());
                t5.setText(dataSnapshot.child("headoffamily").child("category").getValue().toString());
                t6.setText(dataSnapshot.child("headoffamily").child("religion").getValue().toString());
                t7.setText(dataSnapshot.child("headoffamily").child("mobilenumber").getValue().toString());
                t8.setText(dataSnapshot.child("headoffamily").child("address").getValue().toString());
                t9.setText(dataSnapshot.child("headoffamily").child("familyincome").getValue().toString());
                t10.setText(dataSnapshot.child("headoffamily").child("nationality").getValue().toString());
                t11.setText(dataSnapshot.child("headoffamily").child("aadhar").getValue().toString());
                t12.setText(dataSnapshot.child("houseoffamily").child("area").getValue().toString());
                t13.setText(dataSnapshot.child("houseoffamily").child("areabuilt").getValue().toString());
                t14.setText(dataSnapshot.child("houseoffamily").child("conhouse").getValue().toString());
                t15.setText(dataSnapshot.child("houseoffamily").child("room").getValue().toString());
                t16.setText(dataSnapshot.child("houseoffamily").child("toilet").getValue().toString());
                t17.setText(dataSnapshot.child("houseoffamily").child("kitchen").getValue().toString());
                t18.setText(dataSnapshot.child("houseoffamily").child("yearsofstaying").getValue().toString());
                t19.setText(dataSnapshot.child("houseoffamily").child("consent").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private class myhelperclass extends ArrayAdapter<String>
    {

        public myhelperclass(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
            super(context, resource, objects);
        }

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View myrow=getLayoutInflater().inflate(R.layout.mylistmember,parent,false);
            TextView t11,t21,t31,t41,t51;
            t11=myrow.findViewById(R.id.m1);
            t21=myrow.findViewById(R.id.m2);
            t31=myrow.findViewById(R.id.m3);
            t41=myrow.findViewById(R.id.m4);
            t51=myrow.findViewById(R.id.m5);
            t11.setText(m1.get(position));
            t21.setText(m2.get(position));
            t31.setText(m3.get(position));
            t41.setText(m4.get(position));
            t51.setText(m5.get(position));



            return myrow;

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.edithead)
        {


        }
        else if(item.getItemId()==R.id.deletehousemem)
        {
            Intent a = new Intent(Infoshow.this,deletemember.class);
            a.putExtra("id", d);
            startActivity(a);



        }
        else if(item.getItemId()==R.id.addhousemem)
        {
            Intent a = new Intent(Infoshow.this,addnewmember.class);
            a.putExtra("id", d);
            startActivity(a);
            Infoshow.this.finish();

        }
        else if(item.getItemId()==R.id.edithouseinfo)
        {


        }
        else if(item.getItemId()==R.id.deleteentry)

        {

            firebaseAuth= FirebaseAuth.getInstance();
            db= FirebaseDatabase.getInstance().getReference().child("deleterequest");
            db.child(d).child("agentname").setValue(firebaseAuth.getCurrentUser().getEmail().toString());
            Toast.makeText(this, "Your Delete Request Has been Send", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(Infoshow.this,mysubmission.class);
            startActivity(a);
            Infoshow.this.finish();

        }
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent a = new Intent(Infoshow.this,mysubmission.class);
        startActivity(a);
        Infoshow.this.finish();

    }

    void fetchvalues() {



                    db =FirebaseDatabase.getInstance().getReference().child("forms").child(d).child("members");
                    db.addChildEventListener(new ChildEventListener() {

                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            //Toast.makeText(Infoshow.this, "hello", Toast.LENGTH_SHORT).show();
                            String aa = dataSnapshot.getKey();

                            String aahar1 = dataSnapshot.child("aahar").getValue(String.class);
                            String namea = dataSnapshot.child("nameofmember").getValue(String.class);
                            String age1 = dataSnapshot.child("age").getValue(String.class);
                            String gender1 = dataSnapshot.child("gender").getValue(String.class);
                            String relation1 = dataSnapshot.child("relation").getValue(String.class);
                            //Toast.makeText(Infoshow.this, namea, Toast.LENGTH_SHORT).show();

                            mid.add(aa);
                            m1.add(namea);
                            m2.add(age1);
                            m3.add(gender1);
                            m4.add(relation1);
                            m5.add(aahar1);
                            obj.notifyDataSetChanged();




                        }


                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });




    }
}


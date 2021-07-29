package com.example.alieandataproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

  final static String URL = "https://api.postalpincode.in/pincode/";
  EditText ed_pincode;
  TextView tv_error;
  Button btn;
  RecyclerView rv_data;
  List<PostOffice> list;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_screen);
    ed_pincode = findViewById(R.id.ed_pincode);
    btn = findViewById(R.id.button);
    rv_data = findViewById(R.id.rv_data);
    tv_error = findViewById(R.id.tv_error);

    rv_data.setVisibility(View.GONE);
    tv_error.setVisibility(View.VISIBLE);


    list = new ArrayList<>();

    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (!ed_pincode.getText().toString().equals("")) {
          int pin = Integer.parseInt(ed_pincode.getText().toString().trim());

          RequestQueue queue = Volley.newRequestQueue(HomeScreen.this);
          StringRequest request = new StringRequest(Request.Method.GET, URL + pin, new Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {
              try {
                JSONArray object = new JSONArray(response);
                for (int i=0;i<object.length();i++){
                  JSONObject object1 = object.getJSONObject(i);
                  String message = object1.getString("Message");
                  String status = object1.getString("Status");
                  JSONArray array = object1.getJSONArray("PostOffice");
                  if (status.equals("Success")){
                    for (int j = 0;j<array.length();j++){
                      JSONObject object2 = array.getJSONObject(j);
                      PostOffice postOffice = new PostOffice();
                      postOffice.setName(object2.getString("Name"));
                      postOffice.setDescription(object2.getString("Description"));
                      postOffice.setBranchType(object2.getString("BranchType"));
                      postOffice.setDeliveryStatus(object2.getString("DeliveryStatus"));
                      postOffice.setCircle(object2.getString("Circle"));
                      postOffice.setDistrict(object2.getString("District"));
                      postOffice.setDivision(object2.getString("Division"));
                      postOffice.setRegion(object2.getString("Region"));
                      postOffice.setBlock(object2.getString("Block"));
                      postOffice.setState(object2.getString("State"));
                      postOffice.setCountry(object2.getString("Country"));
                      postOffice.setPincode(object2.getString("Pincode"));

                      list.add(postOffice);


                    }
                    Log.e("Volley_onResponse", message);
                  }else {
                    Log.e("Volley_onResponse", message);
                  }
                }

                Rv_Adapter adapter = new Rv_Adapter(list,HomeScreen.this);
                rv_data.setLayoutManager(new LinearLayoutManager(HomeScreen.this,LinearLayoutManager.VERTICAL,false));
                rv_data.setAdapter(adapter);

                rv_data.setVisibility(View.VISIBLE);
                tv_error.setVisibility(View.GONE);

              } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Volley_onResponse_JSONException", e.getMessage());
              }


            }
          }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              Log.e("Volley_onErrorResponse", error.getMessage());
            }
          });

          queue.add(request);
        }
        else if (ed_pincode.getText().toString().equals("")){
          ed_pincode.setError("Enter Field");
        }
      }
    });

  }
}
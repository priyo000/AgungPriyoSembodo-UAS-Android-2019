package com.example.koneksiwebservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceJSON extends AppCompatActivity {
    private EditText input;
    private TextView tnama;
    private TextView tid;
    private TextView tasal;
    private TextView tkamar;
    private RequestQueue ueue;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_json);

        ueue = Volley.newRequestQueue(this);
        Button btnJson = findViewById(R.id.btnparse);
        input = findViewById(R.id.inputnya);
        tnama = findViewById(R.id.tvnama);
        tid = findViewById(R.id.tvid);
        tasal = findViewById(R.id.tvasal);
        tkamar = findViewById(R.id.tvkamar);

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.getText().equals("")){
                    index =0;
                }else {
                    index = Integer.parseInt(String.valueOf(input.getText()))-1;
                }
                uraiJSON(index);
            }
        });
    }

    private void uraiJSON(final int index){
        String url = "http://192.168.5.35/XML000/JSONUAS.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                        JSONObject jsonnye = response.getJSONObject(index);

                        String idnye = jsonnye.getString("id");
                        String namanye = jsonnye.getString("nama");
                        String asalnye = jsonnye.getString("asal_daerah");
                        String kamarnye = jsonnye.getString("kamar");

                        tid.setText(idnye);
                        tnama.setText(namanye);
                        tasal.setText(asalnye);
                        tkamar.setText(kamarnye);


                }
                catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        ueue.add(request);
    }
}

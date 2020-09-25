package com.example.myapplication;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.textfield.TextInputLayout;


public class ui_signup_info extends Fragment {

    RequestQueue requestQueue;
    StringRequest stringRequest;
    TextInputLayout username, password;
    Button bttn_signup;
    CardView ui_signup_final;

    TextView greetfinal, greetfinalform;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ui_signup_info() {

    }


    public static ui_signup_info newInstance(String param1, String param2) {
        ui_signup_info fragment = new ui_signup_info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ui_signup_info, container, false);

            ui_signup_final = view.findViewById(R.id.ui_signup_info);
            greetfinal = view.findViewById(R.id.tv_greetingfinalstep);
            greetfinalform = view.findViewById(R.id.tv_greetfillFinal);
        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .playOn(ui_signup_final);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .delay(10)
                .playOn(greetfinal);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .delay(10)
                .playOn(greetfinalform);




         // RECEIVE OBJECT CLASS FROM LAST FRAGMENT
            Bundle bundle = getArguments();
            final User newUser = (User) bundle.getSerializable("newUser");



        //INITIALIZE USERNAME AND PASSWORD EDT
            username = view.findViewById(R.id.edt_username);
            password = view.findViewById(R.id.edt_password);
            bttn_signup = view.findViewById(R.id.bttn_Signup);




            bttn_signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestQueue = Volley.newRequestQueue(getContext());

                    stringRequest = new StringRequest(Request.Method.POST,
                            "https://xototlprojects.com/AndroidPHP/registeruser.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("success")) {
                                        Toast.makeText(getContext(), "User Registered", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(),
                                            error.getMessage(),
                                            Toast.LENGTH_LONG).show();

                                }
                            })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> map = new HashMap<>();

                            map.put("username", username.getEditText().getText().toString());
                            map.put("password",password.getEditText().getText().toString());
                            map.put("firstname",newUser.getFname());
                            map.put("middlename", newUser.getMname());
                            map.put("lastname", newUser.getLname());
                            map.put("dateofbirth", newUser.getDob());
                            map.put("address", newUser.getAddress());

                            return map;
                        }
                    };

                    requestQueue.add(stringRequest);
                }
            });


        return view;
    }
}
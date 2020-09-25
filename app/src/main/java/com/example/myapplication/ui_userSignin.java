package com.example.myapplication;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ui_userSignin extends Fragment {

    CardView ui_userSignin;
    TextView greetHello, greetSignup;
    Button bttn_signup;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ui_userSignin() {

    }


    public static ui_userSignin newInstance(String param1, String param2) {
        ui_userSignin fragment = new ui_userSignin();
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

       View view = inflater.inflate(R.layout.fragment_ui_user_signin, container, false);

       ui_userSignin = view.findViewById(R.id.ui_userlogin);
       greetHello = view.findViewById(R.id.tv_greetingHello);
       greetSignup = view.findViewById(R.id.tv_greetingSignup);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .playOn(ui_userSignin);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .delay(100)
                .playOn(greetHello);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .delay(100)
                .playOn(greetSignup);


        // BUTTON SIGNUP
        bttn_signup = view.findViewById(R.id.bttn_Signup);

        bttn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLyt_firstdisplay, new ui_usersignup())
                        .commit();
            }
        });

       return view;
    }
}
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
import com.google.android.material.textfield.TextInputLayout;


public class ui_usersignup extends Fragment {

    CardView ui_usersignup;
    TextView greetSignup, greetFillup;
    Button bttn_next;

    TextInputLayout fname, mname, lname, dob, address;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ui_usersignup() {

    }

    public static ui_usersignup newInstance(String param1, String param2) {
        ui_usersignup fragment = new ui_usersignup();
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

        View view = inflater.inflate(R.layout.fragment_ui_usersignup, container, false);

        greetSignup = view.findViewById(R.id.tv_greetingSignup);
        greetFillup = view.findViewById(R.id.tv_greetingfillup);

        ui_usersignup = view.findViewById(R.id.ui_usersignup);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .playOn(ui_usersignup);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .playOn(greetSignup);

        YoYo.with(Techniques.BounceInUp)
                .duration(1500)
                .delay(10)
                .playOn(greetFillup);

        // BUTTON NEXT

        fname = view.findViewById(R.id.edt_fName);
        mname = view.findViewById(R.id.edt_mName);
        lname = view.findViewById(R.id.edt_lName);
        dob = view.findViewById(R.id.edt_dob);
        address = view.findViewById(R.id.edt_address);




        bttn_next = view.findViewById(R.id.bttn_next);

        bttn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ui_signup_info ui_signup_info = new ui_signup_info();

                Bundle bundle = new Bundle();
                User newUser = new User(
                        fname.getEditText().getText().toString(),
                        mname.getEditText().getText().toString(),
                        lname.getEditText().getText().toString(),
                        dob.getEditText().getText().toString(),
                        address.getEditText().getText().toString()
                         );
                bundle.putSerializable("newUser", newUser);
                ui_signup_info.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frameLyt_firstdisplay, ui_signup_info)
                        .addToBackStack(null)
                        .commit();

            }
        });
        return view;
    }
}
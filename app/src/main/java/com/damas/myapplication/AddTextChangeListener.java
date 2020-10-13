package com.damas.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.damas.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTextChangeListener extends AppCompatActivity {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    String user;
    String pass;
    String esubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text_change_listener);
        ButterKnife.bind(this);
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!etUser.getText().toString().isEmpty()){
                    user = etUser.getText().toString();
                    Log.e("Hasil",user);
                }else {
                    etUser.setError("Isi dulu dong");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
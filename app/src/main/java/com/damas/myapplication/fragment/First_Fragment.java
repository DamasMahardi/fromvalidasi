package com.damas.myapplication.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.damas.myapplication.R;
import com.damas.myapplication.Model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public class First_Fragment extends Fragment {
    View view;
    User user;


    @BindView(R.id.btn_masuk)
    Button btnMasuk;

    //edit tex
    @BindView(R.id.et_nama)
    EditText etNama;
    @BindView(R.id.et_nim)
    EditText etNim;
    @BindView(R.id.et_tempat)
    EditText etTempat;
    @BindView(R.id.et_tanggal)
    EditText etTanggal;

    //Text View
    @BindView(R.id.tv_nim)
    TextView tvNim;
    @BindView(R.id.tv_nama)
    TextView tvNama;
    @BindView(R.id.tv_errorNama)
    TextView tverrorNama;
    @BindView(R.id.tv_errorNim)
    TextView tvErrorNim;
    @BindView(R.id.tv_errorTempat)
    TextView tverrorTempat;
    @BindView(R.id.tv_errorTanggal)
    TextView tverrorTanggal;


    //string global variable
    String nim= "";
    String nama= "";
    String tempat = "";
    String tanggal="";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    




    public First_Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_,container,false);
        ButterKnife.bind(this,view);
        validasiEditText();
        onClick();

        // Inflate the layout for this fragment
        return view;

    }

    private void onClick() {
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subValidation();
                if (!nim.isEmpty() && !nama.isEmpty() && !tempat.isEmpty() && !tanggal.isEmpty()){
                    Log.e("Nim :", nim);
                    Log.e("Nama : ",nama);
                    Log.e("Tempat :",tempat);
                    Log.e("Tanggal :",tanggal);
                    ResultFragment result = new ResultFragment();

                    User user = new User();
                    Bundle bundle = new Bundle();

                    user.setNim(nim);
                    user.setNama(nama);
                    user.setTempat(tempat);
                    user.setTanggal(tanggal);
                    bundle.putSerializable("user",user);
                    result.setArguments(bundle);
                    moveResultFragment(result);
                    Toasty.success(getActivity(),"Berhasil mengisi data",Toasty.LENGTH_SHORT).show();


                }else {
                    Toasty.info(getContext(), "Harap di isi dulu",Toasty.LENGTH_SHORT, true).show();
                }
                closeKeyboard();
            }
        });
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
                showDatevalid();
            }
        });
    }

    private void moveResultFragment(ResultFragment resultFragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameActivity, resultFragment).commit();

    }


    private void showDatevalid() {
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                etTanggal.setText(simpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

     void subValidation() {
        validNim();
        validNama();
        validTempat();
        validTanggal();


    }

    private boolean validNama() {
        //String pNama = ".*[A-Z].*";
        if (!etNama.getText().toString().isEmpty()){
            nama = etNama.getText().toString();
            etNama.setSelection(etNama.getText().length());
        }else {
            etNama.setError("Masukan nama anda");
            return false;
        }
        return true;
    }

    private boolean validNim() {
        if (!etNim.getText().toString().isEmpty() && etNim.length() == 9){
            nim = etNim.getText().toString();
            tvErrorNim.setError(null);
            etNim.setSelection(etNim.getText().length());
        }else {
            etNim.setError("Enter NIM lengt 9 digits");
            etNim.requestFocus();
            return  false;
        }
        return true;

    }
    private boolean validTempat(){
        if (!etTempat.getText().toString().isEmpty()){
            tempat = etTempat.getText().toString();

            etTempat.setSelection(etTempat.getText().length());
        }else {
            etTempat.setError(" Enter your tempat");
            return false;
        }
        return true;

    }
    private boolean validTanggal(){
        Log.e("Tanggal = ", tanggal);
        if (!etTanggal.getText().toString().isEmpty()){
            tanggal = etTanggal.getText().toString();
            etTanggal.setError(null);
            etTanggal.setSelection(etTanggal.getText().length());
        }else {
            etTanggal.setError("Enter your data");
            return  false;
        }
        return true;

    }

    private void validasiEditText() {
        tvErrorNim.setVisibility(View.VISIBLE);
        etNim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validNim();

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvErrorNim.setText(0+ s.toString().length()+"/9");

            }
        });
        tverrorNama.setVisibility(View.VISIBLE);
        etNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validNama();

            }

            @Override
            public void afterTextChanged(Editable s) {
                tverrorNama.setText(0+s.toString().length()+"/30");

            }
        });
        tverrorTempat.setVisibility(View.VISIBLE);
        etTempat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validTempat();

            }

            @Override
            public void afterTextChanged(Editable s) {
                tverrorTempat.setText(0+s.toString().length()+"/30");

            }
        });
        tverrorTanggal.setVisibility(View.VISIBLE);
        etTanggal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validTempat();

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!etTanggal.getText().toString().isEmpty()){
                    etTanggal.setError(null);
                    tverrorTanggal.setVisibility(View.GONE);
                }

            }
        });



    }


    private void closeKeyboard(){
        View view = this.getActivity().getCurrentFocus();
        if (view !=null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
package com.example.outland;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.goodiebag.pinview.Pinview;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import org.w3c.dom.Text;
import static com.example.outland.R.color.alphaColorPrimary;
import static com.example.outland.R.color.colorPrimary;


public class RegisterActivity extends AppCompatActivity {


    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;
    //firebase auth object
    private FirebaseAuth mAuth;


    Pinview pin;
    Button registerbtn;
    ConstraintLayout registerLayout;
    ConstraintLayout otpLayout;
    EditText phoneEditText;
    EditText nameEditText;
    String phoneNumber;
    String countryCode;
    private ProgressBar progressBar;
    private FirebaseFirestore firebaseFirestore;
   // private FirebaseAuth firebaseAuth;
    private Map<Object,String> userdata;
    private TextView otpTextView;
    // public static boolean onBackPress = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing objects
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_bar);
        pin = (Pinview) findViewById(R.id.pinview);
        pin.setTextColor(Color.WHITE);
        registerbtn = (Button) findViewById(R.id.register_btn);
        registerLayout = (ConstraintLayout) findViewById(R.id.register_screen);
        otpLayout = (ConstraintLayout) findViewById(R.id.otp_screen);
        phoneEditText = (EditText) findViewById(R.id.phoneText);
        nameEditText = (EditText)findViewById(R.id.nameText);
        firebaseFirestore = FirebaseFirestore.getInstance();
        //firebaseAuth = FirebaseAuth.getInstance();
        otpTextView = (TextView) findViewById(R.id.otp_text);



        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        // Gets phone number and change us to otp screen
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                countryCode = "+91";
                phoneNumber = countryCode + phoneEditText.getText().toString().trim();
                sendVerificationCode(phoneNumber);
                if (phoneNumber.isEmpty() || phoneNumber.length() < 10 ){

                    phoneEditText.setError("Please enter a valid number.");
                    phoneEditText.requestFocus();

                    return;



                }

                otpTextView.append(" " + phoneNumber);
                //Toast.makeText(RegisterActivity.this, nameEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                sendVerificationCode(phoneNumber);
                registerLayout.setVisibility(View.GONE);
                otpLayout.setVisibility(View.VISIBLE);


            }
        });

        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button

        findViewById(R.id.verify_btn).setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                String code = pin.getValue();

                if (code.isEmpty() || code.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Please enter valid OTP", Toast.LENGTH_SHORT).show();
                    pin.requestFocus();
                    return;
                }

                //verifying the code entered manually

                verifyVerificationCode(code);
            }
        });






    }

    private void sendVerificationCode(String number) {
        progressBar.setVisibility(View.VISIBLE);
        // Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                30,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                pin.setValue(code);
                //verifying the code
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {



            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

            //signing the user
            signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            userdata = new HashMap<>();
                            userdata.put("Name", nameEditText.getText().toString());
                            userdata.put("Phone Number", phoneNumber);


                            firebaseFirestore.collection("USERS").document(mAuth.getUid()).set(userdata)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){

                                                CollectionReference userDataReference =  firebaseFirestore.collection("USERS").document(mAuth.getUid()).collection("USER_DATA");

                                                ////// MAPS

                                                Map<String, Object> cartMap = new HashMap<>();
                                                cartMap.put("list_size", (long) 0);

                                                Map<String, Object> myAddressesMap = new HashMap<>();
                                                cartMap.put("list_size", (long) 0);

                                                /////// MAPS

                                                //////// LISTS

                                                final List<String> docNames = new ArrayList<>();
                                                docNames.add("MY_CART");
                                                docNames.add("MY_ADDRESSES");

                                                List<Map<String, Object>> docFields = new ArrayList<>();
                                                docFields.add(cartMap);
                                                docFields.add(myAddressesMap);


                                                ////////// LISTS


                                                for (int x = 0; x < docNames.size(); x++){

                                                    final int finalX = x;
                                                    userDataReference.document(docNames.get(x)).set(docFields.get(x)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                            if (task.isSuccessful()){
                                                                if (finalX == docNames.size() - 1) {

                                                                    mainIntent();

                                                                }
                                                            }else {

                                                                String error = task.getException().getMessage();
                                                                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();

                                                            }

                                                        }
                                                    });

                                                }


                                            }else {

                                                String error = task.getException().getMessage();
                                                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();


                                            }

                                        }
                                    });




                        } else {

                            //verification unsuccessful.. display an error message
                            // Toast.makeText(RegisterActivity.this, "Verification Code is wrong", Toast.LENGTH_LONG).show();


                            String message = "Somthing is wrong, we will fix it soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Verification Code is wrong";
                            }
                            Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }

    }

    private void checkInputs(){

        if (!TextUtils.isEmpty(nameEditText.getText())){

            if (!TextUtils.isEmpty(phoneEditText.getText())){

                registerbtn.setEnabled(true);
                registerbtn.setTextColor(getResources().getColor(colorPrimary));



            }else{

                registerbtn.setEnabled(false);
                registerbtn.setTextColor(getResources().getColor(alphaColorPrimary));

            }


        } else {

            registerbtn.setEnabled(false);
            registerbtn.setTextColor(getResources().getColor(alphaColorPrimary));


        }

    }

    private void mainIntent(){

        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

}

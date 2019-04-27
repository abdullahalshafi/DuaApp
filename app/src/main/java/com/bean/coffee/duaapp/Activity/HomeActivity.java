package com.bean.coffee.duaapp.Activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bean.coffee.duaapp.Class.SharedPref;
import com.bean.coffee.duaapp.Model.HomePresenterImpl;
import com.bean.coffee.duaapp.Presenter.HomePresenter;
import com.bean.coffee.duaapp.R;
import com.bean.coffee.duaapp.View.HomeView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView {

    //vars
    public static final int MULTIPLE_PERMISSIONS = 10;
    HomePresenter homePresenter;
    ProgressDialog progressDialog, subscriptionDialog;
    private static Handler handler = new Handler(Looper.getMainLooper());

    String[] permissions = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE};

    //widgets
    LinearLayout dailyDuaLinearLayout, romjanDuaLinearLayout, sehriDuaLinearLayout, todoLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initUi();
        onclickListeners();


        if (checkPermissions()) {
            //  permissions  granted.

        }

//        String subscriptionData = SharedPref.read(SharedPref.SUBSCRIPTION_DATA, "");
//        if(subscriptionData.equals("SUBSCRIBED")){
//
//        }else{
//            if(checkConnectivity()){
//                subscriptionDialog = ProgressDialog.show(this, "Please wait.",
//                        "Checking subscription..!", true);
//                homePresenter.subscriberStatusCheck();
//            }else {
//                showNetworkError();
//            }
//        }

    }

    private void initUi() {
        dailyDuaLinearLayout = findViewById(R.id.home_daily_dua_linear_layout);
        romjanDuaLinearLayout = findViewById(R.id.home_romjan_dua_linear_layout);
        sehriDuaLinearLayout = findViewById(R.id.home_sehri_and_iftar_dua_linear_layout);
        todoLinearLayout = findViewById(R.id.home_to_do_linear_layout);

        homePresenter = new HomePresenterImpl(this, HomeActivity.this);
        SharedPref.init(getApplicationContext());
    }

    private void onclickListeners() {
        dailyDuaLinearLayout.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, DailyDuaActivity.class)));
        romjanDuaLinearLayout.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, RamadanActivity.class)));
        sehriDuaLinearLayout.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, SehriActivity.class)));
        todoLinearLayout.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, TodoActivity.class)));
    }

    public void unsubscribe(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you really want to unsubscribe?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> homePresenter.unSubscribe())
                .setNegativeButton(android.R.string.no, (dialog, which) -> {

                })
                .setCancelable(false)
                .show();

    }


    private void fireSubscribeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Please subscribe to continue")
                .setMessage("It will cost you 2 tk per day")
                .setPositiveButton("OK", (dialog, which) -> homePresenter.pushSubscribeData())
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                    finish();
                    System.exit(0);
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void subscriberStatusCheck(String status) {

        // Toast.makeText(this, status, Toast.LENGTH_SHORT).show();

        if (status.equals("UNREGISTERED")) {
            if (subscriptionDialog != null && subscriptionDialog.isShowing()) {
                subscriptionDialog.dismiss();
            }
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            fireSubscribeDialog();
        } else if (status.equals("PENDING CHARGE")) {
            if (subscriptionDialog != null && subscriptionDialog.isShowing()) {
                subscriptionDialog.dismiss();
            }
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(this, "You don't have sufficient balance to subscribe", Toast.LENGTH_SHORT).show();
            fireSubscribeDialog();
        } else if (status.equals("invalidUser")) {
            if (subscriptionDialog != null && subscriptionDialog.isShowing()) {
                subscriptionDialog.dismiss();
            }
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(HomeActivity.this, "Subscription was unsuccessful, Please try again", Toast.LENGTH_SHORT).show();
            fireSubscribeDialog();
        } else if (status.equals("REGISTERED")) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (subscriptionDialog != null && subscriptionDialog.isShowing()) {
                subscriptionDialog.dismiss();
            }
            SharedPref.write(SharedPref.SUBSCRIPTION_DATA, "SUBSCRIBED");
        }
    }

    @Override
    public void delayProgressDialog() {

        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Authenticating subscription..! \nThis may take a minute", true);

        handler.postDelayed(() -> {
            if (progressDialog.isShowing()) {

                homePresenter.subscriberStatusCheck();
            }
        }, 5000);
    }

    @Override
    public void unsubscribeUser() {
        SharedPref.write(SharedPref.SUBSCRIPTION_DATA, "UNSUBSCRIBED");
        Toast.makeText(this, "Successfully unsubscribed", Toast.LENGTH_SHORT).show();
        fireSubscribeDialog();
    }

    private void showNetworkError() {
        Dialog dialog = new Dialog(this);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

        builder.setMessage("You are offline please check your internet connection and retry. \nInternet connection is required to" +
                " check the subscription.")
                .setTitle("No Internet Connection")
                .setPositiveButton("Refresh", (dialog1, which) -> {
                    finish();
                    startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                });

        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            //we are connected to a network
            return true;
        } else
            return false;
    }

    //check permission
    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permissions granted.
                } else {
                    String permission = "";
                    for (String per : permissions) {
                        permission += "\n" + per;
                    }

                    Log.d("permissions", "onRequestPermissionsResult: " + permission);
                    // permissions list of don't granted permission
                }
                return;
            }
        }
    }


}

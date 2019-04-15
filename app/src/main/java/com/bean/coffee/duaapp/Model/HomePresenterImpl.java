package com.bean.coffee.duaapp.Model;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import androidx.core.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;

import com.bean.coffee.duaapp.Class.VolleySingleton;
import com.bean.coffee.duaapp.Presenter.HomePresenter;
import com.bean.coffee.duaapp.View.HomeView;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

import java.util.Map;

public class HomePresenterImpl implements HomePresenter {


    private HomeView homeView;
    private static final String SERVER_URL= "http://dailydua.firewoods.co/api.php";
    private Context context;

    private String appId;

    public HomePresenterImpl(HomeView homeView,Context context){
        this.homeView=homeView;
        this.context=context;
        this.appId= Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    @Override
    public void pushSubscribeData() {

        String message="ram968 "+ appId;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("21213", null, message, null, null);
            homeView.delayProgressDialog();

        }catch (Exception e) {
           // Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            Log.e("sms Error",e.toString());

            Dialog dialog = new Dialog(context);

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

            builder.setMessage("Please allow all the required permissions")
                    .setPositiveButton("Exit", (dialog1, which) -> System.exit(0));

            dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);
        }


    }

    @Override
    public void subscriberStatusCheck() {

        JSONObject js = new JSONObject();
        try {
            js.put("requestId", appId);
            js.put("action", "statusCheck");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, SERVER_URL,js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    Log.d("reply", "onResponse: "+response);

                    String value=response.getString("status");

                    if(value!=null && !value.isEmpty() && !value.equals("null")){
                        homeView.subscriberStatusCheck(value);
                    }else{
                        homeView.subscriberStatusCheck("UNREGISTERED");
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("volley error", "Error: " + error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void unSubscribe() {

        JSONObject js = new JSONObject();
        try {
            js.put("requestId", appId);
            js.put("action", "unsubscribe");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, SERVER_URL,js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    Log.d("reply", "onResponse: "+response);
                    String value= response.getString("status");
                    if(value!=null && !value.isEmpty() && !value.equals("null")){
                        if(value.equals("UNREGISTERED")){
                            homeView.unsubscribeUser();
                        }else{
                            Toast.makeText(context, "Please try again, something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(context, "Please try again, something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d("volley error", "Error: " + error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

}

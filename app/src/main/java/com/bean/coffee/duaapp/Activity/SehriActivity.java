package com.bean.coffee.duaapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bean.coffee.duaapp.R;

public class SehriActivity extends AppCompatActivity {

    //widgets
    private Button nextBTN,prevBTN,backBTN;
    private TableLayout ramadanSehriIftarTime1,ramadanSehriIftarTime2,ramadanSehriIftarTime3,ramadanSehriIftarTime4,
            ramadanSehriIftarTime5,ramadanSehriIftarTime6;
    private TextView ramadanDayType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehri);

        nextBTN=findViewById(R.id.nextBTN);
        prevBTN=findViewById(R.id.prevBTN);
        backBTN=findViewById(R.id.backBTN);

        ramadanDayType=findViewById(R.id.ramadanDayType);

        ramadanSehriIftarTime1=findViewById(R.id.ramadanSehriIftarTime1);
        ramadanSehriIftarTime2=findViewById(R.id.ramadanSehriIftarTime2);
        ramadanSehriIftarTime3=findViewById(R.id.ramadanSehriIftarTime3);
        ramadanSehriIftarTime4=findViewById(R.id.ramadanSehriIftarTime4);
        ramadanSehriIftarTime5=findViewById(R.id.ramadanSehriIftarTime5);
        ramadanSehriIftarTime6=findViewById(R.id.ramadanSehriIftarTime6);

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ramadanSehriIftarTime1.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime1.setVisibility(View.GONE);
                    ramadanSehriIftarTime2.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("রহমতের ১০দিন");
                }
                else if(ramadanSehriIftarTime2.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime2.setVisibility(View.GONE);
                    ramadanSehriIftarTime3.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("মাগফেরাতের ১০দিন");
                }
                else if(ramadanSehriIftarTime3.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime3.setVisibility(View.GONE);
                    ramadanSehriIftarTime4.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("মাগফেরাতের ১০দিন");
                }
                else if(ramadanSehriIftarTime4.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime4.setVisibility(View.GONE);
                    ramadanSehriIftarTime5.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("নাজাতের ১০দিন");
                }
                else if(ramadanSehriIftarTime5.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime5.setVisibility(View.GONE);
                    ramadanSehriIftarTime6.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("নাজাতের ১০দিন");
                }

            }
        });

        prevBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ramadanSehriIftarTime6.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime6.setVisibility(View.GONE);
                    ramadanSehriIftarTime5.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("নাজাতের ১০দিন");
                }
                else if(ramadanSehriIftarTime5.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime5.setVisibility(View.GONE);
                    ramadanSehriIftarTime4.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("মাগফেরাতের ১০দিন");
                }
                else if(ramadanSehriIftarTime4.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime4.setVisibility(View.GONE);
                    ramadanSehriIftarTime3.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("মাগফেরাতের ১০দিন");
                }
                else if(ramadanSehriIftarTime3.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime3.setVisibility(View.GONE);
                    ramadanSehriIftarTime2.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("রহমতের ১০দিন");
                }
                else if(ramadanSehriIftarTime2.getVisibility()==View.VISIBLE) {

                    ramadanSehriIftarTime2.setVisibility(View.GONE);
                    ramadanSehriIftarTime1.setVisibility(View.VISIBLE);
                    ramadanDayType.setText("রহমতের ১০দিন");
                }

            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

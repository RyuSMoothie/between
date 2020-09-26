package com.example.serverpro;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class SubActivity extends AppCompatActivity { //이 클래스 이름이 SubActivity

    private TextView tv, ck;
    int rnum, ck_main = 0;
    private int ck_sub = 0;
    private String str;
    int sub_num = 0;
    //하은코드

    //예현코드 : 자리 위치
    EditText title;
    EditText place;
    EditText content;
    TextView tv1;


    String year_1;
    String month_1;
    String day_1;
    String date1;

    String year_2;
    String month_2;
    String day_2;
    String date2;

    String year_3;
    String month_3;
    String day_3;
    String date3;


    int Hour = 0, Minute = 0;
    int year, month, day;
    int Hour1, Minute1, year1, month1, day1;
    TextView mTxtDate;
    TextView mTxtTime;
    TextView mTxtDate1;
    TextView mTxtTime1;
    Switch aSwitch;


    String alarmtime;
    String alarmdate;
   // String time1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //텍스튜뷰 연결
        mTxtDate = (TextView) findViewById(R.id.txtdate);
        mTxtTime = (TextView) findViewById(R.id.txttime);
        mTxtDate1 = (TextView) findViewById(R.id.txtdate1);
        mTxtTime1 = (TextView) findViewById(R.id.txttime1);


        ImageView imageView = (ImageView) findViewById(R.id.imageview);

        // drawable에 있는 이미지를 지정합니다.
        imageView.setImageResource(R.drawable.page2);//page2는 그림 이름

        //aSwitch = (Switch) findViewById(R.id.switch1);


        mTxtTime.setText(String.format(""));
        mTxtTime1.setText(String.format(""));
        //Time
        Button button6 = findViewById(R.id.btn6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog
                        = new TimePickerDialog(SubActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        /*String apm = "";
                        if (hourOfDay < 12) {
                            if (hourOfDay == 0) hourOfDay = 12;
                            apm = "AM";
                        } else {
                            if (hourOfDay != 12) hourOfDay -= 12;
                            apm = "PM";
                        }*/
                        //mTxtTime.setText(String.format("%d:%02d %s", hourOfDay, minute, apm)); //시,분 받아서 보내줌
                        mTxtTime.setText(String.format("%02d%02d00", hourOfDay, minute));
                    }

                }, Hour, Minute, false);

                timePickerDialog.show();
            }
        });

        //date
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        Button button5 = findViewById(R.id.btn5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SubActivity.this, dateSetListener, year, month, day).show();
            }
        });
        //Time1
        Button button8 = findViewById(R.id.btn8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog1
                        = new TimePickerDialog(SubActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay1, int minute1) {
                       /* String apm1 = "";
                        if (hourOfDay1 < 12) {
                            if (hourOfDay1 == 0) hourOfDay1 = 12;
                            apm1 = "AM";
                        } else {
                            if (hourOfDay1 != 12) hourOfDay1 -= 12;
                            apm1 = "PM";
                        }*/
                        mTxtTime1.setText(String.format("%02d%02d00", hourOfDay1, minute1)); //시,분 받아서 보내줌
                    }

                    {
                    }
                }, Hour1, Minute1, false);

                timePickerDialog1.show();
            }
        });

        //date1
        GregorianCalendar calendar1 = new GregorianCalendar();
        year1 = calendar1.get(Calendar.YEAR);
        month1 = calendar1.get(Calendar.MONTH);
        day1 = calendar1.get(Calendar.DAY_OF_MONTH);

        Button button7 = findViewById(R.id.btn7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SubActivity.this, dateSetListener1, year1, month1, day1).show();

            }
        });
        //수민코드(08.31)- 스위치 ~
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mTxtTime.setText(String.format("하루종일"));
                    mTxtTime1.setText(String.format("하루종일"));

                    // The toggle is enabled
                } else {
                    mTxtTime.setText(String.format(""));
                    mTxtTime1.setText(String.format(""));
                    //Time
                    Button button6 = findViewById(R.id.btn6);
                    button6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TimePickerDialog timePickerDialog
                                    = new TimePickerDialog(SubActivity.this,
                                    android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    /*String apm = "";
                                    if (hourOfDay < 12) {
                                        if (hourOfDay == 0) hourOfDay = 12;
                                        apm = "AM";
                                    } else {
                                        if (hourOfDay != 12) hourOfDay -= 12;
                                        apm = "PM";
                                    }*/
                                    //mTxtTime.setText(String.format("%d:%02d %s", hourOfDay, minute, apm)); //시,분 받아서 보내줌
                                    mTxtTime.setText(String.format("%02d%02d00", hourOfDay, minute));
                                }

                            }, Hour, Minute, false);

                            timePickerDialog.show();
                        }
                    });

                    //date
                    GregorianCalendar calendar = new GregorianCalendar();
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH);
                    day = calendar.get(Calendar.DAY_OF_MONTH);

                    Button button5 = findViewById(R.id.btn5);
                    button5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            new DatePickerDialog(SubActivity.this, dateSetListener, year, month, day).show();
                        }
                    });
                    //Time1
                    Button button8 = findViewById(R.id.btn8);
                    button8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TimePickerDialog timePickerDialog1
                                    = new TimePickerDialog(SubActivity.this,
                                    android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay1, int minute1) {
                                    /*String apm1 = "";
                                    if (hourOfDay1 < 12) {
                                        if (hourOfDay1 == 0) hourOfDay1 = 12;
                                        apm1 = "AM";
                                    } else {
                                        if (hourOfDay1 != 12) hourOfDay1 -= 12;
                                        apm1 = "PM";
                                    }*/
                                    mTxtTime1.setText(String.format("%02d%02d00", hourOfDay1, minute1)); //시,분 받아서 보내줌
                                }

                                {
                                }
                            }, Hour1, Minute1, false);

                            timePickerDialog1.show();
                        }
                    });

                    //date1
                    GregorianCalendar calendar1 = new GregorianCalendar();
                    year1 = calendar1.get(Calendar.YEAR);
                    month1 = calendar1.get(Calendar.MONTH);
                    day1 = calendar1.get(Calendar.DAY_OF_MONTH);

                    Button button7 = findViewById(R.id.btn7);
                    button7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            new DatePickerDialog(SubActivity.this, dateSetListener1, year1, month1, day1).show();

                        }
                    });

                    // The toggle is disabled
                }
            }
        }); //~수민코드(08.31)-스위치


        //예현코드
        title = findViewById(R.id.Edit_title);
        place = findViewById(R.id.Edit_place);
        content = findViewById(R.id.Edit_content);
        tv1=findViewById(R.id.tv1);

        //예현코드


        Intent intent = getIntent();


        //얘가 왜 디폴트 값을 가져오냐? 이유: 못받아오니까!!!
        Intent tomain = getIntent();
        ck_sub = tomain.getIntExtra("ck_main", 5);


        //버튼 설정
        // sub2로 이동
        Button button2 = findViewById(R.id.btn2); //btn2라고 내가 생성한 거(activity_sub2에서 확인 가능)
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity.this, Sub2Activity.class);
                startActivityForResult(intent, 0);  //수민 코드(2020.08.29)


                //    finish();//초기 화면으로 이동
            }
        });




        //main으로 이동
        Button button3 = findViewById(R.id.btn4); //btn2라고 내가 생성한 거(activity_sub2에서 확인 가능)
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/*
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                Intent intent1 = getIntent();

                //intent.putExtra("ck_sub",ck_sub);

                startActivity(intent);*/
                //    finish();//초기 화면으로 이동
            }
        });


    }
    public void clickGet(View view){
        String title1 = title.getText().toString();
        String place1 = place.getText().toString();
        String content1 = content.getText().toString();
        String date1 = mTxtDate.getText().toString();
        String time1 = mTxtTime.getText().toString();
        String datetime1 = date1 + time1;
        String date2 = mTxtDate.getText().toString();
        String time2 = mTxtTime.getText().toString();
        String datetime2 = date2 + time2;
//Get 방식
        String serverUrl = "http://15.164.218.79:8080/test_table/testget.jsp";

        try {
            title1 = URLEncoder.encode(title1, "utf-8");
            place1 = URLEncoder.encode(place1, "utf-8");
            content1 = URLEncoder.encode(content1, "utf-8");
            date1 = URLEncoder.encode(date1, "utf-8");
            time1 = URLEncoder.encode(time1, "utf-8");
            datetime1 = URLEncoder.encode(datetime1, "utf-8");
            date2 = URLEncoder.encode(date2, "utf-8");
            time2 = URLEncoder.encode(time2, "utf-8");
            datetime2 = URLEncoder.encode(datetime2, "utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String getUrl = serverUrl + "?title1=" + title1 + "&place1=" + place1 + "&content1=" + content1 +"&datet1=" + datetime1 + "&date2" + datetime2;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //while문으로 line해서 읽어오는 작업과 UI에 업데이트하는 runOnUiThread도 안만들어도 된다.
                tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });
        //서버와 데이터를 주고 받는 요청 객체를
        //서버로 보내줄 우체통 같은 역할의 객체
        //요청큐(RequestQueue)
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //우체통에 요청 편지 넣기
        //요청큐에 요청 객체 추가..
        requestQueue.add(stringRequest);
    }



    //수민 코드(2020.08.29)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            alarmtime = mTxtTime.getText().toString();
            alarmdate = mTxtDate.getText().toString();

            int timecal=Integer.parseInt(alarmtime);
            int datecal=Integer.parseInt(alarmdate);

            int time_hour=timecal/10000;
            int time_minute=(timecal-(time_hour*10000))/100;
            //int time_seconds=(timecal-(time_hour*10000)-(time_minute*100)); //사실 이건 필여없음 ㅎ

            int time_year=datecal/10000;
            int time_month=(datecal-(time_year*10000))/100;
            int time_day=(datecal-(time_year*10000)-(time_month*100));




           // String timecal;

            if (resultCode == 0) {
                int rnum = data.getIntExtra("rnum", 0);

                switch(rnum){
                    case 1:
                        str="없음";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);
                        break;
                    case 2:
                        str = "일정 시작 시간";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);
                        break;
                    case 3:
                        str = "5분 전";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);

                        if(time_minute<5)
                        {
                            if(time_hour==0)
                            {
                                time_day--;
                                time_hour=24-1;
                            }
                            else
                            {
                                time_hour--;
                            }

                            time_minute=60+time_minute-5;
                        }
                        else
                        {
                            time_minute=time_minute-5;
                        }

                        break;
                    case 4:
                        str = "15분 전";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);

                        if(time_minute<15)
                        {
                            if(time_hour==0)
                            {
                                time_day--;
                                time_hour=24-1;
                            }
                            else
                            {
                                time_hour--;
                            }
                            //time_hour--;
                            time_minute=60+time_minute-15;
                        }
                        else
                        {
                            time_minute=time_minute-15;
                        }
                        break;
                    case 5:
                        str = "30분 전";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);

                        if(time_minute<30)
                        {
                            if(time_hour==0)
                            {
                                time_day--;
                                time_hour=24-1;
                            }
                            else
                            {
                                time_hour--;
                            }
                            //time_hour--;
                            time_minute=60+time_minute-30;
                        }
                        else
                        {
                            time_minute=time_minute-30;
                        }

                        break;
                    case 6:
                        str = "1시간 전";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);

                        if(time_hour==0)
                        {
                            time_day--;
                            time_hour=24-1;
                        }
                        else
                        {
                            time_hour--;
                        }
                       // time_hour--;

                        break;
                    case 7:
                        str = "1일 전";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);

                        if(time_day==1)
                        {
                            time_month--;

                            if(time_month==1 || time_month==3 || time_month==5 || time_month==7 || time_month==8 || time_month==12 || time_month==10)
                            {
                                time_day=31;
                            }
                            else if (time_month == 2)
                            {
                                time_day=28;
                            }
                            else
                            {
                                time_day=30;
                            }
                        }
                        else
                        {
                            time_day--;
                        }
                        break;
                    case 8:
                        str = "1주 전";
                        tv = findViewById(R.id.tv);//tv_second 아이디 찾기
                        tv.setText(str);


                        if(time_day<8)
                        {
                            time_month--;
                            if(time_month==1 || time_month==3 || time_month==5 || time_month==7 || time_month==8 || time_month==12 || time_month==10)
                            {
                                time_day=31+time_day-7;
                            }
                            else if (time_month == 2)
                            {
                                time_day=28+time_day-7;
                            }
                            else
                            {
                                time_day=30+time_day-7;
                            }
                        }
                        else
                        {
                            time_day-=7;
                        }

                        break;

                }
            }

           // test_view.setText(alarmtime);
            if(time_hour<10)
            {
                alarmtime="0"+time_hour;
            }
            else
            {
                alarmtime=""+time_hour;
            }
            if(time_minute<10)
            {
                alarmtime+="0"+time_minute;
            }
            else
            {
                alarmtime+=""+time_minute;
            }

            alarmtime+="00";
            String test_alarm=""+time_year;

            if(time_month<10)
            {
                test_alarm+="0"+time_month;
            }
            else
            {
                test_alarm+=""+time_month;
            }
            if(time_day<10)
            {
                test_alarm+="0"+time_day;
            }
            else
            {
                test_alarm+=""+time_day;
            }
            alarmtime=test_alarm+alarmtime;


           // TextView test_view=findViewById(R.id.test);
            //alarmtime=Integer.toString(timecal);
           // test_view.setText(alarmtime);
        }
    }

    public void clickPost(View view) {
        final String title1= title.getText().toString();
        final String place1= place.getText().toString();
        final String content1= content.getText().toString();
        final String date1= mTxtDate.getText().toString();
        final String time1 = mTxtTime.getText().toString();
        final String datetime1 = date1 + time1;
        final String date2= mTxtDate1.getText().toString();
        final String time2 = mTxtTime1.getText().toString();
        final String datetime2 = date2 + time2;
        final String Alarm = alarmtime;

        //Post 방식으로 보낼 서버 주소
        String serverUrl= "http://15.164.218.79:8080/test_table/testget.jsp";

        //PostTest.php로 보낼 요청 객체 생성
        //결과를 String으로 받는 객체
        StringRequest stringRequest= new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
            //POST 방식으로 보낼 데이터를
            //리턴해주는 콜백 메소드

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> datas= new HashMap<>();
                datas.put("title", title1);
                datas.put("place",place1);
                datas.put("content",content1);
                datas.put("datetime1",datetime1);
                datas.put("datetime2",datetime2);
                datas.put("Alarm",Alarm);

                System.out.println(datetime1);
                System.out.println(datetime2);
                return datas;

            }

        };


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Intent intent = new Intent(SubActivity.this, MainActivity.class);
        //Intent intent1 = getIntent();

        //intent.putExtra("ck_sub",ck_sub);

        startActivity(intent);
    }






    // ~ 수민 코드(2020.08.29) ~
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            String msg = String.format("%d / %d / %d", year,monthOfYear+1, dayOfMonth);
            Toast.makeText(SubActivity.this, msg, Toast.LENGTH_SHORT).show();
            //mTxtDate.setText(String.format("%d/%d/%d", year, monthOfYear + 1, dayOfMonth));
            mTxtDate.setText(String.format("%d%02d%02d", year, monthOfYear + 1, dayOfMonth));
        }
    };

    private DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year1, int monthOfYear1,
                              int dayOfMonth1) {
            // TODO Auto-generated method stub
            String msg1 = String.format("%d / %d / %d", year1,monthOfYear1+1, dayOfMonth1);
            Toast.makeText(SubActivity.this, msg1, Toast.LENGTH_SHORT).show();
            mTxtDate1.setText(String.format("%d%02d%02d", year1, monthOfYear1 + 1, dayOfMonth1));
        }
    };

}




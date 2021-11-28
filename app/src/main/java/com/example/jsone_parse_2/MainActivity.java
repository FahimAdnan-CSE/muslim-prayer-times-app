package com.example.jsone_parse_2;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    public static String divison;
    Button click,exit1;
    Button manual;
    RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayout5,relativeLayoutsurjoudoy;

    public static TextView data;
    public static AutoCompleteTextView actx;
    private static final String[] districName = new String[]{
            "Barisal", "Chittagong", "Dhaka", "Khulna", "Mymensingh", "Rajshahi", "Rangpur", "Sylhet"
    };

    public ImageView imageView;


    public static TextView location_info, date_info, temparature_info, fojor_namaj_info, johor_namaj_info, asor_namaj_info, magrib_namaj_info, isha_namaj_info,
            surjo_udoy_info, text_tapmatra, text_degree,dateshow,lastupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout1=(RelativeLayout) findViewById(R.id.fojorbackground);
        relativeLayout2=(RelativeLayout) findViewById(R.id.dhuhrbackground);
        relativeLayout3=(RelativeLayout) findViewById(R.id.asorbackground);
        relativeLayout4=(RelativeLayout) findViewById(R.id.magribbackground);
        relativeLayout5=(RelativeLayout) findViewById(R.id.ishabackground);
        relativeLayoutsurjoudoy=(RelativeLayout) findViewById(R.id.surjoudoybackground);


          exit1 =(Button) findViewById(R.id.exit1);

          exit1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  exitmethod();
              }
          });







        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.my_dialog);
        Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // Toast.makeText(getApplicationContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();






        click = (Button) findViewById(R.id.button);
        manual=(Button) findViewById(R.id.manual);
//        data = (TextView) findViewById(R.id.fetcheddata);

        actx = findViewById(R.id.actv);

        mQueue = Volley.newRequestQueue(this);

//        location_info = (TextView) findViewById(R.id.location_id);
//        date_info = (TextView) findViewById(R.id.date_id);
//        temparature_info = (TextView) findViewById(R.id.temparature_id);
        fojor_namaj_info = (TextView) findViewById(R.id.fozor_id);
        johor_namaj_info = (TextView) findViewById(R.id.johor_id);
        asor_namaj_info = (TextView) findViewById(R.id.asor_id);
        magrib_namaj_info = (TextView) findViewById(R.id.magrib_id);
        isha_namaj_info = (TextView) findViewById(R.id.isha_id);
        surjo_udoy_info = (TextView) findViewById(R.id.surjoUdoy_id);
        dateshow=(TextView) findViewById(R.id.dateshow);
        lastupdate=(TextView) findViewById(R.id.lastupdate);

//        text_tapmatra = (TextView) findViewById(R.id.tapmatra_id);
//        text_degree = (TextView) findViewById(R.id.degree_id);

//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(getApplicationContext(),Main2Activity.class);
//                startActivity(intent);
               // finish();

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);




            }
        });

        final AutoCompleteTextView actx = (AutoCompleteTextView) findViewById(R.id.actv);


        ImageView image = (ImageView) findViewById(R.id.img);
        Button btn = (Button) findViewById(R.id.button);
        actx.setThreshold(1);
        //make a array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, districName);
        actx.setAdapter(adapter);
        //show drop down image click able
          // prayerData();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actx.showDropDown();
            }
        });
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prayerData();
                jsonParse();

//                fetchData process =new fetchData();
//                process.execute();
               // process.isCancelled();



            }
        });





    }


    private void jsonParse() {


        String data ="";
        String dataParsed = "";
        String singleParsed ="";
        String coun="";
        int length=0;
        String Imsak ="";
        String Sunrise ="";
        String Fajr ="";
        String Dhuhr ="";
        String Asr ="";
        String Sunset ="";
        String Maghrib ="";
        String Isha ="";
        String Midnight ="";
        String url="";


        if (divison.equals("Barisal"))
        {
             url = "https://api.pray.zone/v2/times/today.json?city=Barisal";
        }



        else if (divison.equals("Chittagong"))
        {
             url = "https://api.pray.zone/v2/times/today.json?city=Chittagong";
        }


        else if (divison.equals("Dhaka"))
        {
            url = "https://api.pray.zone/v2/times/today.json?city=dhaka-bd-bd-4933";

        }
        else if (divison.equals("Khulna"))
        {
            url = "https://api.pray.zone/v2/times/today.json?city=khulna";
        }
        else if (divison.equals("Mymensingh"))
        {
            url = "https://api.pray.zone/v2/times/today.json?city=Mymensingh";
        }
        else if (divison.equals("Rajshahi"))
        {
            url = "https://api.pray.zone/v2/times/today.json?city=Rajshahi";
        }
        else if (divison.equals("Rangpur"))
        {
            url = "https://api.pray.zone/v2/times/today.json?city=Rangpur";
        }
        else if (divison.equals("Sylhet"))
        {
            url = "https://api.pray.zone/v2/times/today.json?city=Sylhet";
        }

        else
        {
             Toast.makeText(getApplicationContext(),"Input a Valid country",Toast.LENGTH_SHORT).show();
        }







       // String url = "https://api.pray.zone/v2/times/month.json?city=dhaka-bd-bd-4933&month=2021-02";
        JsonObjectRequest request = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("key","Len"+response);
                        String data ="";
                        String dataParsed = "";
                        String singleParsed ="";
                        String coun="";
                        int length=0;
                        String Imsak ="";
                        String Sunrise ="";
                        String Fajr ="";
                        String Dhuhr ="";
                        String Asr ="";
                        String Sunset ="";
                        String Maghrib ="";
                        String Isha ="";
                        String Midnight ="";
                        String date="";
                        String hijri="";
                        try {



                            JSONObject jsonObject =new JSONObject(String.valueOf(response));

                            String one=jsonObject.getString("results");

                            JSONObject jsonObject1=new JSONObject(one);

                            JSONArray jsonArray=jsonObject1.getJSONArray("datetime");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String n=jsonObject2.getString("times");
                                JSONObject jsonObject3 =new JSONObject(n);
                                Sunrise = jsonObject3.getString("Sunrise");

                                Imsak=jsonObject3.getString("Imsak");
                                Fajr =jsonObject3.getString("Fajr");
                                Dhuhr= jsonObject3.getString("Dhuhr");
                                Asr =jsonObject3.getString("Asr");
                                Sunset =jsonObject3.getString("Sunset");
                               // Maghrib =jsonObject3.getString("Maghrib");
                                Maghrib =jsonObject3.getString("Sunset");
                                Isha =jsonObject3.getString("Isha");
                                Midnight =jsonObject3.getString("Midnight");









                            }

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String n=jsonObject2.getString("date");

                                JSONObject datess =new JSONObject(n);
                                date= datess.getString("gregorian");
                                hijri=datess.getString("hijri");

                            }











                            // MainActivity.data.setText(this.data+"\n"+this.length+"\n"+this.dataParsed);
                            Date _24HourDt = null;
                            SimpleDateFormat _12HourSDF = null;


                            try {

                                SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                _12HourSDF = new SimpleDateFormat("hh:mm a");
                                _24HourDt = _24HourSDF.parse(Asr);
                                //System.out.println(_24HourDt);
                                //System.out.println(_12HourSDF.format(_24HourDt));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Date _24HourDt1 = null;
                            SimpleDateFormat _12HourSDF1 = null;


                            try {

                                SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                _12HourSDF1 = new SimpleDateFormat("hh:mm a");
                                _24HourDt1 = _24HourSDF.parse(Sunset);
                                //System.out.println(_24HourDt);
                                //System.out.println(_12HourSDF.format(_24HourDt));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Date _24HourDt2 = null;
                            SimpleDateFormat _12HourSDF2 = null;


                            try {

                                SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                _12HourSDF2 = new SimpleDateFormat("hh:mm a");
                                _24HourDt2 = _24HourSDF.parse(Maghrib);
                                //System.out.println(_24HourDt);
                                //System.out.println(_12HourSDF.format(_24HourDt));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Date _24HourDt3 = null;
                            SimpleDateFormat _12HourSDF3 = null;


                            try {

                                SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                _12HourSDF3 = new SimpleDateFormat("hh:mm a");
                                _24HourDt3 = _24HourSDF.parse(Isha);
                                //System.out.println(_24HourDt);
                                //System.out.println(_12HourSDF.format(_24HourDt));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Date _24HourDt4 = null;
                            SimpleDateFormat _12HourSDF4 = null;


                            try {

                                SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                _12HourSDF4 = new SimpleDateFormat("hh:mm a");
                                _24HourDt4 = _24HourSDF.parse(Midnight);
                                //System.out.println(_24HourDt);
                                //System.out.println(_12HourSDF.format(_24HourDt));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }






                            // MainActivity.data.setText("\nImsak: "+Imsak+"\n\nSunrise: "+Sunrise+"\n\nFajr: "+Fajr+"\n\nDhuhr: "+Dhuhr+"\n\nAsr: "+_12HourSDF.format(_24HourDt)+"\n\nSunset: "+_12HourSDF1.format(_24HourDt1)+"\n\nMaghrib: "+_12HourSDF2.format(_24HourDt2)+"\n\nIsha: "+_12HourSDF3.format(_24HourDt3)+"\n\nMidnight: "+_12HourSDF4.format(_24HourDt4)+"\n \n ''O you who believe, seek help through patience and prayer. Surely, Allah is with those who are patient.''(Al Quran-2:153)");
                            String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
                            MainActivity.fojor_namaj_info.setText(Fajr+" AM");
                            MainActivity.johor_namaj_info.setText(Dhuhr+" PM");
                            MainActivity.asor_namaj_info.setText(_12HourSDF.format(_24HourDt));
                            MainActivity.magrib_namaj_info.setText(_12HourSDF2.format(_24HourDt2));
                            MainActivity.isha_namaj_info.setText(_12HourSDF3.format(_24HourDt3));
                            MainActivity.surjo_udoy_info.setText(Sunrise+" AM");
                            MainActivity.dateshow.setText("Date : "+date+"\nHijri : "+hijri);
                            MainActivity.lastupdate.setText("Last Update at "+currentDateTimeString);

                            Calendar calendar = Calendar.getInstance(Locale.getDefault());
                            int hour = calendar.get(Calendar.HOUR_OF_DAY);
                            int minute = calendar.get(Calendar.MINUTE);
                            int builtinsecond=(hour*60*60)+(minute*60);



                            String tmpStr10 = String.valueOf(hour);
                            String tmpStr11 = String.valueOf(minute);

                            StringBuilder stringBuilder=new StringBuilder();

                            stringBuilder.append(tmpStr10);
                            stringBuilder.append(":");
                            stringBuilder.append(tmpStr11);







                            String ttt=Fajr;
                            char c0=ttt.charAt(0);
                            char c1=ttt.charAt(1);
                            char c3=ttt.charAt(3);
                            char c4=ttt.charAt(4);
                            int indxh= (c0-'0')*10+(c1-'0')*1;
                            int indxm=(c3-'0')*10+(c4-'0')*1;

                            int second= (indxh*60*60)+(indxm*60);

                            Log.d("key","Sec: "+second);

                            int recfajr=times(Fajr);
                            int recdhuhr=times(Dhuhr);
                            int recasr=times(Asr);
                            int recmaghrib=times(Maghrib);
                            int recisha=times(Isha);
                            int recsurjoudoy=times(Sunrise);
                            int recmidnight=times(Midnight);


                            if (builtinsecond>=recfajr&&builtinsecond<recsurjoudoy)
                            {
                                relativeLayout1.setBackgroundColor(Color.RED);
                            }
                            else if (builtinsecond>=recdhuhr&&builtinsecond<recasr)
                            {

                                relativeLayout2.setBackgroundColor(Color.parseColor("#91E9EF"));
                            }
                            else if (builtinsecond>=recasr&&builtinsecond<recmaghrib)
                            {
                                relativeLayout3.setBackgroundColor(Color.RED);
                            }
                            else if (builtinsecond>=recmaghrib&&builtinsecond<recisha)
                            {

                                relativeLayout4.setBackgroundColor(Color.parseColor("#91E9EF"));


                            }
                            else if (builtinsecond>=recmaghrib&&builtinsecond<recmidnight)
                            {
                                relativeLayout5.setBackgroundColor(Color.parseColor("#91E9EF"));
                            }

















                        }



                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }










    private void prayerData() {
        final AutoCompleteTextView actx = (AutoCompleteTextView) findViewById(R.id.actv);

        if (actx.getText().toString().isEmpty()) {

            Toast.makeText(this, "Enter A Location", Toast.LENGTH_LONG).show();
        } else {

            divison = actx.getText().toString();

//            Intent intent=new Intent(MainActivity.this,year_second_semester.class);
//            intent.putExtras("sum",divison);
//            startActivity(intent);
//            fetchData process = new fetchData();
//            process.execute();


        }



    }


    private  int times(String s)
    {

        String ttt=s;
        char c0=ttt.charAt(0);
        char c1=ttt.charAt(1);
        char c3=ttt.charAt(3);
        char c4=ttt.charAt(4);
        int indxh= (c0-'0')*10+(c1-'0')*1;
        int indxm=(c3-'0')*10+(c4-'0')*1;

        int second= (indxh*60*60)+(indxm*60);


        return second;

    }


    private void exitmethod() {

        AlertDialog.Builder alertDialogBuilder;

        alertDialogBuilder =new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setIcon(R.drawable.alert);
        alertDialogBuilder.setMessage(R.string.Dialog);
        alertDialogBuilder.setTitle(R.string.Title);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();


            }
        });

        alertDialogBuilder.setNegativeButton(" No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        //alertDialogBuilder.setNeutralButton() here i can not use this object

        AlertDialog alertDialog= alertDialogBuilder.create();
        alertDialog.show();



    }




}

//    public  String passing()
//    {
//        return divison;
//    }
//    public  void Toast()
//    {
//        Toast.makeText(this, "Location Not valid", Toast.LENGTH_LONG).show();
//    }





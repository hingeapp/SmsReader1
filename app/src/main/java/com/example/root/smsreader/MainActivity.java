package com.example.root.smsreader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables have prefix "m". rest of the name uses CamelCase
public  int mLenAllSms = 0;
public int mCount = 0;


    public String mINR[] ;
    ListView mLvSms;
    TextView mTvCount; // for TextView, add Tv after prefix of "m"
    Button mBtnAnalysis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "List view initialized"); //use Log.d() . this is not run during in release builds. Logging affects performance
        List<String> smsList;
        smsList = readSms();
        mLenAllSms = smsList.size();
        Log.d(TAG, "readSms func called");
        Log.d(TAG, " "+ mLenAllSms +" objects will be created now .....");

        SmsAttributes[] smsAttributes = new SmsAttributes[mLenAllSms];
        Log.d(TAG, mLenAllSms +" Objects created successfully...");

        try {
            //mLvSms = (ListView) findViewById(R.id.listView);
            mTvCount = (TextView)findViewById(R.id.textView3);
        
            String messageCsv = ""; // use camelCase for naming variables
        
            Log.d(TAG, "going inside a for loop");
        
            for(int smsIndex =0 ; smsIndex < smsList.size(); smsIndex++){
                Log.d(TAG, "In the for loop to call d class with index "+smsIndex);
        
                String get = smsList.get(smsIndex);
                smsAttributes[smsIndex] = new SmsAttributes(get);
                Log.d(TAG, " obj sucess and constructor called....");
        
            }
        
        
            for (int smsIndex = 0; smsIndex < smsList.size(); smsIndex++) {
                Log.d(TAG, "Inside a for loop now ......... splitting....");
                messageCsv += smsList.get(smsIndex) + ",";
            }
            Log.d(TAG, " **************** Display **************");
        
            for(int j=0 ; j< smsList.size() ; j++){
                Log.d(TAG, "Date for object "+ smsAttributes[j] + " is "+smsAttributes[j].mydate);
                Log.d(TAG, "mINR for object "+ smsAttributes[j] + " is "+smsAttributes[j].INR);
                Log.d(TAG, "Place for object "+ smsAttributes[j] + " is "+smsAttributes[j].place);
        
            }
        
            String messageArray[];
        
            messageArray = messageCsv.split(",");
        
            Log.d(TAG, "splitting done.....");
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, messageArray);
        
            Log.d(TAG, "ArrayAdapter initialized ......");
           // mLvSms.setAdapter(adapter);
        
            Log.d(TAG, "list view shud show something now !!!");

            Log.d(TAG, "mCount value converted to String ...");
            Log.d(TAG, mCount + " is the mTvCount integer...");

           // Log.d(TAG, mCount+ " is the mTvCount string...");

            mTvCount.setText(mCount);
            Log.d(TAG, "mCount value set successfully .....");


    }catch(Exception e){
        e.getMessage();

        Log.d(TAG, "Fucked up !!!! Something went wrong !!!" + e.getMessage());
            // :D Always good to know the error which caused the fuck up. display error using e.getMessage()
    }


    /*    mBtnAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"In onclickListener of mBtnAnalysis ........");
                Intent transfer = new Intent(MainActivity.this, Analysis.class);


                Log.d(TAG, "Starting activity of  a new Intent .......");

                startActivity(transfer);

            }
        });*/


        int number_tvs = (mLenAllSms *3)+6;
        Log.d(TAG, number_tvs + " text views will be created now ");



        TextView[] textViews = new TextView[number_tvs];

        Log.d(TAG, "Tv array initialized.......");

        textViews[0] = (TextView)findViewById(R.id.textView1);
        textViews[1] = (TextView)findViewById(R.id.textView12);
        textViews[2]= (TextView)findViewById(R.id.textView3);
        textViews[3]= (TextView)findViewById(R.id.textView4);
        textViews[4]= (TextView)findViewById(R.id.textView5);
        textViews[5]= (TextView)findViewById(R.id.textView6);
        textViews[6]= (TextView)findViewById(R.id.textView7);
        textViews[7]= (TextView)findViewById(R.id.textView8);
        textViews[8]= (TextView)findViewById(R.id.textView9);
        textViews[9] = (TextView)findViewById(R.id.textView10);
        textViews[10] = (TextView)findViewById(R.id.textView11);
        textViews[11]= (TextView)findViewById(R.id.textView12);
        textViews[12]= (TextView)findViewById(R.id.textView13);
        textViews[13]= (TextView)findViewById(R.id.textView14);
        textViews[14]= (TextView)findViewById(R.id.textView15);

        Log.d(TAG,number_tvs +" textviews are created successfully.........");
        int tcount = 6;
        for (int i = 0; i < 3; i++) {

            Log.d(TAG, "In for loop "+ i);
            Log.d(TAG, "Date for  "+ smsAttributes[i] + " is "+smsAttributes[i].mydate);
            Log.d(TAG, "mINR for  "+ smsAttributes[i] + " is "+smsAttributes[i].INR);
            Log.d(TAG, "Place for  "+ smsAttributes[i] + " is "+smsAttributes[i].place);

            textViews[tcount].setText(smsAttributes[i].mydate);
            tcount++;
            Log.d(TAG, "Date set for "+i);

            textViews[tcount].setText(smsAttributes[i].INR);
            tcount++;
            Log.d(TAG, "mINR set for "+i);

            textViews[tcount].setText(smsAttributes[i].place);
            tcount++;
            Log.d(TAG, "Place  set for "+i);

        }



    }// end of oncreate method.

public List<String> readSms(){

            Log.d(TAG, "Inside readsms func.....");
            List<String> sms = new ArrayList<String>();

            try {

                Log.d(TAG, "sms string done ");
                Uri geturi = Uri.parse("content://sms/inbox");

                Log.d(TAG, "uri parsed to inbox successfully .....");
                Cursor cur = getContentResolver().query(geturi, null, null, null, null);

                Log.d(TAG, "cursor obj set ....");

                while (cur.moveToNext()) {
                    Log.d(TAG, "inside while loop....");
                    String address = cur.getString(cur.getColumnIndex("address"));
                    Log.d(TAG, " Address is "+address);

                    String body = cur.getString(cur.getColumnIndexOrThrow("body"));
                    Log.d(TAG, " Body is " + body);
                            if(body.contains(" mINR ")|| body.contains(" on ") || body.contains(" at ") || body.contains("Rs") ) {
                              mCount++;
                                Log.d(TAG, "Incrementing mTvCount value to "+ mCount);

                                Log.d(TAG, "getting string and storing in 'body' string ");
                                sms.add(body);
                            }else{
                                Log.d(TAG, "msg not added");
                                }
                                        }
                Log.d(TAG, "outside while loop");

               }catch (Exception e){
                Log.d(TAG, "Something went wrong in the readsms func ..............  :( ");

            }
            return sms;
        }//end of readsms method

}// end of class .








































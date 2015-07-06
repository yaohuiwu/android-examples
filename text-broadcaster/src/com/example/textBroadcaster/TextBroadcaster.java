package com.example.textBroadcaster;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TextBroadcaster extends Activity {

    private static final String ACTIVITY_TAG = "MyTextBroadcaster";

    private Context context;
    private TextView etBroadcastContent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        context = this;

        Button btnSendBroadcast = (Button) findViewById(R.id.btn_sendBroadcast);
        btnSendBroadcast.setOnClickListener(new SendBroadcastClickListener());

        etBroadcastContent = (TextView) findViewById(R.id.txt_broadcastContent);
    }

    private class SendBroadcastClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            String content = etBroadcastContent.getText().toString().trim();
            if(content.length() < 1){
                Toast.makeText(context, etBroadcastContent.getHint(), Toast.LENGTH_LONG).show();
                return;
            }

            Intent intent = new Intent();
            intent.setAction("com.example.textBroadcaster.TextBroadcastReceiver");
            intent.putExtra("content", content);
            Log.w(ACTIVITY_TAG, content);
            sendBroadcast(intent);
        }
    }
}

package brickyard.tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import brickyard.tracker.bean.UserProfileBean;
import brickyard.tracker.util.DbHelper;

public class WelcomeSplashActivity extends Activity {
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_splash_activity);

        mWaitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    DbHelper dbHelper = new DbHelper(getApplicationContext());

                    UserProfileBean userProfileBean = dbHelper.getApplicationUserProfile(getApplicationContext());
                    if(userProfileBean.getName().equalsIgnoreCase("")) {
                        Intent i = new Intent(getApplicationContext(), UserProfile.class);
                        startActivity(i);
                    } else if(userProfileBean.getEnablePassword().equalsIgnoreCase("1")) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    } else {
                        Intent i = new Intent(getApplicationContext(), Dashboard.class);
                        //Intent i = new Intent(getApplicationContext(), AddRecord.class);
                        startActivity(i);
                    }

                    finish();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, 500);  // Give a 3 seconds delay.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWaitHandler.removeCallbacksAndMessages(null);
    }
}

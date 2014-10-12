package cn.jpush.notificationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/** 
 * Create by luozc at Oct 11, 2014
 */
public class OtherActivity extends Activity {

    private final String TAG = OtherActivity.class.getSimpleName();
    private TextView mShowTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_other);
        this.mShowTime = (TextView) this.findViewById(R.id.show_time);
        Intent intent = this.getIntent();
        String time = intent.getStringExtra("time");
        this.mShowTime.setText(time);
    }
}

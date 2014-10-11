package cn.jpush.notificationdemo;

import java.util.Calendar;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNotification(View view) {
        Intent intent = new Intent(this, OtherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String currentTime =  DateTimeUtil.formatDateDefault(Calendar.getInstance().getTime());
        intent.putExtra("time", currentTime);
        int notificationId = (int) System.currentTimeMillis();
        PendingIntent pendingIntent= PendingIntent.getActivity(
            this, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(currentTime)
                .setContentText("text")
                .setTicker("tricker")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("big text"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        notification.defaults=Notification.DEFAULT_SOUND;
        notification.vibrate = new long[] {100,400,100,400};
        notification.ledARGB=Color.GREEN;//led灯颜色
        notification.ledOffMS=1000;//关闭时间 毫秒
        notification.ledOnMS=1000;//开启时间 毫秒
        notification.flags|=Notification.FLAG_SHOW_LIGHTS; 
        notificationManager.notify(notificationId, notification);
    }
}

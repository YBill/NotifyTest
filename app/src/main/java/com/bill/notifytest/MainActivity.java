package com.bill.notifytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bill.module_notification.ChannelEntity;
import com.bill.module_notification.ImportanceType;
import com.bill.module_notification.NotifyManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotifyManager notifyManager = new NotifyManager(this);

        ChannelEntity chatChannel = new ChannelEntity(Constants.CHANNEL_CHAT, "新聊天消息", ImportanceType.IMPORTANCE_HIGH);
        chatChannel.setDescription("个人或群组发来的聊天消息");
        notifyManager.createNotificationGroupWithChannel(Constants.GROUP_CHAT, "聊天消息", chatChannel);

    }

    public void handleClick2(View view) {
        Intent intent = new Intent(MainActivity.this, NotifyService.class);
        startService(intent);
    }

}

package com.fanyiran.chat.impl;

import android.content.Context;
import android.content.Intent;

import com.fanyiran.chat.ChatActivity;
import com.fanyiran.componentlib.iinterface.ILaunchChat;

/**
 * Created by fanqiang on 2019/2/27.
 */
public class LaunchChatImpl implements ILaunchChat{
    @Override
    public void launchChatActivity(Context context) {
        Intent intent = new Intent(context, ChatActivity.class);
        context.startActivity(intent);
    }
}

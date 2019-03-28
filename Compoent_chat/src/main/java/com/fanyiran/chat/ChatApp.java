package com.fanyiran.chat;

import android.app.Application;

import com.fanyiran.chat.impl.LaunchChatImpl;
import com.fanyiran.componentlib.CompoentService;
import com.fanyiran.componentlib.iinterface.IInit;

/**
 * Created by fanqiang on 2019/2/27.
 */
public class ChatApp extends Application implements IInit {
    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public void initApp() {
        CompoentService.getInstance().setLaunchChat(new LaunchChatImpl());
    }
}

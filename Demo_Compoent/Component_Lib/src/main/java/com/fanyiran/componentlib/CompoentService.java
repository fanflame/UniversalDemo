package com.fanyiran.componentlib;

import com.fanyiran.componentlib.iinterface.ILaunchChat;
import com.fanyiran.componentlib.iinterface.ILaunchLogin;
import com.fanyiran.componentlib.impl.EmptyLaunchChatImpl;
import com.fanyiran.componentlib.impl.EmptyLaunchLoginImpl;

/**
 * Created by fanqiang on 2019/2/27.
 */
public class CompoentService {
    private ILaunchLogin launchLogin;
    private ILaunchChat launchChat;
    private static final CompoentService ourInstance = new CompoentService();

    public static CompoentService getInstance() {
        return ourInstance;
    }

    private CompoentService() {
    }

    public ILaunchLogin getLaunchLogin() {
        if(launchLogin == null){
            launchLogin = new EmptyLaunchLoginImpl();
        }
        return launchLogin;
    }

    public void setLaunchLogin(ILaunchLogin launchLogin) {
        this.launchLogin = launchLogin;
    }

    public ILaunchChat getLaunchChat() {
        if(launchChat == null){
            launchChat = new EmptyLaunchChatImpl();
        }
        return launchChat;
    }

    public void setLaunchChat(ILaunchChat launchChat) {
        this.launchChat = launchChat;
    }
}

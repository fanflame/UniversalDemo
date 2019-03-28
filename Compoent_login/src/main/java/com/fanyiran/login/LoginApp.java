package com.fanyiran.login;

import android.app.Application;

import com.fanyiran.componentlib.CompoentService;
import com.fanyiran.componentlib.iinterface.IInit;
import com.fanyiran.login.impl.LaunchLogin;

/**
 * Created by fanqiang on 2019/2/27.
 */
public class LoginApp extends Application implements IInit{
    @Override
    public void initApp() {
        CompoentService.getInstance().setLaunchLogin(new LaunchLogin());
    }
}

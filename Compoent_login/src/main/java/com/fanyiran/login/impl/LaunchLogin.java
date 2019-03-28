package com.fanyiran.login.impl;

import android.content.Context;
import android.content.Intent;

import com.fanyiran.componentlib.iinterface.ILaunchLogin;
import com.fanyiran.login.LoginActivity;

/**
 * Created by fanqiang on 2019/2/27.
 */
public class LaunchLogin implements ILaunchLogin {
    @Override
    public void launchLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}

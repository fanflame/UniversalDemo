package com.fanyiran.invocationhandlerdemo;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Invocation(listener = "setOnClickListener",arg = View.OnClickListener.class,method = "onClick")
@interface OnClick {
    int[] value();
}

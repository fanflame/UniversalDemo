package com.fanyiran.invocationhandlerdemo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Invocation {
    String listener();
    Class<?> arg();
    String method();
}

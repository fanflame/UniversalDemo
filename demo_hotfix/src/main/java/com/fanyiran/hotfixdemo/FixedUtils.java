package com.fanyiran.hotfixdemo;

import android.content.Context;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;

/**
 * Created by fanqiang on 2019/3/7.
 */
public class FixedUtils {
    public static void fix(Context context) {
        File file = context.getDir(Constants.PATCH_DEX_PATH,Context.MODE_PRIVATE);
        if (!file.exists()) {
            return;
        }
        // NOTE: 这里只加载了一个特定dex
        BaseDexClassLoader baseDexClassLoader = new BaseDexClassLoader(file.getAbsolutePath()+"/patch.dex",
                new File(Constants.PATCH_DEX_OPTIMIZED),null,context.getClassLoader());
        BaseDexClassLoader systemDexClassLoader = (BaseDexClassLoader) context.getClassLoader();

        Object systemDexElements =  getDexElements(BaseDexClassLoader.class,systemDexClassLoader);
        Object dexElements = getDexElements(BaseDexClassLoader.class,baseDexClassLoader);
        Object orderResult = orderDexElements(dexElements, systemDexElements);
        setOrderdDexElements(BaseDexClassLoader.class,systemDexClassLoader,orderResult);
    }

    private static void setOrderdDexElements(Class<BaseDexClassLoader> baseDexClassLoaderClass, BaseDexClassLoader loader, Object orderResult) {
        try {
            Field pathList = baseDexClassLoaderClass.getDeclaredField("pathList");
            pathList.setAccessible(true);
            Object pathListObject = pathList.get(loader);
            Field dexElementsField = pathList.getType().getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            dexElementsField.set(pathListObject,orderResult);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static Object getDexElements(Class<BaseDexClassLoader> baseDexClassLoaderClass, BaseDexClassLoader loader){
        try {
            Field pathList = baseDexClassLoaderClass.getDeclaredField("pathList");
            pathList.setAccessible(true);
            Object pathListObject = pathList.get(loader);
            Field dexElementsField = pathList.getType().getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            return dexElementsField.get(pathListObject);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object orderDexElements(Object dexElements, Object systemDexElements) {
        int length = Array.getLength(dexElements);
        int sysLength = Array.getLength(systemDexElements);

        Object result = Array.newInstance(dexElements.getClass().getComponentType(), length + sysLength);

        for (int i = 0; i < length + sysLength; i++) {
            if(i < length){
                Array.set(result,i,Array.get(dexElements,i));
            }else{
                Array.set(result,i,Array.get(systemDexElements,i - length));
            }
        }
        return result;
    }

}

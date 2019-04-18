package com.fanyiran.hermes_lib;

import java.util.List;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class CommunicationBean {
    public static final String TYPE_GETINSTANCE = "TYPE_GETINSTANCE";
    public static final String TYPE_METHOD = "TYPE_METHOD";


    /**
     * className :
     * methodName :
     * methodParams : [{"paramsClass":"class","paramObject":"object"}]
     * type : 1
     */

    private String className;
    private String methodName;
    private String type;
    private List<MethodParamsBean> methodParams;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MethodParamsBean> getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(List<MethodParamsBean> methodParams) {
        this.methodParams = methodParams;
    }

    public static class MethodParamsBean {
        /**
         * paramsClass : class
         * paramObject : object
         */

        private String paramsClass;
        private String paramObject;

        public String getParamsClass() {
            return paramsClass;
        }

        public void setParamsClass(String paramsClass) {
            this.paramsClass = paramsClass;
        }

        public String getParamObject() {
            return paramObject;
        }

        public void setParamObject(String paramObject) {
            this.paramObject = paramObject;
        }
    }
}

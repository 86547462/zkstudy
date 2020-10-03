package com.sec.daili.demo04;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 自动生成代理类
 */
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public ProxyInvocationHandler() {
    }

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    //处理代理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        seehose();
        //动态代理的1本质就是使用反射
        return  method.invoke(target,args);

    }

    //房东带你看房子
    public void seehose()
    {
        System.out.println("增加了日志功能.......");
    }

}

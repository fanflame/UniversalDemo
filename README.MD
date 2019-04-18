# UniversalDemo

本工程汇集以下demo程序：

## demo_animatorpath:自定义路径动画
要点：主要用到自定义proporty动画以及模仿系统的Path类实现动画的初始化；

proporty动画可以用在非ui类中处理ui属性。

## demo_compoent:组件化
要点：主要用到通过修改build.gradle的插件类型以及通过共有lib来实现组件间通信

## demo_customrecycleview:自定义RecycleView
要点：自定义RecycleView以及实现Adapter以及RecycleView是如何实现滑动的

## demo_dynamicproxyimpl:android实现动态代理

## demo_hotfix:热更新
要点：dex文件插桩

## demo_incrementalupdate:增量更新
要点：bsdiff

## demo_invocationhandler:EventBus原理
要点：主要用到注解以及动态代理；

通过注解获取到注解的信息(绑定的事件)；通过动态代理实现

## demo_libjpeg:图片哈夫曼编码

## demo_gradleplugin:gradle插件以及使用Javassit操作class

## demo_mutilthread:自定义lock实现线程安全
要点：分别使用Atomic与自定义Lock实现i++的操作；

自定义Lock使用LockSupport.park()与LockSupport.unPark(thread)来阻塞和释放锁。

## demo_hermes:饿了么进程间通信
要点：涉及AIDL，注解，反射，动态代理；通信的实体类PeopleManager在主进程和其他进程中只存在一份（主进程中）；
需要注意的是其他进程调用ProgressManager.getInstance().getInstance(Ipeople.class)通过动态代理返回这个实例在主进程的单例
调用该实例也就是通过动态代理AIDL反射调用主进程的方法

## demo_loadapkplugin:加载apk插件
要点：需要手动生成DexClassLoader以及Resourcs来加载插件class以及资源；需要管理插件Activity的生命周期

使用：将Demo_LoadApkPlugin_ApkPlugin编译之后生成的apk重命名为plugin.apk,放于Demo_LoadApkPlugin的asset中。

问题：插件activity如何startActivity();

## demo_baseadapter:封装RecycleAdapter
要点:将itemLayout等与type相关的内容封装进ItemType

# TODO
- 动态代理未完成
- 自定义RecycleView待完善，滑动有问题
- 热更新
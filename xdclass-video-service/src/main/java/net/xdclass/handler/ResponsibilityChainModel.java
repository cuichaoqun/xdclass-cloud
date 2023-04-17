package net.xdclass.handler;

import java.util.Optional;


/*
    责任链模式,通过继承方式实现每个类的共有部分,通过暴露一个连接子类的方法把各个类连接到一起,
    在用一个父类的方法调用封装子类与自己的方法
 */
public class ResponsibilityChainModel {

    public static void main(String[] args) {
        Handler aHandler=new AHandler();
        aHandler.connect(new BHandler());
        aHandler.doHandler();
    }

}
abstract class Handler {
    private Handler handler;

    public Handler connect(Handler handler) {
        this.handler = handler;
        return this.handler;
    }
    public void doHandler(){
        test();
        Optional.ofNullable(handler).ifPresent(Handler::doHandler);
        //ifPresent如果存在 ,意思是是子类不为空调用子类的这个方法
        //Handler::doHandler 他的意思是 ==> e(传入的参数)-> e这个类的这个doHandler方法的执行

    }
    abstract public void test();
}
class AHandler extends Handler{
    public AHandler(){
        super();
    }
    @Override
    public void test() {
        System.out.println("A对象调用");
    }
}
class BHandler extends Handler {

    @Override
    public void test() {
        System.out.println("BHandler");
    }
}
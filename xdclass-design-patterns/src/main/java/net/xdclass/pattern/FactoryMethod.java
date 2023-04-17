package net.xdclass.pattern;

/**
 * Factory Method 定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法模式使得一个类的实例化延时到子类。
 *
 * 应用场景：
 *
 * 1.当你不知道该使用对象的确切类型的时候；
 *
 * 2.当你希望为库或框架提供扩展其它内部组件的方法时。
 *
 * 主要优点：
 *
 * 1.将具体产品和创建者解耦；
 *
 * 2.符合单一职责原则；
 *
 * 3.符合开闭原则。
 * .设主机A向主机B连续发送5个分组，主机B对每个收到的分组进行确认，其中第二个分组丢失，其余分组以及重传
 * 的第二个分组主机B均正确接收，主机A正确接收所有的ACK;各分组从1开始依次连续编号(即1、2、3……)，主机A的
 * 超时时间足够长。对应GBN、SR 和TCP协议，请回答:
 * 1-1.主机A分别发送了多少个分组?
 * 发送了6个分组
 * 1-2.主机B分别发送了多少个ACK?
 * 发送了5个ACK
 * 1-3.如果超时时间比5RTT长得多，则哪个协议成功交付5个分组的时间最短?
 * TCP
 * 时序 电路交换 时延带宽积  服务访问点(相邻层服务互相访问) network 客户与客户  socket (套接字)
 * 根域名服务器 用户跟踪 
 *
 * */
public class FactoryMethod {

    public static void main(String[] args) {
        //创建子类，使用继承的父类来接收
        //Application application = new CreateProduct1A();
        Application application = new CreateProduct1B();
        //使用接口类来接收创建出来类，体现了多态
        Product1 product1Object = application.getProduct1();
        //创建的是哪个类，调用到的就是哪个类的方法实现
        product1Object.method();

    }
}
    //定义一个接口类
    interface Product1{
        public void method();
    }

    //类实现了接口，必须实现接口中的方法
    class Product1A implements Product1{
        @Override
        public void method() {
            System.out.println("Product1A method");
        }
    }


    class Product1B implements Product1{
        @Override
        public void method() {
            System.out.println("Product1B method");
        }
    }

    //抽象类里面不一定要有抽象方法；有抽象方法，类必须要是抽象类


/***/
    abstract class Application {
        //工厂方法类，返回的是一个接口类
        abstract Product1 getProduct1();
    }

    //子类继承了抽象类，子类必须实现抽象类里的方法
    class CreateProduct1A extends Application{
        @Override
        Product1 getProduct1() {
            //Product1A实现了Product1接口，所以可以把创建的Product1A用Product1接收
            return new Product1A();
        }
    }

    //子类继承了抽象类，子类必须实现抽象类里的方法
    class CreateProduct1B extends Application {
        @Override
        Product1 getProduct1() {
            return new Product1B();
        }
    }


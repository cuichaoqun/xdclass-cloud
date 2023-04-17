package net.xdclass.pattern;


/**
 * Abstract Factory提供一个创建一系列相关或相互依赖对象的接口，而无需指定他们具体的类。
 *
 * 应用场景：
 *
 * 程序需要处理不同序列的相关产品，但是你不希望它依赖于这些产品的具体类时，可是使用抽象工厂。
 *
 * 优点：
 *
 * 1.可以确信你从工厂得到的产品彼此是兼容的；
 *
 * 2.可以避免具体产品和客户端代码之间的紧密耦合；
 *
 * 3.符合单一职责原则；
 *
 * 4.符合开闭原则。
 *
 * 主机A向主机B连续发送了两个TCP报文段，其字节序号分别为50和80。假设此前发送的数据报已正确接收，请回答下列问题：
 * （1）第一个报文段携带了多少个字节的数据?
 *   30个
 * （2）主机B收到第一个报文段后发回的确认号是多少?
 *      发送80 希望第二个报文段从 80 开始发送
 * （3）如果主机B收到第二个报文段后发回的确认号是160，试问A发送的第二个报文段中的数据有多少字节?
 *    160-80  = 80 个
 * （4）如果A发送的第一个报文段丢失，但第二个报文段到达了B。B在第二个报文段到达后向A发送确认。试问这个确认号应为多少?
 *    50
 *
 *  （1）第一个报文段携带了30个字节的数据。
 * （2）主机B收到第一个报文段后发回的确认号是80。
 * （3）A发送的第二个报文段中的数据有80字节。
 * （4）向A发送的确认号为50。
 *
 * 主机甲与主机乙之间已建立一个TCP连接，主机甲向主机乙发送了两个连续的TCP段，
 * 分别包含300字节和500字节的有效载荷，第一个段的序号为200，
 * 主机乙正确接收到两个段后，发送给主机甲的确认序号是多少？
 *
 * 500
 * 1000
 *
 * 主机甲与主机乙之间已建立一个TCP连接，主机甲向主机乙发送了3个连续的TCP报文段，
 * 分别包含300字节、400字节和500字节的有效载荷，第3个段的序号为900。若主机乙仅正确接收到第1个和第3个报文段，
 * 则主机乙发送给主机甲的确认序号是多少？
 *
 * 第一段 200 - 499
 * 第二段 500-  899
 * 第三段 900 - 1399
 *
 * 答：
 * 第二个报文段传失败了，只传成功第一个，要求出第一个的确认序列号：
 * 第三段的序号为900，所以第二段的原始序号为900-400=500；
 * （第一段的原始序列号为500-300=200；
 * 第一段的确认序列号为200+300=500，两者相等）
 * 主机乙发送给主机甲的确认序号是500。
 *
 * 例题：主机甲与主机乙之间已建立一个TCP连接，双方持续有数据传输，且数据无差错与丢失。
 * 若甲收到1个来自乙的TCP报文段，该段的序号为1913、确认序号为2046、有效载荷为100字节，
 * 则甲立即发送给乙的TCP报文段的序号和确认序号分别是多少？
 *
 * 乙 发送给甲确认序号 2046 说明 甲下次发送的确认序号为 2046
 *
 * 1946 + 99 = 2045
 * 甲发送给乙的tcp报文段序号 为  乙发送给甲的确认序号 2046
 *
 * 答：
 * 甲立即发送给乙的TCP报文段的序号：2046
 *
 * 甲发送给乙的确认序号即为乙发送给甲的序号 1913 + 有效荷载的数据 100
 * 2013
 *
 * 确认序号：2013
 * （解析：甲发送给乙的TCP报文段序号即为乙发送给甲的确认序号2046；甲发送给乙的确认序号即为乙发送给甲的序号1913+有效载荷的数据100。）
 *
 *
 * */



public class AbstractFactory {
    public static void main(String[] args) {
        //创建一个类，使用其实现的接口来接收
        IDataBaseUtils iDataBaseUtils = new MysqlDataBaseUtils();
        IConnection iConnection = iDataBaseUtils.getConnection();
        iConnection.connection();
        ICommand iCommand = iDataBaseUtils.getCommand();
        iCommand.command();
    }
}

interface IConnection{
    void connection();
}

interface ICommand{
    void command();
}

interface IDataBaseUtils{
    //包含一组的工厂方法
    IConnection getConnection();
    ICommand getCommand();
}

class MysqlConnection implements IConnection{
    @Override
    public void connection() {
        System.out.println("mysql connection");
    }
}

class OracleConnection implements IConnection{
    @Override
    public void connection() {
        System.out.println("oracle connection");
    }
}

class MysqlCommand implements ICommand{
    @Override
    public void command() {
        System.out.println("mysql command");
    }
}

class OracleCommand implements ICommand{
    @Override
    public void command() {
        System.out.println("oracle command");
    }
}

class MysqlDataBaseUtils implements IDataBaseUtils{
    @Override
    public IConnection getConnection() {
        return new MysqlConnection();
    }
    @Override
    public ICommand getCommand() {
        return new MysqlCommand();
    }
}

class OracleDataBaseUtils implements IDataBaseUtils{
    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }
    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}

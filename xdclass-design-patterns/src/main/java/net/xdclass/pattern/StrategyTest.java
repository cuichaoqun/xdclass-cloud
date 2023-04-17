package net.xdclass.pattern;

import java.util.List;

/**
 *
 * 策略模式Stragety定义了算法族，分别封装起来，让它们直接可以互相替换，此模式的变化独立于算法的使用者。
 *
 * 应用场景：
 *
 * 1.业务代码需要根据场景不同，切换不同的实现逻辑。
 *
 * 2.代码中存在大量if - else判断。
 *
 * 优点：
 *
 * 1.符合开闭原则。
 *
 * 2.避免使用多重条件转移语句，例if - else 语句、switch语句。
 *
 * 3.可以提高算法的安全性和保密性。
 *
 * 经典案例：
 * 电路交换
 * 通讯前：建立电路连接 建立电路
 * 通讯中：独占一个信道 传输数据
 * 通讯后：拆除电路连接 拆除电路
 * 实时性比较高，时延和时延抖动都比较小
 *
 * 缺点：不适用于突发性数据传输，信道利用率低，且传输速率单一
 *
 * 分组交换与报文交换比较
 * 1.设备存储要求容量低
 * 2.交换速度快
 * 3.可靠传输效率高
 * 4.更加公平
 *
 * 分组传输通常需要 存储-转发
 *
 * 称为 一个跳步
 *
 * 通常将连接两个节点的直接链路称为 一个跳步
 *
 * 信号从发送端发送出来 经过一段物理链路达到接收所需的时间 称为 传播时延
 *
 * 传输时延 也叫 发送时延
 * 一个节点到另一个节点的时间称为时延
 *
 * dp=D/V。
 * dt=L/R。
 * G=dp*R，
 * 设主机A和主机B由一条带宽为R=10（8）bit/s、长度为D=100m的链路互连，信号传播速率为V=250000km/s。如果主机A从t=0时刻开始向主机B发送长度为 L=1024bit的分组。
 * 试求：
 * 1. 主机A和主机B间的链路传输时延dt。
 *  传输时延 dt = L/R = 1024/10（8）  = 1.024 * 10(-5) s
 * 2.  主机A发送该分组的传播时延dp。
 *  传播时延 dp = D/V = 100/2.5 * 10 8 =  4 * 10(-7)
 * 3. 该分组从主机A到主机B的延迟T。(忽略结点处理时延和排队时延)
 *·延时 T = dt + dp;
 *
 * 1.024*10(-5) + 4*10(-7) = 1.064 * 10(-5)
 * 4.在t=dt时刻，分组的第一位在何处。（说明原因）
 *分组的第一位已到达主机B，因为：dt>dp。
 *  分组在 主机A 与主机B 之间 因为存在传播时延
 * 5.  主机A与主机B间链路的时延带宽积G。
 *
 * G = dp*R = 4*10(-7) * 10(8)
 * ` = 40
 *
 * 1. 主机A和主机B间的链路传输时延dt。
 *      dt=L/R=1024bit÷108bit/s=1.024×10-5s
 * 2.  主机A发送该分组的传播时延dp。
 *     dp=D/V=100m÷250000km/s=100÷250000×103m/s=4×10-7s
 * 3. 该分组从主机A到主机B的延迟T。(忽略结点处理时延和排队时延)
 *     T=dt+dp=1.024×10-5s+4×10-7s=1.064×10-5s
 * 4.在t=dt时刻，分组的第一位在何处。（说明原因）
 *    分组的第一位已到达主机B，因为：dt>dp。
 * 5.  主机A与主机B间链路的时延带宽积G。
 *     G=dp×R=4×10-7s×108bit/s=40bit
 *
 *     如图所示网络中，A在t=0时刻开始向C发送一个4Mbit的文件；B在t=(0.1+e)（e为无线趋近于0的小正实数）时刻向D发送一个2Mbit的文件。忽略传播延迟和结点处理延迟（注M=106）
 *     。如果采用报文交换方式，则A将文件交付给C需要大约多长时间？B将文件交付给D需要大约多长时间？（说明计算过程）
 *
 *     A- c 所用时间分为三段
 * A-C发送过程无需排队，因此时间延迟就是三段链路发送延迟的和
 *     第一段： dt = L/R = 4/20 = 0.2
 *     第二段： dt = L/R = 4/40 = 0.1
 *     第三段： dt = L/R = 4/20 = 0.2
 *     一共所需 0.2 + 0.1 + 0.2 = 0.5
 *     B - D 所用时间
 *      第一段： dt = L/R = 2/20 = 0.1
*  *    第二段： dt = L/R = 2/40 = 0.05
*  *    第三段： dt = L/R = 2/20 = 0.1
 *     B-D发送过程：
 * B发送延迟=2/20=0.1s
 * 文件到达路由器1，此时路由器1刚要发送A的文件，B的文件需要排队，则排队延迟=路由器1发送A文件发送延迟=4/40=0.1s
 *     因为B 在 t = (0.1+e) 开始出发 所以需要等待 A 先过通道
 *     一共所需 0.1 + 0.1 + 0.05 + 0.1 = 0.35
 *
 * 假设主机A向主机B发送一个大小为30Mb的文件，主机A到主机B的距离为10000公里，所有链路的传输速率均为10Mbps，信号传播速率为2×10^8m/s。  针对以下情况试求：
 * 1. 若主机A到主机B的路径上只有一条链路，则该文件作为一个分组从主机A到主机B的发送时延、传播时延和总时延分别是多少?
 *    传输时延  dt = L/R = 1*10(7)/2*10(8) = 0.05S
 *    传播时延  dp = D/V = 3*10(4)/1*10(4) = 3S
 *    总时延  = dt + dp = 5 + 3 = 3.05s
 * 2. 若主机A到主机B的路径上只有两条等长的链路且由一台路由器连接(忽略路由器的处理时延和排队时延)，则该文件作为一个分组从主机A到主机B的总时延是多少?(写出求解过程)
 *    分为两条等长的链路
 *
 *    dt = L/R = 0.5*10(7)/2*10(8) = 0.025s
 *    dp = D/V = 3*10(4)/1*10(4) = 3S
 *
 *    总时延 = 2dt + 2dp = 2*0.025 + 2*3 = 6.05 s
 *
 * 3. 针对(2)中链路的情形，若将该文件分成三个等长的分组并顺序发送，则第三个分组从主机A到主机B的总时延是多少?(写出求解过程)
 *
 *   A——————（）——————B
 *   30Mb 分为三分 10Mb
 *   第一分段 A 到路由器 时 第二分段还在等待 这段时常为
 *    dt = L/R = 0.5*10(7)/2*10(-8) = 0.025s
 *    dp = D/V = 1*10(4)/1*10(4) = 1s
 *    此时
 *    总共是三个分组 当分组1通过路由器后分组2才开始发送
 *    所以需要四段
 *    4 *（dt+dp） = 4* 1.025 = 4.1
 *    1.025+1.025
 *
 *    主机  A 的排队时延为  2s
 *
 *

 分组三在主机A的排队时延=2×（10Mb/10Mbps）=2s
 分组三在主机A的发送时延=文件大小/传输速率=10Mb/10Mbps=1s
 主机A到路由器的传播时延=距离/传播速率=5000公里/(2×10^8m/s)=0.025s
 分组三在路由器的发送时延=文件大小/传输速率=10Mb/10Mbps=1s
 路由器到主机B的传播时延=距离/传播速率=5000公里/(2×10^8m/s)=0.025s
 总时延=2+1+0.025+1+0.025=4.05s


 设某网页的URL为“http://www.abc.com/index.html”，且该URL对应的IP地址在你的计算机上没有缓存；文件index.html引用了 8个小图像。在域名解析的过程中，
 无等待的一次DNS解析请求与响应时间记为RTTd,HTTP请求传输Web对象过程的一次往返时间记为RTTh。试给出：
 1. 该URL中的域名。
 www.abc.com
 2. 浏览器解析到该URL对应的IP地址的最短时间和最长时间。
 最短时间 1 RTTd （本地域名服务器）
 最长时间 4 RTTd （本地域名服务器，根 顶级 权威）

 3. 若浏览器没有配置并行TCP连接，则基于HTTP1.0获取该Web页的完整内容（包括引用的图像）所需要的时间（不包括域名解析时间)。
   一共是 一个html 页面 + 8 张图片
   每次请求建立一次连接
   2 *（ 1+8）= 18 RTTd

 4. 若浏览器配置5个并行TCP连接，则基于HTTP1.0获取该Web页的完整内容（包括引用的图像）需要的时间（不包括域名解析时间)。
  先建立页面
  6 RTTd

 5. 若浏览器没有配置并行TCP连接，则基于非流水方式的HTTP1.1获取该Web 页完整内容需要的时间以及基于流水方式的HTTP1.1
 获取该Web页的完整内容（包括引用的图像）需要的时间（不包括域名解析时间）。
非流水说明
 建立连接 1 RTTD
 创建html 页面 1Rttd
 非流水方式  8 rttd + 1 + 1 = 10
 流水方式

  3RTTd

 假设使用某主机的浏览器在浏览网页时点击了一个超链接，其URL为“http://www.indi.cn/fl.html”，URL中的域名对应的IP地址在该主机上没有缓存；
 文件fl.html引用了5个小图像；域名解析过程中，无等待的一次DNS解析请求与响应时间记为RTTd，HTTP请求传输Web对象过程的一次往返时间记为RTTh。
 1. 什么情形下浏览器解析到URL对应的IP地址的时间最短？最短时间是多少？
 该URL 在本地域名服务器上 最短时间为 1Rttd
 2.什么情形下浏览器解析到URL对应的IP地址的时间最长？最长时间是多少？
 该url 在权威域名服务器上 最短时间为  4Rttd
 3.域名解析时间最长时，查询了哪些域名服务器？
 本地域名服务器
 根域名服务器
 顶级域名服务器
 权威域名服务器
 4. 若浏览器没有配置并行TCP连接，试求基于HTTP1.0的默认连接方式获取URL链接Web页完整内容（包括引用的图像）所需要的时间（不包括域名解析时间）并写出计算过程。
  获取整个页面
 1.建立 tcp连接 1个 RTTh ，创建 html页面 1个RTTh
 2.八张图片 建立八个tcp连接 5个 RTTh 获取 5张图片 建立 5个 RTTh
 1+1+5+5 = 12个RTTh


 * */



public class StrategyTest {

    public static void main(String[] args) {
        Zombie zombie = new NormalZombie(new OneStepMove(),new OneAttack());
        zombie.display();
        zombie.move();
        zombie.attack();
        //根据传入的值，改变对应的方法调用输出的结果
        zombie.setMoveable(new TenStepMove());
        zombie.setAttackable(new TenAttack());
        System.out.println("......进过策略模式传递对象改变后，调用同样的方法输出结果......");
        zombie.move();
        zombie.attack();
    }
}

//移动的接口
interface Moveable{
    void move();
}
//攻击的接口
interface Attackable{
    void attack();
}

//定义一个抽象类
abstract class Zombie {
    abstract void display();
    Moveable moveable;
    Attackable attackable;
    abstract void move();
    abstract void attack();

    public Zombie(Moveable moveable, Attackable attackable) {
        this.moveable = moveable;
        this.attackable = attackable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }
}

//子类继承抽象类
class NormalZombie extends Zombie{

    public NormalZombie(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    void display() {
        System.out.println("普通类");
    }

    @Override
    void move() {
        moveable.move();
    }

    @Override
    void attack() {
        attackable.attack();
    }
}

//子类继承抽象类
class StyleZombie extends Zombie{

    public StyleZombie(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    void display() {
        System.out.println("风格类......");
    }

    @Override
    void move() {
        moveable.move();
    }

    @Override
    void attack() {
        attackable.attack();
    }
}

//定义类实现具体的移动方法
class OneStepMove implements Moveable{
    @Override
    public void move() {
        System.out.println("走一步");
    }
}

class TenStepMove implements Moveable{
    @Override
    public void move() {
        System.out.println("走十步");
    }
}
/**
 *
 * 34.假定线性表的数据元素的类型为 Data Type，顺序表的结构定义如下：
 *         * const int Maxsize=100;
 *         * typedef struct { Data Type data[Maxsize];
 *         * int length; }SeqList;
 *         * SeqList L;
 *         * 设计算法实现顺序表的插入运算 InsertSeqlist(SeqList L,Data Type x, int i)。
 *         * 该算法是指在顺序表的第 I （1≤i≤n+1）个元素之前，插入一个新元素 x。
 *         * 使长度为 n 的线性表（a1，a2，…，ai-1，ai，…，an） 变为长度为 n+1 的线性表（a1，a2，…，ai-1，x，ai，…，an）。
 *
 * */

class OneAttack implements Attackable{
    @Override
    public void attack() {
        System.out.println("一次攻击");
    }

    public static void main(String[] args) {

    }
    void InsertSeqlist(List list, int x, int i){
        int maxsize = 100;
        if(list.size() == maxsize) {
            return; //exit("表已满");
        }
        if(i<1 || i> list.size()+1){
            return; //exit("位置错");
        }

        for (int j = list.toArray().length; j >= i; j--) {
            if(j != i){

                  list.set(j+1,list.get(j)) ;
             }else {
                 list.set(j,x);
             }

        }
    }

}

class TenAttack implements Attackable{


    @Override
    public void attack() {
        System.out.println("十次攻击");
        /*
        * 2
        * n-1
        * O（n平方）
        * O(log2(n)) 二分查找法
        * O(nlog2(n)) 快速
        * n(n-1)/2
        * O(n+e)
        * 栈
        * L->next
        * 双亲表示法
        * 查找 建立 读取 插入 删除
        *  组织方式 存储方式 一组操作
        * 二分探测法  二次探测法
        *
        * 拓扑排序为二维图
        * 拓扑序列
        * DAEBFC
        * 快速排序
        * 61 87 12 3 8 79
        * [8 3 12] 61 [87 79]
        * 平均时间复杂度 O（log2(n)）
        * 最坏情况复杂度 O（nlog2(n)）
        *34.假定线性表的数据元素的类型为 Data Type，顺序表的结构定义如下：
        * const int Maxsize=100;
        * typedef struct { Data Type data[Maxsize];
        * int length; }SeqList;
        * SeqList L;
        * 设计算法实现顺序表的插入运算 InsertSeqlist(SeqList L,Data Type x, int i)。
        * 该算法是指在顺序表的第 I （1≤i≤n+1）个元素之前，插入一个新元素 x。
        * 使长度为 n 的线性表（a1，a2，…，ai-1，ai，…，an） 变为长度为 n+1 的线性表（a1，a2，…，ai-1，x，ai，…，an）。
        *
        *
        *  35.已知二叉链表的类型定义如下：
        * typedef struct btnode { DataType data; struct btnode *lchild,*rchild; } *BinTree;
        * 以二叉链表作存储结构，试编写求二叉树叶子结点个数的算法 leafnode_num(BinTree bt)。
        * typedef struct btnode {
        *   DataType data;
        *   struct btnode *lchild,
        *   *rchild;
        * }
        *  *BinTree;
        * temp.n=n+s.n
        * .声明一个交通工具（vehicle）基类，具有 maxspeed、weight 成员变量，run、stop 成员函数（简单 输出提示”正在行进”，"停止"）同时编写 vehicle 类的构造函数和析构函数。
        * 由此派生出自行车类 (bicycle)、汽车类(motorcar)，自行车类有高度（height）属性
        * ，汽车（motorcar）类有座位数（seatnum）。 从 bicycle 和 motorcar 派生出摩托车类（motor-cycle），在继承过程中注意把 vehicle 设置为虚基类。
        *
        * #include <iostream>
        *  using namespace std;
        *  class vehicle{
        *   private int maxspeed;
        *   private int weight;
        *
        *   public vehicle (int m, int w){
        *       maxspeed = m;
        *       weight = w;
        *       cout<<"vehicle构造函数"<<endl;
        *   }
        *   ～vehicle(){
        *       cout<<"vehicle析构函数"<<endl;
        *   }
        *   void run(){
        *        cout<<"车在进行中*****"<<endl;
        *  }
        *  void stop(){
        *   cout<<"车停止"<<endl;
        *   }
        * }
        *   class bicycle:virtual public vehicle{
        *       private double height;
        *       public bicycle(int m,int w,double h) virtual(m,w)
        *       |height=h;|
        *   };
        *   class motorcar:virtual public virtual
        *  运行时
        *  纯虚
        *  抽象类
        *  多态
        *  B::s() called.??
        *  E::E() called.
        *  E::~E() called.
        *  B::~start() called.
        *  fun()  called.
        *
        * E::E() called.
        * fun() calld.
        * E::~E() called.
        * B::~start() called
        * */
    }
}

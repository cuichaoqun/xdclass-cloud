package net.xdclass.pattern;

public class Singleton {

}
class LazySingleton{
    private volatile static LazySingleton lazySingleton = null;

    public LazySingleton() {
    }

    public LazySingleton getInstance(){
        if(null == lazySingleton){
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }
}


class HungrySingleton{

    private static HungrySingleton  hungrySingleton = new HungrySingleton();

    public HungrySingleton() {
    }

    public HungrySingleton getInstance(){
        return hungrySingleton;
    }
}

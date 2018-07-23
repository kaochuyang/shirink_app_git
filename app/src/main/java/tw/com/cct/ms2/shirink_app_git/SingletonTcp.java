package tw.com.cct.ms2.shirink_app_git;

public class SingletonTcp {
    private static final SingletonTcp ourInstance = new SingletonTcp();

    public static SingletonTcp getInstance() {
        return ourInstance;
    }

    private SingletonTcp() {
    }







}

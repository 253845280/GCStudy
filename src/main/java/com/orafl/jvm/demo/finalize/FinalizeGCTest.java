package com.orafl.jvm.demo.finalize;

import javax.xml.ws.Holder;
import java.util.HashMap;

/**
 * finalize 是执行很慢的。 进入 一个队列执行
 * 官方不推荐使用了。
 * 一般用来做finally 事件处理
 * 但是finally 更好用
 */
public class FinalizeGCTest {

    public static FinalizeGCTest HOOK = null;

    public void isalive(){
        System.out.println("I'm alive");
    }

    public void finalize() throws Throwable {
        super.finalize();

        System.out.println(" 回收了回收了 finalize excuted ! 执行了执行了。 ");
        FinalizeGCTest.HOOK=this;
    }

    public static void main(String[] args) throws InterruptedException {

        HOOK =new FinalizeGCTest();

        //自我救赎第一步
        HOOK = null;
        System.gc();
        //Thread.sleep(500);
        if(HOOK!=null){
            HOOK.isalive();
        }else {
            System.out.println("第一次就被回收掉了。");
        }



        //自我救赎第一步
        HOOK = null;
        System.gc();
        //Thread.sleep(500);
        if(HOOK!=null){
            HOOK.isalive();
        }else {
            System.out.println("第二次被回收掉了。");
        }

        //System.out.println(HOOK);
    }
}

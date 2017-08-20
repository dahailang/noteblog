package com.pursuit.noteblog;
public class CPUTest {  
      
    //定义时间片大小（毫秒）  
    public static final double TIME = 1000;  
    //画直线方法  
    private static void lineGraph(double rate) throws InterruptedException{  
        while (true){  
            doSomeSimpleWork(rate * TIME);  
            Thread.sleep((long) (TIME - rate * TIME));  
        }  
    }  
    //画正弦曲线方法  
    private static void sinGraph() throws InterruptedException{  
        double x = 0;  
        double y = 0;         
        while (true){  
            y = (Math.sin(x) + 1) * TIME / 2;  
            doSomeSimpleWork(y);  
            x += 0.1;  
            Thread.sleep((long) (TIME - y));  
        }  
    }  
    //占用CPU方法  
    private static void doSomeSimpleWork(double time) {  
        long startTime = System.currentTimeMillis();  
        while ((System.currentTimeMillis() - startTime) < time) {  
        }  
    }  
      
    /** 
     * @param args the command line arguments 
     */  
    public static void main(String[] args) throws InterruptedException {  
        //lineGraph(0.5);  
        sinGraph();  
    }  
      
}  
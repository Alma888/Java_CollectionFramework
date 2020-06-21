# Java_CollectionFramework

package List;
//ArrayList 中ensureCapacity方法的使用与优化

//对于ArrayList 中的一个方法ensureCapacity(int n)，这个方法可以对ArrayList底层的
// 数组进行扩容，显式的调用这个函数。
//  （由源码可知，系统的默认扩容是：扩容为原数组长度的1.5倍）
//    如果参数n大于底层数组长度的1.5倍(默认扩容)，那么这个数组的容量就会被扩容到这个参数值n；
//    如果参数n小于底层数组长度的1.5倍(默认扩容)，那么这个容量就会被扩容到底层数组长度的1.5倍。

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//总之，就是这个函数可以对底层数组进行扩容，在适当的时机，很好的利用这个函数，
//将会使我们写出来的程序性能得到提升。
public class ListDemo1 {
    public static void main(String[] args) {
        final int N=1000000;
        Object obj=new Object();
        ArrayList list1=new ArrayList();
        long start=System.currentTimeMillis();
        for(int i=0;i<N;i++){
            list1.add(obj);
        }
        System.out.println(System.currentTimeMillis()-start);

        ArrayList list2=new ArrayList();
        long start2=System.currentTimeMillis();
        list2.ensureCapacity(N);//显式的对底层数组进行扩容
        for(int i=0;i<N;i++){
            list2.add(obj);
        }
        System.out.println(System.currentTimeMillis()-start2);

        List list=Collections.synchronizedList(new ArrayList<>());
    }
}

//第2段的效率显然比第1段高很多。
// 原因：因为第1段代码如果没有一次性扩到想要的最大容量
// 的话，它就会在添加元素的过程中，一点一点的进行扩容，
//要知道对数组的扩容是要进行数组拷贝的，这就会浪费大量的时间。
// 如果已经预知容器可能会装多少元素，最好显式的调用ensureCapacity这个方法，一次性扩容到位。

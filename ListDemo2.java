# Java_CollectionFramework

package List;
//要想使用ArrayList集合类，并且希望是线程安全的，那就用
// Collections.synchronizedList方法。
import java.util.*;

public class ListDemo2 extends Vector {
    public static void main(String[] args) {
        List<String> list=Collections.synchronizedList(new ArrayList<>());
        list.add("aaa");
        list.add("bbb");
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}

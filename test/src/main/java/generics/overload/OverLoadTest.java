package generics.overload;

import java.util.List;

/**
 * 方法重载时，将 参数中的 泛型 擦除后，两个方法 的 特征签名相同，故 两个方法不能相互重载
 *
 * 由于 方法的返回值 不作为重载的特征签名，故即使 方法的返回值相同也不会使得两个方法进行重载
 */
public class OverLoadTest {
//    public int test(List<String> list) {
//        System.out.println(list);
//        return 0;
//    }
//
//    public long test(List<Integer> list) {
//        System.out.println(list);
//    }
}

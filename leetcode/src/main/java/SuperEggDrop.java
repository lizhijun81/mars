//你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N 共有 N 层楼的建筑。
//
// 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
//
// 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
//
// 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
//
// 你的目标是确切地知道 F 的值是多少。
//
// 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
//
//
//
//
//
//
// 示例 1：
//
// 输入：K = 1, N = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
//否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
//如果它没碎，那么我们肯定知道 F = 2 。
//因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
//
//
// 示例 2：
//
// 输入：K = 2, N = 6
//输出：3
//
//
// 示例 3：
//
// 输入：K = 3, N = 14
//输出：4
//
//
//
//
// 提示：
//
//
// 1 <= K <= 100
// 1 <= N <= 10000
//
// Related Topics 数学 二分查找 动态规划


import java.util.HashMap;
import java.util.Map;

/**
 * 1. 状态：鸡蛋个数、楼梯的层数
 * 2. 表示：dp[N][K] = n; N 层楼 K 个鸡蛋，最坏的情况下至少需要扔的次数
 * 3. 状态转移：dp[N][K] = max{dp[I-1][K-1], dp[I+1][K]}; // 第I层碎了，往下层实验；第I层没碎，往上层实验; 不一定是二分的方式进行实验；比如100层2个鸡蛋；所以只能 依次试
 * 4. 最优解：min{dp[N][K]}
 *
 * base case :
 *      1 个鸡蛋，只能逐层实验 N 次；
 *      1 层，只需 1 次 ；
 */
public class SuperEggDrop {
    Map<String, Integer> dp = new HashMap<>();

    public int superEggDrop_1(String I, int K, int N) {// 第I层怎么选择，二分的方式，十分的方式？ 不确定，只能按层实验
        if (K == 1) {
            return N;
        }

        if (N == 0) {
            return 0;
        }

        if (dp.get(N) != null) {
            return dp.get(N);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {//  从i层开始扔
            int c1 = superEggDrop_1(I +"_" + i,K - 1, i - 1);
            int c2 = superEggDrop_1(I +"_" + i, K,N - i);
            int c = Math.max(c1, c2) + 1;
            if (c < min) {
                min = c;
                if ( i != 1)
                    System.out.println("I=" + I + "     i=" + i + "     N=" + N + "     K=" + K);
            }
        }

//        dp.put(N, min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int superEggDrop(int K, int N) {
        if (K == 1) {
            return N;
        }

        if (N == 0) {
            return 0;
        }

        if (dp.get(N+ "_" + K) != null) {
            return dp.get(N+ "_" + K);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {//  从i层开始扔
            min = Math.min(min, Math.max(superEggDrop(K - 1, i - 1), superEggDrop( K,N - i)) + 1);
        }

        dp.put(N+ "_" + K, min);

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        SuperEggDrop superEggDrop = new SuperEggDrop();
//        System.out.println(superEggDrop.superEggDrop_1("-1",3, 15));
        System.out.println(superEggDrop.superEggDrop(3, 15));
//        System.out.println();
    }
}

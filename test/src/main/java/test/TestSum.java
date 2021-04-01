package test;

public class TestSum {

    public static final int[] M = new int[]{1,2,3,4,5,6,15,20,30,40,50,139};
    public static final int N = 55;

    public void sum() {
        int s1 = -1;
        int s2 = -1;
        int m_1 = Integer.MAX_VALUE;
        for (int i = 0; i < M.length; i++) {
            int r = N - M[i];
            int m_2 = Integer.MAX_VALUE;

            int n = 0;
            for (int j = 0; j < M.length; j++) {// 找最优的第二个数
                if (j == i) {
                    continue;
                }

                if (m_2 < Math.abs(r - M[j])) {// 递增，跳过
                    break;
                }
                n = j;
                m_2 = Math.abs(r - M[j]);
            }

            if (m_1 < Math.abs(N - (M[i] + M[n]))) {
                continue;
            }
            m_1 = Math.abs(N - (M[i] + M[n]));
            s1 = i;
            s2 = n;
        }

        System.out.println(M[s1] + ", " + M[s2]);
    }

    public static void main(String[] args) {
        TestSum testSum = new TestSum();
        testSum.sum();


        Integer a = 1000;
        int b = 1000;
        System.out.println(a == b);

        Integer c = 1000;
        System.out.println(a == c);
    }

}

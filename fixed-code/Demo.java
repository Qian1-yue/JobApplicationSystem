public class Demo {

    public static void main(String[] args) {

        int[] scores = {80, 90, 70};

        int sum = 0;

        // 修复：下标到 length - 1 为止，原来用 <= 会多访问一次导致数组越界
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }

        System.out.println(sum);
    }
}

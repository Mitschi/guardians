public class MainClass {
    public static void main(String[] args) {
        int test = 0;
        test++;
        System.out.println(test);

        for (int idx = 0; idx < 50; idx++) {
            for (int i = 0; i <= idx; i++) {
                if (i % 10 == 0) {
                    System.out.print("[" + i + "],");
                } else {
                    System.out.print(i + ",");
                }
            }
            System.out.println(" ");
        }

    }
}
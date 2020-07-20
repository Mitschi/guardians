import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        int test = 0;
        int l = 0;
        Scanner scan = new Scanner(System.in);
        test++;
        System.out.println(test);

        System.out.println("gib eine zahl ein");
        l=scan.nextInt();
        for (int idx = 0; idx < l; idx++) {
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
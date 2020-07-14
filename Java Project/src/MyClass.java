public class MyClass {
    public static void main(String[] args) {
        for(int idx = 0; idx < 200; idx++) {
            for (int innerIdx = 0; innerIdx < idx; innerIdx++) {
                if (innerIdx % 2 == 0)
                    System.out.print("#");
                else
                    System.out.print("-");
            }
            System.out.print("\n");
        }
    }
}

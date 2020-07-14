public class MyClass {
    public static void main(String[] args) {
        for(int idx = 0; idx < args.length + 185; idx++) {
            for (int innerIdx = 0; innerIdx < idx; innerIdx++) {
                System.out.print("#");
            }
            System.out.print("\n");
        }
    }
}

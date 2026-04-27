public class Test {
    public static void main(String[] args) {
        try {
            Integer.valueOf("");
        } catch (Exception e) {
            System.out.println("NFE Message: " + e.getMessage());
        }
    }
}

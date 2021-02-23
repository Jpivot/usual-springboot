package algorithm;

public class PrecisionLoss{

    public static void main(String[] args) {
        float a = 1.0f - 0.6f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false
    }
}

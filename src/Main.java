import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        for (String arg : args){
            System.out.print(arg + " ");
        }
        System.out.println();
        if (args.length == 0) printUsage();
        for (int i=0; i<args.length; i+=2){
            switch (args[i]) {
                case "-e":
                    printE(args[i+1]);
                    break;
                case "-fib":
                    printFib(args[i+1]);
                    break;
                case "-fac":
                    printFactorial(args[i+1]);
                    break;
                default:
                    printIncorrectArg(args[i]);
                    break;
            }

        }
    }





    static void printE(String num){
        System.out.println("Value of e using " + num + " iterations is " + getE(num));
    }
    static void printFactorial(String num){
        try {
            int numInt = Integer.parseInt(num);
            if (numInt >= 0){
                System.out.println("Factorial of " + num + " is " + getFactorial(new BigInteger(num)));
            }
            else System.out.println("Valid factorial range is [0, 2147483647]");

        }
        catch (java.lang.NumberFormatException exception){
            System.out.println("Valid factorial range is [0, 2147483647]");
        }

    }
    static void printFib(String num){
        int integer = Integer.parseInt(num);
        if (integer >= 0 && integer <= 40){
            System.out.println("Fibonacci of " + num + " is " + getFib(integer));
        }
        else {
            System.out.println("Valid fibonacci range is [0, 40]");
        }
    }

    static BigInteger getFactorial(BigInteger num){
        if (num.compareTo(new BigInteger("0")) == 0) return new BigInteger("1");
        return num.multiply( getFactorial(num.add(new BigInteger("-1"))));
    }
    static int getFib(int num){
        int t1 = 0, t2 = 1;
//        System.out.print("First " + num + " terms: ");
        for (int i = 1; i <= num; ++i) {
//            System.out.print(t1 + " + ");
            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }
        return t2;
    }
    static BigDecimal getE(String iterations){
        if (Integer.parseInt(iterations)==0){
            return new BigDecimal("1");
        }
        int iter = Integer.parseInt(iterations) - 1;
        String iterated = Integer.toString(iter);
        BigDecimal append = getE(iterated);
        BigDecimal toReturn = new BigDecimal("1").divide(new BigDecimal(iterations), ).setScale(20);
        return toReturn.add(append);
    }

    static void printIncorrectArg(String arg){
        System.out.println("Unknown command line argument: " + arg);
    }
    static void printUsage(){
        System.out.println("--- Assign 1 Help ---\n" +
                "  -fib [n] : Compute the Fibonacci of [n]; valid range [0, 40]\n" +
                "  -fac [n] : Compute the factorial of [n]; valid range, [0, 2147483647]\n" +
                "  -e [n] : Compute the value of 'e' using [n] iterations; valid range [1, 2147483647]");
    }
}

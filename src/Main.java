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
        System.out.println("Value of e using " + num + " iterations is " + getE(Integer.parseInt(num)));
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

    static float getE(int iterations){
        if (iterations == 0){
            return 1;
        }
        int denominator = new BigInteger(Integer.toString(iterations)).intValue();
        int denom = getFactorial(new BigInteger("" + denominator)).intValue();
        return (float) 1/denom + getE(iterations - 1);
//        return new BigDecimal(1/denom + getE(iterations-1));

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

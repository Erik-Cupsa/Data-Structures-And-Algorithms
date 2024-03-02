import java.math.BigInteger;
import java.util.Scanner;

public class A2_Q2 {

	public static String mod_fibonacci(int N, BigInteger K) {
		BigInteger[] fibonacciNums = new BigInteger[10000];
		fibonacciNums[0] = BigInteger.valueOf(0);
		fibonacciNums[1] = BigInteger.valueOf(1);
		fibonacciNums[2] = BigInteger.valueOf(1);

		for (int i = 3; i <= N; i++){
			fibonacciNums[i] = fibonacciNums[i-2].add(fibonacciNums[i-1]);
		}
		return calculateFibonacci(N, K, fibonacciNums);
	}

	private static String calculateFibonacci(int N, BigInteger K, BigInteger[] fibonacciNums){
		if (N == 1) return "X";
		if (N == 2) return "Y";
		BigInteger currFib = fibonacciNums[N-2];
		if(K.compareTo(currFib)>0){
			return calculateFibonacci(N-1, K.subtract(currFib), fibonacciNums);
		}
		else{
			return calculateFibonacci(N-2, K, fibonacciNums);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		BigInteger k = scanner.nextBigInteger();
		String result = mod_fibonacci(n, k);
		System.out.println(result);
	}

}

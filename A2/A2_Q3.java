import java.util.*;

public class A2_Q3 {

	//	private static int[] helper(int[] values_bills, int price, int[][][] dp, int i, int j, int[][] hits, int[] cumSum){
//		if(j==0){
//			return new int[]{0,0};
//		}
//		if(cumSum[i] < j){
//			return new int[]{-1, -1};
//		}
//		if (cumSum[i] == j) {
//			return new int[] {values_bills[i-1], i};
//		}
//		if (values_bills[i-1] == j) {
//			return new int[]{j, 1};
//		}
//		if(hits[i][j] == 1){
//			int[] newDP = new int[]{dp[i][j][0], dp[i][j][1]};
//			return newDP;
//		}
//		int[] candidate;
//		int[] prev = helper(values_bills, price, dp,i-1, j, hits, cumSum);
//		if(j-values_bills[i-1] >= 0){
//			candidate = helper(values_bills, price, dp, i-1, j-values_bills[i-1], hits, cumSum);
//			if(candidate[0] != -1){
//				candidate[0] = candidate[0] + values_bills[i-1];
//				candidate[1] = candidate[1]+ 1;
//			}
//			// candidate = new int[]{dp[i-1][j-values_bills[i-1]][0]+ values_bills[i-1], dp[i-1][j-values_bills[i-1]][1]+ 1};
//		}
//		else if(j-values_bills[i-1] < 0){
//			candidate = new int[]{values_bills[i-1], 1};
//		}
//		else{
//			candidate = new int[]{-1, -1};
//			// candidate[0] = -1;
//			// candidate[1] = -1;
//		}
//		// if(i == j && i == 1){
//		// 	System.out.println(Arrays.toString(candidate));
//		// 	System.out.println(Arrays.toString(prev));
//		// }
//		hits[i][j] = 1;
//		// System.out.println(i);
//		// System.out.println(j);
//		// System.out.println(Arrays.toString(candidate));
//		// System.out.println(Arrays.toString(prev));
//		if(candidate[0] < prev[0] && candidate[0] != -1 || prev[0] == -1){
//			dp[i][j] = new int[]{candidate[0], candidate[1]};
//			return candidate;
//		}
//		else if(candidate[0] == prev[0]){
//			if(candidate[1] < prev[1]){
//				dp[i][j] = new int[]{candidate[0], candidate[1]};
//				return candidate;
//			}
//			else{
//				dp[i][j] = new int[]{prev[0], prev[1]};
//				return prev;
//			}
//		}
//		else{
//			dp[i][j] = new int[]{prev[0], prev[1]};
//			return prev;
//		}
//	}
	public static int[] change(int[] values_bills, int price) {
//		int[][][] dp = new int[values_bills.length + 1][price + 1][2];
//		int[][] hits = new int[values_bills.length + 1][price + 1];
//		int[] cumSum = new int[values_bills.length + 1];
//		for (int i = 0; i < values_bills.length; i++ ) {
//			cumSum[i + 1] = cumSum[i] + values_bills[i];
//		}
		int[] costs = new int[10001];
		for (int i = 0; i < values_bills.length; i++) {
			costs[0] = 0;
			for (int j = 1; j <= 10000; j++) {
				costs[j] = -1;
			}
		}
		Arrays.sort(values_bills);
		costs[values_bills[0]] = 1;
		int sum = 0;
		int bills = 0;
		for (int i = 0; i < values_bills.length; i++ ) {
			sum += values_bills[i];
			bills++;
			if (sum >= price) break;
		}
		int[] answer = new int[]{sum, bills};
		for (int i = 1; i < values_bills.length; i++ ) {
			int[] temp = new int[]{-1, -1};
			for (int j = Math.min(10000, answer[0])-values_bills[i]; j >= 0; j-- ) {
				if (costs[j] != -1) {
					if (costs[j+values_bills[i]] == -1 || costs[j+values_bills[i]] > costs[j] + 1) {
						costs[j + values_bills[i]] = costs[j] + 1;
					}
					if (j + values_bills[i] >= price && costs[j + values_bills[i]] != -1) {
						if (temp[0] == -1 || j + values_bills[i] < temp[0] || j + values_bills[i] == temp[0] && costs[j + values_bills[i]] < temp[1]) {
							temp = new int[]{j + values_bills[i], costs[j + values_bills[i]]};
						}
					}
				}
			}
			if (temp[0] != -1 && (temp[0] < answer[0] || temp[0] == answer[0] && temp[1] < answer[1])) {
				answer = temp;
			}
		}
//		answer = helper(values_bills, price, dp, values_bills.length, price, hits, cumSum);
		return answer;
	}
	private static int[] parseValuesBills(String input) {
		String[] values = input.substring(input.indexOf('[') + 1, input.indexOf(']')).split(",");

		int[] valuesBills = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesBills[i] = Integer.parseInt(values[i].trim());
		}
		return valuesBills;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String valueBillsString = scanner.nextLine();
		String priceString = scanner.nextLine();
		int price = Integer.parseInt(priceString.substring(7, 11));
		int[] valueBills = parseValuesBills(valueBillsString);
		int[] result = change(valueBills, price);
		System.out.println(Arrays.toString(result));
		scanner.close();
	}

}
/*
 *  0 1 2 3 4 5 ...
 * 0 0, 0 -1 -1 -1 -1 -1...
 * 1 0 val - cost ... 0 -1 -1 -1
 * 2 0
 * 3 0
 * 4 0
 * 5 0
 * ...
 *  v [i, j] = min {v[i-1][j], variable} i = bill, j = cost, variable = v[i-1][j-j[i]] + value of bill
 * curr cost - val bill
 */
import java.math.BigInteger;
import java.util.*;

public class A2_Q3 {

	
	public static int[] change(int[] values_bills, int price) {
		int[] answer = new int[2];
		answer[0] = values_bills[0];
		answer[1] = price;
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

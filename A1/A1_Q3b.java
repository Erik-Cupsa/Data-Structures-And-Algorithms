import java.util.*;

public class A1_Q3b {

	public static int protocol_3b(int[] arrangement) {
		HashSet<Integer> uniqueCourses = new HashSet<>(); //set for current list of unique courses
		//initializing variables
		int left = 0;
		int right = 0;
		int maxLength = 0;

		while (right < arrangement.length) { //looping through array
            if (!uniqueCourses.contains(arrangement[right])) { //unique course found
                uniqueCourses.add(arrangement[right]); //add course to list
                right++; //extend window right
                int currentLength = right - left; //calculate current length and update max
                maxLength = Math.max(maxLength, currentLength);
            } else {
                // Duplicate exam found, so remove the left most exam
                uniqueCourses.remove(arrangement[left]);
                left++; //shift the window left
            }
        }

        return maxLength; //return max length of window
	}

}
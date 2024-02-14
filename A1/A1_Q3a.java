import java.util.*;

public class A1_Q3a {

	public static int protocol_3a(int[] arrangement) {
		//initializing hash map to store courses and their last occurunces
		java.util.HashMap<Integer, Integer> courses = new java.util.HashMap<>();

		int minDistance = arrangement.length; //setting min distance to the size of arrangement in case no duplicate courses found

		for(int i=0; i < arrangement.length; i++){ // looping through arrangments
			int curr = arrangement[i]; //current course
			if(courses.containsKey(curr)){ //if course in courses hash map, meaning it has been encountered before
				int val = courses.get(curr);
				val = i - val;
				minDistance = Math.min(minDistance, val); //see if distance is less than previous min
			}
			courses.put(curr, i); //update courses hashmap
		}
		return minDistance; //return min distance
	}
}

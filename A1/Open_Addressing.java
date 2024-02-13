//No collaborators
import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
            //following provided formula
            int h = (A*key) % power2(w) >> (w-r); //first calculating h(k)
            return (h+i) % m; //returning g(k) based off of h(k)
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //initializing variables
            int numCollisions = 0;
            int hash = 0;
            
            for(int i = 0; i < m; i++){ //looping through array
                hash = probe(key, i); //calculate probe value
                if(Table[hash] == -1 || Table[hash] == -2){ //if we found an empty (including previously deleted slot)
                    Table[hash] = key; //insert key and break out of loop
                    break;
                }
                numCollisions ++; //increment amount of collisions
            }
            return numCollisions; //returning collisions
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int numCollisions = 0;
            for (int key: keyArray) {
                numCollisions += insertKey(key);
            }
            return numCollisions;
        }
            
         /**Removes key k from the hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //initializing variables
            int numCollisions = 0;
            int hash = 0;
            
            for (int i = 0; i < m; i++){ //looping through array
                hash = probe(key, i); //calculating probe value
                if(Table[hash] == key){ // if they key is found
                    Table[hash] = -2; //delete it by assigning a specific value (chose -2) and break
                    break;
                }
                else if(Table[hash] == -1){ //if an empty space, increment collisions and return
                    numCollisions++;
                    break;
                }
                numCollisions++;
            }
            return numCollisions; //returning collisions
        }
}

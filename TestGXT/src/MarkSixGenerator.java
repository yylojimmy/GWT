import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MarkSixGenerator {
	public static int[] ignoreList = {};
	public static final int maxNum = 33;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ignoreCheckLst = new ArrayList<Integer>();
		List<Integer> specialNumberLst = new ArrayList<Integer>();
		for(int i = 0; i< ignoreList.length; i++){
			ignoreCheckLst.add(ignoreList[i]);
			specialNumberLst.add(ignoreList[i]);
		}
		generate(ignoreCheckLst, specialNumberLst);
		generate(ignoreCheckLst, specialNumberLst);
		generate(ignoreCheckLst, specialNumberLst);
		generate(ignoreCheckLst, specialNumberLst);
		generate(ignoreCheckLst, specialNumberLst);
		processRemainNum(ignoreCheckLst);
		//generate(ignoreCheckLst, specialNumberLst);
		//generate(ignoreCheckLst, specialNumberLst);
		//generate(ignoreCheckLst, specialNumberLst);
		List<Integer> sevenNumLst = new ArrayList<Integer>();
		Random randomGenerator = new Random();
		System.out.print("seventh List -");
		for(int i = 0; i < 6; i++){
			int randomInt = randomGenerator.nextInt(17);
		      if(!checkExist(sevenNumLst,randomInt) && randomInt != 0){
		    	  sevenNumLst.add(randomInt);
		    	  System.out.print(randomInt+"|");
		      }else{
		    	  i--;
		      }
		}
		System.out.println();
	    
	}
	
	public static boolean checkExist(List<Integer> ignoreCheckLst, int randomNum){
		boolean returnFlg = false;
		for(int i = 0; i < ignoreCheckLst.size(); i++){
			if(randomNum == ignoreCheckLst.get(i)){
				returnFlg = true;
				//System.out.println("number is in ignoreLst "+randomNum);
				break;
			}
		}
		return returnFlg;
	}

	public static void generate(List<Integer> ignoreCheckLst, List<Integer> specialNumberLst){
		Random randomGenerator = new Random();
		int[] choosenLst = new int[6];
		
	    for (int idx = 0; idx < 6; idx++){
	      int randomInt = randomGenerator.nextInt(maxNum+1);
	      if(!checkExist(ignoreCheckLst,randomInt) && randomInt != 0){
	    	  choosenLst[idx] = randomInt;
	    	  ignoreCheckLst.add(randomInt);
	      }else{
	    	  idx--;
	      }
	      
	    }
	    System.out.print("Generate List - ");
	    for(int j = 0; j < choosenLst.length; j++){
	    	System.out.print(choosenLst[j]+" ");
	    }
	    Random specialGenerator = new Random();
	    if(specialNumberLst.size()>0){
	    	int randomSpecialNum = specialGenerator.nextInt(specialNumberLst.size());
	    	System.out.print(" | "+specialNumberLst.get(randomSpecialNum));
	    	specialNumberLst.remove(randomSpecialNum);
	    }
	    
	    System.out.println();
	}
	
	public static void processRemainNum(List<Integer> ignoreCheckLst){
		System.out.print("special List - ");
		for(int i = 1; i<= maxNum; i++){
			boolean exist = checkExist(ignoreCheckLst,i);
			if(!exist){
				System.out.print(i+"|");
			}
		}
		
		Random specialGenerator = new Random();
		for(int i = 0; i < 3; i++){
			int randomSpecialindex = specialGenerator.nextInt(ignoreCheckLst.size());
			System.out.print(ignoreCheckLst.get(randomSpecialindex)+"|");
			ignoreCheckLst.remove(randomSpecialindex);
		}
		System.out.println();
	}
}

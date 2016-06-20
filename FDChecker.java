//Allen Lee (al728) , Di Huang (dh626) , Zhenglin Lu (zl474)
import java.util.*;

public class FDChecker {

	/**
	 * Checks whether a decomposition of a table is dependency
	 * preserving under the set of functional dependencies fds
	 * 
	 * @param t1 one of the two tables of the decomposition
	 * @param t2 the second table of the decomposition
	 * @param fds a complete set of functional dependencies that apply to the data
	 * 
	 * @return true if the decomposition is dependency preserving, false otherwise
	 **/
	public static boolean checkDepPres(AttributeSet t1, AttributeSet t2, Set<FunctionalDependency> fds) {
		//your code here
		//a decomposition is dependency preserving, if local functional dependencies are
		//sufficient to enforce the global properties
		//To check a particular functional dependency a -> b is preserved, 
		//you can run the following algorithm
		//result = a
		//while result has not stabilized
		//	for each table in the decomposition
		//		t = result intersect table 
		//		t = closure(t) intersect table
		//		result = result union t
		//if b is contained in result, the dependency is preserved
		for (FunctionalDependency f : fds){
			AttributeSet result = f.left;
			
			AttributeSet oldResult = new AttributeSet();
			while (!oldResult.equals(result)){
				oldResult = result;
				
				for (int i = 0; i < 2; i++){
					AttributeSet Z = t1;
					if (i == 1) Z = t2;
					
					AttributeSet temp = common(closure(common(result, Z), fds), Z);
					result.addAll(temp);
				}				
			}
			
			if (!result.contains(f.right))
				return false;						
		}
		return true;
	}

	/**
	 * Checks whether a decomposition of a table is lossless
	 * under the set of functional dependencies fds
	 * 
	 * @param t1 one of the two tables of the decomposition
	 * @param t2 the second table of the decomposition
	 * @param fds a complete set of functional dependencies that apply to the data
	 * 
	 * @return true if the decomposition is lossless, false otherwise
	 **/
	public static boolean checkLossless(AttributeSet t1, AttributeSet t2, Set<FunctionalDependency> fds) {
		//your code here
		//Lossless decompositions do not lose information, the natural join is equal to the 
		//original table.
		//a decomposition is lossless if the common attributes for a superkey for one of the
		//tables.
		
		AttributeSet common = common(t1, t2);	
		AttributeSet commonPlus = closure(common, fds);
	    
		if (commonPlus.containsAll(t1) || commonPlus.containsAll(t2))
	    	return true;
	    
	    return false;
	}

	//recommended helper method
	//finds the total set of attributes implied by attrs
	private static AttributeSet closure(AttributeSet attrs, Set<FunctionalDependency> fds) {
		AttributeSet closure = new AttributeSet(attrs);
		
		AttributeSet oldClosure = new AttributeSet();
		while(!oldClosure.equals(closure)){
	        oldClosure = closure;
			
			for (FunctionalDependency fd : fds){
				if (closure.containsAll(fd.left)){
					closure.add(fd.right);
				}			
			}
		}
		
		return closure;
	}
	
	//find the intersection attributes set between two attribute set
	private static AttributeSet common(AttributeSet t1, AttributeSet t2){
		AttributeSet common = new AttributeSet();
		for (Attribute t1Attr : t1){
			if (t2.contains(t1Attr))
				common.add(t1Attr);
		}
		return common;
	}
}

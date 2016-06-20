
import static org.junit.Assert.*;
import java.util.*;

public class Test {

	@org.junit.Test
	public void depPresBasictest() {
		AttributeSet t1 = new AttributeSet();
		AttributeSet t2 = new AttributeSet();
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		
		t1.add(new Attribute("a"));
		t2.add(new Attribute("b"));
		
		fds.add(new FunctionalDependency(t1,new Attribute("a")));

		// tables
		// a
		// b
		// fds
		// a -> a
		assertTrue(FDChecker.checkDepPres(t1, t2, fds));
		
		
		fds.add(new FunctionalDependency(t1, new Attribute("b")));
		// tables
		// a
		// b
		// fds
		// a -> a
		// a -> b
		assertFalse(FDChecker.checkDepPres(t1, t2, fds));
	}

	@org.junit.Test
	public void losslessBasictest() {
		AttributeSet t1 = new AttributeSet();
		AttributeSet t2 = new AttributeSet();
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		
		t1.add(new Attribute("a"));
		t2.add(new Attribute("b"));
		
		// tables
		// a
		// b
		// fds
		assertFalse(FDChecker.checkLossless(t1, t2, fds));
		
		t1.add(new Attribute("b"));
		// tables
		// a b
		// b
		// fds
		assertTrue(FDChecker.checkLossless(t1, t2, fds));
	}
	
	@org.junit.Test
	public void depPresFDtest() {
		AttributeSet t1 = new AttributeSet();
		AttributeSet t2 = new AttributeSet();
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		
		t1.add(new Attribute("a"));
		t1.add(new Attribute("b"));
		t2.add(new Attribute("b"));
		
		fds.add(new FunctionalDependency(t1,new Attribute("b")));

		// tables
		// a b
		// b
		// fds
		// ab -> b
		assertTrue(FDChecker.checkDepPres(t1, t2, fds));
		
		
		fds.add(new FunctionalDependency(t2, new Attribute("a")));
		// tables
		// a b
		// b
		// fds
		// ab -> b
		// b -> a
		assertTrue(FDChecker.checkDepPres(t1, t2, fds));
	}

	@org.junit.Test
	public void losslesstest() {
		AttributeSet t1 = new AttributeSet();
		AttributeSet t2 = new AttributeSet();
		Set<FunctionalDependency> fds = new HashSet<FunctionalDependency>();
		
		t1.add(new Attribute("a"));
		t1.add(new Attribute("b"));
		t2.add(new Attribute("b"));
		t2.add(new Attribute("c"));
		t2.add(new Attribute("d"));
		
		AttributeSet temp = new AttributeSet();
		temp.add(new Attribute("b"));
		fds.add(new FunctionalDependency(temp,new Attribute("c")));
		// tables
		// a b
		// b c d
		// fds
		// b -> c
		assertFalse(FDChecker.checkLossless(t1, t2, fds));
		
		fds.add(new FunctionalDependency(temp, new Attribute("d")));
		// tables
		// a b
		// b c d
		// fds
		// b -> c
		// b -> d
		assertTrue(FDChecker.checkLossless(t1, t2, fds));
		
      
	}
}

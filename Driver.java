import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException  {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double[] c1 = {6,5,-1};
		int[] e1 = {0,3,2};
		Polynomial p1 = new Polynomial(c1,e1);
		double[] c2 = { -6,-5,1};
		int[] e2 = {1,3,2};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		File file = new File("read.txt"); 
		Polynomial t = new Polynomial(file);
		Polynomial y = t.multiply(p2);
		s.saveToFile("file.txt");
		
		
		
	}
		
}
	
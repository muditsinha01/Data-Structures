package bigo;
import java.util.Scanner;
interface Algorithm {
	long run(long N);
	String name();
}
class Method1 implements Algorithm{
	@Override
	public long run(long N) {
		return 0;
	}
	@Override
	public String name() {
		return null;
	}
}
class Method2 implements Algorithm{
	@Override
	public long run(long N) {
		return 0;
	}
	@Override
	public String name() {
		return null;
	}
}
class Method3 implements Algorithm{
	@Override
	public long run(long N) {
		return 0;
	}
	@Override
	public String name() {
		return null;
	}
}
class Method4 implements Algorithm{
	@Override
	public long run(long N) {
		return 0;
		}
	@Override
	public String name() {
		return null;
	}
}
class Method5 implements Algorithm{
	@Override
	public long run(long N) {
		return 0;
	}
	@Override
	public String name() {
		return null;
	}
}
// Method1 etc. are place holders for the data structure methods, change them as needed. Calculate big o by adding up
// the time complexity and then removing the coefficient. 
public class BigO {
	public BigO() {}
	  public static void main(String[] args) {
		  Scanner scanner = new Scanner(System.in);
		  int method = 0;
		  Algorithm[] methods = {new Method1(), new Method2(), new Method3(), new Method4(), new Method5()};
		  while(method==0) {
			  System.out.println("Choose the method you want to evaluate (1 to 5):");
			  method = scanner.nextInt();
			  if(method<1 || method>5)
				  method=0;
		  }
		  System.out.println("Method chosen: "+method+" ("+methods[method-1].name()+")");
		  while(true) {
			  System.out.println("Enter problem size N (enter 0 to quit):");
			  int N = scanner.nextInt();
			  if(N>0) {
				  long cost = methods[method-1].run(N);
				  System.out.println("N="+N+", cost="+cost);
			  } else
				  break;
		  }
		  System.out.println("The end.");
		  scanner.close();
	  }
}

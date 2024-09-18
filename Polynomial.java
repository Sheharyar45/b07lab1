public class Polynomial{
	double pnomial[];
	
	public Polynomial() {
		
		pnomial = new double[1];
		pnomial[0] = 0;
		
	}
	public Polynomial(double[] poly) {
		pnomial = new double[poly.length];
		for(int i = 0 ; i < poly.length;i++) {
			pnomial[i] = poly[i];
		}
	}
	public Polynomial add(Polynomial x) {
		double result[];
		
		if(x.pnomial.length > pnomial.length) {
			result = new double[x.pnomial.length];
			for(int i = 0; i < x.pnomial.length ; i++) {
				if(i < pnomial.length) {
					result[i] = x.pnomial[i] + pnomial[i];
				}
				else {
					result[i] = x.pnomial[i];
				}
					
			}
		}
		else {
			result = new double[pnomial.length];
			for(int i = 0; i < pnomial.length ; i++) {
				if(i < x.pnomial.length) {
					result[i] = x.pnomial[i] + pnomial[i];
				}
				else {
					result[i] = pnomial[i];
				}
					
			}
		}
		Polynomial res  = new Polynomial(result);
		return res;
		
		
	}
	public double evaluate(double num){
		double sum = 0;
		for(int i = 0; i < pnomial.length ; i++) {
			sum = sum + pnomial[i]*(Math.pow(num,i));
				
		}
		return sum;
		
	}
	public boolean hasRoot(double num) {
		return evaluate(num) == 0;
	}
	
	
	
	
	
}
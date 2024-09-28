import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Polynomial{
	double coeff[];
	int expo[];
	
	public Polynomial() {
		
		coeff = new double[1];
		coeff[0] = 0;
		expo = new int[1];
		expo[0] = 0;
		
	}
	public Polynomial(double[] c , int[] e) {
		int len = 0;
		for(int i = 0 ; i < c.length;i++) {
			if(c[i]!=0){
				len = len+1;
			}
			
		}

		coeff = new double[len];
		expo = new int[len];
		int count = 0;
		for(int i = 0 ; i < c.length;i++) {
			if(c[i]!=0){
				coeff[count] = c[i];
			    expo[count] = e[i];
				count = count + 1;
			}
			
			
		}
	}
	public Polynomial(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
          
        // Assuming the file contains only one line
        if (scanner.hasNextLine()) {
            String polynomialString = scanner.nextLine();
            parsePolynomial(polynomialString);
        }
       
        scanner.close();
	}
	public void parsePolynomial(String poly) {
		String sent = poly.replace("-", "+-");
		if(sent.startsWith("+")) {
			sent = sent.substring(1);
		}
		String[] eq = sent.split("\\+");
		coeff = new double[eq.length];
		expo = new int[eq.length];
		
		for(int i = 0 ; i < eq.length ; i++) {
			if(eq[i].contains("x")) {
				String[] temp = eq[i].split("x");
				
				if(temp.length==0 || temp[0].isEmpty()) {
					coeff[i] = 1;
				}
				else {
					if(temp[0].equals("-")) {
						coeff[i] = -1;
					}
					else {
						coeff[i] = Double.parseDouble(temp[0]);
					}
					
				}
				if(temp.length<2 || temp[1].isEmpty()) {
					expo[i] = 1;
				}
				else{
					if(temp[1].equals("-")) {
						expo[i] = -1;
					}
					else {
						expo[i] = Integer.parseInt(temp[1]);
					}
				}
			}
			else {
				coeff[i] = Double.parseDouble(eq[i]);
				expo[i] = 0;
			}
		}
	}
	
	public String toString(){
		String result = "";
		for(int i = 0 ; i < expo.length ; i++){
			if(coeff[i] == -1 && expo[i]!=0){
				result = result + "-";
			}
			else if(coeff[i] == 1 && expo[i]!=0){
				result = result + "";
			}
			else {
				result = result + (int)coeff[i];
			}
			
			if(expo[i] != 0) {
			    if(expo[i] == 1){
			        result = result + "x";
			    }
			    else{
			        result = result + "x" + expo[i];
			    }
			}
			if(i != expo.length-1 && coeff[i+1]>=0) {
				result = result + "+";
			}
			
		}
		return result;
	}
	
	public void saveToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
             
            
            printWriter.println(this.toString());
            printWriter.close();

           
        } catch (IOException e) {
            System.out.println("An error occurred while saving the polynomial to the file.");
            e.printStackTrace();
        }
    }
	public int search(int[] e, int num,int count){
		for(int i = 0 ; i  < count ; i++){
			if(e[i] == num){
				return i;
				
			}
		}
		return -1;
	}
	public int addlen(int[] e){
		int counter = e.length;
		for(int i = 0 ; i  < expo.length ; i++){
			if(search(e,expo[i],e.length)==-1){
				counter = counter + 1;
				
			}
		}
		return counter;
		
	}
	
	
	
	
	
	public Polynomial add(Polynomial x) {
		int len = addlen(x.expo);
		int[] newexpo = new int[len];
		double[] newcoeff = new double[len];
		
		for(int i = 0 ; i < x.expo.length ; i++) {
			newexpo[i] = x.expo[i];
			newcoeff[i] = x.coeff[i];
		}
		int count = x.expo.length;
		for(int i = 0 ; i < expo.length ; i++) {
			int val = search(newexpo,expo[i],count);
			if(val==-1) {
				newexpo[count] = expo[i];
				newcoeff[count] = coeff[i];
				count = count+1;
			}
			else {
				newcoeff[val] = newcoeff[val] + coeff[i];
			}
		}
		
		
		
		Polynomial res  = new Polynomial(newcoeff,newexpo);
		return res;
		
		
	}
	
	public int mullen(int[] e){
		int counter = 0;
		String str = "";
		for(int i = 0 ; i  < expo.length ; i++){
			
			for(int j = 0 ; j  < e.length ; j++){
				int num = expo[i]+e[j];
				String temp = String.valueOf(num);
				if(!str.contains(temp)) {
					str = str + temp + ",";
					counter = counter + 1;
				}
				
			}
		
		}
		return counter;
		
	}
	
	
	public Polynomial multiply(Polynomial x) {
		int len = mullen(x.expo);
		int[] newexpo = new int[len];
		double[] newcoeff = new double[len];
		int counter = 0;
		
		for(int i = 0 ; i < x.expo.length ; i++) {
			for(int j = 0 ; j  < expo.length ; j++){
				int num = x.expo[i]+expo[j];
				double c = x.coeff[i] * coeff[j];
				int val = search(newexpo,num,counter);
				if(val == -1) {
					newexpo[counter] = num;
					newcoeff[counter] = c;
					counter = counter + 1;
				}
				else {
					newcoeff[val] = newcoeff[val] + c;
				}
			}
		}
		Polynomial res  = new Polynomial(newcoeff,newexpo);
		return res;		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public double evaluate(double num){
		double sum = 0;
		for(int i = 0; i <coeff.length ; i++) {
			sum = sum + coeff[i]*(Math.pow(num,expo[i]));
				
		}
		return sum;
		
	}
	public boolean hasRoot(double num) {
		return evaluate(num) == 0;
	}
	
	
	
	
	
}
package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	// Testing Main section add optional test cases to the input file to be read (input.txt)
	// Testing main section add optional results to the output file (output.txt).
	
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("output.txt")));
			Complex a;
			String [] parts = br.readLine().split(",");
			
			a = new Complex(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
			
			// Perform the Mathematical operations. Add more operations here if you like.
			a.addition(new Complex(2.0, 4.2));
			bw.write(a.toString() + "\n");

			a.subtraction(new Complex(1.2, 1.2));
			bw.write(a.toString() + "\n");

			a.multiplication(new Complex(2.5, -3.1));
			bw.write(a.toString() + "\n");
		
			a.subtraction(new Complex(1.2, -1.2));
			bw.write(a.toString() + "\n");

			a.addition(new Complex(-2.0, -4.10));
			bw.write(a.toString() + "\n");
			
			a.division(new Complex(2.0, -2.0));
			bw.write(a.toString() + "\n");
			
			a.addition(new Complex(2.5, 1.8));
			bw.write(a.toString() + "\n");
			
			a.conjugate();
			a.polarForm();
			
			a = a.rotate(a, 100);
			a.polarForm();
			bw.write(a.toString() + "\n");
			
			// Perform the Scientific operations. Add more operations here if you like.
			Complex number = new Complex(4.5, 2.02);
			
			number.get_Voltage(new Complex(4.5, 2.1), new Complex(4.3, 2.1));
			bw.write(number.toString() + "\n");
			
			number.get_Current(new Complex(4.5, 2.02), new Complex(1.8, 1.3));
			bw.write(number.toString() + "\n");
			
			number.squareRoot();
			bw.write(number.toString() + "\n");
			
			br.close();
			bw.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

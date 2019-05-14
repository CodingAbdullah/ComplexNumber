package api;

public class Complex {
	private double real_Part;
	private double imaginary_Part;
	
	public Complex() {
		this(0, 0);
	}
	
	public Complex(double real, double imaginary) {
		this.real_Part = real;
		this.imaginary_Part = imaginary;
	}
		
	public double getReal_Part() {
		return this.real_Part;
	}

	public void setReal_Part(double real_Part) {
		this.real_Part = real_Part;
	}

	public double getImaginary_Part() {
		return this.imaginary_Part;
	}

	public void setImaginary_Part(double imaginary_part) {
		this.imaginary_Part = imaginary_part;
	}
	
	public void addition(Complex complexNumber) {
		this.real_Part += complexNumber.getReal_Part();
		this.imaginary_Part += complexNumber.getImaginary_Part();
		
		System.out.println("After Addition: " + this.toString());
	}
	
	public void subtraction(Complex complexNumber) {
		this.real_Part -= complexNumber.getReal_Part();
		this.imaginary_Part -= complexNumber.getImaginary_Part();
		
		System.out.println("After Subtraction: " + this.toString());
	}
	
	 /* (a + bi) * (c + di)
	  * a.c + a.di + c.bi + bi.di --> b.d.(-1)..because i.i = i^2 = -1
	  * real part ... -> a.c - b.d
	  * imaginary part ... -> (a.d + c.b)i 
	  */
	
	public void multiplication(Complex complexNumber) {		
		double newRealPart = ((this.real_Part) * (complexNumber.getReal_Part())) - ((this.imaginary_Part) * (complexNumber.getImaginary_Part()));
		double newImaginaryPart = (this.real_Part * complexNumber.getImaginary_Part()) + (complexNumber.getReal_Part() * this.imaginary_Part);	
		
		this.real_Part = newRealPart;
		this.imaginary_Part = newImaginaryPart;
		
		System.out.println("After Multiplication: " + this.toString());
	}
	
	 /* (a + bi) / (c + di)
	  * [(a + bi) * (c - di)] / [(c^2 - d^2(-1)]
	  * [a.c - a.di + c.bi - bi.di]/ [c^2 + d^2]
	  * [a.c + b.d + i (c.b - a.d)] / [c^2 + d^2]
	  * [Top_Part] / [Bottom_Part]
	  * 
	  * (a + bi) / (c - di)
	  * [(a + bi) * (c + di)]/[(c^2 - d^2(-1)]
	  * [a.c + a.di + c.bi + b.d(-1)]/[c^2 + d^2]
	  * [a.c - b.d + i (a.d + c.b)]/[c^2 + d^2]
	  * [Top_Part] / [Bottom_Part]
	  */
		
	public void division(Complex complexNumber) {
		double top_Part_Real = 0.0;
		double top_Part_Imaginary = 0.0;
		double bottom_Part = Math.pow(complexNumber.getReal_Part(), 2) + Math.pow(complexNumber.getImaginary_Part(), 2);
		double conjugate_Img_Part = Math.abs(complexNumber.getImaginary_Part());
		
		if ((int)bottom_Part == 0) {
			System.out.println("Division by 0!");
		}
		else if (complexNumber.getImaginary_Part() > 0) {
			top_Part_Real = (this.real_Part * complexNumber.getReal_Part() + this.imaginary_Part * conjugate_Img_Part);
			top_Part_Imaginary = (complexNumber.getReal_Part() * this.imaginary_Part) - (this.real_Part * conjugate_Img_Part);
		}
		else if (complexNumber.getImaginary_Part() <= 0) {
			top_Part_Real = (this.real_Part * complexNumber.getReal_Part() - this.imaginary_Part * conjugate_Img_Part);
			top_Part_Imaginary = (complexNumber.getReal_Part() * this.imaginary_Part) + (this.real_Part * conjugate_Img_Part);
		}
		
	 // Perform the division
		if ((int)bottom_Part != 0) {
			this.real_Part = top_Part_Real/bottom_Part;
			this.imaginary_Part = top_Part_Imaginary/bottom_Part;
		}
		
		System.out.println("After Division: " + this.toString());
	}
	
	public void conjugate() {
		if (this.imaginary_Part < 0) {
			System.out.println("The Conjugate of " + this.toString() + " is: " + this.real_Part + " + " + -1 * this.imaginary_Part + "i");
		}
		else if (this.imaginary_Part == 0) {
			System.out.println("The Conjugate of " + this.toString() + " is: " + this.real_Part + " + 0i");
		}
		else {
			System.out.println("The Conjugate of " + this.toString() + " is: " + this.real_Part + " - " + this.imaginary_Part + "i");
		}		
	}
	
	/* 
	 * a + bi
	 * r = sqrt(a^2 + b^2)
	 * theta = (tan O = b/a ... sin O = b/r ... cos O =  a/r)
	 * Polar coordinates : -> (r, O)
	 */
	
	public void polarForm() {
		double r = Math.sqrt(Math.pow(this.real_Part, 2) + Math.pow(this.imaginary_Part, 2));
		double theta = Math.toDegrees(Math.atan2(this.imaginary_Part, this.real_Part));
		
		if (theta < 0) {
			theta = 360 - Math.abs(theta);
		}
		
		System.out.println("The Polar Coordinates of " + this.toString() + " is: " + "(" + r + ", " + theta + ")");		
	}
	
	/*
	 * Suppose, a + bi = z^2
	 * z^2 = x + yi -> by example...
	 * 
	 * a + bi = (x + yi)(x + yi)
	 * RHS... (x^2 + 2xyi + y^2.i^2) .. i^2 = -1
	 * Therefore, a + bi = (x^2 - y^2) + (2xy)i [Real - Imaginary] parts...
	 * 
	 * a = x^2 - y^2 ---> (1) Real
	 * b = (2xy) -------> (2) Imaginary
	 * Two eqns, two unknowns solve for x and y. 
	 * Note a and b are values from the original complex number.
	 * 
	 * Solving for x in (1) by subbing for y... (b/2x) from (2) into (1) gives us the quatric (4th degree) polynomial 
	 * with coefficients defined by the simQuatric method.. 4x^4 - (4.a)x^2 - b^2 = 0. Check for real roots for x^2 not x. 
	 * 
	 * We are solving for x^2 in (1)
	 * 
	 * If x^2 exists (solutionExists method), find pairs (x, y).
	 * 
	 */
	
    public void squareRoot() {
    	double positive_y = 0.0;
    	
		switch (this.solution().length) {
			case 0:
				System.out.println("The square of this complex number is also complex! Therefore, it does not exist!");
				break;
			
			case 2:
				positive_y = ((Double)this.solution()[0] - this.real_Part);
				
				// Check for the solutions that solve (2), return proper combinations		
	
				System.out.println("The square root of " + this.toString() + " is: " + (Double)this.solution()[0] + " " + positive_y + "i");
				break;	
		}
	}
	
    private Object[] solution() {
		double a = this.simQuatric()[0];
		double b = this.simQuatric()[1];
		double c = this.simQuatric()[2];
		
		double discriminant = (Math.pow(b, 2) - 4 * a * c);
		double wholeEquationPositive;
		double wholeEquationNegative;
		
		if (discriminant >= 0) {
			wholeEquationPositive =  (-1 * b + Math.sqrt(discriminant))/(2 * a);
			wholeEquationNegative = (-1 * b - Math.sqrt(discriminant))/(2 * a);
			
			return new Double [] {wholeEquationPositive, wholeEquationNegative};
		}
		else {
			return new Double[] {};
		}
	}
	
	private double[] simQuatric() {
		return new double[] {4.0, -4 * this.real_Part, -1 * Math.pow(this.imaginary_Part, 2)};	
	}
	
	public Complex rotate(Complex a, int angle) {
		double thetaNewAngle = Math.toDegrees(Math.atan2(a.getImaginary_Part(), a.getReal_Part()));
		double r = Math.sqrt(Math.pow(a.getReal_Part(), 2) + Math.pow(a.getImaginary_Part(), 2));
	
		if ((angle > 360) || (angle < 0)) {
			System.out.println("Invalid angle, please make sure it is bounded between 0 - 360 degrees");
		}
		else {
			thetaNewAngle += angle;
			
		// Reset the unit circle angle cycle.
			
			if (thetaNewAngle >= 360) {
				thetaNewAngle -= 360;
			}
			System.out.println("The Rotation will be counter-clockwise and the new angle is: " + thetaNewAngle);
		}
		
		// CAST Rule
	
		return new Complex(r * Math.cos(thetaNewAngle), r * Math.sin(thetaNewAngle));
	}
		// V = IR, Ohm's Law.
		// I = V/R.
	    // Perform the division on v. Retrieve the real and imaginary part of v for the current and return.
	
	public Complex get_Current(Complex v, Complex z) {	
		v.division(z); 
		return new Complex(v.getReal_Part(), v.getImaginary_Part());
	}
		// V = IR
	public Complex get_Voltage(Complex i, Complex z) {
		i.multiplication(z);
		return new Complex(i.getReal_Part(), i.getImaginary_Part());
	}
	
	@Override
	public boolean equals(Object c) {
		if (((this.real_Part) == ((Complex) c).getReal_Part()) && ((this.imaginary_Part == ((Complex) c).getImaginary_Part()))){
			return true;
		}
		else {
			return false;
		}	
	}
	
	@Override
	public String toString() {
		if (this.imaginary_Part >= 0) {
			return this.real_Part + " + " + this.imaginary_Part + "i";
		}
		else {
			return this.real_Part + " - " + -1 * this.imaginary_Part + "i";
		}
		
	}
}

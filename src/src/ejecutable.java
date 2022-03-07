package src;

import java.math.BigInteger;

public class ejecutable {

	public static void main(String[] args) {
		long ini = System.currentTimeMillis();
		MetodosNumericos metodos = new MetodosNumericos();

		BigInteger minorRange = new BigInteger(args[0]);
		BigInteger mayorRange = new BigInteger(args[1]);

		BigInteger p = metodos.generarRandom(minorRange, mayorRange);
		while ( !metodos.esPrimo(p))
		{
			p = metodos.generarRandom(minorRange, mayorRange);
		}
		
		BigInteger q = metodos.generarRandom(minorRange, mayorRange);
		while ( !metodos.esPrimo(q) || q.compareTo(p)==0)
		{
			q = metodos.generarRandom(minorRange, mayorRange);
		}
		
		
		System.out.println("Veryfiyng data:");
		System.out.println("\tMinimun Range: " + minorRange.doubleValue());
		System.out.println("\tMaximum Range: " + mayorRange.doubleValue());
		System.out.println("The generated numbers for p and q are:");
		System.out.println("\t p:" + p.doubleValue());
		System.out.println("\t q:" + q.doubleValue());
		System.out.println("Calculating RSA:");
		metodos.rsa(p,q);
		long fin = System.currentTimeMillis();
		System.out.println("It lasted " + (fin-ini) + " ms completing the process.");
		
	}

}

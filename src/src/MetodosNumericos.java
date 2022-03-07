package src;

import java.math.BigInteger;
import java.util.Random;

public class MetodosNumericos {

	public BigInteger[] gcd2(BigInteger a, BigInteger b)
	{
		if(b.compareTo(BigInteger.ZERO)==0)
		{
			BigInteger [] answer = {a,BigInteger.ONE, BigInteger.ZERO};
			return answer; 
		}
		BigInteger [] answerp = gcd2(b,a.mod(b));
		BigInteger q = a.divide(b);
		BigInteger[] answer = {answerp[0],answerp[2], answerp[1].subtract(q.multiply(answerp[2]))};
		return answer;
	}

	public BigInteger [] solveModularEquation(BigInteger a, BigInteger b, BigInteger n) {
		BigInteger [] extgcd = gcd2(a, n);
		BigInteger d = extgcd[0];
		BigInteger x1=extgcd[1];

		if(b.mod(d).compareTo(BigInteger.ZERO)>= 1) {
			return new BigInteger [0];
		}
		BigInteger q = b.divide(d);
		BigInteger q2 = n.divide(d);
		BigInteger [] answer = new BigInteger [d.intValue()];
		answer[0] = (x1.multiply(q)).mod(n);
		for(int i = 1 ; i<d.intValue() ; i++) 
		{
			answer[i] = answer[0].add(answer[0].add(new BigInteger(i+"").multiply(q2)).mod(n));
		}
		return answer;
	}

	public boolean esPrimo(BigInteger n)
	{
		BigInteger THREE = new BigInteger("3");

		if( n.compareTo(THREE)<=-1 )
		{
			return n.compareTo(BigInteger.ONE) >= 1;
		}
		if ( n.mod(BigInteger.TWO) == BigInteger.ZERO || n.mod(THREE) == BigInteger.ZERO )
		{
			return false;
		}
		BigInteger i = new BigInteger("5");
		while( i.pow(2).compareTo(n) <= 0)
		{
			if( n.mod(i) == BigInteger.ZERO  || n.mod(i.add(BigInteger.TWO)) == BigInteger.ZERO)
			{
				return false;
			}
			i = i.pow(2);
		}
		return true;
	} 


	public BigInteger generarRandom(BigInteger minorRange, BigInteger mayorRange)
	{
		BigInteger bigInteger = mayorRange.subtract(minorRange);
		Random randNum = new Random();
		int len = mayorRange.bitLength();
		BigInteger num = new BigInteger(len, randNum);
		if (num.compareTo(minorRange) < 0)
		{
			num = num.add(minorRange);			
		}
		if (num.compareTo(bigInteger) >= 0)
		{		
			num = num.mod(bigInteger).add(minorRange);
		}

		return num;
	}
	public void rsa(BigInteger p, BigInteger q)
	{
		System.out.println("For the values of p and q we have the following internal values for rsa:");
		BigInteger n = p.multiply(q);
		BigInteger m = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		BigInteger e = new BigInteger("3");
		while (gcd2(m, e)[0].compareTo(BigInteger.ONE) !=0)
		{
			e = e.add(new BigInteger("2"));
		}

		BigInteger d = solveModularEquation(e,BigInteger.ONE,m)[0];
		System.out.println("\t n = "+ n+" \n\t m = "+m+" \n\t e = "+ e+" \n\t d = "+ d);
		System.out.println("The public key is the tuple: \n\t<e,n> = <"+e+","+n+"> \n\tDefined by:  \n\t\t P(x; e,n) = x^e mod n \n\t\t P(x; "+e+","+n+") = x^"+e+" mod "+n);
		System.out.println("The private key is the tuple: \n\t<d,n> = <"+d+","+n+"> \n\tDefined by:  \n\t\t P(y; d,n) = y^d mod n \n\t\t P(y; "+d+","+n+") = x^"+d+" mod "+n);
	}	

}


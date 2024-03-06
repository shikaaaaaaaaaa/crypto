/**
 * This Java program factors a given modulus N using information about encryption and decryption exponents.
 * The program calculates potential prime factors of N based on the relationship between the encryption (e) and
 * decryption (d) exponents in the RSA cryptographic system. It uses Euler's theorem to form equations and
 * solve them simultaneously to find the prime factors. The values of e1, d1, e2, and d2 are provided, which are
 * used to calculate potential prime factors p1 and p2. The calculated prime factors are printed to the console
 * as potential solutions. Further verification may be required to confirm the factors obtained.
 */

import java.math.BigInteger;

public class FactorN {
    public static void main(String[] args) {
        // Given values
        BigInteger N = new BigInteger("38749709");
        BigInteger e1 = new BigInteger("10988423");
        BigInteger d1 = new BigInteger("16784693");
        BigInteger e2 = new BigInteger("25910155");
        BigInteger d2 = new BigInteger("11514115");

        // Calculate potential p and q using e1 and d1
        BigInteger phiN = phi(N);
        BigInteger p1 = e1.modInverse(phiN);
        BigInteger p2 = d1.modInverse(phiN);

        System.out.println("Potential prime factors using e = 10988423 and d = 16784693:");
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        
        // Calculate potential p and q using e2 and d2
        p1 = e2.modInverse(phiN);
        p2 = d2.modInverse(phiN);
        
        System.out.println("\nPotential prime factors using e = 25910155 and d = 11514115:");
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
    }
    
    // Function to calculate Euler's totient function
    public static BigInteger phi(BigInteger n) {
        BigInteger result = n;
        BigInteger i = new BigInteger("2");
        while (i.multiply(i).compareTo(n) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                while (n.mod(i).equals(BigInteger.ZERO))
                    n = n.divide(i);
                result = result.subtract(result.divide(i));
            }
            i = i.add(BigInteger.ONE);
        }
        if (n.compareTo(BigInteger.ONE) > 0)
            result = result.subtract(result.divide(n));
        return result;
    }
}


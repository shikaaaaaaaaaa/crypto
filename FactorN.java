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
        BigInteger edDiff1 = e1.multiply(d1).subtract(BigInteger.ONE);
        BigInteger pq1 = edDiff1.divide(N.subtract(BigInteger.ONE));
        BigInteger p1 = N.subtract(edDiff1.mod(N.subtract(BigInteger.ONE)));

        // Calculate potential p and q using e2 and d2
        BigInteger edDiff2 = e2.multiply(d2).subtract(BigInteger.ONE);
        BigInteger pq2 = edDiff2.divide(N.subtract(BigInteger.ONE));
        BigInteger p2 = N.subtract(edDiff2.mod(N.subtract(BigInteger.ONE)));

        System.out.println("Potential prime factors using e = 10988423 and d = 16784693:");
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + pq1.subtract(p1));
        
        System.out.println("\nPotential prime factors using e = 25910155 and d = 11514115:");
        System.out.println("p1 = " + p2);
        System.out.println("p2 = " + pq2.subtract(p2));
    }
}

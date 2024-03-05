/*
This Java program demonstrates the ElGamal encryption scheme, a public-key cryptosystem based on the discrete logarithm problem. In the 'main' 
method, two scenarios are addressed: Part (a) involves Alice encrypting a message using Bob's public key YB and a randomly chosen integer k, 
resulting in a ciphertext computed using the ElGamal encryption algorithm. In Part (b), Alice re-encrypts the same message with a different 
randomly chosen integer k, resulting in a different ciphertext. The 'encrypt' method implements the ElGamal encryption process, utilizing 
parameters such as the plaintext message, Bob's public key, the primitive root alpha, the prime modulus q, and the random integer k. This 
program effectively illustrates the ElGamal encryption process and how different choices of the random integer k can lead to distinct 
ciphertexts, highlighting the non-deterministic nature of the encryption scheme.
*/


import java.math.BigInteger;

public class ElGamalEncryption {
    public static void main(String[] args) {
        // Common parameters
        BigInteger q = new BigInteger("157");
        BigInteger alpha = new BigInteger("5");

        // Part (a): Encryption with Bob's public key YB = 10 and k = 3
        BigInteger yB = new BigInteger("10");
        BigInteger k1 = new BigInteger("3");
        BigInteger m1 = new BigInteger("9");
        BigInteger[] ciphertext1 = encrypt(m1, yB, alpha, q, k1);
        System.out.println("Ciphertext (Part a): (" + ciphertext1[0] + ", " + ciphertext1[1] + ")");

        // Part (b): Finding C2 when Alice chooses k = 25 for encoding M = 9
        BigInteger k2 = new BigInteger("25");
        BigInteger m2 = new BigInteger("9");
        BigInteger c1 = new BigInteger("25"); // Since c1 = α^k (mod q)
        BigInteger c2 = m2.multiply(yB.modPow(k2, q)).mod(q); // Compute c2 = M * Y^k (mod q)
        System.out.println("C2 (Part b): " + c2);
    }

    // ElGamal encryption function
    public static BigInteger[] encrypt(BigInteger message, BigInteger yB, BigInteger alpha, BigInteger q, BigInteger k) {
        BigInteger[] ciphertext = new BigInteger[2];
        // Compute c1 = α^k (mod q)
        ciphertext[0] = alpha.modPow(k, q);
        // Compute c2 = M * Y^k (mod q)
        ciphertext[1] = message.multiply(yB.modPow(k, q)).mod(q);
        return ciphertext;
    }
}

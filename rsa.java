/**
 * This Java program demonstrates RSA encryption and decryption. In the RSA cryptographic system,
 * each user has a public key and a private key. The public key consists of two numbers: the modulus N
 * and the public exponent e. The private key consists of the modulus N and the private exponent d.
 * 
 * This program simulates a scenario where Alice publishes her RSA public key (modulus N and public exponent e),
 * Bob encrypts a message using Alice's public key, Alice decrypts the ciphertext using her private key, and
 * determines the original plaintext message. The RSA encryption and decryption algorithms are implemented
 * using BigInteger arithmetic for handling large integer operations.
 */

import java.math.BigInteger;

public class RSA {
    // RSA public key components
    private static final BigInteger N = new BigInteger("2038667");
    private static final BigInteger e = new BigInteger("103");

    public static void main(String[] args) {
        // Part (a): Bob encrypts message m = 892383
        BigInteger m = new BigInteger("892383");
        BigInteger ciphertext = encrypt(m);
        System.out.println("Ciphertext sent by Bob: " + ciphertext);

        // Part (b): Alice finds decryption exponent d
        BigInteger p = new BigInteger("1301");
        BigInteger q = N.divide(p);
        BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phiN);
        System.out.println("Decryption exponent d for Alice: " + d);

        // Part (c): Alice decrypts the ciphertext
        BigInteger receivedCiphertext = new BigInteger("317730");
        BigInteger decryptedMessage = decrypt(receivedCiphertext, d);
        System.out.println("Decrypted message received by Alice: " + decryptedMessage);
    }

    // RSA encryption
    public static BigInteger encrypt(BigInteger plaintext) {
        return plaintext.modPow(e, N);
    }

    // RSA decryption
    public static BigInteger decrypt(BigInteger ciphertext, BigInteger d) {
        return ciphertext.modPow(d, N);
    }
}

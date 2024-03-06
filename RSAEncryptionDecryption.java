// The RSAEncryptionDecryption class implements a simple RSA encryption and decryption algorithm.
// The main method prompts the user to input a message and an encoding scheme for encryption.
// It then encrypts the message using RSA encryption with the provided encoding scheme and displays the encrypted blocks.
// Next, it prompts the user to input a ciphertext separated by spaces for decryption.
// After obtaining the ciphertext, it decrypts it using RSA decryption and the provided encoding scheme, then displays the decrypted message.
// The encrypt method takes a message and an encoding scheme as input and encrypts each character of the message using RSA encryption.
// It utilizes BigInteger for handling large integer operations required in RSA encryption.
// The decrypt method takes an array of encrypted blocks and an encoding scheme as input and decrypts each block using RSA decryption.
// It handles spaces represented by the encoding scheme and constructs the decrypted message accordingly.

import java.math.BigInteger;
import java.util.Scanner;

public class RSAEncryptionDecryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user input for message and encoding scheme
        System.out.println("Enter the message: ");
        String message = scanner.nextLine().toUpperCase();
        System.out.println("Enter the encoding scheme (00 to 26): ");
        int encodingScheme = scanner.nextInt();

        // Encrypt the message
        BigInteger[] encryptedMessage = encrypt(message, encodingScheme);
        System.out.println("Encrypted Message:");
        for (BigInteger block : encryptedMessage) {
            System.out.print(block + " ");
        }
        System.out.println();

        // Decrypt the message
        System.out.println("\nDecrypting the given ciphertext...");
        System.out.println("Enter the ciphertext separated by spaces: ");
        scanner.nextLine(); // Consume the newline character
        String cipherText = scanner.nextLine();
        String[] cipherTextArray = cipherText.split("\\s+");
        BigInteger[] encryptedBlocks = new BigInteger[cipherTextArray.length];
        for (int i = 0; i < cipherTextArray.length; i++) {
            encryptedBlocks[i] = new BigInteger(cipherTextArray[i]);
        }
        String decryptedMessage = decrypt(encryptedBlocks, encodingScheme);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }

    public static BigInteger[] encrypt(String message, int encodingScheme) {
        BigInteger[] encryptedBlocks = new BigInteger[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            int value;
            if (ch == ' ') {
                value = encodingScheme; // Space
            } else {
                value = ch - 'A'; // Adjust mapping to start from 0 for letters
            }
            BigInteger plaintext = BigInteger.valueOf(value);
            encryptedBlocks[i] = plaintext.modPow(BigInteger.valueOf(13), BigInteger.valueOf(10000)); // e=13, n=10000
        }
        return encryptedBlocks;
    }

    public static String decrypt(BigInteger[] encryptedBlocks, int encodingScheme) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (BigInteger block : encryptedBlocks) {
            BigInteger plaintext = block.modPow(BigInteger.valueOf(37), BigInteger.valueOf(10000)); // d=37, n=10000
            int value = plaintext.intValue();
            char ch;
            if (value == 0) { // Check for space (encoded as 0)
                ch = ' '; // Space
            } else {
                ch = (char) (value + 'A' - 1); // Adjust mapping back to ASCII values
            }
            decryptedMessage.append(ch);
        }
        return decryptedMessage.toString();
    }
    
}

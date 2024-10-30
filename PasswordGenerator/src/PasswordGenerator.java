import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()_+{}|[]\\";

    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int charType = random.nextInt(4);
            switch (charType) {
                case 0 -> password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
                case 1 -> password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
                case 2 -> password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
                case 3 -> password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));
            }
        }

        return password.toString();
    }

    public static int getPasswordLength() {
        Scanner scanner = new Scanner(System.in);
        int length;
        do {
            System.out.print("Enter password length (8-12): ");
            length = scanner.nextInt();
        } while (length < 8 || length > 12);
        scanner.close();
        return length;
    }
}
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String[] CURRENCY_CODES = {"USD", "EUR", "BYN", "UAH", "RUB"};
    private static final int DECIMAL_PLACES = 2;
    private final Scanner scanner;
    private final Currency[] rates = {
            new Currency("USD", 1.0),
            new Currency("EUR", 0.92),
            new Currency("BYN", 3.31),
            new Currency("UAH", 41.30),
            new Currency("RUB", 97.20)
    };
    ;

    public CurrencyConverter() {
        this.scanner = new Scanner(System.in);

    }


    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice (or 0 to exit): ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                running = false;
            } else if (choice >= 1 && choice <= CURRENCY_CODES.length) {
                performConversion(choice - 1);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nCurrency Converter");
        System.out.println("Available currencies:");
        for (int i = 0; i < CURRENCY_CODES.length; i++) {
            System.out.printf("%d. %s%n", i + 1, CURRENCY_CODES[i]);
        }
    }

    private void performConversion(int fromCurrencyIndex) {
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        System.out.println("\nConversion results:");
        String fromCurrency = CURRENCY_CODES[fromCurrencyIndex];
        double fromRate = rates[fromCurrencyIndex].rate();

        for (Currency rate : rates) {
            if (!rate.name().equals(fromCurrency)) {
                double convertedAmount = amount * (rate.rate() / fromRate);
                BigDecimal rounded = BigDecimal.valueOf(convertedAmount)
                        .setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
                System.out.printf("%s: %.2f%n", rate.name(), rounded);
            }
        }
    }
}

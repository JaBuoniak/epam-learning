package jmp.app;

import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.bank.api.Bank;
import jmp.cloud.bank.BankImpl;
import jmp.cloud.service.ServiceImpl;
import jmp.service.api.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.exit;

public class Application {
    private static String[] MENU_OPTIONS = {
            "1- Subscribe new DebitCard",
            "2- Subscribe new CreditCard",
            "3- Find subscription",
            "0- Exit",
    };

    private final Service service;
    private final Bank bank;

    public Application() {
        service = new ServiceImpl();
        bank = new BankImpl();
    }

    public static void main(String[] args) {
        Application application = new Application();
        menu(application);
    }

    private static void menu(Application application) {
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 0) {
            printMenu();
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1: subscribeNewBankCard(application, BankCardType.DEBIT); break;
                    case 2: subscribeNewBankCard(application, BankCardType.CREDIT); break;
                    case 3: findSubscription(application); break;
                    case 0: exit(0);
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer value between 0 and " + (MENU_OPTIONS.length - 1));
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error happened. Please try again");
                scanner.next();
            }
        }
    }

    public static void printMenu() {
        for (var option : MENU_OPTIONS) {
            System.out.println(option);
        }
        System.out.print("Choose your action : ");
    }

    private static void subscribeNewBankCard(Application application, BankCardType type) {
        Scanner scanner = new Scanner(System.in);
        User user;
        try {
            System.out.println("\n\n\tSubscribing new " + type.name().toLowerCase(Locale.ROOT) + " card for customer" +
                    "\n\nProvide customer's data");
            System.out.print("\nSurname:\t");
            var surname = scanner.next();
            System.out.print("\nName:\t");
            var name = scanner.next();
            System.out.print("\nBorn (date yyyy-mm-dd):\t");
            var birthday = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
            user = new User(name, surname, birthday);
        } catch (Exception e) {
            System.out.println("Provided data are not valid.\n" + e.getMessage());
            return;
        }
        BankCard bankCard = application.bank.createBankCard(user, type);
        application.service.subscribe(bankCard);
    }

    private static void findSubscription(Application application) {
        Scanner scanner = new Scanner(System.in);
        String cardNumber;
        try {
            System.out.print("\n\n\tFind subscription of bank card" +
                    "\n\nProvide card's number:\t");
            cardNumber = scanner.nextLine().trim().replaceAll("\\s", "");
        } catch (Exception e) {
            System.out.println("Provided data are not valid.\n" + e.getMessage());
            return;
        }

        if (cardNumber.matches("[0-9]+")) {
            Optional<Subscription> subscription = application.service.getSubscriptionByBankCardNumber(cardNumber);
            subscription.ifPresentOrElse(
                    s -> System.out.println("\nThe bank card is valid since " + s.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)),
                    () -> System.out.println("\nCould not found any subscription for provided number."));
        }
    }
}

package jmp.app;

import jmp.app.exceptions.SubscriptionNotFoundException;
import jmp.bank.api.Bank;
import jmp.cloud.bank.BankImpl;
import jmp.cloud.service.ServiceImpl;
import jmp.dto.BankCard;
import jmp.dto.BankCardType;
import jmp.dto.Subscription;
import jmp.dto.User;
import jmp.service.api.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.System.exit;

@SuppressWarnings({"squid:S106","squid:S1192"})
public class Application {
    @SuppressWarnings("squid:S3008")
    private static String[] MENU_OPTIONS = {
            "1- Subscribe new DebitCard",
            "2- Subscribe new CreditCard",
            "3- Find subscription",
            "4- List subscriptions for period",
            "0- Exit",
    };

    private final Service service;
    private final Bank bank;

    public Application() {
        service = new ServiceImpl();
        bank = new BankImpl();
    }

    public static void main(String[] args) {
        ServiceLoader.load(ServiceImpl.class);
        ServiceLoader.load(BankImpl.class);
        Application application = new Application();
        menu(application);
    }

    @SuppressWarnings({"squid:S4524","squid:S128"})
    private static void menu(Application application) {
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 0) {
            printMenu();
            try {
                option = scanner.nextInt();
                switch (option) {
                    default:
                        continue;
                    case 1:
                        subscribeNewBankCard(application, BankCardType.DEBIT);
                        break;
                    case 2:
                        subscribeNewBankCard(application, BankCardType.CREDIT);
                        break;
                    case 3:
                        findSubscription(application);
                        break;
                    case 4:
                        listSubscriptionsForPeriod(application);
                    case 0:
                        exit(0);
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

    @SuppressWarnings({"squid:S6353","squid:S6126"})
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

        try {
            if (cardNumber.matches("[0-9]+")) {
                Optional<Subscription> optSubscription = application.service.getSubscriptionByBankCardNumber(cardNumber);
                Subscription subscription = optSubscription.orElseThrow(SubscriptionNotFoundException::new);
                System.out.println("\nThe bank card is valid since " + subscription.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
        } catch (SubscriptionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listSubscriptionsForPeriod(Application application) {
        Scanner scanner = new Scanner(System.in);
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.now();
        try {
            System.out.println("\n\n\tList subscriptions created in period" +
                    "\n\nProvide period range:\t");
            System.out.print("\nStart date (yyyy-mm-dd):\t");
            start = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.print("\nEnd date (yyyy-mm-dd):\t");
            end = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            System.out.println("Provided data are not valid.\n" + e.getMessage());
            return;
        }

        LocalDate finalStart = start;
        LocalDate finalEnd = end;
        List<Subscription> subscriptions = application.service.getAllSubscriptionsByCondition(s ->
                s.getStartDate().isAfter(finalStart) && s.getStartDate().isBefore(finalEnd));

        subscriptions.forEach(s ->
                System.out.println(s.getBankcard() + "\t" + s.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
    }
}

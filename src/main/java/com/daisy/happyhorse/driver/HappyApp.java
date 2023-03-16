package com.daisy.happyhorse.driver;

import com.daisy.happyhorse.model.business.manager.HappyManager;
import com.daisy.happyhorse.model.domain.Horse;
import com.daisy.happyhorse.model.domain.Customer;
import com.daisy.happyhorse.model.domain.RentalCart;
import com.daisy.happyhorse.model.services.exception.PropertyFileNotFoundException;
import com.daisy.happyhorse.model.services.factory.IServiceFactory;
import com.daisy.happyhorse.model.services.factory.ServiceFactory;
import com.daisy.happyhorse.model.services.manager.PropertyManager;
import com.daisy.happyhorse.model.services.repository.IRepositoryWrapper;
import com.daisy.happyhorse.model.services.repository.RepositoryWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyApp {
    private static final Logger logger = LogManager.getLogger("com.daisy.happyhorse");

    private HappyManager shopManager;
    private RentalCart cart;

    public HappyApp() {

    }

    public boolean Initialize() {
        String propertyFileLocation = System.getProperty("prop_location");
        if (propertyFileLocation == null) {
            logger.error("No prop_location environment variable set.");
        }

        PropertyManager propertyManager = new PropertyManager();
        try {
            propertyManager.loadProperties(propertyFileLocation);
        } catch (PropertyFileNotFoundException e) {
            logger.error("Could not load properties file");
            return false;
        }

        IRepositoryWrapper repository = new RepositoryWrapper();
        IServiceFactory factory = new ServiceFactory(propertyManager);

        shopManager = new HappyManager(propertyManager, repository, factory);
        cart = new RentalCart();

        return true;
    }

    public void Run() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        boolean running = true;

        while (running) {
            printMainMenu();
            userInput = inputScanner.nextLine();
            Integer userChoice = null;
            if (isNumeric(userInput)) {
                userChoice = Integer.parseInt(userInput);
            } else {
                System.out.println("Error: you entered an invalid number");
                continue;
            }

            switch (userChoice) {
                case 1:
                    printAvailableHorses(shopManager.getAvailableHorses(), userInput, inputScanner);
                    break;
                case 2:
                    startCheckoutProcess(inputScanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("The value you entered does not correspond with a valid action");
                    break;
            }

        }

        inputScanner.close();
    }

    private void printMainMenu() {
        System.out.println("Main Menu\n");
        System.out.printf("Reservation Cart: (%d)\n", 0);

        System.out.println("1. View Available Horses");
        System.out.println("2. Checkout");
        System.out.println("3. Exit");
    }

    private void printAvailableHorses(ArrayList<Horse> horses, String input, Scanner inputScanner) {
        while (true) {
            System.out.println("Displaying all horses available to rent within the next month\n");
            for (Horse horse : horses) {
                System.out.println(horse);
            }
            System.out.println("R: Return");

            System.out.println(
                    "You can add a horse to your cart by entering the number next to the desired horse, or you can enter R to return to the main menu.");

            input = inputScanner.nextLine();
            if (input.equals("R")) {
                return;
            }

            if (isNumeric(input)) {
                addToCart(Integer.parseInt(input));
            } else {
                System.out.println("Invalid value entered");
            }

        }

    }

    private String getInput(String outputMessage, Scanner scanner) {
        String input;

        System.out.println(outputMessage);
        input = scanner.nextLine();

        return input;
    }

    private void startCheckoutProcess(Scanner scanner) {
        Customer customer = new Customer();
        customer.setFirstName(getInput("Please enter your first name: ", scanner));
        customer.setLastName(getInput("Please enter your last name: ", scanner));
        customer.setEmail(getInput("Please enter your email address: ", scanner));
        customer.setPhone(getInput("Please enter your phone number: ", scanner));

        cart.setCustomer(customer);
        shopManager.performAction("createReservation", cart);

        System.out.println("Your reservation is being created");
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean addToCart(int horseId) {
        if (cart.getHorses().stream().filter(b -> b.getId() == horseId).count() > 0) {
            System.out.println("That horse is already in your cart");
        } else {
            Horse horse = shopManager.findHorse(horseId);
            if (horse != null) {
                cart.getHorses().add(horse);
                System.out.println("Successfully added horse to your cart");
            } else {
                logger.error("Could not find horse: " + horseId);
                System.out.println("We couldn't find the horse you were looking for");
            }
        }
        return true;
    }

}
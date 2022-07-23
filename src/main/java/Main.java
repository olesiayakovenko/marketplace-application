import java.util.*;

/**
 * Runs and enables the use of the marketplace application.
 */
public class Main {

    public static void main(String[] args) throws NotEnoughMoneyException {
        callMenu();
    }

    /**
     * Calls the menu and allows to select a menu item.
     *
     * @throws NotEnoughMoneyException if the user has a smaller amount of money than the price of the product.
     */
    private static void callMenu() throws NotEnoughMoneyException {
        try {
            int itemNum;
            scanner = new Scanner(System.in);

            do {
                displayInfoMessage(MENU_MESSAGE);
                itemNum = scanner.nextInt();

                runOperation(itemNum);
            } while (itemNum != 0);

        } catch (InputMismatchException e) {
            displayFailMessage(INVALID_ITEM_MESSAGE);
            callMenu();
        } finally {
            scanner.close();
        }
    }

    /**
     * Starts the operation according to the operation number.
     *
     * @param operationNum Number of the operation to be run.
     * @throws NotEnoughMoneyException if the user has a smaller amount of money than the price of the product.
     */
    private static void runOperation(int operationNum) throws NotEnoughMoneyException {
        switch (operationNum) {
            case 1 -> displayUserList();
            case 2 -> displayProductList();
            case 3 -> buyProduct();
            case 4 -> displayUserProducts();
            case 5 -> displayProductUsers();
            case 0 -> displaySuccessMessage(EXIT_MESSAGE);
            default -> displayFailMessage(NONEXISTENT_ITEM_MESSAGE);
        }
    }

    /**
     * Outputs each user's identification number, first name, last name and amount of money.
     */
    private static void displayUserList() {
        for (User user : users.values()) {
            user.printUserInfo();
        }
    }

    /**
     * Outputs each product's identification number, name and price.
     */
    private static void displayProductList() {
        for (Product product : products.values()) {
            product.printProductInfo();
        }
    }

    /**
     * Asks to enter the id of the user who wants to buy the product,
     * the id of the product the user wants to buy and tries to make the purchase.
     *
     * @throws NotEnoughMoneyException if the user has a smaller amount of money than the price of the product.
     */
    private static void buyProduct() throws NotEnoughMoneyException {
        displayInfoMessage(USER_ID_MESSAGE);
        int userId = scanner.nextInt();

        displayInfoMessage(PRODUCT_ID_MESSAGE);
        int productId = scanner.nextInt();

        // Check whether the user and product with the entered IDs exist.
        if (users.containsKey(userId) && products.containsKey(productId)) {
            // Check whether the user has enough money to make a purchase.
            if (users.get(userId).getMoney() >= products.get(productId).getPrice()) {
                makePurchase(userId, productId);
                storePurchaseInfo(userId, productId);
                displaySuccessMessage(PURCHASE_SUCCESS_MESSAGE);
            } else {
                throw new NotEnoughMoneyException();
            }
        } else {
            displayFailMessage(PURCHASE_FAIL_MESSAGE);
        }
    }

    /**
     * Decreases user's amount of money by product price.
     *
     * @param userId    The user's identification number.
     * @param productId The product's identification number.
     */
    private static void makePurchase(int userId, int productId) {
        users.get(userId).setMoney(users.get(userId).getMoney() - products.get(productId).getPrice());
    }

    /**
     * Stores information about the user and their products and the product and its users.
     *
     * @param userId    The user's identification number.
     * @param productId The product's identification number.
     */
    private static void storePurchaseInfo(int userId, int productId) {
        /*
         * Checks or is there data on the user's previous purchases.
         * If not, put user's id to HashMap as key and create ArrayList for value.
         */
        if (!userProducts.containsKey(userId)) {
            userProducts.put(userId, new ArrayList<>());
        }

        /*
         * Checks or is there data on the product's previous buyers.
         * If not, put product's id to HashMap as key and create ArrayList for value.
         */
        if (!productUsers.containsKey(productId)) {
            productUsers.put(productId, new ArrayList<>());
        }

        userProducts.get(userId).add(products.get(productId));
        if (!productUsers.get(productId).contains(users.get(userId))) {
            productUsers.get(productId).add(users.get(userId));
        }
    }

    /**
     * Asks to enter the id of the user and displays the list of user's products.
     */
    private static void displayUserProducts() {
        displayInfoMessage(USER_ID_MESSAGE);
        int userId = scanner.nextInt();

        if (userProducts.containsKey(userId)) {
            for (Product product : userProducts.get(userId)) {
                product.printProductInfo();
            }
        }
    }

    /**
     * Asks to enter the id of the product and displays the list of users who have purchased the product.
     */
    private static void displayProductUsers() {
        displayInfoMessage(PRODUCT_ID_MESSAGE);
        int productId = scanner.nextInt();

        if (productUsers.containsKey(productId)) {
            for (User user : productUsers.get(productId)) {
                user.printUserInfo();
            }
        }
    }

    /**
     * Outputs a green message into the console.
     *
     * @param message Text of the message to be displayed.
     */
    private static void displaySuccessMessage(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    /**
     * Outputs a red message into the console.
     *
     * @param message Text of the message to be displayed.
     */
    private static void displayFailMessage(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }

    /**
     * Outputs a blue message into the console.
     *
     * @param message Text of the message to be displayed.
     */
    private static void displayInfoMessage(String message) {
        System.out.println(ANSI_BLUE + message + ANSI_RESET);
    }

    // Three users that can buy products.
    private static final HashMap<Integer, User> users = new HashMap<>() {{
        put(1, new User(1, "Ann", "Adams", 100));
        put(2, new User(2, "Boris", "Brown", 12000));
        put(3, new User(3, "Christian", "Cook", 150000));

    }};

    // Three products that can be sold.
    private static final HashMap<Integer, Product> products = new HashMap<>() {{
        put(1, new Product(1, "Apple", 125));
        put(2, new Product(2, "Bell", 6550));
        put(3, new Product(3, "Cake", 50000));
    }};

    private static Scanner scanner = new Scanner(System.in);

    private static final HashMap<Integer, ArrayList<Product>> userProducts = new HashMap<>();
    private static final HashMap<Integer, ArrayList<User>> productUsers = new HashMap<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static final String PURCHASE_SUCCESS_MESSAGE = "The purchase was successful.";
    private static final String PURCHASE_FAIL_MESSAGE = "The purchase was not made. Please check the entered 'id'.";

    private static final String USER_ID_MESSAGE = "Enter user id: ";
    private static final String PRODUCT_ID_MESSAGE = "Enter product id: ";

    private static final String NONEXISTENT_ITEM_MESSAGE = "Menu item with entered number was not found.";
    private static final String INVALID_ITEM_MESSAGE = "Use only digits for entry.";
    private static final String EXIT_MESSAGE = "Program execution completed.";
    private static final String MENU_MESSAGE = """
            ----------------------------------
                           MENU
                           
            1. Display list of all users
            2. Display list of all products
            3. Buy product
            4. Display list of user products
            5. Display list of users that bought product.
            0. Exit.
            ----------------------------------
            Select option:""";
}
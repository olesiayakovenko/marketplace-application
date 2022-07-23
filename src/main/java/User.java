/**
 * Represents a user.
 */
public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private int money;

    /**
     * Creates a user with the specified identification number, first name, last name and amount of money.
     *
     * @param id        The user's identification number.
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param money     The user's amount of money.
     */
    public User(int id, String firstName, String lastName, int money) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;
    }

    /**
     * Gets the user's amount of money.
     *
     * @return An int representing user's amount of money in coins.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the user's amount of money.
     *
     * @param money An int containing the user's amount of money.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Outputs user's identification number, first name, last name and amount of money.
     */
    public void printUserInfo() {
        System.out.println("Id: " + id + " | First name: " + firstName +
                " | Last name: " + lastName + " | Money: " + money);
    }
}
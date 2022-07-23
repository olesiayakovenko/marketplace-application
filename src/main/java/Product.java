/**
 * Represents a product.
 */
public class Product {
    private final int id;
    private final String name;
    private final int price;

    /**
     * Creates a product with the specified identification number, name and price.
     *
     * @param id    The product's identification number.
     * @param name  The product's name.
     * @param price The product's price in coins.
     */
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the product's price.
     *
     * @return An int representing product's price in coins.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Outputs product's identification number, name and price.
     */
    public void printProductInfo() {
        System.out.println("Id: " + id + " | Name: " + name + " | Price: " + price);
    }
}
package packt.java11.mybusiness.productinformation.lookup;

public class InventoryItemAmount {
    private int amount;
    private String id;

    public int getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "{id: " + id + ", amount: " + amount + "}";
    }
}

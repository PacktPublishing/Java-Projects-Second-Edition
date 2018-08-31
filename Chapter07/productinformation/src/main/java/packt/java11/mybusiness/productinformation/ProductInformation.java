package packt.java11.mybusiness.productinformation;

public class ProductInformation {
    private String id;
    private String title;
    private String description;
    private final double size[] = new double[3];
    private double weight;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double[] getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public static final ProductInformation emptyProductInformation = new ProductInformation();

    static {
        emptyProductInformation.setTitle("");
        emptyProductInformation.setDescription("");
        emptyProductInformation.setId("");
        emptyProductInformation.setWeight(0);
        emptyProductInformation.getSize()[0] = 0;
        emptyProductInformation.getSize()[1] = 0;
        emptyProductInformation.getSize()[2] = 0;
    }
}

package packt.java11.bulkorder.dtos;

import java.lang.annotation.Annotation;
import java.util.List;

public class ProductInformation {
    private String id;
    private String title;
    private String description;
    private final double size[] = new double[3];
    private double weight;
    private List<Class<? extends Annotation>> check;

    public List<Class<? extends Annotation>> getCheck() {
        return check;
    }
// START SNIPPET  checkScript
    private String checkScript;

    public String getCheckScript() {
        return checkScript;
    }

    public void setCheckScript(String checkScript) {
        this.checkScript = checkScript;
    }
// END SNIPPET
    public boolean hasCheck(){
        return check != null;
    }

    public void setCheck(List<Class<? extends Annotation>> check) {
        this.check = check;
    }

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

    public boolean isValid(){
        return this != emptyProductInformation;
    }
}

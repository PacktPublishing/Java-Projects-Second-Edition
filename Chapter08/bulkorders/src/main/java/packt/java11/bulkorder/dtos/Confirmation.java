//START SNIPPET Confirmation
package packt.java11.bulkorder.dtos;

public class Confirmation {
    private final Order order;
    private final boolean accepted;

    private Confirmation(Order order, boolean accepted) {
        this.order = order;
        this.accepted = accepted;
    }

    public static Confirmation accepted(Order order) {
        return new Confirmation(order, true);
    }

    public static Confirmation refused(Order order) {
        return new Confirmation(order, false);
    }

    public Order getOrder() {
        return order;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
//END SNIPPET

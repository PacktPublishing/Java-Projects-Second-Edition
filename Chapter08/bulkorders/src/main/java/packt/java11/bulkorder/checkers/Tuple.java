package packt.java11.bulkorder.checkers;
//START SNIPPET Tuple
public class Tuple<R, S> {
    final public R r;
    final public S s;

    private Tuple(R r, S s) {
        this.r = r;
        this.s = s;
    }

    public static <R, S> Tuple tuple(R r, S s) {
        return new Tuple<>(r, s);
    }
}
//END SNIPPET
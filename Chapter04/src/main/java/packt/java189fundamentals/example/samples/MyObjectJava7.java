package packt.java189fundamentals.example.samples;

import java.util.Objects;

public class MyObjectJava7 {
    Object field1;
    Object field2;
    Object field3;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyObjectJava7 that = (MyObjectJava7) o;
        return Objects.equals(field1, that.field1) &&
                Objects.equals(field2, that.field2) &&
                Objects.equals(field3, that.field3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3);
    }
}

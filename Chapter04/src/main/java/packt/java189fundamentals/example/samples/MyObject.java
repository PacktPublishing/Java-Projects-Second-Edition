package packt.java189fundamentals.example.samples;

public class MyObject {
    Object field1;
    Object field2;
    Object field3;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyObject myObject = (MyObject) o;

        if (!field1.equals(myObject.field1)) return false;
        if (!field2.equals(myObject.field2)) return false;
        return field3.equals(myObject.field3);

    }

    @Override
    public int hashCode() {
        int result = field1.hashCode();
        result = 31 * result + field2.hashCode();
        result = 31 * result + field3.hashCode();
        return result;
    }
}

package packt.java189fundamentals.varhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AllocArray {
    public static void main(String[] args) {
        VarHandle mh = MethodHandles.arrayElementVarHandle(MyClass.class);
        mh.get();
    }

    public static class MyClass {
        public String name;
        public int age;
    }
}

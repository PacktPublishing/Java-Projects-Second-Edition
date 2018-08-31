package tryfinally;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PrivateStaticFinalSetter {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field z = OnePrinter.class.getDeclaredField("ONE");
        z.setAccessible(true);
        Field f = Field.class.getDeclaredField("modifiers");
        int modifiers = z.getModifiers();
        f.setAccessible(true);
        f.set(z,modifiers & ~Modifier.FINAL);
        z.set(null, 2);
        OnePrinter.printOne();
    }
}

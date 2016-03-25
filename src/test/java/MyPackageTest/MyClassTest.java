package MyPackageTest;

import MyPackage.MyClass;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void ClassTestExample() {
        assertEquals(new MyClass().getClass().toString(), "class MyPackage.MyClass");
    }

}

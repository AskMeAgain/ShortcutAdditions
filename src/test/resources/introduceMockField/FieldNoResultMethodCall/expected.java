package IntroduceMockFieldTest.Abc;

import ask.me.again.shortcut.additions.introducemock.entities.TestClass;
import org.mockito.Mock;

class IntroduceMockFieldExample {
    @Mock
    Integer integer;
    @Mock
    String string;

    private TestClass testClass = new TestClass();

    void example() {
        testClass.nested(integer, string);
    }
}

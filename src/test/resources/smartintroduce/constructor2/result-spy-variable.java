package io.github.askmeagain.more.shortcuts.introducemock;

import org.mockito.Mockito;
import io.github.askmeagain.more.shortcuts.introducemock.entities.SmartIntroduceTestClass;

public class Abc {

    void ignoreThisMethod() {
        //yes
    }

    void test() {
        var test = 1;

        var abc = Mockito.spy(String.class);
        var abc33 = Mockito.spy(Optional.class);

        new SmartIntroduceTestClass(<caret>abc, "KEEP THIS", abc33);
    }
}

package io.github.askmeagain.more.shortcuts.introducemock;

import org.mockito.Spy;
import io.github.askmeagain.more.shortcuts.introducemock.entities.SmartIntroduceTestClass;

public class Abc {

    @Spy
    String abc;
    @Spy
    Optional<Integer> abc33;

    void ignoreThisMethod() {
        //yes
    }

    void test() {
        var test = 1;
        new SmartIntroduceTestClass(<caret>abc, method22("KEEP THIS"), abc33);
    }

    private String method22(String asddasd) {
        return null;
    }
}

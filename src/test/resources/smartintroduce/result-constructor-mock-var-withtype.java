package io.github.askmeagain.more.shortcuts.introducemock;

import io.github.askmeagain.more.shortcuts.introducemock.entities.SmartIntroduceTestClass;

public class Abc {

    void test() {
        var test = 1;

        String abc = Mockito.mock(String.class);
        String def = Mockito.mock(String.class);
        Optional<Integer> abc33 = Mockito.mock(Optional.class);

        new SmartIntroduceTestClass(<caret>abc, def, abc33);
    }
}

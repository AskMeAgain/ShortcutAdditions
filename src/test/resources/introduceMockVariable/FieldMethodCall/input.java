package IntroduceMockFieldTest.Abc;

import ask.me.again.shortcut.additions.introducemock.entities.TestClass;

class IntroduceMockVariableExample {

  private TestClass testClass= new TestClass();

  void example() {
    var result = testClass.nested(<caret>null,null);
  }
}
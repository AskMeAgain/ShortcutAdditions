package io.github.askmeagain.more.shortcuts.mapstructbuilder;

import lombok.Builder;
import lombok.Value;

public class MapperTest {

  public void test(){
    var input = InputContainer.builder()
        .number1("abc")
        .build();

    var output = ComplexOutputs.builder()
        .orig1(input.getNumber1())
        .orig2(input.getNumber1())
        .mul(Multiplication.builder()
            .nested1(input.getAnotherZero().getAnotherOne().getAnotherOneOne())
            .nested2(input.getNumber1())
            .build())
        .build();
  }

  @Value
  @Builder
  private static class ComplexOutputs {

    String orig1;
    String orig2;
    Multiplication mul;

  }

  @Value
  @Builder
  private static class InputContainer {

    String number1;
    InputContainer anotherZero;
    InputContainer anotherOne;
    String anotherOneOne;

  }

  @Value
  @Builder
  private static class Multiplication {

    String nested1;
    String nested2;

  }
}
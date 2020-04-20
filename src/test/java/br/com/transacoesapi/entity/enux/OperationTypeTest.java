package br.com.transacoesapi.entity.enux;


import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OperationTypeTest {

  @Rule
  public ExpectedException rule = ExpectedException.none();

  @Test
  public void allValuesAreEntryOrOutput() {
    String message = "OperationType (%s) deve ser registrado como de entrada ou como de saída";
    Arrays.stream(OperationType.values()).forEach(value ->
        MatcherAssert.assertThat(String.format(message, value.getDescription()),
            (value.isValueEntry() || value.isOutputValue()), Matchers.is(true)));
  }

  @Test
  public void failIfNotIsValidEnumValue() {
    int id = -1;
    rule.expectMessage(String.format("Tipo de operação (%s) não suportado", id));

    OperationType.getEnum(id);
  }

}
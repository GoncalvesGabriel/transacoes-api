package br.com.transacoesapi.service.validator.account;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.transacoesapi.dto.account.CreateAccountDTO;
import br.com.transacoesapi.repository.AccountRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoExistsAccountWithDocumentValidatorTest {

  @Mock
  private AccountRepository repository;

  @Mock
  private CreateAccountDTO accountDTO;

  @InjectMocks
  private NoExistsAccountWithDocumentValidator validator;

  @Rule
  public ExpectedException rule = ExpectedException.none();

  @Before
  public void setup() {
    when(accountDTO.getDocumentNumber()).thenReturn("01234567899");
  }

  @Test
  public void validateSuccess() {
    when(repository.existsAccountByDocumentNumber(anyString())).thenReturn(false);

    validator.validate(accountDTO);

    verify(accountDTO, times(1)).getDocumentNumber();
  }

  @Test
  public void validateFail() {
    when(repository.existsAccountByDocumentNumber(anyString())).thenReturn(true);

    rule.expectMessage(String.format("Cliente com número de documento %s já possuí conta cadastrada", "01234567899"));

    validator.validate(accountDTO);

    verify(accountDTO, times(1)).getDocumentNumber();
  }
}
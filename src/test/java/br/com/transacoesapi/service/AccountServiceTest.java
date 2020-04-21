package br.com.transacoesapi.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.dto.account.AccountDTO;
import br.com.transacoesapi.dto.account.CreateAccountDTO;
import br.com.transacoesapi.repository.AccountRepository;
import br.com.transacoesapi.service.validator.PreEntityValidator;
import java.util.List;
import java.util.Optional;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  @Rule
  public ExpectedException rules = ExpectedException.none();

  @Mock
  private AccountRepository repository;

  @Mock
  private List<PreEntityValidator<CreateAccountDTO>> preValidators;

  @Mock
  private CreateAccountDTO createDTO;

  @InjectMocks
  private AccountService service;

  @Test
  public void createSuccess() {
    Account account = Account.builder().id(1L).documentNumber("12345678900").build();
    when(createDTO.createAccount()).thenReturn(account);

    AccountDTO accountDTO = service.create(createDTO);

    verify(repository, times(1)).save(account);
    preValidators.stream().forEach(preValidator -> verify(preValidator, times(1)).validate(this.createDTO));
    assertThat("Id de conta retornada é diferente do da conta criada", accountDTO.getId(), is(account.getId()));
    assertThat("Documento do cliente da conta retornada é diferente do da conta criada", accountDTO.getDocumentNumber(), is(account.getDocumentNumber()));
  }

  @Test
  public void findDTOByIdIsPresent() {
    long id = 1L;
    Account account = Account.builder().id(1L).documentNumber("12345678900").build();
    when(repository.findById(id)).thenReturn(Optional.of(account));

    Optional<AccountDTO> accountDTO = service.findDTOById(id);

    assertThat("Método deveria retornar um DTO de account", accountDTO.isPresent(), is(true));
  }

  @Test
  public void findDTOByIdEmpty() {
    long id = 1L;
    when(repository.findById(id)).thenReturn(Optional.empty());

    Optional<AccountDTO> accountDTO = service.findDTOById(id);

    assertThat("Método deveria retornar um DTO de account", accountDTO.isPresent(), is(false));
  }

  @Test
  public void findByIdExists() {
    long id = 1L;
    Account account = Account.builder().id(1L).documentNumber("12345678900").build();
    when(repository.findById(id)).thenReturn(Optional.of(account));

    Account returnedAccount = service.findById(id);

    assertThat("Conta retornada é diferente da esperada", returnedAccount, is(account));
  }

  @Test
  public void findByIdNotFound() {
    long id = 1L;
    when(repository.findById(id)).thenReturn(Optional.empty());
    rules.expectMessage(String.format("Conta não encontrada com id: %s", id));

    Account returnedAccount = service.findById(id);
  }
}
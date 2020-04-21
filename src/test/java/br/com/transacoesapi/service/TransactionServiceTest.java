package br.com.transacoesapi.service;

import static br.com.transacoesapi.entity.enux.OperationType.SAQUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.dto.transaction.TransactionDTO;
import br.com.transacoesapi.repository.TransactionRepository;
import br.com.transacoesapi.service.builder.EntityBuilder;
import java.time.LocalDateTime;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

  @Mock
  private TransactionRepository repository;

  @Mock
  private EntityBuilder<Transaction, CreateTransactionDTO> createBuilder;

  @Mock
  private CreateTransactionDTO createDTO;

  @InjectMocks
  private TransactionService service;

  @Test
  public void createSuccess() {
    Account account = Account.builder().id(1L).documentNumber("12345678900").build();
    Transaction transaction = Transaction.builder().amount(100).eventDate(LocalDateTime.now()).operationType(SAQUE).account(account).build();
    when(createBuilder.builder(createDTO)).thenReturn(transaction);

    TransactionDTO transactionDTO = service.create(createDTO);

    verify(repository, times(1)).save(transaction);
    MatcherAssert.assertThat("Número da conta retornado não confere com número para o qual a tranasação foi criada", transactionDTO.getAccountId(), Matchers.is(transaction.getNumberAccount()));
    MatcherAssert.assertThat("Valor retornado não confere com o valor da tranasação criada", transactionDTO.getAmount(), Matchers.is(transaction.getAmount()));
    MatcherAssert.assertThat("Data/Hora retornado não confere com o data/hora de criação da tranasação", transactionDTO.getEventDate(), Matchers.is(transaction.getEventDate()));
    MatcherAssert.assertThat("Tipo da operação retornado não confere com o tipo da  tranasação criada", transactionDTO.getOperationType(),
        Matchers.is(transaction.getOperationType()));
  }

  @Test
  public void findDTOByIdIsPresent() {
    long id = 1L;
    Account account = Mockito.mock(Account.class);
    Transaction transaction = Transaction.builder().account(account).build();
    when(repository.findById(id)).thenReturn(Optional.of(transaction));

    Optional<TransactionDTO> transactionDTO = service.findDTOById(id);

    assertThat("Método deveria retornar um DTO de transaction", transactionDTO.isPresent(), is(true));
  }

  @Test
  public void findDTOByIdEmpty() {
    long id = 1L;
    when(repository.findById(id)).thenReturn(Optional.empty());

    Optional<TransactionDTO> transactionDTO = service.findDTOById(id);

    assertThat("Método deveria retornar um DTO de account", transactionDTO.isPresent(), is(false));
  }
}
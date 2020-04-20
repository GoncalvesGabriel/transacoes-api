package br.com.transacoesapi.service.builder;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.entity.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.entity.enux.OperationType;
import br.com.transacoesapi.service.AccountService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuilderTransactionByCreateTransactionDTOTest {

  @Mock
  private AccountService accountService;

  @Mock
  private CreateTransactionDTO createTransactionDTO;

  @InjectMocks
  private BuilderTransactionByCreateTransactionDTO builder;

  private double valor = 102.34;

  private  Account account;

  @Before
  public void setup() {
    long accountId = 1L;
    account = Account.builder().id(accountId).documentNumber("21000219").build();
    when(createTransactionDTO.getAccountId()).thenReturn(accountId);
    when(accountService.findById(accountId)).thenReturn(account);
    when(createTransactionDTO.getAmount()).thenReturn(valor);
  }

  @Test
  public void builderEntryTransaction() {
    when(createTransactionDTO.getOperationTypeEnum()).thenReturn(OperationType.PAGAMENTO);

    Transaction transaction = this.builder.builder(createTransactionDTO);

    MatcherAssert.assertThat(transaction.getAmount(), is(valor));
    MatcherAssert.assertThat(transaction.getOperationType(), is(OperationType.PAGAMENTO));
    MatcherAssert.assertThat(transaction.getAccount(), is(account));
    MatcherAssert.assertThat(transaction.getEventDate(), Matchers.notNullValue());
  }

  @Test
  public void builderOutputTransaction() {
    when(createTransactionDTO.getOperationTypeEnum()).thenReturn(OperationType.SAQUE);

    Transaction transaction = this.builder.builder(createTransactionDTO);

    MatcherAssert.assertThat(transaction.getAmount(), is(-valor));
    MatcherAssert.assertThat(transaction.getOperationType(), is(OperationType.SAQUE));
    MatcherAssert.assertThat(transaction.getAccount(), is(account));
    MatcherAssert.assertThat(transaction.getEventDate(), Matchers.notNullValue());
  }
}
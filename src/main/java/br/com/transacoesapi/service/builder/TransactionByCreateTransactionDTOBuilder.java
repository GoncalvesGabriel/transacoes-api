package br.com.transacoesapi.service.builder;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.entity.enux.OperationType;
import br.com.transacoesapi.service.AccountService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionByCreateTransactionDTOBuilder implements EntityBuilder<Transaction, CreateTransactionDTO> {

  private AccountService accountService;

  @Autowired
  public TransactionByCreateTransactionDTOBuilder(AccountService accountService) {
    this.accountService = accountService;
  }

  @Override
  public Transaction builder(CreateTransactionDTO dto) {
    Account account = accountService.findById(dto.getAccountId());
    OperationType operationTypeEnum = dto.getOperationTypeEnum();
    double amount = dto.getAmount();
    if(operationTypeEnum.isOutcome()) {
      amount = amount * -1;
    }
    return new Transaction(account, operationTypeEnum, amount, LocalDateTime.now());
  }
}

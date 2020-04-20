package br.com.transacoesapi.entity.dto.transaction;

import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.entity.enux.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class TransactionDTO {

  @JsonProperty("transaction_id")
  private Long id;

  @JsonProperty("account")
  private Long accountId;

  @JsonProperty("operation_type")
  private OperationType operationType;

  private double amount;

  @JsonProperty("event_date")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime eventDate;

  public TransactionDTO(Transaction transaction) {
    this.id = transaction.getId();
    this.accountId = transaction.getNumberAccount();
    this.operationType = transaction.getOperationType();
    this.amount = transaction.getAmount();
    this.eventDate = transaction.getEventDate();
  }
}

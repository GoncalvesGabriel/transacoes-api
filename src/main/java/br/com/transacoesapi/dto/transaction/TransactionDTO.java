package br.com.transacoesapi.dto.transaction;

import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.dto.util.JsonEnumSerializer;
import br.com.transacoesapi.entity.enux.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import lombok.Builder;
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
  @JsonSerialize(using = JsonEnumSerializer.class)
  private OperationType operationType;

  private double amount;

  @JsonProperty("event_date")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime eventDate;

  public TransactionDTO(Transaction transaction) {
    this(transaction.getId(), transaction.getNumberAccount(), transaction.getOperationType(), transaction.getAmount(), transaction.getEventDate());
  }

  @Builder
  public TransactionDTO(Long id, Long accountId, OperationType operationType, double amount, LocalDateTime eventDate) {
    this.id = id;
    this.accountId = accountId;
    this.operationType = operationType;
    this.amount = amount;
    this.eventDate = eventDate;
  }
}

package br.com.transacoesapi.dto.transaction;

import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.dto.CreateDTO;
import br.com.transacoesapi.entity.enux.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class CreateTransactionDTO implements CreateDTO<Transaction> {

  @NotNull
  @JsonProperty("account_id")
  private Long accountId;

  @NotNull
  @JsonProperty("operation_type_id")
  private Integer operationType;

  @NotNull
  @JsonProperty("amount")
  private double amount;

  @JsonIgnore
  public OperationType getOperationTypeEnum(){
    return OperationType.getEnum(getOperationType());
  }

  @Builder
  public CreateTransactionDTO(@NotNull Long accountId, @NotNull Integer operationType, @NotNull double amount) {
    this.accountId = accountId;
    this.operationType = operationType;
    this.amount = amount;
  }
}

package br.com.transacoesapi.entity.dto.transaction;

import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.entity.dto.CreateDTO;
import br.com.transacoesapi.entity.enux.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

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

}

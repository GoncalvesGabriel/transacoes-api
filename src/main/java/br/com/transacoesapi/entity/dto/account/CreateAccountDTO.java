package br.com.transacoesapi.entity.dto.account;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.entity.dto.CreateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

public @Data class CreateAccountDTO implements CreateDTO {

  @NotNull
  @JsonProperty("document_number")
  private String documentNumber;

  public Account createAccount() {
    Account account = new Account();
    account.setDocumentNumber(this.getDocumentNumber());
    return account;
  }
}

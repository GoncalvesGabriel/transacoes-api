package br.com.transacoesapi.dto.account;

import br.com.transacoesapi.dto.CreateDTO;
import br.com.transacoesapi.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class CreateAccountDTO implements CreateDTO<Account> {

  @NotNull
  @JsonProperty("document_number")
  private String documentNumber;

  public Account createAccount() {
    Account account = new Account();
    account.setDocumentNumber(this.getDocumentNumber());
    return account;
  }
}

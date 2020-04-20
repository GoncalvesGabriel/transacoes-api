package br.com.transacoesapi.entity.dto.account;

import br.com.transacoesapi.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class AccountDTO {

  @JsonProperty("account_id")
  private Long id;

  @JsonProperty("document_number")
  private String documentNumber;

  public AccountDTO(Account account) {
    this.id = account.getId();
    this.documentNumber = account.getDocumentNumber();
  }
}

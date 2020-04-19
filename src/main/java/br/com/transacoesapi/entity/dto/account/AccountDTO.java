package br.com.transacoesapi.entity.dto.account;

import br.com.transacoesapi.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data
class AccountDTO {

  private Long id;

  private String documentNumber;

  public AccountDTO(Account account) {
    this.id = account.getId();
    this.documentNumber = account.getDocumentNumber();
  }
}

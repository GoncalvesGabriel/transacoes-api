package br.com.transacoesapi.entity.dto.account;

import br.com.transacoesapi.entity.Account;
import lombok.Data;

public @Data class CreateAccountDTO {

  private String documentNumber;

  public Account createAccount() {
    Account account = new Account();
    account.setDocumentNumber(this.getDocumentNumber());
    return account;
  }
}

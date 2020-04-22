package br.com.transacoesapi.dto.account;

import br.com.transacoesapi.entity.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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

  @Builder
  public AccountDTO(Long id, String documentNumber) {
    this.id = id;
    this.documentNumber = documentNumber;
  }
}

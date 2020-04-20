package br.com.transacoesapi.service.validator.Account;

import br.com.transacoesapi.entity.dto.account.CreateAccountDTO;
import br.com.transacoesapi.repository.AccountRepository;
import br.com.transacoesapi.service.validator.PreEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoExistAccountWithDocumentValidate implements PreEntityValidator<CreateAccountDTO> {

  private AccountRepository repository;

  @Autowired
  public NoExistAccountWithDocumentValidate(AccountRepository repository) {
    this.repository = repository;
  }

  @Override
  public void validate(CreateAccountDTO dto) {
    String documentNumber = dto.getDocumentNumber();
    boolean exist = repository.existsAccountByDocumentNumber(documentNumber);
    if (exist) {
      throw new IllegalArgumentException(String.format("Cliente com número de documento %s já possuí conta cadastrada", documentNumber));
    }
  }
}

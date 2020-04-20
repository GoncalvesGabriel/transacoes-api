package br.com.transacoesapi.service;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.entity.dto.account.AccountDTO;
import br.com.transacoesapi.entity.dto.account.CreateAccountDTO;
import br.com.transacoesapi.repository.AccountRepository;
import br.com.transacoesapi.service.validator.PreEntityValidator;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {

  private AccountRepository repository;

  private List<PreEntityValidator<CreateAccountDTO>> preValidators;

  @Autowired
  public AccountService(AccountRepository repository, List<PreEntityValidator<CreateAccountDTO>> preValidators) {
    this.repository = repository;
    this.preValidators = preValidators;
  }

  public AccountDTO create(CreateAccountDTO accountDTO) {
    preValidators.stream().forEach(validator -> validator.validate(accountDTO));
    Account account = accountDTO.createAccount();
    repository.save(account);
    return new AccountDTO(account);
  }

  public Optional<AccountDTO> findBy(Long id) {
    Optional<Account> account = repository.findById(id);
    if (account.isPresent()) {
      return Optional.of(new AccountDTO(account.get()));
    }
    return Optional.empty();
  }

  public Account findById(Long id) {
    Optional<Account> account = repository.findById(id);
    if (account.isPresent()) {
      return account.get();
    }
    throw new IllegalArgumentException(String.format("Conta não encontrada com id: %s", id));
  }
}

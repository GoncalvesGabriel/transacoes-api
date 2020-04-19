package br.com.transacoesapi.service;

import br.com.transacoesapi.entity.Account;
import br.com.transacoesapi.entity.dto.account.AccountDTO;
import br.com.transacoesapi.entity.dto.account.CreateAccountDTO;
import br.com.transacoesapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  private AccountRepository repository;

  public AccountDTO create(CreateAccountDTO accountDTO) {
    Account account = accountDTO.createAccount();
    repository.save(account);
    return new AccountDTO(account);
  }
}

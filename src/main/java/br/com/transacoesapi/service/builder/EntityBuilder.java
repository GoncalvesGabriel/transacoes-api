package br.com.transacoesapi.service.builder;

import br.com.transacoesapi.entity.DomainEntity;
import br.com.transacoesapi.dto.CreateDTO;

public interface EntityBuilder<T extends DomainEntity, R extends CreateDTO<T>> {

  public T builder(R dto);

}

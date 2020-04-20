package br.com.transacoesapi.service.validator;


import br.com.transacoesapi.entity.dto.CreateDTO;

public interface PreEntityValidator<T extends CreateDTO> {

  void validate(T dto);

}

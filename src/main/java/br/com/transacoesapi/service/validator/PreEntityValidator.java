package br.com.transacoesapi.service.validator;


import br.com.transacoesapi.dto.CreateDTO;

public interface PreEntityValidator<T extends CreateDTO> {

  void validate(T dto);

}

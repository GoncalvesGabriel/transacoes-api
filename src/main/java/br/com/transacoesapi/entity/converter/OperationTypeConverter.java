package br.com.transacoesapi.entity.converter;

import br.com.transacoesapi.entity.enux.OperationType;
import javax.persistence.AttributeConverter;

public class OperationTypeConverter implements AttributeConverter<OperationType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(OperationType operationType) {
    return operationType.getId();
  }

  @Override
  public OperationType convertToEntityAttribute(Integer id) {
    return OperationType.getEnum(id);
  }
}

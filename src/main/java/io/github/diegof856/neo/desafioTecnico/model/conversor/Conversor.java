package io.github.diegof856.neo.desafioTecnico.model.conversor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.util.List;

public class Conversor implements AttributeConverter<List<String>, String> {
private final ObjectMapper mapper = new ObjectMapper();

@Override
public String convertToDatabaseColumn(List<String> roles) {
    try {
        return mapper.writeValueAsString(roles);
    } catch (Exception e) {
        throw new RuntimeException("Erro convertendo roles para JSON", e);
    }
}

@Override
public List<String> convertToEntityAttribute(String rolesJson) {
    try {
        return mapper.readValue(rolesJson, new TypeReference<List<String>>() {});
    } catch (Exception e) {
        throw new RuntimeException("Erro convertendo JSON para roles", e);
    }
}
}
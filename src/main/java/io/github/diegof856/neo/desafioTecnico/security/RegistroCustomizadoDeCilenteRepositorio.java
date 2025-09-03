package io.github.diegof856.neo.desafioTecnico.security;

import io.github.diegof856.neo.desafioTecnico.model.Cliente;
import io.github.diegof856.neo.desafioTecnico.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroCustomizadoDeCilenteRepositorio implements RegisteredClientRepository {
    private final ClienteService clienteService;
    private final TokenSettings tokenSettings;
    private final ClientSettings clientSettings;
    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        var cliente = this.clienteService.obterPorClientID(id);
        if(cliente == null){
            return null;
        }
        return registrarCliente(cliente);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var cliente = this.clienteService.obterPorClientID(clientId);
        if(cliente == null){
            return null;
        }
        return registrarCliente(cliente);
    }

    private RegisteredClient registrarCliente(Cliente client){
        return RegisteredClient
                .withId(client.getId().toString())
                .clientId(client.getClienteId())
                .clientSecret(client.getClienteSegredo())
                .redirectUri(client.getRedirectURI())
                .scope(client.getScopo()).clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .tokenSettings(tokenSettings)
                .clientSettings(clientSettings)
                .build();
    }
}

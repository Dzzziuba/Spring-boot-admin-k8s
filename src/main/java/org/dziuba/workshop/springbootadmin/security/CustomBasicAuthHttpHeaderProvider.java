package org.dziuba.workshop.springbootadmin.security;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Primary
@Component
public class CustomBasicAuthHttpHeaderProvider extends BasicAuthHttpHeaderProvider {

    @Value("${actuator.username}")
    private String username;

    @Value("${actuator.password}")
    private String password;

    @NotNull
    @Override
    public HttpHeaders getHeaders(@NotNull Instance instance) {
        var httpHeaders = new HttpHeaders();
        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            httpHeaders.set("Authorization", super.encode(username, password));
        }
        return httpHeaders;
    }
}

package com.tasker.calendar_manager.service.dto;

import java.util.List;

public record WebDto(
        String clientId,
        String projectId,
        String authUriHttps,
        String tokenUriHttps,
        String authProviderX509CertUrlHttps,
        String clientSecret,
        List<String> redirectUris
) {
}

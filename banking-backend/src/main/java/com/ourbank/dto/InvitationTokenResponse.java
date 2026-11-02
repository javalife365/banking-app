package com.ourbank.dto;

import java.time.LocalDateTime;

public record InvitationTokenResponse (
        String token,
        LocalDateTime expiresAt
){
}

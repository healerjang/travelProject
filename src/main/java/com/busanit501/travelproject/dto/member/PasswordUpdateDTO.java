package com.busanit501.travelproject.dto.member;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PasswordUpdateDTO {
    private String oldPassword;
    private String newPassword;
}

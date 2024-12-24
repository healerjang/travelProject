package com.busanit501.travelproject.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    private String memberID;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;

    public boolean isData() {
        return memberID != null && !memberID.isEmpty() && memberName != null && !memberName.isEmpty() && memberEmail != null && !memberEmail.isEmpty() && memberPhone != null && !memberPhone.isEmpty();
    }
}

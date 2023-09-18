package com.example.client.service.web.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    private String firstName;
    private String lastName;
    private String address;
    @Email
    private String email;
    private String telephone;

}

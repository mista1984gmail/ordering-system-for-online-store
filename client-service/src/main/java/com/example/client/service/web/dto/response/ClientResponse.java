package com.example.client.service.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String telephone;
}

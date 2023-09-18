package com.example.client.service.domain.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ClientUtil {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String telephone;
    private List<OrderUtil> orders;
}

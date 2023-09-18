package com.example.client.service.service.convertor;

import com.example.client.service.domain.entity.Client;
import com.example.client.service.service.dto.ClientDto;
import com.example.client.service.web.dto.request.ClientRequest;
import com.example.client.service.web.dto.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface ClientMapper {
    Client dtoToModel(ClientDto clientDto);
    ClientDto modelToDto(Client client);

    List<ClientDto> toListDto(List<Client> clients);
    @Mapping(target = "id", ignore = true)
    void updateEntityToModel(@MappingTarget Client target, ClientDto source);
    @Mapping(target = "id", source = "id")
    ClientDto requestToDto(Long id, ClientRequest clientRequest);

    ClientDto requestToDto(ClientRequest clientRequest);

    ClientResponse dtoToResponse(ClientDto clientDto);

    List<ClientResponse> toListResponse(
            List<ClientDto> clients);


}

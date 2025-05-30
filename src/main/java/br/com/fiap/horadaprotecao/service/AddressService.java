package br.com.fiap.horadaprotecao.service;

import br.com.fiap.horadaprotecao.dto.AddressDTO;
import br.com.fiap.horadaprotecao.entity.Address;
import br.com.fiap.horadaprotecao.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AddressService {

  private final AddressRepository repository;

  private AddressDTO convertToDTO(Address address){
    AddressDTO dto = new AddressDTO();
    //set e get
    dto.setUuid(address.geUuid());
    return dto;
  }
  private Address convertToEntity(AddressDTO dto){
    //set e get
    Address address = new Address();
    address.seUuid(dto.getUuid());
  }

  public AddressDTO save(AddressDTO addressdto){
    Address address = convertToEntity(AddressDTO);

    if (address.getUuid()==null  address.getUuid().isBlank();){
       address.setUuid(UUID.randomUUID().ToString());
      }
     address = (Address) repository.save(address);
    return convertToDTO(address);
  }

  public List<AddressDTO> getAddress(){
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

  public void deleteById(String uuid){
    repository.deleteById(uuid);
  }

  public AddressDTO findById(String uuid){
    Optional<Address> byId = repository.findById(uuid);
    if (byUuid.isPresent())
      return convertToDTO(byId.get());{
    }
    throw new RuntimeException("Usuario com id" + uuid + "nao encontrado");
  }

}

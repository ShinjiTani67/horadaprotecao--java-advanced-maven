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
    dto.setUuid(address.getUuid());
    dto.setBairro(address.getBairro());
    dto.setCep(address.getCep());
    dto.setRua(address.getRua());

    return dto;
  }

  private Address convertToEntity(AddressDTO dto){
    Address address = new Address();
    address.setUuid(dto.getUuid());
    address.setRua(dto.getRua());
    address.setCep(dto.getCep());
    address.setBairro(dto.getBairro());

    return address;
  }

  public AddressDTO save(AddressDTO addressdto){
    Address address = convertToEntity(addressdto);

    if (address.getUuid()==null){
       address.setUuid(UUID.randomUUID());
      }
     address = (Address) repository.save(address);
    return convertToDTO(address);
  }

  public List<AddressDTO> getAddress(){
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

  public void deleteById(UUID uuid){
    repository.deleteById(uuid);
  }

  public AddressDTO findById(UUID uuid){
    Optional<Address> byUuid = repository.findById(uuid);
    if (byUuid.isPresent())
      return convertToDTO(byUuid.get());{
    }
    throw new RuntimeException("Usuario com id" + uuid + "nao encontrado");
  }

  public AddressDTO findByEmail(String email) {
    Optional<Address> address = repository.findByUser_Email(email);
    if (address.isPresent())
      return convertToDTO(address.get());
    throw new RuntimeException("Endereço com email de usuário " + email + " não encontrado");
  }

}

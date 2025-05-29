package br.com.fiap.horadaprotecao.service;

import org.springframework.stereotype.Service;



@Servic
@AllArgsConstructor
public class AddressService {

  private final AddressRepository repository;

  private AddressDTO convertToDTO(Address address){
    AddressDTO dto = new AddressDTO();
    //set e get
    dto.setUuid(address.geUuid());
    return dto;
  }
  private Adress convertToEntity(AddressDTO dto){
    //set e get
    Address address = new Address();
    address.seUuid(dto.getUuid());
  }

  public AddressDTO save(AddressDTO addressdto){
    Address address = convertoEntity(AddressDTO);

    if (address.getUuid()==null  address.getUuid().isBlank()){
       address.setUuid(UUI.randomUUID().ToString());
      }
     address = (Address) reposiory.save(address);
    return convetToDTO(address);
  }

  //List<AddressDTO>
  //deleteByUuid
  //findByUuid

  //public List<UserDTO> getUser(){
  //      return repository.findAll().stream()
  //              .map(this::convertToDTO)
  //              .collect(Collectors.toList());
  //  }

    //public void deleteById(String id){
      //  repository.deleteById(id);
    //}

    //public UserDTO findById(String id){
      //  Optional<User> byId = repository.findById(id);
        //if (byId.isPresent())
          //  return convertToDTO(byId.get());{
        //}
        //throw new RuntimeException("Usuario com id" + id + "nao encontrado");
    //}
}

package br.com.fiap.horadaprotecao.service;

@Service
@AllArgsConstructor
public class FloodZoneService {
  private final FloodedZoneRepository repository;

  private FloodedZoneDTO convertToDTO (FloodedZone floodzone){
    FloodedZoneDTO dto = new FloodedZoneDTO();
    dto,setUuid(floodedzone.getUuid());
    //get e set
    return dto;
  }

  private FloodedZone convertToEntity(FloodedZoneDTO dto){

    FloodedZone floodedzone = new FloodedZone();
    floodedzone.setUuid(dto.getUuid());
    //set e get
  }
  public FloodedZoneDTO save(FloodedZoneDTO floodedzonedto){
    
  }
  public List<FloodedZoneDTO> getFloodedZone(){
      return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
  }
  public void deleteById(String uuid){
        repository.deleteUuid(uuid);
    }

  public FloodedZoneDTO findByUuid(Uuid uuid){
    Optional<FloodedZone> byUuid = repository.findbyUuid(uuid);
    if (byUuid.isPresent())
            return convertToDTO(byUuid.get());{
        }
        throw new RuntimeException("Usuario com id" + id + "nao encontrado");
  }
}

package br.com.fiap.horadaprotecao.service;

import br.com.fiap.horadaprotecao.dto.FloodZoneDTO;
import br.com.fiap.horadaprotecao.entity.FloodZone;
import br.com.fiap.horadaprotecao.repository.FloodZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FloodZoneService {
  private final FloodZoneRepository repository;

  private FloodZoneDTO convertToDTO (FloodZone floodzone){
    FloodZoneDTO dto = new FloodZoneDTO();
    dto.setUuid(floodzone.getUuid());
    //get e set
    return dto;
  }

  private FloodZone convertToEntity(FloodZoneDTO dto){

    FloodZone floodedzone = new FloodZone();
    floodedzone.setUuid(dto.getUuid());
    //set e get
  }
  public FloodZoneDTO save(FloodZoneDTO floodedzonedto){
    
  }
  public List<FloodZoneDTO> getFloodedZone(){
      return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
  }
  public void deleteById(String uuid){
        repository.deleteUuid(uuid);
    }

  public FloodZoneDTO findByUuid(Uuid uuid){
    Optional<FloodZone> byUuid = repository.findbyUuid(uuid);
    if (byUuid.isPresent())
            return convertToDTO(byUuid.get());{
        }
        throw new RuntimeException("Usuario com id" + uuid + "nao encontrado");
  }
}

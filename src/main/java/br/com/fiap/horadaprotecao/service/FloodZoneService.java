package br.com.fiap.horadaprotecao.service;

import br.com.fiap.horadaprotecao.dto.FloodZoneDTO;
import br.com.fiap.horadaprotecao.entity.FloodZone;
import br.com.fiap.horadaprotecao.repository.FloodZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FloodZoneService {

  private final FloodZoneRepository repository;

  private FloodZoneDTO convertToDTO(FloodZone floodZone) {
    FloodZoneDTO dto = new FloodZoneDTO();
    dto.setUuid(floodZone.getUuid());
    dto.setNivelRisco(floodZone.getNivelRisco());
    dto.setRaioEmKm(floodZone.getRaioEmKm());
    return dto;
  }

  private FloodZone convertToEntity(FloodZoneDTO dto) {
    FloodZone floodZone = new FloodZone();
    floodZone.setUuid(dto.getUuid());
    floodZone.setNivelRisco(dto.getNivelRisco());
    floodZone.setRaioEmKm(dto.getRaioEmKm());
    return floodZone;
  }

  public FloodZoneDTO save(FloodZoneDTO floodZoneDTO) {
    FloodZone floodZone = convertToEntity(floodZoneDTO);

    if (floodZone.getUuid() == null) {
      floodZone.setUuid(UUID.randomUUID());
    }

    floodZone = repository.save(floodZone);
    return convertToDTO(floodZone);
  }

  public List<FloodZoneDTO> getFloodedZone() {
    return repository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }

  public void deleteById(UUID uuid) {
    repository.deleteById(uuid);
  }

  public FloodZoneDTO findByUuid(UUID uuid) {
    return repository.findById(uuid)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("FloodZone com UUID " + uuid + " não encontrado"));
  }

  public List<FloodZoneDTO> findByAddressUuid(UUID addressUuid) {
    return repository.findByAddress_Uuid(addressUuid).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
  }
}

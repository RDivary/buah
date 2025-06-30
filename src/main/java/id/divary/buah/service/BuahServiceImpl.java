package id.divary.buah.service;

import id.divary.buah.dto.buah.BuahCreateUpdateDto;
import id.divary.buah.dto.buah.BuahDto;
import id.divary.buah.entity.Buah;
import id.divary.buah.repository.BuahRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuahServiceImpl implements BuahService{

    private final BuahRepository buahRepository;

    @Override
    public void create(BuahCreateUpdateDto buahDto) {
        Buah buah = Buah.builder()
                .name(buahDto.name())
                .isDeleted(false)
                .build();

        buahRepository.save(buah);
    }

    @Override
    public BuahDto findById(String id) {
        return buahRepository.findById(id)
                .map(this::buildBuahDto)
                .orElseThrow(() -> new RuntimeException("Buah not found"));
    }

    @Override
    public List<BuahDto> findAll() {
        return buahRepository.findAllByIsDeletedFalse().stream()
                .map(this::buildBuahDto)
                .toList();
    }

    @Override
    public void update(String id, BuahCreateUpdateDto buahDto) {
        Buah buah = buahRepository.findById(id).orElseThrow(() -> new RuntimeException("Buah not found"));

        if (buah.isDeleted()) {
            throw new RuntimeException("Buah is Deleted");
        }

        buah.setName(buahDto.name());

        buahRepository.save(buah);
    }

    @Override
    public void delete(String id) {
        Buah buah = buahRepository.findById(id).orElseThrow(() -> new RuntimeException("Buah not found"));

        if (buah.isDeleted()) {
            throw new RuntimeException("Buah is Deleted");
        }

        buah.setDeleted(true);

        buahRepository.save(buah);
    }

    private BuahDto buildBuahDto(Buah buah) {
        return new BuahDto(buah.getId(), buah.getName(), buah.isDeleted());
    }
}

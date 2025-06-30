package id.divary.buah.service;

import id.divary.buah.dto.buah.BuahCreateUpdateDto;
import id.divary.buah.dto.buah.BuahDto;

import java.util.List;

public interface BuahService {
    void create(BuahCreateUpdateDto buahDto);
    BuahDto findById(String id);
    List<BuahDto> findAll();
    void update(String id, BuahCreateUpdateDto buahDto);
    void delete(String id);
}

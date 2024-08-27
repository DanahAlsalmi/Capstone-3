package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Fabric;
import com.example.capstone3.Repository.FabricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FabricService {

    private final FabricRepository fabricRepository;

    public List<Fabric> getAllFabric() {
        return fabricRepository.findAll();
    }

    public void addFabric(Fabric fabric) {
        fabricRepository.save(fabric);
    }

    public void updateFabric(Integer id, Fabric fabric){
        Fabric f = fabricRepository.findFabricById(id);
        if(f == null) {
            throw new ApiException("Fabric with id " + id + " not found");
        }
        f.setColor(fabric.getColor());
        f.setName(fabric.getName());
        f.setPrice(fabric.getPrice());
        f.setType(fabric.getType());
        f.setPattern(fabric.getPattern());
        f.setDescription(fabric.getDescription());
        fabricRepository.save(f);
    }

    public void deleteFabric(Integer id) {
        Fabric f = fabricRepository.findFabricById(id);
        if(f == null) {
            throw new ApiException("Fabric with id " + id + " not found");
        }
        fabricRepository.delete(f);
    }
}

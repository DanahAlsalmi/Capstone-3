package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Tailor;
import com.example.capstone3.Repository.TailorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TailorService {

    private final TailorRepository tailorRepository;

    //Get Tailors
    public List<Tailor> getAllTailors() {
        return tailorRepository.findAll();
    }

    //Add Tailor
    public void addTailor(Tailor tailor) {
        tailorRepository.save(tailor);
    }

    //Update Tailor
    public void updateTailor(Integer id,Tailor tailor) {
        Tailor t = tailorRepository.findTailorById(id);
        if (t == null) {
            throw new ApiException("Tailor with id '" + id + "' not found");
        }
        t.setName(tailor.getName());
        t.setEmail(tailor.getEmail());
        t.setPhone(tailor.getPhone());
        t.setAcceptOrder(tailor.isAcceptOrder());
        t.setPriceByMeter(tailor.getPriceByMeter());
        tailorRepository.save(t);
    }

    //Delete Tailor
    public void deleteTailor(Integer id) {
        Tailor t = tailorRepository.findTailorById(id);
        if (t == null) {
            throw new ApiException("Tailor with id '" + id + "' not found");
        }
        tailorRepository.delete(t);
    }
}

package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Designer;
import com.example.capstone3.Repository.DesignerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignerService {

    private final DesignerRepository designerRepository;

    //Get Designers
    public List<Designer> getAllDesigners() {
        return designerRepository.findAll();
    }

    //Add Designer
    public void addDesigner(Designer designer) {
        designerRepository.save(designer);
    }

    //Update Designer
    public void updateDesigner(Integer id,Designer designer) {
        Designer d = designerRepository.findDesignerById(id);
        if(d==null) {
            throw new ApiException("Designer with id '" + id + "' not found");
        }
        d.setName(designer.getName());
        d.setEmail(designer.getEmail());
        d.setPhone(designer.getPhone());
        d.setPrice(designer.getPrice());
        designerRepository.save(d);
    }

    //Delete Designer
    public void deleteDesigner(Integer id) {
        Designer d = designerRepository.findDesignerById(id);
        if(d==null) {
            throw new ApiException("Designer with id '" + id + "' not found");
        }
        designerRepository.delete(d);
    }

}

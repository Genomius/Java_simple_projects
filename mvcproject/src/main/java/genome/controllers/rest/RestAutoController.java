package genome.controllers.rest;

import genome.converters.AutoToAutoDtoConverter;
import genome.dto.AutoDto;
import genome.models.Auto;
import genome.services.AutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAutoController {

    @Autowired
    private AutoService autoService;
    
    @RequestMapping(value = "/autos", method = RequestMethod.GET)
    public List<AutoDto> getAutos(){
        List<AutoDto> resultAutos = new ArrayList<AutoDto>();
        List<Auto> autos = autoService.getAllAutos();
        
        for (Auto auto : autos) {
            resultAutos.add(AutoToAutoDtoConverter.convertWithUser(auto));
        }
        
        return resultAutos;
    }
    
    @RequestMapping(value = "/autos", method = RequestMethod.POST)
    public ResponseEntity<List<Auto>> addAuto(
            @RequestBody Auto auto
    ){
        autoService.saveAuto(auto);
        
        List<Auto> autos = autoService.getAllAutos();
        ResponseEntity<List<Auto>> response = new ResponseEntity<List<Auto>>(autos, HttpStatus.CREATED);
        
        return response;
    }
}

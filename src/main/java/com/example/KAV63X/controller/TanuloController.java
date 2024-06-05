package com.example.KAV63X.controller;
import com.example.KAV63X.model.Tanulo;
import com.example.KAV63X.repo.TanuloRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TanuloController {
    @Autowired
    private TanuloRepo tanuloRepo;
    @GetMapping("/getAllTanulok")

    public ResponseEntity<List<Tanulo>> getAllTanulok() {

        try {
            List<Tanulo> tanuloLista = new ArrayList<>();
            tanuloRepo.findAll().forEach(tanuloLista::add);

            if (tanuloLista.isEmpty()) {
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tanuloLista, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getTanuloByAzon/{azon}")

    public ResponseEntity<Tanulo> getTanuloByAzon(@PathVariable Long azon) {

        Optional<Tanulo> tanuloData= tanuloRepo.findById(azon);

        if (tanuloData.isPresent()) {
            return new ResponseEntity<>(tanuloData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addTanulo")

    public ResponseEntity<Tanulo> addTanulo(@RequestBody Tanulo tanulo) {
        Tanulo tanuloObj= tanuloRepo.save(tanulo);

        return new ResponseEntity<>(tanuloObj, HttpStatus.OK);
    }

    @PostMapping("/frissitTanuloByAzon/{azon}")
    public ResponseEntity<Tanulo> frissitTanulovByAzon(@PathVariable Long azon, @RequestBody Tanulo ujTanuloData) {

        Optional<Tanulo> regitanuloData=  tanuloRepo.findById(azon);

        if (regitanuloData.isPresent()) {

            Tanulo frissitTanuloData= regitanuloData.get();

            frissitTanuloData.setAzon(ujTanuloData.getAzon());
            frissitTanuloData.setOsztaly(ujTanuloData.getOsztaly());

            Tanulo tanuloObj= tanuloRepo.save(frissitTanuloData);

            return new ResponseEntity<>(tanuloObj, HttpStatus.OK);

        }



  /*      if (regitanuloData.isPresent()) {

            Tanulo frissitTanuloData= regitanuloData.get();

            frissitTanuloData.clone(ujTanuloData.getNev());
            frissitTanuloData.setOsztaly(ujTanuloData.getOsztaly());

            Tanulo tanuloObj= tanuloRepo.save(frissitTanuloData);

            return new ResponseEntity<>(tanuloObj, HttpStatus.OK);

        }
*/
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/torolTanuloByAzon/{azon}")
    public ResponseEntity<HttpStatus> torolTanuloByAzon(@PathVariable Long azon) {
        tanuloRepo.deleteById(azon);

        return new ResponseEntity<>(HttpStatus.OK);





    }
}

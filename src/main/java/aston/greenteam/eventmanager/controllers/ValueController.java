package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.ValueDTO;
import aston.greenteam.eventmanager.entities.Value;
import aston.greenteam.eventmanager.services.impl.ValuesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/parameter/value")
@AllArgsConstructor
public class ValueController {

    private final ValuesServiceImpl valuesServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<ValueDTO> findByValueId(@PathVariable Long id) {
        return new ResponseEntity<>(valuesServiceImpl.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ValueDTO>> findAll() {
        return new ResponseEntity<>(valuesServiceImpl.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ValueDTO> create(@RequestBody ValueDTO valueDTO) {
        return new ResponseEntity<>(valuesServiceImpl.createValue(valueDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ValueDTO> update(@RequestBody Value value) {
        return new ResponseEntity<>(valuesServiceImpl.updateValue(value), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        valuesServiceImpl.deleteValue(id);
        return HttpStatus.OK;
    }
}

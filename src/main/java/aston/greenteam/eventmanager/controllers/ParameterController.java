package aston.greenteam.eventmanager.controllers;

import aston.greenteam.eventmanager.dtos.ParameterDTO;
import aston.greenteam.eventmanager.entities.Parameter;
import aston.greenteam.eventmanager.services.impl.ParametersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/parameters")
@AllArgsConstructor
public class ParameterController {

    private final ParametersServiceImpl parametersServiceImpl;

    @GetMapping
    public ResponseEntity<List<ParameterDTO>> findAll() {
        return new ResponseEntity<>(parametersServiceImpl.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterDTO> findByParameterId(@PathVariable Long id) {
        return new ResponseEntity<>(parametersServiceImpl.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParameterDTO> create(@RequestBody ParameterDTO parameterDTO) {
        return new ResponseEntity<>(parametersServiceImpl.createParameter(parameterDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ParameterDTO> update(@RequestBody Parameter parameter) {
        return new ResponseEntity<>(parametersServiceImpl.updateParameter(parameter), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        parametersServiceImpl.deleteParameter(id);
        return HttpStatus.OK;
    }

    @PutMapping("/{parameterId}/value/{valueId}")
    public ParameterDTO assignProjectToEmployee(@PathVariable Long parameterId,
                                                @PathVariable Long valueId) {
        return parametersServiceImpl.assignValueToParameter(parameterId, valueId);
    }
}
package co.edu.uniquindio.software.actividad.saludvital.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.software.actividad.saludvital.model.Resultado;
import co.edu.uniquindio.software.actividad.saludvital.service.ResultadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resultados")
@CrossOrigin(origins = "*")
public class ResultadoController {

    private final ResultadoService service;

    public ResultadoController(ResultadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resultado> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultado> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Resultado> buscarPorPaciente(@RequestParam String paciente) {
        return service.buscarPorPaciente(paciente);
    }

    @GetMapping("/tipo")
    public List<Resultado> buscarPorTipoExamen(@RequestParam String tipoExamen) {
        return service.buscarPorTipoExamen(tipoExamen);
    }

    @PostMapping
    public ResponseEntity<Resultado> crear(@Valid @RequestBody Resultado resultado) {
        Resultado creado = service.crear(resultado);
        return ResponseEntity.created(URI.create("/api/resultados/" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> actualizar(@PathVariable Long id, @Valid @RequestBody Resultado resultado) {
        return service.actualizar(id, resultado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean ok = service.eliminar(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

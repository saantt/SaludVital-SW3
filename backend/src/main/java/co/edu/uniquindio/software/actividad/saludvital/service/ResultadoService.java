package co.edu.uniquindio.software.actividad.saludvital.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.uniquindio.software.actividad.saludvital.model.Resultado;

@Service
public class ResultadoService {

    private final Map<Long, Resultado> storage = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public ResultadoService() {
        // Datos de ejemplo para demostración
        inicializarDatosEjemplo();
    }

    private void inicializarDatosEjemplo() {
        String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fechaAyer = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fechaSemana = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        crear(new Resultado(null, "Juan Pérez", "Hemograma Completo", fechaSemana, 
                "Hemoglobina: 14.2 g/dL (Normal), Hematocrito: 42% (Normal), Leucocitos: 7,500/μL (Normal)", 
                "Todos los valores dentro de parámetros normales", "Dr. María González", fechaAyer));

        crear(new Resultado(null, "Ana García", "Radiografía de Tórax", fechaAyer, 
                "Sin alteraciones pulmonares. Estructuras óseas normales. Corazón de tamaño normal.", 
                "Radiografía sin hallazgos patológicos", "Dr. Carlos López", fechaHoy));

        crear(new Resultado(null, "Luis Rodríguez", "Perfil Lipídico", fechaSemana, 
                "Colesterol Total: 180 mg/dL (Normal), LDL: 110 mg/dL (Normal), HDL: 45 mg/dL (Normal), Triglicéridos: 120 mg/dL (Normal)", 
                "Perfil lipídico dentro de rangos normales", "Dr. María González", fechaAyer));

        crear(new Resultado(null, "Carmen Silva", "Ecografía Abdominal", fechaAyer, 
                "Hígado de tamaño normal, sin lesiones. Vesícula biliar sin alteraciones. Riñones de tamaño y forma normales.", 
                "Ecografía sin hallazgos patológicos", "Dr. Ana Martínez", fechaHoy));
    }

    public List<Resultado> listar() {
        return new ArrayList<>(storage.values());
    }

    public List<Resultado> buscarPorPaciente(String paciente) {
        return storage.values().stream()
                .filter(r -> r.getPaciente().toLowerCase().contains(paciente.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Resultado> obtenerPorId(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Resultado crear(Resultado resultado) {
        long id = idCounter.getAndIncrement();
        resultado.setId(id);
        if (resultado.getFechaResultado() == null || resultado.getFechaResultado().isEmpty()) {
            resultado.setFechaResultado(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        storage.put(id, resultado);
        return resultado;
    }

    public Optional<Resultado> actualizar(Long id, Resultado datos) {
        if (!storage.containsKey(id)) return Optional.empty();
        Resultado existente = storage.get(id);
        existente.setPaciente(datos.getPaciente());
        existente.setTipoExamen(datos.getTipoExamen());
        existente.setFechaExamen(datos.getFechaExamen());
        existente.setResultado(datos.getResultado());
        existente.setObservaciones(datos.getObservaciones());
        existente.setMedico(datos.getMedico());
        if (datos.getFechaResultado() != null && !datos.getFechaResultado().isEmpty()) {
            existente.setFechaResultado(datos.getFechaResultado());
        }
        return Optional.of(existente);
    }

    public boolean eliminar(Long id) {
        return storage.remove(id) != null;
    }

    public List<Resultado> buscarPorTipoExamen(String tipoExamen) {
        return storage.values().stream()
                .filter(r -> r.getTipoExamen().toLowerCase().contains(tipoExamen.toLowerCase()))
                .collect(Collectors.toList());
    }
}

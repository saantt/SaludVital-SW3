package co.edu.uniquindio.software.actividad.saludvital.service;

import co.edu.uniquindio.software.actividad.saludvital.model.Cita;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CitaService {

    private final Map<Long, Cita> storage = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Cita> listar() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Cita> obtenerPorId(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Cita crear(Cita cita) {
        long id = idCounter.getAndIncrement();
        cita.setId(id);
        storage.put(id, cita);
        return cita;
    }

    public Optional<Cita> actualizar(Long id, Cita datos) {
        if (!storage.containsKey(id)) return Optional.empty();
        Cita existente = storage.get(id);
        existente.setPaciente(datos.getPaciente());
        existente.setFecha(datos.getFecha());
        existente.setMotivo(datos.getMotivo());
        return Optional.of(existente);
    }

    public boolean eliminar(Long id) {
        return storage.remove(id) != null;
    }
}

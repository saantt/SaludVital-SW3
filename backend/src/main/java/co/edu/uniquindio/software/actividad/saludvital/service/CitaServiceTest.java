/* package co.edu.uniquindio.software.actividad.saludvital.service;

import co.edu.uniquindio.software.actividad.saludvital.model.Cita;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CitaServiceTest {

    @Test
    void crearYListar() {
        CitaService s = new CitaService();
        Cita c = new Cita(null, "Ana", "2025-10-05", "Consulta");
        Cita creada = s.crear(c);
        assertNotNull(creada.getId());
        assertEquals(1, s.listar().size());
    }

    @Test
    void actualizarYEliminar() {
        CitaService s = new CitaService();
        Cita c = s.crear(new Cita(null, "P", "2025-10-01", "M"));
        Long id = c.getId();
        s.actualizar(id, new Cita(null, "P2", "2025-10-02", "M2"));
        assertEquals("P2", s.obtenerPorId(id).get().getPaciente());
        assertTrue(s.eliminar(id));
        assertFalse(s.obtenerPorId(id).isPresent());
    }
} */

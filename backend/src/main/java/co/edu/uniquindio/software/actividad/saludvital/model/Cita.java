package co.edu.uniquindio.software.actividad.saludvital.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Cita {
    private Long id;

    @NotBlank(message = "Paciente es requerido")
    @Size(max = 100)
    private String paciente;

    @NotBlank(message = "Fecha es requerida")
    private String fecha;

    @Size(max = 300)
    private String motivo;

    public Cita() {}

    public Cita(Long id, String paciente, String fecha, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}

package co.edu.uniquindio.software.actividad.saludvital.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Resultado {
    private Long id;

    @NotBlank(message = "Paciente es requerido")
    @Size(max = 100)
    private String paciente;

    @NotBlank(message = "Tipo de examen es requerido")
    @Size(max = 100)
    private String tipoExamen;

    @NotBlank(message = "Fecha del examen es requerida")
    private String fechaExamen;

    @NotBlank(message = "Resultado es requerido")
    @Size(max = 1000)
    private String resultado;

    @Size(max = 500)
    private String observaciones;

    @NotBlank(message = "Médico es requerido")
    @Size(max = 100)
    private String medico;

    private String fechaResultado;

    public Resultado() {}

    public Resultado(Long id, String paciente, String tipoExamen, String fechaExamen, 
                    String resultado, String observaciones, String medico, String fechaResultado) {
        this.id = id;
        this.paciente = paciente;
        this.tipoExamen = tipoExamen;
        this.fechaExamen = fechaExamen;
        this.resultado = resultado;
        this.observaciones = observaciones;
        this.medico = medico;
        this.fechaResultado = fechaResultado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getTipoExamen() { return tipoExamen; }
    public void setTipoExamen(String tipoExamen) { this.tipoExamen = tipoExamen; }

    public String getFechaExamen() { return fechaExamen; }
    public void setFechaExamen(String fechaExamen) { this.fechaExamen = fechaExamen; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public String getFechaResultado() { return fechaResultado; }
    public void setFechaResultado(String fechaResultado) { this.fechaResultado = fechaResultado; }
}

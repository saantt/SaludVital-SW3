const API = "http://localhost:8080/api/citas";

async function listarCitas() {
  const res = await fetch(API);
  const data = await res.json();
  const lista = document.getElementById("lista");
  lista.innerHTML = "";
  data.forEach(c => {
    const li = document.createElement("li");

    const info = document.createElement("div");
    const title = document.createElement("div");
    title.textContent = c.paciente + " • " + c.fecha;
    title.style.fontWeight = '600';
    const meta = document.createElement("div");
    meta.textContent = c.motivo || "";
    meta.className = "meta";

    info.appendChild(title);
    info.appendChild(meta);

    const actions = document.createElement("div");

    const edit = document.createElement("button");
    edit.className = "icon-btn";
    edit.innerHTML = "✏️";
    edit.onclick = () => editarCita(c);

    const del = document.createElement("button");
    del.className = "icon-btn";
    del.innerHTML = "🗑️";
    del.onclick = () => eliminarCita(c.id);

    actions.appendChild(edit);
    actions.appendChild(del);

    li.appendChild(info);
    li.appendChild(actions);
    lista.appendChild(li);
  });
}

function limpiarForm() {
  document.getElementById("id").value = "";
  document.getElementById("paciente").value = "";
  document.getElementById("fecha").value = "";
  document.getElementById("motivo").value = "";
}

function editarCita(cita) {
  document.getElementById("id").value = cita.id;
  document.getElementById("paciente").value = cita.paciente;
  document.getElementById("fecha").value = cita.fecha;
  document.getElementById("motivo").value = cita.motivo || "";
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

async function guardarCita() {
  const id = document.getElementById("id").value;
  const paciente = document.getElementById("paciente").value.trim();
  const fecha = document.getElementById("fecha").value.trim();
  const motivo = document.getElementById("motivo").value.trim();

  if (!paciente || !fecha) {
    alert("Paciente y fecha son obligatorios.");
    return;
  }

  const body = { paciente, fecha, motivo };

  if (id) {
    await fetch(`${API}/${id}`, {
      method: "PUT",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify(body)
    });
  } else {
    await fetch(API, {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify(body)
    });
  }

  limpiarForm();
  listarCitas();
}

async function eliminarCita(id) {
  if (!confirm("¿Eliminar esta cita?")) return;
  await fetch(`${API}/${id}`, { method: "DELETE" });
  listarCitas();
}

document.addEventListener("DOMContentLoaded", () => {
  listarCitas();
});

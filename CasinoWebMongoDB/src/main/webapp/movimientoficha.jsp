<%@ page import="co.edu.unbosque.dao.MovimientoFichaDAO,co.edu.unbosque.model.MovimientoFichaDTO,java.util.*,java.time.LocalDateTime,co.edu.unbosque.dao.InventarioFichaDAO,co.edu.unbosque.model.InventarioFichaDTO,co.edu.unbosque.dao.EmpleadoDAO,co.edu.unbosque.model.EmpleadoDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Movimientos de Fichas</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f6fa;
            margin: 0;
            padding: 0;
        }
        header { background: linear-gradient(90deg, #222, #444); color: #fff; text-align: center; padding: 20px 0; }
        nav { background-color: #333; display: flex; justify-content: center; }
        nav ul { list-style: none; display: flex; margin: 0; padding: 10px 0; }
        nav ul li { margin: 0 15px; }
        nav ul li a { text-decoration: none; color: #eee; padding: 10px 15px; border-radius: 5px; transition: 0.3s; }
        nav ul li a:hover, nav ul li a.activo { background-color: #e67e22; color: #fff; }
        main { width: 90%; margin: 30px auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        form { display: flex; flex-wrap: wrap; gap: 10px; margin-bottom: 20px; }
        input[type="text"], input[type="number"], input[type="datetime-local"], select, textarea { 
            flex: 1; padding: 10px; border-radius: 5px; border: 1px solid #ccc; 
        }
        textarea { min-height: 60px; resize: vertical; }
        button { background-color: #27ae60; color: white; padding: 10px 20px; border-radius: 5px; border:none; cursor:pointer; }
        button:hover { background:#219150; }
        button:disabled { background-color: #95a5a6; cursor: not-allowed; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding:10px; text-align:center; }
        th { background:#34495e; color:white; }
        .acciones button { margin: 0 5px; background:#3498db; }
        .acciones button.eliminar { background:#e74c3c; }
        .exito { color:#27ae60; background:#d5f5e3; padding:8px; border:1px solid #27ae60; border-radius:5px; }
        .error { color:#e74c3c; background:#fadbd8; padding:8px; border:1px solid #e74c3c; border-radius:5px; }
        .advertencia { color:#e67e22; background:#fdebd0; padding:8px; border:1px solid #e67e22; border-radius:5px; }
        footer { background:#222; text-align:center; color:#aaa; padding:15px; margin-top:30px; }
        .form-group { display: flex; flex-direction: column; flex: 1; }
        .form-group label { margin-bottom: 5px; font-weight: bold; color: #555; }
        .ingreso { color: #27ae60; font-weight: bold; }
        .egreso { color: #e74c3c; font-weight: bold; }
        .radio-group { display: flex; gap: 15px; margin-top: 5px; }
        .radio-option { display: flex; align-items: center; gap: 5px; }
        .saldo-info { background: #e8f4fd; padding: 8px; border-radius: 5px; margin-top: 5px; font-size: 0.9em; }
        .saldo-actual { font-weight: bold; color: #2980b9; }
    </style>

    <script>
        let saldoActual = 0;
        
        function cargarMovimiento(id, idInventario, idEmpleado, cantidad, tipoMov, observacion) {
            document.getElementById("id").value = id;
            document.getElementById("idInventario").value = idInventario;
            document.getElementById("idEmpleado").value = idEmpleado;
            document.getElementById("cantidad").value = cantidad;
            document.getElementById("observacion").value = observacion;
            
            document.querySelectorAll('input[name="tipoMov"]').forEach(radio => {
                if (radio.value === tipoMov) {
                    radio.checked = true;
                }
            });
            
            actualizarInfoSaldo();
        }

        function actualizarInfoSaldo() {
            const idInventario = document.getElementById("idInventario").value;
            const tipoMov = document.querySelector('input[name="tipoMov"]:checked');
            const cantidad = parseInt(document.getElementById("cantidad").value) || 0;
            
            if (idInventario) {
                saldoActual = 1000;
                document.getElementById("saldoInfo").innerHTML = 
                    `Saldo actual: <span class="saldo-actual">${saldoActual} fichas</span>`;
                
                if (tipoMov && tipoMov.value === "EGRESO" && cantidad > saldoActual) {
                    document.getElementById("saldoInfo").innerHTML += 
                        ` <span style="color: #e74c3c;">❌ Saldo insuficiente</span>`;
                }
            } else {
                document.getElementById("saldoInfo").innerHTML = "Seleccione una bóveda";
            }
        }

        function validarCantidad() {
            const cantidad = parseInt(document.getElementById("cantidad").value);
            const tipoMov = document.querySelector('input[name="tipoMov"]:checked');
            
            if (cantidad < 1) {
                alert("La cantidad debe ser mayor a 0");
                document.getElementById("cantidad").value = 1;
                return false;
            }
            
            if (tipoMov && tipoMov.value === "EGRESO" && cantidad > saldoActual) {
                alert(`Saldo insuficiente. Saldo actual: ${saldoActual} fichas`);
                document.getElementById("cantidad").value = saldoActual;
                return false;
            }
            
            return true;
        }

        function limpiarFormulario() {
            document.getElementById("id").value = '';
            document.getElementById("idInventario").value = '';
            document.getElementById("idEmpleado").value = '';
            document.getElementById("cantidad").value = '';
            document.getElementById("observacion").value = '';
            document.querySelectorAll('input[name="tipoMov"]').forEach(radio => {
                radio.checked = false;
            });
            document.getElementById("saldoInfo").innerHTML = '';
        }

        document.addEventListener('DOMContentLoaded', function() {
            document.getElementById('idInventario').addEventListener('change', actualizarInfoSaldo);
            document.getElementById('cantidad').addEventListener('input', actualizarInfoSaldo);
            document.querySelectorAll('input[name="tipoMov"]').forEach(radio => {
                radio.addEventListener('change', actualizarInfoSaldo);
            });
        });
    </script>
</head>

<body>
<header>
    <h1>🔄 Panel de Movimientos de Fichas</h1>
</header>

<nav>
    <ul>
        <li><a href="index.jsp">Inicio</a></li>
        <li><a href="tipojuego.jsp">Tipos de Juego</a></li>
        <li><a href="empleado.jsp">Empleados</a></li>
        <li><a href="cliente.jsp">Clientes</a></li>
        <li><a href="apuesta.jsp">Apuestas</a></li>
        <li><a href="eventojuego.jsp">Eventos</a></li>
        <li><a href="inventarioficha.jsp">Inventario Fichas</a></li>
        <li><a href="movimientoficha.jsp" class="activo">Movimientos</a></li>
    </ul>
</nav>

<main>

<section>
<h2>Registrar / Actualizar Movimiento</h2>

<form method="post" action="movimientoficha.jsp" onsubmit="return validarCantidad()">
    <input type="hidden" id="id" name="id">
    
    <div class="form-group">
        <label for="idInventario">Bóveda de Inventario:</label>
        <select id="idInventario" name="idInventario" required>
            <option value="">Seleccionar Bóveda</option>
            <%
            InventarioFichaDAO inventarioDAO = new InventarioFichaDAO();
            ArrayList<InventarioFichaDTO> inventarios = (ArrayList<InventarioFichaDTO>) inventarioDAO.consultar();
            if (inventarios != null) {
                for (InventarioFichaDTO inventario : inventarios) {
            %>
            <option value="<%= inventario.getIdInventario() %>" data-saldo="<%= inventario.getSaldo() %>">
                <%= inventario.getNombreBoveda() %> (Saldo: <%= inventario.getSaldo() %> fichas)
            </option>
            <% } } %>
        </select>
        <div id="saldoInfo" class="saldo-info"></div>
    </div>

    <div class="form-group">
        <label for="idEmpleado">Empleado Responsable:</label>
        <select id="idEmpleado" name="idEmpleado" required>
            <option value="">Seleccionar Empleado</option>
            <%
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            ArrayList<EmpleadoDTO> empleados = (ArrayList<EmpleadoDTO>) empleadoDAO.consultar();
            if (empleados != null) {
                for (EmpleadoDTO empleado : empleados) {
            %>
            <option value="<%= empleado.getIdEmpleado() %>"><%= empleado.getNombre() %> - <%= empleado.getCargo() %></option>
            <% } } %>
        </select>
    </div>

    <div class="form-group">
        <label for="cantidad">Cantidad de Fichas:</label>
        <input type="number" id="cantidad" name="cantidad" min="1" placeholder="Cantidad" required>
    </div>

    <div class="form-group">
        <label>Tipo de Movimiento:</label>
        <div class="radio-group">
            <div class="radio-option">
                <input type="radio" id="ingreso" name="tipoMov" value="INGRESO" required>
                <label for="ingreso" style="color: #27ae60;">📥 INGRESO</label>
            </div>
            <div class="radio-option">
                <input type="radio" id="egreso" name="tipoMov" value="EGRESO" required>
                <label for="egreso" style="color: #e74c3c;">📤 EGRESO</label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="observacion">Observación:</label>
        <textarea id="observacion" name="observacion" placeholder="Descripción del movimiento (opcional)"></textarea>
    </div>

    <div style="flex-basis: 100%; display: flex; gap: 10px; margin-top: 15px;">
        <button type="submit" name="accion" value="agregar">📥 Registrar Movimiento</button>
        <button type="submit" name="accion" value="actualizar">✏️ Actualizar Movimiento</button>
        <button type="button" onclick="limpiarFormulario()">🔄 Limpiar</button>
        <button type="button" onclick="validarCantidad()">✅ Validar</button>
    </div>
</form>

<%
String accion = request.getParameter("accion");
if (accion != null) {
    MovimientoFichaDAO dao = new MovimientoFichaDAO();

    if (accion.equals("agregar")) {
        try {
            MovimientoFichaDTO movimiento = new MovimientoFichaDTO();
            movimiento.setIdInventario(request.getParameter("idInventario"));
            movimiento.setIdEmpleado(request.getParameter("idEmpleado"));
            movimiento.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            movimiento.setTipoMov(request.getParameter("tipoMov"));
            movimiento.setObservacion(request.getParameter("observacion"));
            movimiento.setFechaHora(LocalDateTime.now());
            
            String resultado = dao.agregar(movimiento);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">✅ Movimiento registrado correctamente y saldo actualizado en el inventario.</p>
<%
            } else {
                throw new Exception(resultado);
            }
        } catch (Exception e) {
            String mensajeError = e.getMessage();
            if (mensajeError.contains("Saldo insuficiente")) {
%>
<p class="error">❌ Error: Saldo insuficiente en la bóveda seleccionada.</p>
<%
            } else if (mensajeError.contains("ERROR")) {
%>
<p class="error">❌ <%= mensajeError %></p>
<%
            } else {
%>
<p class="error">❌ Error al registrar movimiento: <%= mensajeError %></p>
<%
            }
        }
    }

    if (accion.equals("actualizar")) {
        try {
            MovimientoFichaDTO movimiento = new MovimientoFichaDTO();
            String id = request.getParameter("id");
            movimiento.setIdInventario(request.getParameter("idInventario"));
            movimiento.setIdEmpleado(request.getParameter("idEmpleado"));
            movimiento.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            movimiento.setTipoMov(request.getParameter("tipoMov"));
            movimiento.setObservacion(request.getParameter("observacion"));
            
            String resultado = dao.actualizar(id, movimiento);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">🔁 Movimiento actualizado correctamente y saldo ajustado en el inventario.</p>
<%
            } else {
                throw new Exception(resultado);
            }
        } catch (Exception e) {
            String mensajeError = e.getMessage();
%>
<p class="error">❌ Error al actualizar movimiento: <%= mensajeError %></p>
<%
        }
    }

    if (accion.equals("eliminar")) {
        try {
            String id = request.getParameter("id");
            String resultado = dao.eliminar(id);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">🗑 Movimiento eliminado correctamente y saldo revertido en el inventario.</p>
<%
            } else {
                throw new Exception(resultado);
            }
        } catch (Exception e) {
            String mensajeError = e.getMessage();
%>
<p class="error">❌ Error al eliminar movimiento: <%= mensajeError %></p>
<%
        }
    }
} %>
</section>

<section>
<h2>Historial de Movimientos</h2>

<%
MovimientoFichaDAO dao = new MovimientoFichaDAO();
ArrayList<MovimientoFichaDTO> lista = (ArrayList<MovimientoFichaDTO>) dao.consultar();

int totalIngresos = 0;
int totalEgresos = 0;
if (lista != null && !lista.isEmpty()) {
    for (MovimientoFichaDTO m : lista) {
        if ("INGRESO".equals(m.getTipoMov())) {
            totalIngresos += m.getCantidad();
        } else {
            totalEgresos += m.getCantidad();
        }
    }
}
%>

<div style="display: flex; gap: 15px; margin-bottom: 20px;">
    <div style="flex: 1; background: #d5f5e3; padding: 15px; border-radius: 5px; text-align: center;">
        <h3 style="margin: 0; color: #27ae60;">📥 Total Ingresos</h3>
        <p style="font-size: 1.5em; font-weight: bold; margin: 5px 0;"><%= totalIngresos %> fichas</p>
    </div>
    <div style="flex: 1; background: #fadbd8; padding: 15px; border-radius: 5px; text-align: center;">
        <h3 style="margin: 0; color: #e74c3c;">📤 Total Egresos</h3>
        <p style="font-size: 1.5em; font-weight: bold; margin: 5px 0;"><%= totalEgresos %> fichas</p>
    </div>
    <div style="flex: 1; background: #e8f4fd; padding: 15px; border-radius: 5px; text-align: center;">
        <h3 style="margin: 0; color: #2980b9;">⚖️ Diferencia</h3>
        <p style="font-size: 1.5em; font-weight: bold; margin: 5px 0; color: <%= (totalIngresos - totalEgresos) >= 0 ? "#27ae60" : "#e74c3c" %>;">
            <%= totalIngresos - totalEgresos %> fichas
        </p>
    </div>
</div>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Bóveda</th>
    <th>Empleado</th>
    <th>Cantidad</th>
    <th>Tipo</th>
    <th>Observación</th>
    <th>Fecha/Hora</th>
    <th>Acciones</th>
</tr>
</thead>

<tbody>
<%
if (lista != null && !lista.isEmpty()) {
    for (MovimientoFichaDTO m : lista) {
        String nombreInventario = "N/A";
        String nombreEmpleado = "N/A";
        
        try {
            InventarioFichaDTO inventario = inventarioDAO.consultarPorId(m.getIdInventario());
            if (inventario != null) nombreInventario = inventario.getNombreBoveda();
        } catch (Exception e) {}
        
        try {
            EmpleadoDTO empleado = empleadoDAO.consultarPorId(m.getIdEmpleado());
            if (empleado != null) nombreEmpleado = empleado.getNombre();
        } catch (Exception e) {}
%>
<tr>
    <td><%= m.getIdMov() %></td>
    <td><%= nombreInventario %></td>
    <td><%= nombreEmpleado %></td>
    <td><strong><%= m.getCantidad() %> fichas</strong></td>
    <td class="<%= m.getTipoMov().equals("INGRESO") ? "ingreso" : "egreso" %>">
        <%= m.getTipoMov() %>
    </td>
    <td><%= m.getObservacion() != null ? m.getObservacion() : "<em>Sin observación</em>" %></td>
    <td><%= m.getFechaHora() != null ? m.getFechaHora().toString().replace("T", " ") : "N/A" %></td>

    <td class="acciones">
        <form method="post" action="movimientoficha.jsp" style="display:inline;">
            <input type="hidden" name="id" value="<%= m.getIdMov() %>">
            <button type="submit" name="accion" value="eliminar" class="eliminar" 
                    onclick="return confirm('¿Está seguro de eliminar este movimiento? Se revertirá el saldo en el inventario.')">
                🗑 Eliminar
            </button>
        </form>

        <button type="button" onclick="cargarMovimiento(
            '<%= m.getIdMov() %>',
            '<%= m.getIdInventario() %>',
            '<%= m.getIdEmpleado() %>',
            '<%= m.getCantidad() %>',
            '<%= m.getTipoMov() %>',
            '<%= m.getObservacion() != null ? m.getObservacion().replace("\"", "&quot;") : "" %>'
        )">✏️ Actualizar</button>
    </td>
</tr>
<% } } else { %>
<tr>
    <td colspan="8" style="text-align: center; padding: 20px;">
        <p style="color: #7f8c8d; font-style: italic;">No hay movimientos registrados.</p>
    </td>
</tr>
<% } %>
</tbody>
</table>
</section>

</main>

<footer>
<p>© 2025 Casino El Bosque | Gestión de Movimientos de Fichas</p>
</footer>

</body>
</html>
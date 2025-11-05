<%@ page import="co.edu.unbosque.dao.ApuestaDAO,co.edu.unbosque.model.ApuestaDTO,java.util.*,java.time.LocalDateTime,co.edu.unbosque.dao.ClienteDAO,co.edu.unbosque.model.ClienteDTO,co.edu.unbosque.dao.EventoJuegoDAO,co.edu.unbosque.model.EventoJuegoDTO,co.edu.unbosque.dao.EmpleadoDAO,co.edu.unbosque.model.EmpleadoDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Apuestas</title>
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
        input[type="text"], input[type="number"], input[type="datetime-local"], select { 
            flex: 1; padding: 10px; border-radius: 5px; border: 1px solid #ccc; 
        }
        button { background-color: #27ae60; color: white; padding: 10px 20px; border-radius: 5px; border:none; cursor:pointer; }
        button:hover { background:#219150; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding:10px; text-align:center; }
        th { background:#34495e; color:white; }
        .acciones button { margin: 0 5px; background:#3498db; }
        .acciones button.eliminar { background:#e74c3c; }
        .exito { color:#27ae60; background:#d5f5e3; padding:8px; border:1px solid #27ae60; border-radius:5px; }
        .error { color:#e74c3c; background:#fadbd8; padding:8px; border:1px solid #e74c3c; border-radius:5px; }
        footer { background:#222; text-align:center; color:#aaa; padding:15px; margin-top:30px; }
        .form-group { display: flex; flex-direction: column; flex: 1; }
        .form-group label { margin-bottom: 5px; font-weight: bold; color: #555; }
        .ganancia { color: #27ae60; font-weight: bold; }
        .perdida { color: #e74c3c; font-weight: bold; }
    </style>

    <script>
        function cargarApuesta(id, idCliente, idEvento, idEmpleado, monto, resultado, gananciaPerdida, estado) {
            document.getElementById("id").value = id;
            document.getElementById("idCliente").value = idCliente;
            document.getElementById("idEvento").value = idEvento;
            document.getElementById("idEmpleado").value = idEmpleado;
            document.getElementById("monto").value = monto;
            document.getElementById("resultado").value = resultado;
            document.getElementById("gananciaPerdida").value = gananciaPerdida;
            document.getElementById("estado").value = estado;
        }

        function calcularGananciaPerdida() {
            const monto = parseFloat(document.getElementById("monto").value) || 0;
            const resultado = document.getElementById("resultado").value;
            let gananciaPerdida = 0;

            if (resultado === "GANADO") {
                gananciaPerdida = monto * 2;
            } else if (resultado === "PERDIDO") {
                gananciaPerdida = -monto;
            } else if (resultado === "EMPATE") {
                gananciaPerdida = 0;
            }

            document.getElementById("gananciaPerdida").value = gananciaPerdida;
        }
    </script>
</head>

<body>
<header>
    <h1>🎰 Panel de Administración - Apuestas</h1>
</header>

<nav>
    <ul>
        <li><a href="index.jsp">Inicio</a></li>
        <li><a href="tipojuego.jsp">Tipos de Juego</a></li>
        <li><a href="empleado.jsp">Empleados</a></li>
        <li><a href="cliente.jsp">Clientes</a></li>
        <li><a href="apuesta.jsp" class="activo">Apuestas</a></li>
        <li><a href="eventojuego.jsp">Eventos</a></li>
        <li><a href="inventarioficha.jsp" >Inventario Fichas</a></li>
    </ul>
</nav>

<main>

<section>
<h2>Registrar / Actualizar Apuesta</h2>

<form method="post" action="apuesta.jsp">
    <input type="hidden" id="id" name="id">
    
    <div class="form-group">
        <label for="idCliente">Cliente:</label>
        <select id="idCliente" name="idCliente" required>
            <option value="">Seleccionar Cliente</option>
            <%
            ClienteDAO clienteDAO = new ClienteDAO();
            ArrayList<ClienteDTO> clientes = (ArrayList<ClienteDTO>) clienteDAO.consultar();
            if (clientes != null) {
                for (ClienteDTO cliente : clientes) {
            %>
            <option value="<%= cliente.getIdCliente() %>"><%= cliente.getNombre() %> (ID: <%= cliente.getIdCliente() %>)</option>
            <% } } %>
        </select>
    </div>

    <div class="form-group">
        <label for="idEvento">Evento de Juego:</label>
        <select id="idEvento" name="idEvento" required>
            <option value="">Seleccionar Evento</option>
            <%
            EventoJuegoDAO eventoDAO = new EventoJuegoDAO();
            ArrayList<EventoJuegoDTO> eventos = (ArrayList<EventoJuegoDTO>) eventoDAO.consultar();
            if (eventos != null) {
                for (EventoJuegoDTO evento : eventos) {
            %>
            <option value="<%= evento.getIdEvento() %>">Evento <%= evento.getIdEvento() %> - <%= evento.getEstado() %></option>
            <% } } %>
        </select>
    </div>

    <div class="form-group">
        <label for="idEmpleado">Empleado:</label>
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
        <label for="monto">Monto de Apuesta:</label>
        <input type="number" id="monto" name="monto" step="0.01" min="0" placeholder="0.00" required onchange="calcularGananciaPerdida()">
    </div>

    <div class="form-group">
        <label for="resultado">Resultado:</label>
        <select id="resultado" name="resultado" required onchange="calcularGananciaPerdida()">
            <option value="">Seleccionar Resultado</option>
            <option value="GANADO">GANADO</option>
            <option value="PERDIDO">PERDIDO</option>
            <option value="EMPATE">EMPATE</option>
            <option value="PENDIENTE">PENDIENTE</option>
        </select>
    </div>

    <div class="form-group">
        <label for="gananciaPerdida">Ganancia/Pérdida:</label>
        <input type="number" id="gananciaPerdida" name="gananciaPerdida" step="0.01" placeholder="0.00" readonly>
    </div>

    <div class="form-group">
        <label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
            <option value="">Seleccionar Estado</option>
            <option value="ACTIVA">ACTIVA</option>
            <option value="FINALIZADA">FINALIZADA</option>
            <option value="CANCELADA">CANCELADA</option>
        </select>
    </div>

    <div style="flex-basis: 100%; display: flex; gap: 10px; margin-top: 15px;">
        <button type="submit" name="accion" value="agregar">Agregar Apuesta</button>
        <button type="submit" name="accion" value="actualizar">Actualizar Apuesta</button>
        <button type="button" onclick="calcularGananciaPerdida()">Calcular Ganancia/Pérdida</button>
    </div>
</form>

<%
String accion = request.getParameter("accion");
if (accion != null) {
    ApuestaDAO dao = new ApuestaDAO();

    if (accion.equals("agregar")) {
        try {
            ApuestaDTO a = new ApuestaDTO();
            a.setIdCliente(request.getParameter("idCliente"));
            a.setIdEvento(request.getParameter("idEvento"));
            a.setIdEmpleado(request.getParameter("idEmpleado"));
            a.setMonto(Double.parseDouble(request.getParameter("monto")));
            a.setResultado(request.getParameter("resultado"));
            a.setGananciaPerdida(Double.parseDouble(request.getParameter("gananciaPerdida")));
            a.setEstado(request.getParameter("estado"));
            a.setFechaHora(LocalDateTime.now());
            
            dao.agregar(a);
%>
<p class="exito">✅ Apuesta registrada correctamente.</p>
<%
        } catch (Exception e) {
%>
<p class="error">❌ Error al registrar apuesta: <%= e.getMessage() %></p>
<%
        }
    }

    if (accion.equals("actualizar")) {
        try {
            ApuestaDTO a = new ApuestaDTO();
            String id = request.getParameter("id");
            a.setIdCliente(request.getParameter("idCliente"));
            a.setIdEvento(request.getParameter("idEvento"));
            a.setIdEmpleado(request.getParameter("idEmpleado"));
            a.setMonto(Double.parseDouble(request.getParameter("monto")));
            a.setResultado(request.getParameter("resultado"));
            a.setGananciaPerdida(Double.parseDouble(request.getParameter("gananciaPerdida")));
            a.setEstado(request.getParameter("estado"));
            
            dao.actualizar(id, a);
%>
<p class="exito">🔁 Apuesta actualizada correctamente.</p>
<%
        } catch (Exception e) {
%>
<p class="error">❌ Error al actualizar apuesta: <%= e.getMessage() %></p>
<%
        }
    }

    if (accion.equals("eliminar")) {
        try {
            String id = request.getParameter("id");
            dao.eliminar(id);
%>
<p class="exito">🗑 Apuesta eliminada correctamente.</p>
<%
        } catch (Exception e) {
%>
<p class="error">❌ Error al eliminar apuesta: <%= e.getMessage() %></p>
<%
        }
    }
} %>
</section>

<section>
<h2>Listado de Apuestas</h2>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Cliente</th>
    <th>Evento</th>
    <th>Empleado</th>
    <th>Monto</th>
    <th>Resultado</th>
    <th>Ganancia/Pérdida</th>
    <th>Estado</th>
    <th>Fecha/Hora</th>
    <th>Acciones</th>
</tr>
</thead>

<tbody>
<%
ApuestaDAO dao = new ApuestaDAO();
ArrayList<ApuestaDTO> lista = (ArrayList<ApuestaDTO>) dao.consultar();

if (lista != null && !lista.isEmpty()) {
    for (ApuestaDTO a : lista) {
        String nombreCliente = "N/A";
        String nombreEmpleado = "N/A";
        
        try {
            ClienteDTO cliente = clienteDAO.consultarPorId(a.getIdCliente());
            if (cliente != null) nombreCliente = cliente.getNombre();
        } catch (Exception e) {}
        
        try {
            EmpleadoDTO empleado = empleadoDAO.consultarPorId(a.getIdEmpleado());
            if (empleado != null) nombreEmpleado = empleado.getNombre();
        } catch (Exception e) {}
%>
<tr>
    <td><%= a.getIdApuesta() %></td>
    <td><%= nombreCliente %></td>
    <td>Evento <%= a.getIdEvento() %></td>
    <td><%= nombreEmpleado %></td>
    <td>$<%= String.format("%.2f", a.getMonto()) %></td>
    <td><%= a.getResultado() %></td>
    <td class="<%= a.getGananciaPerdida() >= 0 ? "ganancia" : "perdida" %>">
        $<%= String.format("%.2f", a.getGananciaPerdida()) %>
    </td>
    <td><%= a.getEstado() %></td>
    <td><%= a.getFechaHora() != null ? a.getFechaHora().toString().replace("T", " ") : "N/A" %></td>

    <td class="acciones">
        <form method="post" action="apuesta.jsp" style="display:inline;">
            <input type="hidden" name="id" value="<%= a.getIdApuesta() %>">
            <button type="submit" name="accion" value="eliminar" class="eliminar">Eliminar</button>
        </form>

        <button type="button" onclick="cargarApuesta(
            '<%= a.getIdApuesta() %>',
            '<%= a.getIdCliente() %>',
            '<%= a.getIdEvento() %>',
            '<%= a.getIdEmpleado() %>',
            '<%= a.getMonto() %>',
            '<%= a.getResultado() %>',
            '<%= a.getGananciaPerdida() %>',
            '<%= a.getEstado() %>'
        )">Actualizar</button>
    </td>
</tr>
<% } } else { %>
<tr><td colspan="10">No hay apuestas registradas.</td></tr>
<% } %>
</tbody>
</table>
</section>

</main>

<footer>
<p>© 2025 Casino El Bosque | Administración de Apuestas</p>
</footer>

</body>
</html>
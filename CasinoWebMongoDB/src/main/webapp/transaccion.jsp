<%@ page import="co.edu.unbosque.dao.TransaccionDAO,co.edu.unbosque.model.TransaccionDTO,java.util.*,java.time.LocalDateTime,java.math.BigDecimal,co.edu.unbosque.dao.EmpleadoDAO,co.edu.unbosque.model.EmpleadoDTO,co.edu.unbosque.dao.MetodoPagoDAO,co.edu.unbosque.model.MetodoPagoDTO,co.edu.unbosque.dao.ClienteDAO,co.edu.unbosque.model.ClienteDTO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Transacciones - Casino El Bosque</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f5f6fa; margin:0; padding:0; }
        header { background: linear-gradient(90deg,#222,#444); color:white; text-align:center; padding:20px 0; }
        nav { background:#333; display:flex; justify-content:center; }
        nav ul { list-style:none; display:flex; margin:0; padding:10px 0; }
        nav ul li { margin:0 15px; }
        nav ul li a { text-decoration:none; color:#eee; padding:10px 15px; border-radius:5px; transition:0.3s; }
        nav ul li a:hover, nav ul li a.activo { background:#e67e22; color:white; }
        main { width:95%; margin:30px auto; background:white; padding:30px; border-radius:10px; box-shadow:0 2px 8px rgba(0,0,0,0.1); }
        form { display:flex; flex-wrap:wrap; gap:10px; margin-bottom:20px; }
        input, select, textarea { flex:1; padding:10px; border-radius:5px; border:1px solid #ccc; }
        textarea { min-height:60px; resize:vertical; }
        button { background:#27ae60; color:white; border:none; padding:10px 20px; border-radius:5px; cursor:pointer; transition:0.3s; }
        button:hover { background:#219150; }
        table { width:100%; border-collapse:collapse; margin-top:15px; }
        th, td { border:1px solid #ddd; padding:10px; text-align:center; }
        th { background:#34495e; color:white; }
        .acciones button { margin:0 3px; background:#3498db; }
        .acciones button.eliminar { background:#e74c3c; }
        .exito { color:#27ae60; background:#d5f5e3; padding:8px; border:1px solid #27ae60; border-radius:5px; }
        .error { color:#e74c3c; background:#fadbd8; padding:8px; border:1px solid #e74c3c; border-radius:5px; }
        footer { background:#222; text-align:center; color:#aaa; padding:15px; margin-top:30px; }
    </style>
</head>

<body>
<header><h1>💰 Gestión de Transacciones - Casino El Bosque</h1></header>

<nav>
<ul>
    <li><a href="dashboard.jsp">🏠 Dashboard</a></li>
    <li><a href="cliente.jsp">👥 Clientes</a></li>
    <li><a href="empleado.jsp">💼 Empleados</a></li>
    <li><a href="apuesta.jsp">🎰 Apuestas</a></li>
    <li><a href="torneo.jsp">🏆 Torneos</a></li>
    <li><a href="transaccion.jsp" class="activo">💰 Transacciones</a></li>
    <li><a href="inventarioficha.jsp">🪙 Fichas</a></li>
</ul>
</nav>

<main>
<h2>Registrar / Actualizar Transacción</h2>

<form method="post" action="transaccion.jsp">
    <input type="hidden" name="idTransaccion" id="idTransaccion">

    <select name="idEmpleado" required>
        <option value="">Seleccionar Empleado</option>
        <%
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        ArrayList<EmpleadoDTO> empleados = (ArrayList<EmpleadoDTO>) empleadoDAO.consultar();
        for (EmpleadoDTO e : empleados) {
        %>
            <option value="<%= e.getIdEmpleado() %>"><%= e.getNombre() %> - <%= e.getCargo() %></option>
        <% } %>
    </select>

    <select name="idMetodo" required>
        <option value="">Seleccionar Método de Pago</option>
        <%
        MetodoPagoDAO metodoDAO = new MetodoPagoDAO();
        ArrayList<MetodoPagoDTO> metodos = (ArrayList<MetodoPagoDTO>) metodoDAO.consultar();
        for (MetodoPagoDTO m : metodos) {
        %>
            <option value="<%= m.getIdMetodo() %>"><%= m.getNombre() %></option>
        <% } %>
    </select>

    <select name="idCliente" required>
        <option value="">Seleccionar Cliente</option>
        <%
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<ClienteDTO> clientes = (ArrayList<ClienteDTO>) clienteDAO.consultar();
        for (ClienteDTO c : clientes) {
        %>
            <option value="<%= c.getIdCliente() %>"><%= c.getNombre() %></option>
        <% } %>
    </select>

    <input type="datetime-local" name="fechaHora" value="<%= java.time.LocalDateTime.now().toString().substring(0,16) %>" required>
    <input type="number" name="monto" step="0.01" placeholder="Monto $" required>

    <select name="tipo" required>
        <option value="">Seleccionar Tipo</option>
        <option value="DEPOSITO">📥 Depósito</option>
        <option value="RETIRO">📤 Retiro</option>
        <option value="PAGO">💳 Pago</option>
        <option value="COMPRA">🛒 Compra</option>
        <option value="VENTA">💰 Venta</option>
        <option value="COMISION">📋 Comisión</option>
        <option value="OTRO">🔧 Otro</option>
    </select>

    <textarea name="observacion" placeholder="Observaciones (opcional)"></textarea>

    <div style="flex-basis:100%;display:flex;gap:10px;margin-top:10px;">
        <button type="submit" name="accion" value="agregar">➕ Registrar</button>
        <button type="submit" name="accion" value="actualizar">✏️ Actualizar</button>
        <button type="submit" name="accion" value="eliminar" class="eliminar">🗑️ Eliminar</button>
    </div>
</form>

<%
TransaccionDAO dao = new TransaccionDAO();
String accion = request.getParameter("accion");
if (accion != null) {
    try {
        if (accion.equals("agregar")) {
            TransaccionDTO t = new TransaccionDTO();
            t.setIdEmpleado(request.getParameter("idEmpleado"));
            t.setIdMetodo(request.getParameter("idMetodo"));
            t.setIdCliente(request.getParameter("idCliente"));
            t.setFechaHora(LocalDateTime.parse(request.getParameter("fechaHora").replace(" ", "T")));
            t.setMonto(new BigDecimal(request.getParameter("monto")));
            t.setTipo(request.getParameter("tipo"));
            t.setObservacion(request.getParameter("observacion"));
            String res = dao.agregar(t);
            if (res.startsWith("2")) out.println("<p class='exito'>✅ Transacción registrada correctamente.</p>");
            else out.println("<p class='error'>❌ Error al registrar.</p>");
        } else if (accion.equals("actualizar")) {
            String id = request.getParameter("idTransaccion");
            TransaccionDTO t = new TransaccionDTO();
            t.setIdEmpleado(request.getParameter("idEmpleado"));
            t.setIdMetodo(request.getParameter("idMetodo"));
            t.setIdCliente(request.getParameter("idCliente"));
            t.setFechaHora(LocalDateTime.parse(request.getParameter("fechaHora").replace(" ", "T")));
            t.setMonto(new BigDecimal(request.getParameter("monto")));
            t.setTipo(request.getParameter("tipo"));
            t.setObservacion(request.getParameter("observacion"));
            String res = dao.actualizar(id, t);
            if (res.startsWith("2")) out.println("<p class='exito'>🔁 Transacción actualizada correctamente.</p>");
            else out.println("<p class='error'>❌ Error al actualizar.</p>");
        } else if (accion.equals("eliminar")) {
            String id = request.getParameter("idTransaccion");
            String res = dao.eliminar(id);
            if (res.startsWith("2")) out.println("<p class='exito'>🗑️ Transacción eliminada correctamente.</p>");
            else out.println("<p class='error'>❌ Error al eliminar.</p>");
        }
    } catch (Exception e) {
        out.println("<p class='error'>❌ Error: " + e.getMessage() + "</p>");
    }
}
%>

<h2>Historial de Transacciones</h2>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Empleado</th>
            <th>Método</th>
            <th>Cliente</th>
            <th>Fecha</th>
            <th>Monto</th>
            <th>Tipo</th>
            <th>Observación</th>
        </tr>
    </thead>
    <tbody>
        <%
        ArrayList<TransaccionDTO> transacciones = (ArrayList<TransaccionDTO>) dao.consultar();
        for (TransaccionDTO t : transacciones) {
            EmpleadoDTO emp = empleadoDAO.consultarPorId(t.getIdEmpleado());
            MetodoPagoDTO met = metodoDAO.consultarPorId(t.getIdMetodo());
            ClienteDTO cli = clienteDAO.consultarPorId(t.getIdCliente());
        %>
        <tr>
            <td><%= t.getIdTransaccion() %></td>
            <td><%= emp != null ? emp.getNombre() : "N/A" %></td>
            <td><%= met != null ? met.getNombre() : "N/A" %></td>
            <td><%= cli != null ? cli.getNombre() : "N/A" %></td>
            <td><%= t.getFechaHora() %></td>
            <td>$<%= t.getMonto() %></td>
            <td><%= t.getTipo() %></td>
            <td><%= t.getObservacion() %></td>
        </tr>
        <% } %>
    </tbody>
</table>

<p><strong><%= dao.obtenerEstadisticas() %></strong></p>

</main>
<footer><p>© 2025 Casino El Bosque | Gestión de Transacciones</p></footer>
</body>
</html>
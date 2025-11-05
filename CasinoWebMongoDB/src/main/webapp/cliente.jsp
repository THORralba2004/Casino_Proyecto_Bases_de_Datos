<%@ page import="co.edu.unbosque.dao.ClienteDAO,co.edu.unbosque.model.ClienteDTO,java.util.*,java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Clientes - Casino El Bosque</title>
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
        main { width: 80%; margin: 30px auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        form { display: flex; flex-wrap: wrap; gap: 10px; margin-bottom: 20px; }
        input[type="text"], input[type="date"], input[type="number"], input[type="email"] { 
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
    </style>

    <script>
        function cargarCliente(id, nombre, documento, correo, telefono, fecha) {
            document.getElementById("id").value = id;
            document.getElementById("nombre").value = nombre;
            document.getElementById("documento").value = documento;
            document.getElementById("correo").value = correo;
            document.getElementById("telefono").value = telefono;
            document.getElementById("fechaRegistro").value = fecha;
        }
    </script>
</head>

<body>
<header>
    <h1>👥 Gestión de Clientes - Casino El Bosque</h1>
</header>

<nav>
    <ul>
        <li><a href="dashboard.jsp">🏠 Dashboard</a></li>
        <li><a href="cliente.jsp" class="activo">👥 Clientes</a></li>
        <li><a href="empleado.jsp">💼 Empleados</a></li>
        <li><a href="apuesta.jsp">🎰 Apuestas</a></li>
        <li><a href="torneo.jsp">🏆 Torneos</a></li>
        <li><a href="inscripcionTorneo.jsp">📝 Inscripciones</a></li>
    </ul>
</nav>

<main>

<section>
<h2>Registrar / Actualizar Cliente</h2>

<form method="post" action="cliente.jsp">
    <input type="text" id="id" name="id" placeholder="ID (solo para actualizar)">
    <input type="text" id="nombre" name="nombre" placeholder="Nombre completo" required>
    <input type="text" id="documento" name="documento" placeholder="Número de documento" required>
    <input type="email" id="correo" name="correo" placeholder="Correo electrónico" required>
    <input type="text" id="telefono" name="telefono" placeholder="Teléfono" required>
    <input type="date" id="fechaRegistro" name="fechaRegistro" required>

    <button type="submit" name="accion" value="agregar">➕ Agregar Cliente</button>
    <button type="submit" name="accion" value="actualizar">✏️ Actualizar Cliente</button>
</form>

<%
String accion = request.getParameter("accion");
if (accion != null) {
    ClienteDAO dao = new ClienteDAO();

    if (accion.equals("agregar")) {
        ClienteDTO c = new ClienteDTO();
        c.setNombre(request.getParameter("nombre"));
        c.setDocumento(request.getParameter("documento"));
        c.setCorreo(request.getParameter("correo"));
        c.setTelefono(request.getParameter("telefono"));
        c.setFechaRegistro(LocalDate.parse(request.getParameter("fechaRegistro")));
        dao.agregar(c);
%>
<p class="exito">✅ Cliente agregado correctamente.</p>
<%
    }

    if (accion.equals("actualizar")) {
        ClienteDTO c = new ClienteDTO();
        String id = request.getParameter("id");
        c.setNombre(request.getParameter("nombre"));
        c.setDocumento(request.getParameter("documento"));
        c.setCorreo(request.getParameter("correo"));
        c.setTelefono(request.getParameter("telefono"));
        c.setFechaRegistro(LocalDate.parse(request.getParameter("fechaRegistro")));
        dao.actualizar(id, c);
%>
<p class="exito">🔁 Cliente actualizado correctamente.</p>
<%
    }

    if (accion.equals("eliminar")) {
        String id = request.getParameter("id");
        dao.eliminar(id);
%>
<p class="exito">🗑️ Cliente eliminado correctamente.</p>
<%
    }
} %>
</section>

<section>
<h2>Listado de Clientes</h2>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Documento</th>
    <th>Correo</th>
    <th>Teléfono</th>
    <th>Fecha Registro</th>
    <th>Acciones</th>
</tr>
</thead>

<tbody>
<%
ClienteDAO dao = new ClienteDAO();
ArrayList<ClienteDTO> lista = (ArrayList<ClienteDTO>) dao.consultar();

if (lista != null && !lista.isEmpty()) {
    for (ClienteDTO c : lista) {
%>
<tr>
<td><%= c.getIdCliente() %></td>
<td><%= c.getNombre() %></td>
<td><%= c.getDocumento() %></td>
<td><%= c.getCorreo() %></td>
<td><%= c.getTelefono() %></td>
<td><%= c.getFechaRegistro() %></td>

<td class="acciones">

<form method="post" action="cliente.jsp" style="display:inline;">
<input type="hidden" name="id" value="<%= c.getIdCliente() %>">
<button type="submit" name="accion" value="eliminar" class="eliminar">🗑️ Eliminar</button>
</form>

<button type="button" onclick="cargarCliente(
'<%= c.getIdCliente() %>',
'<%= c.getNombre() %>',
'<%= c.getDocumento() %>',
'<%= c.getCorreo() %>',
'<%= c.getTelefono() %>',
'<%= c.getFechaRegistro() %>'
)">✏️ Actualizar</button>

</td>
</tr>
<% } } else { %>
<tr><td colspan="7">No hay clientes registrados.</td></tr>
<% } %>
</tbody>
</table>
</section>

</main>

<footer>
<p>© 2025 Casino El Bosque | Gestión de Clientes</p>
</footer>

</body>
</html>
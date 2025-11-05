<%@ page import="co.edu.unbosque.dao.InventarioFichaDAO,co.edu.unbosque.model.InventarioFichaDTO,java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Inventario de Fichas</title>
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
        input[type="text"], input[type="number"], input[type="email"], select { 
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
        .saldo-positivo { color: #27ae60; font-weight: bold; }
        .saldo-bajo { color: #e67e22; font-weight: bold; }
        .saldo-critico { color: #e74c3c; font-weight: bold; }
        .form-group { display: flex; flex-direction: column; flex: 1; }
        .form-group label { margin-bottom: 5px; font-weight: bold; color: #555; }
    </style>

    <script>
        function cargarInventario(id, nombreBoveda, saldo) {
            document.getElementById("id").value = id;
            document.getElementById("nombreBoveda").value = nombreBoveda;
            document.getElementById("saldo").value = saldo;
        }

        function validarSaldo() {
            const saldo = document.getElementById("saldo").value;
            if (saldo < 0) {
                alert("El saldo no puede ser negativo");
                document.getElementById("saldo").value = 0;
            }
        }
    </script>
</head>

<body>
<header>
    <h1>💰 Panel de Administración - Inventario de Fichas</h1>
</header>

<nav>
    <ul>
        <li><a href="index.jsp">Inicio</a></li>
        <li><a href="tipojuego.jsp">Tipos de Juego</a></li>
        <li><a href="empleado.jsp">Empleados</a></li>
        <li><a href="cliente.jsp">Clientes</a></li>
        <li><a href="apuesta.jsp">Apuestas</a></li>
        <li><a href="eventojuego.jsp">Eventos</a></li>
        <li><a href="inventarioficha.jsp" class="activo">Inventario Fichas</a></li>
    </ul>
</nav>

<main>

<section>
<h2>Registrar / Actualizar Bóveda de Fichas</h2>

<form method="post" action="inventarioficha.jsp">
    <input type="hidden" id="id" name="id">
    
    <div class="form-group">
        <label for="nombreBoveda">Nombre de la Bóveda:</label>
        <input type="text" id="nombreBoveda" name="nombreBoveda" placeholder="Ej: Bóveda Principal, Caja Registradora" required>
    </div>

    <div class="form-group">
        <label for="saldo">Saldo de Fichas:</label>
        <input type="number" id="saldo" name="saldo" min="0" placeholder="Cantidad de fichas" required onchange="validarSaldo()">
    </div>

    <div style="flex-basis: 100%; display: flex; gap: 10px; margin-top: 15px;">
        <button type="submit" name="accion" value="agregar">Agregar Bóveda</button>
        <button type="submit" name="accion" value="actualizar">Actualizar Bóveda</button>
        <button type="button" onclick="document.getElementById('id').value=''; document.getElementById('nombreBoveda').value=''; document.getElementById('saldo').value='';">Limpiar</button>
    </div>
</form>

<%
String accion = request.getParameter("accion");
if (accion != null) {
    InventarioFichaDAO dao = new InventarioFichaDAO();

    if (accion.equals("agregar")) {
        try {
            InventarioFichaDTO inventario = new InventarioFichaDTO();
            inventario.setNombreBoveda(request.getParameter("nombreBoveda"));
            inventario.setSaldo(Integer.parseInt(request.getParameter("saldo")));
            
            String resultado = dao.agregar(inventario);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">✅ Bóveda agregada correctamente.</p>
<%
            } else {
                throw new Exception("Error en servidor: " + resultado);
            }
        } catch (Exception e) {
%>
<p class="error">❌ Error al agregar bóveda: <%= e.getMessage() %></p>
<%
        }
    }

    if (accion.equals("actualizar")) {
        try {
            InventarioFichaDTO inventario = new InventarioFichaDTO();
            int id = Integer.parseInt(request.getParameter("id"));
            inventario.setNombreBoveda(request.getParameter("nombreBoveda"));
            inventario.setSaldo(Integer.parseInt(request.getParameter("saldo")));
            
            String resultado = dao.actualizar(id, inventario);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">🔁 Bóveda actualizada correctamente.</p>
<%
            } else {
                throw new Exception("Error en servidor: " + resultado);
            }
        } catch (Exception e) {
%>
<p class="error">❌ Error al actualizar bóveda: <%= e.getMessage() %></p>
<%
        }
    }

    if (accion.equals("eliminar")) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String resultado = dao.eliminar(id);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">🗑 Bóveda eliminada correctamente.</p>
<%
            } else {
                throw new Exception("Error en servidor: " + resultado);
            }
        } catch (Exception e) {
%>
<p class="error">❌ Error al eliminar bóveda: <%= e.getMessage() %></p>
<%
        }
    }
} %>
</section>

<section>
<h2>Inventario de Bóvedas de Fichas</h2>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Nombre Bóveda</th>
    <th>Saldo Fichas</th>
    <th>Estado</th>
    <th>Acciones</th>
</tr>
</thead>

<tbody>
<%
InventarioFichaDAO dao = new InventarioFichaDAO();
ArrayList<InventarioFichaDTO> lista = (ArrayList<InventarioFichaDTO>) dao.consultar();

int totalFichas = 0;
if (lista != null && !lista.isEmpty()) {
    for (InventarioFichaDTO inv : lista) {
        totalFichas += inv.getSaldo();
        String claseSaldo = "saldo-positivo";
        if (inv.getSaldo() < 100) {
            claseSaldo = "saldo-critico";
        } else if (inv.getSaldo() < 500) {
            claseSaldo = "saldo-bajo";
        }
%>
<tr>
    <td><%= inv.getIdInventario() %></td>
    <td><%= inv.getNombreBoveda() %></td>
    <td class="<%= claseSaldo %>"><%= String.format("%,d", inv.getSaldo()) %> fichas</td>
    <td>
        <% if (inv.getSaldo() >= 500) { %>
            <span style="color: #27ae60;">● Óptimo</span>
        <% } else if (inv.getSaldo() >= 100) { %>
            <span style="color: #e67e22;">● Bajo</span>
        <% } else { %>
            <span style="color: #e74c3c;">● Crítico</span>
        <% } %>
    </td>
    <td class="acciones">
        <form method="post" action="inventarioficha.jsp" style="display:inline;">
            <input type="hidden" name="id" value="<%= inv.getIdInventario() %>">
            <button type="submit" name="accion" value="eliminar" class="eliminar">Eliminar</button>
        </form>

        <button type="button" onclick="cargarInventario(
            '<%= inv.getIdInventario() %>',
            '<%= inv.getNombreBoveda() %>',
            '<%= inv.getSaldo() %>'
        )">Actualizar</button>
    </td>
</tr>
<% } } else { %>
<tr><td colspan="5">No hay bóvedas registradas en el inventario.</td></tr>
<% } %>
</tbody>
<tfoot>
    <tr style="background-color: #f8f9fa; font-weight: bold;">
        <td colspan="2">TOTAL GENERAL</td>
        <td style="color: #2c3e50;"><%= String.format("%,d", totalFichas) %> fichas</td>
        <td colspan="2">
            <% if (totalFichas >= 1000) { %>
                <span style="color: #27ae60;">● Inventario Saludable</span>
            <% } else if (totalFichas >= 500) { %>
                <span style="color: #e67e22;">● Inventario Moderado</span>
            <% } else { %>
                <span style="color: #e74c3c;">● Inventario Bajo</span>
            <% } %>
        </td>
    </tr>
</tfoot>
</table>
</section>

</main>

<footer>
<p>© 2025 Casino El Bosque | Gestión de Inventario de Fichas</p>
</footer>

</body>
</html>
<%@ page import="co.edu.unbosque.dao.TorneoDAO,co.edu.unbosque.model.TorneoDTO,java.util.*,java.time.LocalDate,java.math.BigDecimal" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Torneos - Casino El Bosque</title>
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
        input[type="text"], input[type="number"], input[type="date"], select { 
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
        .activo { color: #27ae60; font-weight: bold; }
        .finalizado { color: #7f8c8d; font-style: italic; }
        .premio { color: #f39c12; font-weight: bold; }
        .buyin { color: #e74c3c; font-weight: bold; }
        .filtros { display: flex; gap: 10px; margin-bottom: 20px; }
        .filtros button { background: #3498db; }
        .filtros button.activo { background: #e67e22; }
    </style>

    <script>
        function cargarTorneo(idEvento, nombre, fechaInicio, fechaFin, buyIn, premio) {
            document.getElementById("idEvento").value = idEvento;
            document.getElementById("nombre").value = nombre;
            document.getElementById("fechaInicio").value = fechaInicio;
            document.getElementById("fechaFin").value = fechaFin || '';
            document.getElementById("buyIn").value = buyIn;
            document.getElementById("premio").value = premio;
        }

        function validarFechas() {
            const fechaInicio = new Date(document.getElementById("fechaInicio").value);
            const fechaFin = document.getElementById("fechaFin").value;
            
            if (fechaFin) {
                const fechaFinDate = new Date(fechaFin);
                if (fechaInicio > fechaFinDate) {
                    alert("❌ La fecha de inicio no puede ser posterior a la fecha de fin");
                    document.getElementById("fechaFin").value = '';
                    return false;
                }
            }
            return true;
        }

        function validarMontos() {
            const buyIn = parseFloat(document.getElementById("buyIn").value) || 0;
            const premio = parseFloat(document.getElementById("premio").value) || 0;
            
            if (buyIn < 0 || premio < 0) {
                alert("❌ Los montos no pueden ser negativos");
                return false;
            }
            
            if (premio < buyIn) {
                if (!confirm("⚠️ El premio es menor que el buy-in. ¿Está seguro?")) {
                    return false;
                }
            }
            return true;
        }

        function limpiarFormulario() {
            document.getElementById("idEvento").value = '';
            document.getElementById("nombre").value = '';
            document.getElementById("fechaInicio").value = '';
            document.getElementById("fechaFin").value = '';
            document.getElementById("buyIn").value = '';
            document.getElementById("premio").value = '';
        }

        function setFechaHoy() {
            const hoy = new Date().toISOString().split('T')[0];
            document.getElementById("fechaInicio").value = hoy;
        }
    </script>
</head>

<body onload="setFechaHoy()">
<header>
    <h1>🏆 Gestión de Torneos - Casino El Bosque</h1>
</header>

<nav>
    <ul>
        <li><a href="dashboard.jsp">🏠 Dashboard</a></li>
        <li><a href="cliente.jsp">👥 Clientes</a></li>
        <li><a href="empleado.jsp">💼 Empleados</a></li>
        <li><a href="apuesta.jsp">🎰 Apuestas</a></li>
        <li><a href="torneo.jsp" class="activo">🏆 Torneos</a></li>
        <li><a href="inscripcionTorneo.jsp">📝 Inscripciones</a></li>
        <li><a href="inventarioficha.jsp">💰 Fichas</a></li>
    </ul>
</nav>

<main>

<section>
<h2>Registrar / Actualizar Torneo</h2>

<form method="post" action="torneo.jsp" onsubmit="return validarFechas() && validarMontos()">
    <input type="hidden" id="idEvento" name="idEvento">
    
    <div class="form-group">
        <label for="nombre">Nombre del Torneo:</label>
        <input type="text" id="nombre" name="nombre" placeholder="Ej: Torneo Nacional de Póker" required>
    </div>

    <div class="form-group">
        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" required onchange="validarFechas()">
    </div>

    <div class="form-group">
        <label for="fechaFin">Fecha de Fin (Opcional):</label>
        <input type="date" id="fechaFin" name="fechaFin" onchange="validarFechas()">
    </div>

    <div class="form-group">
        <label for="buyIn">Buy-In ($):</label>
        <input type="number" id="buyIn" name="buyIn" step="0.01" min="0" placeholder="0.00" required onchange="validarMontos()">
    </div>

    <div class="form-group">
        <label for="premio">Premio Total ($):</label>
        <input type="number" id="premio" name="premio" step="0.01" min="0" placeholder="0.00" required onchange="validarMontos()">
    </div>

    <div style="flex-basis: 100%; display: flex; gap: 10px; margin-top: 15px;">
        <button type="submit" name="accion" value="agregar">➕ Crear Torneo</button>
        <button type="submit" name="accion" value="actualizar">✏️ Actualizar Torneo</button>
        <button type="button" onclick="limpiarFormulario(); setFechaHoy()">🔄 Limpiar</button>
    </div>
</form>

<%
String accion = request.getParameter("accion");
if (accion != null) {
    TorneoDAO dao = new TorneoDAO();

    if (accion.equals("agregar")) {
        try {
            TorneoDTO torneo = new TorneoDTO();
            torneo.setNombre(request.getParameter("nombre"));
            torneo.setFechaInicio(LocalDate.parse(request.getParameter("fechaInicio")));
            
            String fechaFinParam = request.getParameter("fechaFin");
            if (fechaFinParam != null && !fechaFinParam.isEmpty()) {
                torneo.setFechaFin(LocalDate.parse(fechaFinParam));
            }
            
            String buyInParam = request.getParameter("buyIn");
            String premioParam = request.getParameter("premio");
            
            torneo.setBuyIn(buyInParam != null && !buyInParam.isEmpty() ? 
                new BigDecimal(buyInParam) : BigDecimal.ZERO);
            torneo.setPremio(premioParam != null && !premioParam.isEmpty() ? 
                new BigDecimal(premioParam) : BigDecimal.ZERO);
            
            System.out.println("🔄 Intentando crear torneo: " + torneo.getNombre());
            
            String resultado = dao.agregar(torneo);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">✅ Torneo creado correctamente.</p>
<%
            } else {
                throw new Exception("Error del servidor - Código: " + resultado);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al crear torneo: " + e.getMessage());
            e.printStackTrace();
%>
<p class="error">❌ Error al crear torneo: <%= e.getMessage() %></p>
<%
        }
    }

    if (accion.equals("actualizar")) {
        try {
            TorneoDTO torneo = new TorneoDTO();
            String idEvento = request.getParameter("idEvento");
            torneo.setNombre(request.getParameter("nombre"));
            torneo.setFechaInicio(LocalDate.parse(request.getParameter("fechaInicio")));
            
            String fechaFinParam = request.getParameter("fechaFin");
            if (fechaFinParam != null && !fechaFinParam.isEmpty()) {
                torneo.setFechaFin(LocalDate.parse(fechaFinParam));
            } else {
                torneo.setFechaFin(null);
            }
            
            String buyInParam = request.getParameter("buyIn");
            String premioParam = request.getParameter("premio");
            
            torneo.setBuyIn(buyInParam != null && !buyInParam.isEmpty() ? 
                new BigDecimal(buyInParam) : BigDecimal.ZERO);
            torneo.setPremio(premioParam != null && !premioParam.isEmpty() ? 
                new BigDecimal(premioParam) : BigDecimal.ZERO);
            
            String resultado = dao.actualizar(idEvento, torneo);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">🔁 Torneo actualizado correctamente.</p>
<%
            } else {
                throw new Exception("Error del servidor - Código: " + resultado);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar torneo: " + e.getMessage());
%>
<p class="error">❌ Error al actualizar torneo: <%= e.getMessage() %></p>
<%
        }
    }

    if (accion.equals("eliminar")) {
        try {
            String idEvento = request.getParameter("idEvento");
            String resultado = dao.eliminar(idEvento);
            if (resultado != null && resultado.startsWith("2")) {
%>
<p class="exito">🗑️ Torneo eliminado correctamente.</p>
<%
            } else {
                throw new Exception("Error del servidor - Código: " + resultado);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar torneo: " + e.getMessage());
%>
<p class="error">❌ Error al eliminar torneo: <%= e.getMessage() %></p>
<%
        }
    }
} %>
</section>

<section>
<h2>Listado de Torneos</h2>

<%
TorneoDAO dao = new TorneoDAO();
String estadisticas = dao.obtenerEstadisticas();
%>

<div style="background: #e8f4fd; padding: 15px; border-radius: 5px; margin-bottom: 20px;">
    <h3 style="margin: 0; color: #2980b9;">📊 Estadísticas de Torneos</h3>
    <p style="margin: 5px 0;"><%= estadisticas %></p>
</div>

<table>
<thead>
<tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Fecha Inicio</th>
    <th>Fecha Fin</th>
    <th>Buy-In</th>
    <th>Premio</th>
    <th>Estado</th>
    <th>Acciones</th>
</tr>
</thead>

<tbody>
<%
ArrayList<TorneoDTO> lista = (ArrayList<TorneoDTO>) dao.consultar();
LocalDate hoy = LocalDate.now();

if (lista != null && !lista.isEmpty()) {
    for (TorneoDTO t : lista) {
        String estado = "activo";
        String claseEstado = "activo";
        
        if (t.getFechaFin() != null && t.getFechaFin().isBefore(hoy)) {
            estado = "finalizado";
            claseEstado = "finalizado";
        }
%>
<tr>
    <td><%= t.getIdEvento() != null ? t.getIdEvento() : "N/A" %></td>
    <td><strong><%= t.getNombre() != null ? t.getNombre() : "Sin nombre" %></strong></td>
    <td><%= t.getFechaInicio() != null ? t.getFechaInicio() : "N/A" %></td>
    <td><%= t.getFechaFin() != null ? t.getFechaFin() : "Sin definir" %></td>
    <td class="buyin">$<%= t.getBuyIn() != null ? String.format("%,.2f", t.getBuyIn()) : "0.00" %></td>
    <td class="premio">$<%= t.getPremio() != null ? String.format("%,.2f", t.getPremio()) : "0.00" %></td>
    <td class="<%= claseEstado %>"><%= estado.toUpperCase() %></td>

    <td class="acciones">
        <form method="post" action="torneo.jsp" style="display:inline;">
            <input type="hidden" name="idEvento" value="<%= t.getIdEvento() != null ? t.getIdEvento() : "" %>">
            <button type="submit" name="accion" value="eliminar" class="eliminar" 
                    onclick="return confirm('¿Está seguro de eliminar el torneo: <%= t.getNombre() != null ? t.getNombre() : "este torneo" %>?')">
                🗑️ Eliminar
            </button>
        </form>

        <button type="button" onclick="cargarTorneo(
            '<%= t.getIdEvento() != null ? t.getIdEvento() : "" %>',
            '<%= t.getNombre() != null ? t.getNombre().replace("'", "\\'") : "" %>',
            '<%= t.getFechaInicio() != null ? t.getFechaInicio() : "" %>',
            '<%= t.getFechaFin() != null ? t.getFechaFin() : "" %>',
            '<%= t.getBuyIn() != null ? t.getBuyIn() : "0" %>',
            '<%= t.getPremio() != null ? t.getPremio() : "0" %>'
        )">✏️ Actualizar</button>
    </td>
</tr>
<% } } else { %>
<tr>
    <td colspan="8" style="text-align: center; padding: 20px;">
        <p style="color: #7f8c8d; font-style: italic;">No hay torneos registrados.</p>
    </td>
</tr>
<% } %>
</tbody>
</table>
</section>

</main>

<footer>
<p>© 2025 Casino El Bosque | Gestión de Torneos</p>
</footer>

</body>
</html>
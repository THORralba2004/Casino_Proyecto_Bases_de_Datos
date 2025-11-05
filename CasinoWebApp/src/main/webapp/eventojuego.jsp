<%@ page import="co.edu.unbosque.dao.EventoJuegoDAO,co.edu.unbosque.dao.TipoJuegoDAO,co.edu.unbosque.model.EventoJuegoDTO,co.edu.unbosque.model.TipoJuegoDTO,java.util.*,java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Eventos de Juego</title>
    <style>
        body {font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;background-color: #f5f6fa;margin: 0;padding: 0;}
        header {background: linear-gradient(90deg, #222, #444);color: #fff;text-align: center;padding: 20px 0;}
        nav {background-color: #333;display: flex;justify-content: center;}
        nav ul {list-style: none;display: flex;margin: 0;padding: 10px 0;}
        nav ul li {margin: 0 15px;}
        nav ul li a {text-decoration: none;color: #eee;padding: 10px 15px;border-radius: 5px;transition: 0.3s;}
        nav ul li a:hover, nav ul li a.activo {background-color: #e67e22;color: #fff;}
        main {width: 80%;margin: 30px auto;background: white;padding: 30px;border-radius: 10px;box-shadow: 0 2px 8px rgba(0,0,0,0.1);}
        h2 {color: #2c3e50;}
        form {display: flex;flex-wrap: wrap;gap: 10px;margin-bottom: 20px;}
        input[type="text"], input[type="date"], select, input[type="number"] {
            flex: 1;padding: 10px;border-radius: 5px;border: 1px solid #ccc;font-size: 14px;
        }
        button {background-color: #27ae60;color: white;border: none;padding: 10px 20px;border-radius: 5px;cursor: pointer;transition: background 0.3s;}
        button:hover {background-color: #219150;}
        table {width: 100%;border-collapse: collapse;margin-top: 15px;}
        table th, table td {border: 1px solid #ddd;text-align: center;padding: 10px;}
        table th {background-color: #34495e;color: white;}
        table tr:nth-child(even) {background-color: #f2f2f2;}
        .acciones button {margin: 0 5px;background-color: #3498db;}
        .acciones button.eliminar {background-color: #e74c3c;}
        .exito {color: #27ae60;background: #d5f5e3;border: 1px solid #27ae60;padding: 8px;border-radius: 5px;}
        footer {text-align: center;background: #222;color: #aaa;padding: 15px;margin-top: 30px;}
    </style>
</head>
<body>
    <header><h1>🎮 Panel de Administración - Eventos de Juego</h1></header>

    <nav>
        <ul>
        <li><a href="index.jsp">Inicio</a></li>
        <li><a href="tipojuego.jsp">Tipos de Juego</a></li>
        <li><a href="empleado.jsp">Empleados</a></li>
        <li><a href="cliente.jsp">Clientes</a></li>
        <li><a href="apuesta.jsp">Apuestas</a></li>
        <li><a href="eventojuego.jsp" class="activo">Eventos</a></li>
        <li><a href="inventarioficha.jsp" >Inventario Fichas</a></li>
        </ul>
    </nav>

    <main>
        <section class="formulario">
            <h2>Registrar / Actualizar Evento</h2>
            <form method="post" action="eventojuego.jsp">
                <input type="number" name="idEvento" placeholder="ID (solo si va a actualizar)">
                
                <label>Tipo de Juego:</label>
                <select name="idTipoJuego" required>
                    <option value="">Seleccione un tipo de juego</option>
                    <%
                        TipoJuegoDAO tipoDAO = new TipoJuegoDAO();
                        ArrayList<TipoJuegoDTO> tipos = (ArrayList<TipoJuegoDTO>) tipoDAO.consultar();
                        for (TipoJuegoDTO t : tipos) {
                    %>
                        <option value="<%= t.getIdTipoJuego() %>"><%= t.getNombre() %> (ID: <%= t.getIdTipoJuego() %>)</option>
                    <% } %>
                </select>

                <input type="date" name="fechaAlta" required>
                <input type="text" name="estado" placeholder="Estado" required>
                
                <button type="submit" name="accion" value="agregar">Agregar</button>
                <button type="submit" name="accion" value="actualizar">Actualizar</button>
            </form>

            <%
                String accion = request.getParameter("accion");
                if (accion != null) {
                    EventoJuegoDAO dao = new EventoJuegoDAO();

                    if (accion.equals("agregar")) {
                        int idTipoJuego = Integer.parseInt(request.getParameter("idTipoJuego"));
                        EventoJuegoDTO nuevo = new EventoJuegoDTO();
                        nuevo.setIdTipoJuego(idTipoJuego);
                        nuevo.setFechaAlta(LocalDate.parse(request.getParameter("fechaAlta")));
                        nuevo.setEstado(request.getParameter("estado"));
                        dao.agregar(nuevo);
            %>
                        <p class="exito">✅ Evento agregado correctamente con Tipo de Juego ID: <%= request.getParameter("idTipoJuego") %></p>
            <%
                    } else if (accion.equals("actualizar")) {
                        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
                        int idTipoJuego = Integer.parseInt(request.getParameter("idTipoJuego"));
                        EventoJuegoDTO actualizado = new EventoJuegoDTO();
                        actualizado.setIdTipoJuego(idTipoJuego);
                        actualizado.setFechaAlta(LocalDate.parse(request.getParameter("fechaAlta")));
                        actualizado.setEstado(request.getParameter("estado"));
                        dao.actualizar(idEvento, actualizado);
            %>
                        <p class="exito">🔁 Evento actualizado correctamente.</p>
            <%
                    } else if (accion.equals("eliminar")) {
                        int idEvento = Integer.parseInt(request.getParameter("idEvento"));
                        dao.eliminar(idEvento);
            %>
                        <p class="exito">🗑️ Evento eliminado correctamente.</p>
            <%
                    }
                }
            %>
        </section>

        <section class="tabla">
            <h2>Listado de Eventos</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID Evento</th>
                        <th>ID Tipo Juego</th>
                        <th>Nombre Tipo Juego</th>
                        <th>Fecha Alta</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        EventoJuegoDAO dao = new EventoJuegoDAO();
                        ArrayList<EventoJuegoDTO> lista = (ArrayList<EventoJuegoDTO>) dao.consultar();
                        if (lista != null && !lista.isEmpty()) {
                            for (EventoJuegoDTO e : lista) {
                                TipoJuegoDAO tdao = new TipoJuegoDAO();
                                TipoJuegoDTO tipo = tdao.obtenerPorId(e.getIdTipoJuego());
                    %>
                        <tr>
                            <td><%= e.getIdEvento() %></td>
                            <td><%= e.getIdTipoJuego() %></td>
                            <td><%= tipo != null ? tipo.getNombre() : "N/A" %></td>
                            <td><%= e.getFechaAlta() %></td>
                            <td><%= e.getEstado() %></td>
                            <td class="acciones">
                                <form method="post" action="evento.jsp" style="display:inline;">
                                    <input type="hidden" name="idEvento" value="<%= e.getIdEvento() %>">
                                    <button type="submit" name="accion" value="eliminar" class="eliminar">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    <% } } else { %>
                        <tr><td colspan="6">No hay eventos registrados.</td></tr>
                    <% } %>
                </tbody>
            </table>
        </section>
    </main>

    <footer>
        <p>© 2025 Casino El Bosque | Administración de Eventos</p>
    </footer>
</body>
</html>

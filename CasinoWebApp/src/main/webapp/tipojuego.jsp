<%@ page import="co.edu.unbosque.dao.TipoJuegoDAO,co.edu.unbosque.model.TipoJuegoDTO,java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Tipos de Juego</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
        }
        header {
            background: #1a1a2e;
            color: white;
            text-align: center;
            padding: 20px;
        }
        nav {
            background: #16213e;
            text-align: center;
            padding: 10px 0;
        }
        nav a {
            color: #eaeaea;
            margin: 0 15px;
            text-decoration: none;
            font-weight: bold;
        }
        nav a:hover { color: #00a8ff; }
        main {
            width: 85%;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th { background-color: #1a1a2e; color: white; }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 7px 14px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover { background-color: #0056b3; }
        .delete-btn { background-color: #dc3545; }
        .delete-btn:hover { background-color: #b02a37; }
        .success { background: #d4edda; color: #155724; padding: 10px; border-radius: 5px; }
    </style>
</head>
<body>
<header>
    <h1>🎮 Administración de Tipos de Juego</h1>
</header>

<nav>
        <a href="index.jsp">Inicio</a>
        <a href="tipojuego.jsp" class="activo">Tipos de Juego</a>
        <a href="empleado.jsp">Empleados</a>
        <a href="cliente.jsp">Clientes</a>
        <a href="apuesta.jsp">Apuestas</a>
        <a href="eventojuego.jsp">Eventos</a>
        <a href="inventarioficha.jsp" >Inventario Fichas</a>
</nav>

<main>
    <%
        TipoJuegoDAO dao = new TipoJuegoDAO();

        // Agregar
        if (request.getParameter("accion") != null && request.getParameter("accion").equals("agregar")) {
            String nombre = request.getParameter("nombre");
            if (nombre != null && !nombre.trim().isEmpty()) {
                TipoJuegoDTO nuevo = new TipoJuegoDTO();
                nuevo.setNombre(nombre);
                dao.agregar(nuevo);
    %>
                <p class="success">✅ Tipo de juego agregado correctamente.</p>
    <%
            }
        }

        // Eliminar
        if (request.getParameter("eliminar") != null) {
            int id = Integer.parseInt(request.getParameter("eliminar"));
            dao.eliminar(id);
    %>
            <p class="success">🗑️ Tipo de juego eliminado.</p>
    <%
        }

        // Actualizar
        if (request.getParameter("accion") != null && request.getParameter("accion").equals("actualizar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            TipoJuegoDTO actualizado = new TipoJuegoDTO();
            actualizado.setIdTipoJuego(id);
            actualizado.setNombre(nombre);
            dao.actualizar(id, actualizado);
    %>
            <p class="success">♻️ Tipo de juego actualizado correctamente.</p>
    <%
        }
    %>

    <!-- FORMULARIO DE CREACIÓN -->
    <section>
        <h2>Agregar Nuevo Tipo de Juego</h2>
        <form method="post">
            <input type="hidden" name="accion" value="agregar">
            <input type="text" name="nombre" placeholder="Nombre del juego" required>
            <button type="submit">Agregar</button>
        </form>
    </section>

    <!-- LISTADO -->
    <section>
        <h2>Listado de Tipos de Juego</h2>
        <table>
            <thead>
                <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
            </thead>
            <tbody>
            <%
                ArrayList<TipoJuegoDTO> lista = (ArrayList<TipoJuegoDTO>) dao.consultar();
                if (lista != null && !lista.isEmpty()) {
                    for (TipoJuegoDTO t : lista) {
            %>
                <tr>
                    <td><%= t.getIdTipoJuego() %></td>
                    <td><%= t.getNombre() %></td>
                    <td>
                        <form method="post" style="display:inline;">
                            <input type="hidden" name="eliminar" value="<%= t.getIdTipoJuego() %>">
                            <button type="submit" class="delete-btn">Eliminar</button>
                        </form>
                        <form method="get" style="display:inline;">
                            <input type="hidden" name="editar" value="<%= t.getIdTipoJuego() %>">
                            <button type="submit">Actualizar</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="3">No hay tipos de juego registrados.</td></tr>
            <%
                }
            %>
            </tbody>
        </table>

        <!-- FORMULARIO DE ACTUALIZACIÓN -->
        <%
            if (request.getParameter("editar") != null) {
                int id = Integer.parseInt(request.getParameter("editar"));
                for (TipoJuegoDTO t : lista) {
                    if (t.getIdTipoJuego() == id) {
        %>
            <h3>Actualizar Tipo de Juego</h3>
            <form method="post">
                <input type="hidden" name="accion" value="actualizar">
                <input type="hidden" name="id" value="<%= t.getIdTipoJuego() %>">
                <input type="text" name="nombre" value="<%= t.getNombre() %>" required>
                <button type="submit">Guardar Cambios</button>
            </form>
        <%
                    }
                }
            }
        %>
    </section>
</main>
</body>
</html>

<%@ page import="co.edu.unbosque.dao.EmpleadoDAO,co.edu.unbosque.model.EmpleadoDTO,java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Gestión de Empleados</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f6fa;
            margin: 0;
            padding: 0;
        }

        header {
            background: linear-gradient(90deg, #222, #444);
            color: #fff;
            text-align: center;
            padding: 20px 0;
        }

        nav {
            background-color: #333;
            display: flex;
            justify-content: center;
        }

        nav ul {
            list-style: none;
            display: flex;
            margin: 0;
            padding: 10px 0;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            text-decoration: none;
            color: #eee;
            padding: 10px 15px;
            border-radius: 5px;
            transition: 0.3s;
        }

        nav ul li a:hover,
        nav ul li a.activo {
            background-color: #e67e22;
            color: #fff;
        }

        main {
            width: 80%;
            margin: 30px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        h2 {
            color: #2c3e50;
        }

        form {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-bottom: 20px;
        }

        input[type="text"], input[type="number"] {
            flex: 1;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        button {
            background-color: #27ae60;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #219150;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        table th, table td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 10px;
        }

        table th {
            background-color: #34495e;
            color: white;
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .acciones button {
            margin: 0 5px;
            background-color: #3498db;
        }

        .acciones button.eliminar {
            background-color: #e74c3c;
        }

        .exito {
            color: #27ae60;
            background: #d5f5e3;
            border: 1px solid #27ae60;
            padding: 8px;
            border-radius: 5px;
        }

        footer {
            text-align: center;
            background: #222;
            color: #aaa;
            padding: 15px;
            margin-top: 30px;
        }
    </style>

    <script>
        function cargarEmpleado(id, nombre, cargo, salario, activo) {
            document.getElementById("idEmpleado").value = id;
            document.getElementById("nombre").value = nombre;
            document.getElementById("cargo").value = cargo;
            document.getElementById("salario").value = salario;
            document.getElementById("activo").value = activo;
        }
    </script>
</head>
<body>
    <header>
        <h1>👨‍💼 Panel de Administración - Empleados</h1>
    </header>

    <nav>
        <ul>
        <li><a href="index.jsp">Inicio</a></li>
        <li><a href="tipojuego.jsp">Tipos de Juego</a></li>
        <li><a href="empleado.jsp" class="activo">Empleados</a></li>
        <li><a href="cliente.jsp">Clientes</a></li>
        <li><a href="apuesta.jsp">Apuestas</a></li>
        <li><a href="eventojuego.jsp">Eventos</a></li>
        <li><a href="inventarioficha.jsp" >Inventario Fichas</a></li>
        </ul>
    </nav>

    <main>
        <section class="formulario">
            <h2>Registrar / Actualizar Empleado</h2>
            <form method="post" action="empleado.jsp">
                <input type="number" id="idEmpleado" name="idEmpleado" placeholder="ID (solo si va a actualizar)">
                <input type="text" id="nombre" name="nombre" placeholder="Nombre del empleado" required>
                <input type="text" id="cargo" name="cargo" placeholder="Cargo" required>
                <input type="number" id="salario" name="salario" placeholder="Salario" step="0.01" required>
            
                <button type="submit" name="accion" value="agregar">Agregar</button>
                <button type="submit" name="accion" value="actualizar">Actualizar</button>
            </form>

            <%
                String accion = request.getParameter("accion");
                if (accion != null) {
                    EmpleadoDAO dao = new EmpleadoDAO();

                    if (accion.equals("agregar")) {
                        EmpleadoDTO nuevo = new EmpleadoDTO();
                        nuevo.setNombre(request.getParameter("nombre"));
                        nuevo.setCargo(request.getParameter("cargo"));
                        nuevo.setSalario(Double.parseDouble(request.getParameter("salario")));
                        // ✅ Por defecto se agrega activo = "S"
                        nuevo.setActivo("S");
                        dao.agregar(nuevo);
            %>
                        <p class="exito">✅ Empleado agregado correctamente</p>
            <%
                    } else if (accion.equals("actualizar")) {
                        int id = Integer.parseInt(request.getParameter("idEmpleado"));
                        EmpleadoDTO actualizado = new EmpleadoDTO();
                        actualizado.setNombre(request.getParameter("nombre"));
                        actualizado.setCargo(request.getParameter("cargo"));
                        actualizado.setSalario(Double.parseDouble(request.getParameter("salario")));
                        actualizado.setActivo(request.getParameter("activo"));
                        dao.actualizar(id, actualizado);
            %>
                        <p class="exito">🔁 Empleado actualizado correctamente.</p>
            <%
                    } else if (accion.equals("eliminar")) {
                        int id = Integer.parseInt(request.getParameter("idEmpleado"));
                        dao.eliminar(id);
            %>
                        <p class="exito">🗑️ Empleado eliminado correctamente.</p>
            <%
                    }
                }
            %>
        </section>

        <section class="tabla">
            <h2>Listado de Empleados</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Cargo</th>
                        <th>Salario</th>
                        <th>Activo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        EmpleadoDAO dao = new EmpleadoDAO();
                        ArrayList<EmpleadoDTO> lista = (ArrayList<EmpleadoDTO>) dao.consultar();
                        if (lista != null && !lista.isEmpty()) {
                            for (EmpleadoDTO e : lista) {
                    %>
                        <tr>
                            <td><%= e.getIdEmpleado() %></td>
                            <td><%= e.getNombre() %></td>
                            <td><%= e.getCargo() %></td>
                            <td><%= e.getSalario() %></td>
                            <td><%= e.getActivo() %></td>
                            <td class="acciones">
                                <form method="post" action="empleado.jsp" style="display:inline;">
                                    <input type="hidden" name="idEmpleado" value="<%= e.getIdEmpleado() %>">
                                    <button type="submit" name="accion" value="eliminar" class="eliminar">Eliminar</button>
                                </form>
                                <button type="button" onclick="cargarEmpleado('<%= e.getIdEmpleado() %>', '<%= e.getNombre() %>', '<%= e.getCargo() %>', '<%= e.getSalario() %>', '<%= e.getActivo() %>')">Actualizar</button>
                            </td>
                        </tr>
                    <% 
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="6">No hay empleados registrados.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </section>
    </main>

    <footer>
        <p>© 2025 Casino El Bosque | Administración de Empleados</p>
    </footer>
</body>
</html>

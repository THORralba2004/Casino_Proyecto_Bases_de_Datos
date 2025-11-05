<%@ page import="co.edu.unbosque.dao.DashboardDAO,co.edu.unbosque.model.DashboardDTO,java.util.*,java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Dashboard - Casino El Bosque</title>
    <style>
        :root {
            --primary: #2c3e50;
            --secondary: #34495e;
            --success: #27ae60;
            --warning: #f39c12;
            --danger: #e74c3c;
            --info: #3498db;
            --light: #ecf0f1;
            --dark: #2c3e50;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            margin: 0;
            padding: 0;
            min-height: 100vh;
        }
        
        .dashboard-container {
            max-width: 1400px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .header {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-radius: 15px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }
        
        .header h1 {
            margin: 0;
            color: var(--primary);
            font-size: 2.5em;
            text-align: center;
        }
        
        .header p {
            margin: 5px 0 0 0;
            color: var(--secondary);
            text-align: center;
            font-size: 1.1em;
        }
        
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 20px;
            margin-bottom: 20px;
        }
        
        .stat-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-radius: 15px;
            padding: 25px;
            text-align: center;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        
        .stat-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 40px rgba(0, 0, 0, 0.15);
        }
        
        .stat-card.clientes { border-left: 5px solid var(--success); }
        .stat-card.empleados { border-left: 5px solid var(--info); }
        .stat-card.apuestas { border-left: 5px solid var(--warning); }
        .stat-card.movimientos { border-left: 5px solid var(--primary); }
        .stat-card.torneos { border-left: 5px solid var(--danger); }
        .stat-card.ingresos { border-left: 5px solid var(--success); }
        .stat-card.egresos { border-left: 5px solid var(--danger); }
        .stat-card.inventario { border-left: 5px solid var(--info); }
        .stat-card.balance { border-left: 5px solid var(--warning); }
        
        .stat-icon {
            font-size: 2.5em;
            margin-bottom: 10px;
        }
        
        .stat-value {
            font-size: 2.2em;
            font-weight: bold;
            margin: 10px 0;
        }
        
        .stat-label {
            color: var(--secondary);
            font-size: 0.9em;
            text-transform: uppercase;
            letter-spacing: 1px;
        }
        
        .quick-actions {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-bottom: 20px;
        }
        
        .action-btn {
            background: rgba(255, 255, 255, 0.95);
            border: none;
            border-radius: 10px;
            padding: 15px;
            text-align: center;
            text-decoration: none;
            color: var(--primary);
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }
        
        .action-btn:hover {
            background: var(--primary);
            color: white;
            transform: translateY(-2px);
        }
        
        .last-update {
            text-align: center;
            color: rgba(255, 255, 255, 0.8);
            margin-top: 20px;
            font-size: 0.9em;
        }
        
        .refresh-btn {
            background: var(--success);
            color: white;
            border: none;
            border-radius: 25px;
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            transition: all 0.3s ease;
            margin: 10px;
        }
        
        .refresh-btn:hover {
            background: #219150;
            transform: scale(1.05);
        }
        
        .health-status {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 20px;
            margin-top: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
        }
        
        .health-status h3 {
            margin-top: 0;
            color: var(--primary);
        }
        
        .health-item {
            display: flex;
            align-items: center;
            margin: 8px 0;
            padding: 8px;
            border-radius: 8px;
            background: rgba(236, 240, 241, 0.5);
        }
        
        .health-icon {
            margin-right: 10px;
            font-size: 1.2em;
        }
        
        @media (max-width: 768px) {
            .stats-grid {
                grid-template-columns: 1fr;
            }
            
            .quick-actions {
                grid-template-columns: repeat(2, 1fr);
            }
            
            .header h1 {
                font-size: 2em;
            }
        }
    </style>
</head>

<body>
    <div class="dashboard-container">
        <div class="header">
            <h1>🎰 Dashboard - Casino El Bosque</h1>
            <p>Visión general en tiempo real de todas las operaciones</p>
            <button class="refresh-btn" onclick="actualizarDashboard()">🔄 Actualizar Dashboard</button>
        </div>
        
        <%
        DashboardDAO dashboardDAO = new DashboardDAO();
        DashboardDTO datos = dashboardDAO.consultar();
        String resumen = dashboardDAO.obtenerResumen();
        String salud = dashboardDAO.verificarSaludSistema();
        %>

        <div class="stats-grid">
            <!-- Clientes -->
            <div class="stat-card clientes">
                <div class="stat-icon">👥</div>
                <div class="stat-value"><%= datos.getTotalClientes() %></div>
                <div class="stat-label">Total Clientes</div>
            </div>
            
            <!-- Empleados -->
            <div class="stat-card empleados">
                <div class="stat-icon">💼</div>
                <div class="stat-value"><%= datos.getTotalEmpleados() %></div>
                <div class="stat-label">Empleados Activos</div>
            </div>
            
            <!-- Apuestas Hoy -->
            <div class="stat-card apuestas">
                <div class="stat-icon">🎰</div>
                <div class="stat-value"><%= datos.getTotalApuestasHoy() %></div>
                <div class="stat-label">Apuestas Hoy</div>
            </div>
            
            <!-- Movimientos Hoy -->
            <div class="stat-card movimientos">
                <div class="stat-icon">🔄</div>
                <div class="stat-value"><%= datos.getTotalMovimientosHoy() %></div>
                <div class="stat-label">Movimientos Hoy</div>
            </div>
            
            <!-- Torneos Activos -->
            <div class="stat-card torneos">
                <div class="stat-icon">🏆</div>
                <div class="stat-value"><%= datos.getTorneosActivos() %></div>
                <div class="stat-label">Torneos Activos</div>
            </div>
            
            <!-- Ingresos Hoy -->
            <div class="stat-card ingresos">
                <div class="stat-icon">📈</div>
                <div class="stat-value">$<%= String.format("%,.2f", datos.getIngresosHoy()) %></div>
                <div class="stat-label">Ingresos Hoy</div>
            </div>
            
            <!-- Egresos Hoy -->
            <div class="stat-card egresos">
                <div class="stat-icon">📉</div>
                <div class="stat-value">$<%= String.format("%,.2f", datos.getEgresosHoy()) %></div>
                <div class="stat-label">Egresos Hoy</div>
            </div>
            
            <!-- Inventario Fichas -->
            <div class="stat-card inventario">
                <div class="stat-icon">💰</div>
                <div class="stat-value"><%= String.format("%,d", datos.getInventarioTotalFichas()) %></div>
                <div class="stat-label">Fichas en Inventario</div>
            </div>
            
            <!-- Balance -->
            <div class="stat-card balance">
                <div class="stat-icon">⚖️</div>
                <div class="stat-value" style="color: <%= datos.getBalanceHoy().compareTo(java.math.BigDecimal.ZERO) >= 0 ? "#27ae60" : "#e74c3c" %>">
                    $<%= String.format("%,.2f", datos.getBalanceHoy()) %>
                </div>
                <div class="stat-label">Balance del Día</div>
            </div>
        </div>

        <div class="quick-actions">
            <a href="cliente.jsp" class="action-btn">👥 Gestión de Clientes</a>
            <a href="empleado.jsp" class="action-btn">💼 Gestión de Empleados</a>
            <a href="apuesta.jsp" class="action-btn">🎰 Gestión de Apuestas</a>
            <a href="torneo.jsp" class="action-btn">🏆 Gestión de Torneos</a>
            <a href="inscripcionTorneo.jsp" class="action-btn">📝 Inscripciones Torneos</a>
            <a href="inventarioficha.jsp" class="action-btn">💰 Control de Fichas</a>
            <a href="movimientoficha.jsp" class="action-btn">🔄 Movimientos de Fichas</a>
            <a href="eventojuego.jsp" class="action-btn">🎯 Eventos de Juego</a>
            <a href="tipojuego.jsp" class="action-btn">🎮 Tipos de Juego</a>
        </div>

        <div class="health-status">
            <h3>🔍 Estado del Sistema</h3>
            <%
            String[] lineasSalud = salud.split("\n");
            for (String linea : lineasSalud) {
                String icono = "✅";
                if (linea.contains("⚠️")) {
                    icono = "⚠️";
                    linea = linea.replace("⚠️", "").trim();
                } else if (linea.contains("✅")) {
                    icono = "✅";
                    linea = linea.replace("✅", "").trim();
                }
            %>
            <div class="health-item">
                <span class="health-icon"><%= icono %></span>
                <span><%= linea %></span>
            </div>
            <% } %>
        </div>

        <div class="last-update">
            📅 Última actualización: <%= datos.getUltimaActualizacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) %>
        </div>
    </div>

    <script>
        function actualizarDashboard() {
            const btn = event.target;
            btn.innerHTML = '⏳ Actualizando...';
            btn.disabled = true;
            
            setTimeout(() => {
                location.reload();
            }, 1000);
        }
        
        // Auto-actualizar cada 2 minutos
        setTimeout(() => {
            document.querySelector('.refresh-btn').click();
        }, 120000);
        
        // Efectos visuales
        document.addEventListener('DOMContentLoaded', function() {
            const cards = document.querySelectorAll('.stat-card');
            cards.forEach((card, index) => {
                card.style.animationDelay = (index * 0.1) + 's';
                card.style.animation = 'fadeInUp 0.6s ease-out forwards';
            });
        });
    </script>

    <style>
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
        
        .stat-card {
            opacity: 0;
        }
    </style>
</body>
</html>
<%-- 
    Document   : vistaEquipos
    Created on : 3 mar. 2023, 13:20:27
    Author     : usumaniana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EQUIPOS</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/equipos/equipos.css"/>
    </head>
    <body>




        <header>
            <h1>EQUIPOS</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../partidos/">Partidos</a></li>
                </ul>
            </nav>
        </header>


        <main>
            <div class="header_main">
                <div class="equipo_filter">
                    <form method="GET" action="filtrarEquipo" class="formFiltro">
                        <input type="text" name="nombreFiltrado" value="" placeholder="Nombre"/>
                        <input type="submit" value="FILTRAR" class="send"/>
                    </form>
                </div>

                <a href="irNuevoEquipo" class="nuevo">NUEVO EQUIPO</a>
            </div>

            <div class="teams_section">

                <c:forEach  var="equipo"  items="${lstEquipos}" >

                    <c:url var="linkActualizar" value="irModificarEquipo">
                        <c:param name="idEquipo" value="${equipo.idEquipo}"></c:param>
                    </c:url>


                    <c:url var="linkEliminar" value="eliminarEquipo">
                        <c:param name="idEquipo" value="${equipo.idEquipo}"></c:param>
                    </c:url>

                    <c:url var="linkJugadoresEquipo" value="irJugadoresEquipo">
                        <c:param name="idEquipo" value="${equipo.idEquipo}"></c:param>
                    </c:url>



                    <div class="team_card">
                        <div class="team_info">

                            <img src="${equipo.imagen}" alt="${equipo.nombre}"/>


                            <div class="info">
                                <p>${equipo.nombre} </p>
                                <p>${equipo.pais}</p>
                                <p>${equipo.conferencia}</p>
                            </div>
                        </div>


                        <div class="team_actions">
                            <a href="${linkActualizar}">Modificar</a>
                            <a href="${linkJugadoresEquipo}">Ver jugadores</a>
                            <a href="${linkEliminar}" class="eliminar">Eliminar</a>
                        </div>
                    </div>



                </c:forEach>
            </div>

        </main>

    </body>
</html>

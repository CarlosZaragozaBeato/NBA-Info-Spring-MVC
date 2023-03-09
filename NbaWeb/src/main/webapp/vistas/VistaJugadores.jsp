<%-- 
    Document   : VistaJugadores
    Created on : 5 mar. 2023, 6:23:41
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jugadores</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/jugadores/jugadores.css"/>
    </head>
    <body>



        <header>
            <h1>JUGADORES</h1>
            <nav>
                <ul>
                    <li><a href="../partidos/">Partidos</a></li>
                    <li><a href="../equipos/">Equipos</a></li>
                </ul>
            </nav>
        </header>


        <main> 
            <div class="header_main">
                <div class="jugador_filter">
                    <form method="GET" action="filtrarJugador" class="formFiltro">


                        <div class="info">
                            <h2>PUNTOS</h2> 
                            <p>ASCENDENTE: <input type="radio" value="asc" name="orden"/></p>
                            <p>DESCENDENTE: <input type="radio" value="desc" name="orden"/></p>
                        </div>   

                        <input type="submit" value="FILTRAR" class="send"/>
                    </form>
                </div>

                <a href="irNuevoJugador" class="nuevo">NUEVO JUGADOR</a>
            </div>

            <div class="players_section">

                <c:forEach  var="jugador"  items="${lstJugador}" >

                    <c:url var="linkActualizar" value="irModificarJugador">
                        <c:param name="idJugador" value="${jugador.idJugador}"></c:param>
                    </c:url>


                    <c:url var="linkEliminar" value="eliminarJugador">
                        <c:param name="idJugador" value="${jugador.idJugador}"></c:param>
                    </c:url>

                    <div class="player_card">
                        <div class="player_info">

                            <div class="info">
                                <p>Nombre: ${jugador.nombre} </p>
                                <p>Apellido: ${jugador.apellido}</p>
                                <p>Total Puntos: ${jugador.totalPuntos}</p>
                                <p>Total Asistencias: ${jugador.totalAsistencias}</p>
                                <p>TOTAL: ${jugador.totalPuntos+jugador.totalAsistencias} </p>

                            </div>
                        </div>


                        <div class="team_actions">
                            <a href="${linkActualizar}">Modificar</a>
                            <a href="${linkEliminar}" class="eliminar">Eliminar</a>
                        </div>
                    </div>



                </c:forEach>
            </div>

        </main>


    </body>
</html>

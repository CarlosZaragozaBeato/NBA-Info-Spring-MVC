<%-- 
    Document   : JugadoresEquipo
    Created on : 4 mar. 2023, 20:34:03
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jugadores Equipo</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/jugadores/JugadoresEquipo.css"/>
    </head>
    <body>

        <header>
            <h1>JUGADORES EQUIPO</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../partidos/">Partidos</a></li>
                    <li><a href="../equipos/">Equipos</a></li>

                </ul>
            </nav>
        </header>
        <main>


            <div class="equipoInfo">
                <img src="${elEquipo.imagen}" alt="EQUIPO LOGO"/>
                <h2>${elEquipo.nombre}</h2>
            </div>


            <div class="jugadores_section">
                
                
         


                <c:if test="${lstJugadores.size() > 0}"> 
                    <c:forEach  var="jugador"  items="${lstJugadores}" >

                    


                        <div class="player">

                            <div class="info_player"<
                                 <p>${jugador.nombre} ${jugador.apellido}</p>

                                <p>Puntos: ${jugador.totalPuntos}</p>
                                <p>Asistencias: ${jugador.totalAsistencias}</p>
                            </div>

                       
                        </div>




                    </c:forEach>
                </c:if>

            </div>

        </main>

    </body>
</html>

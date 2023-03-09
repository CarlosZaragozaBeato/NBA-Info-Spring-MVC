<%-- 
    Document   : modificarJugador
    Created on : 5 mar. 2023, 7:54:11
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Jugador</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/jugadores/nuevoJugador.css"/>
    </head>
    <body>

        <header>
            <h1>MODIFICAR JUGADOR</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../partidos/">Partidos</a></li>
                    <li><a href="../equipos/">Equipos</a></li>

                </ul>
            </nav>
        </header>


        <main>
            <form method="POST" action="modificarJugador" class="container">

                <div>
                    <p>ID: <input type="text" name="idJugador" value="${elJugador.idJugador}"/></p>
                    <p>Nombre: <input type="text" name="nombre" value="${elJugador.nombre}"/> </p>
                    <p>Apellido: <input type="text" name="apellido" value="${elJugador.apellido}"/>  </p>
                    <p>Total Puntos: <input type="number" name="totalPuntos" value="${elJugador.totalPuntos}"/></p>
                    <p>Total Asistencias: <input type="number" name="totalAsistencias" value="${elJugador.totalAsistencias}"/></p>
                    <p>Equipo ID:  <p class="team_id">${elJugador.idEquipo.idEquipo}</p></p>

                    <input type="text" name="idEquipo" value="${elJugador.idEquipo.idEquipo}" class="team_id2">
                </div>


                <div>
                    <div class="image_selection">
                        <c:forEach  var="equipo"  items="${lstEquipos}" >
                            <div class="image_child">
                                <img src="${equipo.imagen}"/> 

                                <input type="radio" name="teams" value="${equipo.imagen}"
                                       onclick="cambiarJugador('${equipo.idEquipo}')" 
                                       class="radio">

                            </div>
                        </c:forEach>
                    </div>
                </div>


                <input type="submit" value="MODIFICAR" class="send"/>

            </form>

        </main>
        <script type="text/javascript">
            var inpt = document.querySelector(".team_id");
            var inpt2 = document.querySelector(".team_id2");

            //var image = document.querySelector(".current");

            function cambiarJugador(valor) {
                inpt.innerHTML = valor;
                inpt2.value = valor;
            }
        </script>
    </body>
</html>

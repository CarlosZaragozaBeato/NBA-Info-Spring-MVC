<%-- 
    Document   : nuevoJugador
    Created on : 5 mar. 2023, 6:36:02
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NUEVO JUGADOR</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/jugadores/nuevoJugador.css"/>
    </head>
    <body>



        <header>
            <h1>Nuevo JUGADOR</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../partidos/">Partidos</a></li>
                    <li><a href="../equipos/">Equipos</a></li>

                </ul>
            </nav>
        </header>


        <main>
            <form method="POST" action="nuevoJugador" class="container">

                <div>
                    <p>ID: <input type="text" name="idJugador" value=""/></p>
                    <p>Nombre: <input type="text" name="nombre" value=""/> </p>
                    <p>Apellido: <input type="text" name="apellido" value=""/>  </p>
                    <p>Total Puntos: <input type="number" name="totalPuntos" value=""/></p>
                    <p>Total Asistencias: <input type="number" name="totalAsistencias" value=""/></p>
                    <p>Equipo ID:  <p class="team_id"></p></p>

                    <input type="text" name="idEquipo" value="" class="team_id2">
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


                <input type="submit" value="NUEVO" class="send"/>

            </form>

        </main>
        <script type="text/javascript">
            var inpt = document.querySelector(".team_id");
            var inpt2 = document.querySelector(".team_id2");


            function cambiarJugador(valor) {
                inpt.innerHTML = valor;
                inpt2.value = valor;
            }
        </script>
    </body>
</html>

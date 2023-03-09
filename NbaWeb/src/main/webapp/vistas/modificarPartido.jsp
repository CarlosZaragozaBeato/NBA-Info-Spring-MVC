<%-- 
    Document   : modificarPartido
    Created on : 5 mar. 2023, 11:39:20
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Partido</title>
          <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/partidos/nuevoPartido.css"/>
    </head>
    <body>

        <header>
            <h1>Modificar Partido</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../partidos/">Partidos</a></li>
                    <li><a href="../equipos/">Equipos</a></li>
                </ul>
            </nav>
        </header>

        <main>
            <form method="POST" action="modificarPartido" class="container">

                <div class="form_header">
                    <div class="match_info">
                        <p>ID: <input type="text" name="idPartido" value="${elPartido.idPartido}"/></p>
                        <p>Temporada <input type="text" name="temporada" value="${elPartido.temporada}"/> </p>
                    </div>


                    <input type="submit" value="MODIFICAR" class="send"/>
                </div>



                <div class="local_player">

                    <h2>EQUIPO LOCAL</h2>
                    <div class="local_player_info">
                        <p>Puntos Local: <input type="text" name="puntosLocal" value="${elPartido.puntosLocal}"/></p>
                        <p>Equipo ID: <span class="local_team_id">${elPartido.idEquipoLocal.idEquipo}</span></p>
                        <input type="text" name="idEquipoLocal" value="${elPartido.idEquipoLocal.idEquipo}" class="team_id_local">
                    </div>


                    <div class="image_selection_local">
                        <c:forEach  var="equipo"  items="${lstEquipos}" >
                            <div class="image_child">
                                <img src="${equipo.imagen}"/> 

                                <input type="radio" name="teams" value="${equipo.imagen}"
                                       onclick="cambiarLocal('${equipo.idEquipo}')" 
                                       class="radio">

                            </div>
                        </c:forEach>
                    </div>
                </div>



                <div class="visitante_player">
                    <h2>EQUIPO VISITANTE</h2>
                    <div class="visitante_player_info">
                        <p>Puntos Visitante: <input type="text" name="puntosVisitante" value="${elPartido.puntosVisitante}"/></p>
                        <p>Equipo ID: <span class="visitante_team_id">${elPartido.idEquipoVisitante.idEquipo}</span></p>
                        <input type="text" name="idEquipoVisitante" value="${elPartido.idEquipoVisitante.idEquipo}" class="team_id_visitante">
                    </div>


                    <div class="image_selection_visitante">
                        <c:forEach  var="equipo"  items="${lstEquipos}" >
                            <div class="image_child">
                                <img src="${equipo.imagen}"/> 

                                <input type="radio" name="teams" value="${equipo.imagen}"
                                       onclick="cambiarVisitante('${equipo.idEquipo}')" 
                                       class="radio">

                            </div>
                        </c:forEach>
                    </div>
                </div>


            </form>

        </main>



        <script type="text/javascript">
            var local = document.querySelector(".team_id_local");
            var mensajeLocal = document.querySelector(".local_team_id");

            var visitante = document.querySelector(".team_id_visitante");
            var mensajeVisitante = document.querySelector(".visitante_team_id");


            function cambiarLocal(valor) {
                local.value = valor;
                mensajeLocal.innerHTML = valor;
            }
            function cambiarVisitante(valor) {
                visitante.value = valor;
                mensajeVisitante.innerHTML = valor;
            }
        </script>

    </body>
</html>

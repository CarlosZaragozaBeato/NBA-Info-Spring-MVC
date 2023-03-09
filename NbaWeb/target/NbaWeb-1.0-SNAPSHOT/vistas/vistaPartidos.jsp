<%-- 
    Document   : vistaPartidos
    Created on : 5 mar. 2023, 9:38:06
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Partidos</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/partidos/partidos.css"/>
    </head>
    <body>



        <header>
            <h1>PARTIDOS</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../equipos/">Equipos</a></li>
                </ul>
            </nav>
        </header>


        <main>
            <a href="irNuevoPartido" class="nuevo">NUEVO PARTIDO</a>

            <div class="match_section">

                <c:forEach  var="partido"  items="${lstPartidos}" >

                    <c:url var="linkActualizar" value="irModificarPartido">
                        <c:param name="idPartido" value="${partido.idPartido}"></c:param>
                    </c:url>


                    <c:url var="linkEliminar" value="eliminarPartido">
                        <c:param name="idPartido" value="${partido.idPartido}"></c:param>
                    </c:url>

                    <div class="match_card">

                        <div class="match_data">
                            <div class="team_data">
                                <img src="${partido.idEquipoLocal.imagen}"/>    
                                <p>${partido.idEquipoLocal.nombre}</p>
                            </div>

                            <div class="match_points">
                                <h3>${partido.puntosLocal}</h3>
                                <p>:</p>
                                <h3>${partido.puntosVisitante}</h3>
                            </div>


                            <div class="team_data"> 
                                <img src="${partido.idEquipoVisitante.imagen}"/>    
                                <p>${partido.idEquipoVisitante.nombre}</p>
                            </div>



                        </div>









                        <div class="match_actions">
                            <a href="${linkActualizar}">Modificar</a>
                            <a href="${linkEliminar}" class="eliminar">Eliminar</a>
                        </div>
                    </div>



                </div>



            </c:forEach>
        </div>

    </main>

</body>
</html>

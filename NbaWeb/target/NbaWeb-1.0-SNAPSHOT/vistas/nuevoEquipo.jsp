<%-- 
    Document   : nuevoEquipo
    Created on : 3 mar. 2023, 14:08:56
    Author     : usumaniana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Equipo</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/index.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/styles/equipos/nuevoEquipo.css"/>


    </head>
    <body>




        <header>
            <h1>Nuevo EQUIPO</h1>
            <nav>
                <ul>
                    <li><a href="../jugadores/">Jugadores</a></li>
                    <li><a href="../partidos/">Partidos</a></li>
                    <li><a href="../equipos/">Equipos</a></li>

                </ul>
            </nav>
        </header>



        <main>

            <form:form  action="nuevoEquipo" modelAttribute="elEquipo" class="container">

                <div class="form_header">
                    <div class="image_container">
                        <img src="https://i.postimg.cc/bJ1ntbTS/team.png" alt="logo actual" class="current"/>
                        <form:input path="imagen"  class="inptImage"/>
                    </div>

                    <div class="form">
                        <p>ID: <form:input path="idEquipo" /></p>
                        <p>Nombre: <form:input path="nombre"/> </p>
                        <p>Pais: <form:input path="pais"/>   </p>
                        <p>Conferencia <form:input path="conferencia"/></p>

                    </div>
                </div>

                <div class="image_selection">


                    <c:forEach  var="equipo"  items="${lstEquipos}" >
                        <c:if test="${equipo.getImagen().length()!=0}"> 
                            <div class="image_child">
                                <img src="${equipo.imagen}"/> 
                                <form:radiobutton path="imagen" value="${equipo.imagen}"
                                                  onclick="cambiarImage('${equipo.imagen}')" class="radio"/>
                            </div>
                        </c:if>

                    </c:forEach>
                </div>



                <br/>
                <input type="submit" value ="NUEVO" class="send"/>
            </form:form>



            <script type="text/javascript">
                var inpt = document.querySelector(".inptImage");

                var image = document.querySelector(".current");

                function cambiarImage(valor) {
                    inpt.value = valor;
                    image.src = valor;
                }
            </script>


        </main>
    </body>
</html>

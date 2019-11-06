<%-- 
    Document   : imc2
    Created on : Oct 29, 2019, 7:40:30 AM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>IMC</h1>
        <form action="imc2.jsp" method="post">
            Peso: <input type="number" id="peso" name="peso"> Kg<br>
            Altura: <input type="number" id="altura" name="altura"> cm<br>
            <input type="submit">
        </form>
        <br>
        <!-- Verificando se parametros foram enviados -->
        <c:if test="${not empty param.peso and not empty param.altura}">

            <!-- Lendo peso e altura -->
            <fmt:parseNumber var = "peso" type = "number" value = "${param.peso}" />
            <fmt:parseNumber var = "altura" type = "number" value = "${param.altura}" />

            <p>Peso : <c:out value = "${peso}" /> Kg</p>
            <p>Altura : <c:out value = "${altura}" /> cm</p>
            
            <!-- calculando IMC -->
            <c:set var="imc" value="${peso / (altura * altura / 100.0) * 100}"/>
            
            <!-- Saída -->
            <p>IMC : <c:out value = "${imc}" /></p>

        </c:if>

        <c:if test="${empty param.peso or empty param.altura}">
            Os valores 'peso' e/ou 'altura' não foram informados.
        </c:if>
            
    </body>
</html>

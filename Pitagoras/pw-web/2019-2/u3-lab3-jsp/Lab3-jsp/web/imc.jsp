<%-- 
    Document   : imc
    Created on : Oct 29, 2019, 7:40:30 AM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>IMC</h1>
        <form action="imc.jsp" method="post">
            Peso: <input type="number" id="peso" name="peso"> Kg<br>
            Altura: <input type="number" id="altura" name="altura"> cm<br>
            <input type="submit">
        </form>
        <br>
        <%
            log("Chegou no JSP");
            String sPeso = request.getParameter("peso");
            String sAltura = request.getParameter("altura");

            log(String.format("Peso: %s\t Altura: %s", sPeso, sAltura));

            if (sPeso != null && sAltura != null){
                int peso = Integer.parseInt(sPeso);
                int altura = Integer.parseInt(sAltura);
                log(String.format("Peso: %d\t Altura: %d", peso, altura));
                
                float imc = peso / (altura * altura / 100.0f) * 100;
                log("IMC: " + imc);
                %>
                
                <br><%= "Peso: " + peso%><br>
                <br><%= "Altura: " + altura%><br>
                <br><%= "IMC: " + String.format("%.2f", imc)%><br>
                <%
            }
        %>
    </body>
</html>

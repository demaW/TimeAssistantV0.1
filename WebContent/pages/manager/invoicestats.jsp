<%@ page contentType="text/html;charset=UTF-8" language="java"
                pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="com.java.task11.i18n.text"/>
<html lang="${language}">


<head>
    <title>Create invoice</title>
    <jsp:include page="header.jsp"/>

    <!-- GRAPH SCRIPT -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/amcharts/amcharts.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/amcharts/serial.js"></script>

    <script type="text/javascript">
        var chart;

        var chartData = [];

        function getBarChartData() {
            var table = document.getElementById('data');

            for (var c = 0, n = table.rows[0].cells.length; c < n; c++) {
                var estTime = parseInt(table.rows[0].cells[c].innerHTML, 10);
                var realTime = parseInt(table.rows[1].cells[c].innerHTML, 10);
                var name = table.rows[2].cells[c].innerHTML;

                chartData.push({
                    name: name,
                    real: realTime,
                    estimate: estTime
                });
            }
            table.style.display = "none"; // hide table
        }

        AmCharts.ready(function () {
            getBarChartData();
            // SERIAL CHART
            chart = new AmCharts.AmSerialChart();
            chart.dataProvider = chartData;
            chart.categoryField = "name";
            chart.startDuration = 1;
            chart.plotAreaBorderColor = "#DADADA";
            chart.plotAreaBorderAlpha = 1;
            // this single line makes the chart a bar chart
            chart.rotate = true;

            // AXES
            // Category
            var categoryAxis = chart.categoryAxis;
            categoryAxis.gridPosition = "start";
            categoryAxis.gridAlpha = 0.1;
            categoryAxis.axisAlpha = 0;

            // Value
            var valueAxis = new AmCharts.ValueAxis();
            valueAxis.axisAlpha = 0;
            valueAxis.gridAlpha = 0.1;
            valueAxis.position = "top";
            chart.addValueAxis(valueAxis);

            // GRAPHS
            // first graph
            var graph1 = new AmCharts.AmGraph();
            graph1.type = "column";
            graph1.title = "real";
            graph1.valueField = "real";
            graph1.balloonText = "real:[[value]]";
            graph1.lineAlpha = 0;
            graph1.fillColors = "#ADD981";
            graph1.fillAlphas = 1;
            chart.addGraph(graph1);

            // second graph
            var graph2 = new AmCharts.AmGraph();
            graph2.type = "column";
            graph2.title = "estimate";
            graph2.valueField = "estimate";
            graph2.balloonText = "estimate:[[value]]";
            graph2.lineAlpha = 0;
            graph2.fillColors = "#81acd9";
            graph2.fillAlphas = 1;
            chart.addGraph(graph2);

            // LEGEND
            var legend = new AmCharts.AmLegend();
            chart.addLegend(legend);

            chart.creditsPosition = "top-right";

            // WRITE
            chart.write("chartdiv");
        });
    </script>


</head>
<body>

<div id="chartdiv" style="width:500px; height:600px;"></div>

<!-- BAR CHART DATA -->
<table id="data">
    <tbody>

    <tr>
        <c:forEach var="est" items="${requestScope.esttime}">
        <td><c:out value="${est}"/></td>
        </c:forEach>
    <tr>
        <c:forEach var="real" items="${requestScope.realtime}">
            <td><c:out value="${real}"/></td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach var="name" items="${requestScope.uniqueusers}">
            <td><c:out value="${name}"/></td>
        </c:forEach>
    </tr>

    </tbody>
</table>

</body>
</html>
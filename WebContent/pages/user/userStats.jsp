<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="com.java.task11.i18n.text" />

<html lang="${language}">
<head>
<title>User Profile</title>
<jsp:include page="import.jsp" />

<!-- GRAPH SCRIPT -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/amcharts/amcharts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/amcharts/serial.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/amcharts/themes/light.js"></script>
<script type="text/javascript">
	var chart;
	var chartCursor;

	var chartLineData = [];
	//AmCharts.theme = AmCharts.themes.light;
	AmCharts.ready(function() {
		drawBarChart();
		drawLineChart();
	});

	function drawBarChart() {
		// SERIAL CHART
		chart = new AmCharts.AmSerialChart();
		chart.dataProvider = getBarChartData();
		chart.categoryField = "category";
		chart.marginRight = 20;
		chart.marginTop = 20;
		chart.marginLeft = 20;
		chart.marginBottom = 20;
		chart.autoMarginOffset = 0;

		// the following two lines makes chart 3D
		chart.depth3D = 0;
		chart.angle = 0;

		// AXES
		// category
		var categoryAxis = chart.categoryAxis;
		categoryAxis.dashLength = 5;
		categoryAxis.gridPosition = "start";

		// value
		var valueAxis = new AmCharts.ValueAxis();
		valueAxis.title = "Hours";
		valueAxis.dashLength = 5;
		chart.addValueAxis(valueAxis);

		// GRAPH            
		var graph = new AmCharts.AmGraph();
		graph.valueField = "value";
		graph.colorField = "color";
		graph.balloonText = "Finished tasks [[category]]: [[value]]";
		graph.type = "column";
		graph.lineAlpha = 0;
		graph.fillAlphas = 0.6;
		chart.addGraph(graph);

		// WRITE
		chart.write("barchart");
	}

	function getBarChartData() {
		var chartData = [];

		var table = document.getElementById('totalfinisheddata');

		var category = 'Real time';
		var value = parseInt(table.rows[0].cells[1].innerHTML, 10);
		var color = "#ADD981";
		chartData.push({
			category : category,
			value : value,
			color : color
		});

		var category = 'Estimate time';
		var value = parseInt(table.rows[1].cells[1].innerHTML, 10);
		var color = "#81acd9";
		chartData.push({
			category : category,
			value : value,
			color : color
		});

		table.style.display = "none"; // hide table

		return chartData;
	}

	// LINE CHART

	function drawLineChart() {
		// get some data first
		getLineChartData();
		// SERIAL CHART
		chart = new AmCharts.AmSerialChart();
		chart.pathToImages = location.protocol + '//' + location.host
				+ "/TimeAssistant/js/amcharts/images/";
		chart.dataProvider = chartLineData;
		chart.categoryField = "date";
		chart.balloon.bulletSize = 5;

		// listen for "dataUpdated" event (fired when chart is rendered) and call zoomChart method when it happens
		chart.addListener("dataUpdated", zoomChart);

		// AXES
		// category
		var categoryAxis = chart.categoryAxis;
		categoryAxis.parseDates = true; // as our data is date-based, we set parseDates to true
		categoryAxis.minPeriod = "DD"; // our data is daily, so we set minPeriod to DD
		categoryAxis.dashLength = 1;
		categoryAxis.minorGridEnabled = true;
		categoryAxis.twoLineMode = true;
		categoryAxis.dateFormats = [ {
			period : 'fff',
			format : 'JJ:NN:SS'
		}, {
			period : 'ss',
			format : 'JJ:NN:SS'
		}, {
			period : 'mm',
			format : 'JJ:NN'
		}, {
			period : 'hh',
			format : 'JJ:NN'
		}, {
			period : 'DD',
			format : 'DD'
		}, {
			period : 'WW',
			format : 'DD'
		}, {
			period : 'MM',
			format : 'MMM'
		}, {
			period : 'YYYY',
			format : 'YYYY'
		} ];

		categoryAxis.axisColor = "#DADADA";

		// value
		var valueAxis = new AmCharts.ValueAxis();
		valueAxis.axisAlpha = 0;
		valueAxis.dashLength = 1;
		valueAxis.title = "Hours";
		chart.addValueAxis(valueAxis);

		// GRAPH
		var graph = new AmCharts.AmGraph();
		graph.type = "smoothedLine";
		graph.title = "red line";
		graph.valueField = "visits";
		graph.bullet = "round";
		graph.bulletBorderColor = "#FFFFFF";
		graph.bulletBorderThickness = 2;
		graph.bulletBorderAlpha = 1;
		graph.lineThickness = 2;
		graph.lineColor = "#5fb503";
		graph.negativeLineColor = "#efcc26";
		graph.connect = false; // this makes the graph not to connect data points if data is missing
		graph.hideBulletsCount = 50; // this makes the chart to hide bullets when there are more than 50 series in selection
		chart.addGraph(graph);

		// CURSOR
		chartCursor = new AmCharts.ChartCursor();
		chartCursor.cursorPosition = "mouse";
		chartCursor.pan = true; // set it to fals if you want the cursor to work in "select" mode
		chart.addChartCursor(chartCursor);

		// SCROLLBAR
		var chartScrollbar = new AmCharts.ChartScrollbar();
		chart.addChartScrollbar(chartScrollbar);

		chart.creditsPosition = "bottom-right";

		// WRITE
		chart.write("linechartdiv");
	}

	function getLineChartData() {

		var table = document.getElementById('linedata');

		for (var r = 0, n = table.rows.length; r < n; r++) {
			var date = new Date(table.rows[r].cells[0].innerHTML);
			var visits = parseInt(table.rows[r].cells[1].innerHTML, 10);

			chartLineData.push({
				date : date,
				visits : visits
			});
		}

		table.style.display = "none"; // hide table	
	}

	// this method is called when chart is first inited as we listen for "dataUpdated" event
	function zoomChart() {
		// different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
		chart
				.zoomToIndexes(chartLineData.length - 40,
						chartLineData.length - 1);
	}

	// changes cursor mode from pan to select
	function setPanSelect() {
		if (document.getElementById("rb1").checked) {
			chartCursor.pan = false;
			chartCursor.zoomable = true;
		} else {
			chartCursor.pan = true;
		}
		chart.validateNow();
	}
</script>
</head>

<body>

	<jsp:include page="mainMenu.jsp" />
	<br />

<<<<<<< 03de9564aa2a01ee2979b0c85fa2546ee15f6065
	<!-- CONTENT -->
	<div class="container">
		<!-- BAR CHART -->
		<div id="barchart" class="col-md-4"
			style="width: 400px; height: 500px; font-size: 12px;"></div>
		<div class="col-md-6">
			<table class="table" style="margin-top: 70px;">
				<tr>
					<td>Estimate time total:</td>
					<td><c:out value="${estimate_time_summ}" /></td>
				</tr>
				<tr>
					<td>Real time total:</td>
					<td><c:out value="${real_time_summ}" /></td>
				</tr>
				<tr>
					<td>Ratio:</td>
					<td><c:out value="${estimate_time_summ * 100/real_time_summ}" /> %</td>
				</tr>
				<tr>
					<td>Worked days total:</td>
					<td>${fn:length(hours)}</td>
				</tr>
				<tr>
					<td>Saved hours:</td>
					<td>${estimate_time_summ - real_time_summ}</td>
				</tr>
				<tr>
					<td>Profit:</td>
					<td>${(estimate_time_summ - real_time_summ) * user.salaryRate}
						$</td>
				</tr>
			</table>
		</div>

		<div id="linechartdiv" style="width: 100%; height: 400px;"></div>
		<div style="margin-left: 35px;">
			<input type="radio" name="group" id="rb1" onclick="setPanSelect()">Select
			<input type="radio" checked="checked" name="group" id="rb2"
				onclick="setPanSelect()">Pan
		</div>
	</div>
=======
    <!-- CONTENT -->
    <div class="container">
        <!-- BAR CHART -->
        <div id="barchart" class="col-md-4"
             style="width: 400px; height: 500px; font-size: 12px;"></div>
        <div class="col-md-6">
            <table class="table" style="margin-top: 70px;">
                <tr>
                    <td>Estimate time total:</td>
                    <td><c:out value="${estimate_time_summ}" /></td>
                </tr>
                <tr>
                    <td>Real time total:</td>
                    <td><c:out value="${real_time_summ}" /></td>
                </tr>
                <tr>
                    <td>Ratio:</td>
                    <td><c:out value="${estimate_time_summ * 100/real_time_summ}" /> %</td>
                </tr>
                <tr>
                    <td>Worked days total:</td>
                    <td>${fn:length(hours)}</td>
                </tr>
                <tr>
                    <td>Saved hours:</td>
                    <td>${estimate_time_summ - real_time_summ}</td>
                </tr>
                <tr>
                    <td>Profit:</td>
                    <td>${(estimate_time_summ - real_time_summ) * user.salaryRate}
                        $</td>
                </tr>
            </table>
        </div>

        <div id="linechartdiv" style="width: 100%; height: 400px;"></div>
        <div style="margin-left: 35px;">
            <input type="radio" name="group" id="rb1" onclick="setPanSelect()">Select
            <input type="radio" checked="checked" name="group" id="rb2"
                   onclick="setPanSelect()">Pan
        </div>
    </div>



    <!-- BAR CHART DATA -->
>>>>>>> 49dc31138aa78094f07b82ca218bf38f24271542



	<!-- BAR CHART DATA -->
	<table id="totalfinisheddata">
		<tbody>
			<tr>
				<td>estimate</td>
				<td><c:out value="${estimate_time_summ}" /></td>
			</tr>
			<tr>
				<td>real</td>
				<td><c:out value="${real_time_summ}" /></td>
			</tr>
		</tbody>
	</table>

	<!-- LINE CHART DATA -->
	<table id="linedata">
		<tbody>
			<c:forEach var="hour" items="${requestScope.hours}">
				<tr>
					<td><fmt:formatDate value="${hour.date}" pattern="yyyy,MM,dd" /></td>
					<td><c:out value="${hour.hours}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
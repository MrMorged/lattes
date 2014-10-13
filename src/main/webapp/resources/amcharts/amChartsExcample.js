/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var myChart = new AmCharts.AmSerialChart(); //objeto gráfico

$(document).ready(function() {
    // Initialize Graph:
    initGraph();
});

// toma como parametro os valores fornecidos pelo ManageBean
function drawMyGraph(xhr, status, args) {
    chartData = args.MYGRAPH;
    myChart.dataProvider = eval(chartData);
    myChart.validateData();
}

function initGraph()
{
    var chartData = [];

    //SERIAL CHART
    myChart = new AmCharts.AmSerialChart();
    myChart.dataProvider = chartData;
    myChart.categoryField = "x";
    myChart.balloon.bulletSize = 3;

    // AXES
    // category
    var categoryAxis = myChart.categoryAxis;
    categoryAxis.gridAlpha = 0.10;
    categoryAxis.axisAlpha = 0;
    categoryAxis.gridPosition = "start";

    // value
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.gridAlpha = 0.1;
    valueAxis.axisAlpha = 0;
    myChart.addValueAxis(valueAxis);

    // GRAPHS
    // first graph
    var graph = new AmCharts.AmGraph();
    graph.title = "Y1";
    graph.valueField = "y1";
    graph.bullet = "round";
    graph.bulletSize = 2;
    graph.bulletBorderThickness = 2;
    graph.lineThickness = 2;
    graph.lineColor = "#5fb503";
    myChart.addGraph(graph);

    // second graph 
    var graph2 = new AmCharts.AmGraph();
    graph2.title = "Y2";
    graph2.valueField = "y2";
    graph2.bullet = "round";
    graph2.bulletSize = 2;
    graph2.bulletBorderThickness = 2;
    graph2.lineThickness = 2;
    graph2.lineColor = "#efcc26";
    myChart.addGraph(graph2);

    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.borderAlpha = 0.2;
    legend.valueWidth = 60;
    legend.horizontalGap = 10;
    myChart.addLegend(legend);

    myChart.write('graphContainer');
}


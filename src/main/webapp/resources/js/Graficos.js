
/*
 * GRAFICOS
 *
 */

function pizza(valor) {
    var t = new Array(valor);
    //t[0].push("graduandos");
    //valor.
    var texto = document.getElementById("divGrafico");
    if (valor.length < 1) {
        var texto = document.getElementById("divGrafico");
        texto.style.dysplay = "inline-block";
        texto.style.position = "relative";
        texto.style.textAlign = "center";
        texto.style.marginTop = "13%";
        texto.innerHTML = "Não existem referências";
    }
    else {
        texto.style.marginTop = "3%";
        //document.getElementById("divGrafico").value = "";
        console.log(t);
        var chart = new AmCharts.AmPieChart();
        chart.dataProvider = valor;
        chart.labelText = "[[title]]:[[value]]"
        chart.balloonText = "[[title]]:([[value]])"
        chart.titleField = "1";
        chart.valueField = "0";
        chart.outlineColor = "#FFFFFF";
        chart.outlineAlpha = 0.8;
        chart.outlineThickness = 2;

        var balloon = chart.balloon;
        balloon.adjustBorderColor = true;
        balloon.color = "#000000";
        balloon.cornerRadius = 5;
        balloon.fillColor = "#FFFFFF";

        // this makes the chart 3D
        chart.depth3D = 15;
        chart.angle = 30;
        var legend = new AmCharts.AmLegend();
        legend.markerType = "square";
        legend.labelText = "[[title]]";
        legend.position = "right";
        //chart.addLegend(legend);
        // WRITE
        chart.write("divGrafico");
    }


    //$("divGrafico").text("Teste");

}
function barra(valor) {
    var t = new Array(valor);
    var texto = document.getElementById("divGrafico2");
    if (valor.length < 1) {
        texto.style.dysplay = "inline-block";
        texto.style.position = "relative";
        texto.style.textAlign = "center";
        texto.style.marginTop = "13%";
        texto.innerHTML = "Não existem referências";
    }
    else {
        texto.style.marginTop = "3%";
        console.log(t);
        chart = new AmCharts.AmSerialChart();
        chart.pathToImages = "resource/amcharts/images/";
        chart.dataProvider = valor;
        chart.categoryField = "1";
        chart.startDuration = 1;

        var balloon = chart.balloon;
        balloon.adjustBorderColor = true;
        balloon.color = "#000000";
        balloon.cornerRadius = 5;
        balloon.fillColor = "#FFFFFF";

        var categoryAxis = chart.categoryAxis;
        categoryAxis.gridPosition = "start";

        var graph1 = new AmCharts.AmGraph();
        graph1.type = "column";
        graph1.title = "1";
        graph1.valueField = "0";
        graph1.lineAlpha = 0;
        graph1.fillAlphas = 1;
        //graph1.fillColors = "#ADD981";
        chart.addGraph(graph1);

        // line
        var graph2 = new AmCharts.AmGraph();
        graph2.type = "line";
        graph2.title = "1";
        graph2.valueField = "0";
        graph2.lineThickness = 2;
        graph2.bullet = "round";
        chart.addGraph(graph2);

        // LEGEND                
        var legend = new AmCharts.AmLegend();
        //chart.addLegend(legend);

        chart.write("divGrafico2");
    }

}
function novaPizza(valor,titulacao) {
    var t = new Array(valor);
    if(titulacao == 1){
        t[0].push("Graduados");
    }
    else if(titulacao == 2){
        t[0].push("Especialistas");
    }
    else if(titulacao == 3){
        t[0].push("Mestres");
    }
    else if(titulacao == 4){
        t[0].push("Doutores");
    }
    else if(titulacao == 5){
        t[0].push("Ciências Agrárias");
    }
    else if(titulacao == 6){
        t[0].push("Ciências Biológicas");
    }
    else if(titulacao == 7){
        t[0].push("Ciências da Saúde");
    }
    else if(titulacao == 8){
        t[0].push("Ciências Exatas e da Terra");
    }
    else if(titulacao == 9){
        t[0].push("Ciências Humanas");
    }
    else if(titulacao == 10){
        t[0].push("Ciências Sociais Aplicadas");
    }
    else if(titulacao == 11){
        t[0].push("Engenharias");
    }
    else if(titulacao == 12){
        t[0].push("Linguística, Letras e Artes");
    }
    else if(titulacao == 13){
        t[0].push("Multidisciplinar");
    }
    var texto = document.getElementById("divGrafico");
    if (t[0][0] == 0) {
        var texto = document.getElementById("divGrafico");
        texto.style.dysplay = "inline-block";
        texto.style.position = "relative";
        texto.style.textAlign = "center";
        texto.style.marginTop = "13%";
        texto.innerHTML = "Não existem referências";
    }
    else {
        texto.style.marginTop = "3%";
        //document.getElementById("divGrafico").value = "";
        console.log(t[0]);
        var chart = new AmCharts.AmPieChart();
        chart.dataProvider = t;
        chart.labelText = "[[value]]";
        chart.balloonText = "[[title]]:([[value]])";
        chart.titleField = "1";
        //chart.startEffect = "elastic";
        chart.valueField = "0";
        chart.outlineColor = "#FFFFFF";
        chart.outlineAlpha = 0.8;
        chart.outlineThickness = 2;

        var balloon = chart.balloon;
        balloon.adjustBorderColor = true;
        balloon.color = "#000000";
        balloon.cornerRadius = 5;
        balloon.fillColor = "#FFFFFF";
        //balloon.textAlign = "right";

        // this makes the chart 3D
        chart.depth3D = 15;
        chart.angle = 30;
        //var legend = new AmCharts.AmLegend();
        //legend.markerType = "square";
        //legend.labelText = "[[title]]";
        //legend.position = "right";
        //chart.addLegend(legend);
        // WRITE
        chart.write("divGrafico");
    }
}


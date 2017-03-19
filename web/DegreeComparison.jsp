<%-- 
    Document   : DegreeComparison
    Created on : 5 Feb, 2017, 2:25:50 PM
    Author     : txsing
--%>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="propertycalculation.InOutDegreeDisCal" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String scaledFilePath = session.getAttribute("uploadDir").toString().concat("scaled.txt");
    String oriFilePath = session.getAttribute("uploadedFilePath").toString();

    int scaledNodeSize = Integer.parseInt(session.getAttribute("scaledNodeSize").toString());
    int oriNodeSize = Integer.parseInt(session.getAttribute("originalnode").toString());

    String outDegreePlotString = InOutDegreeDisCal.computeInOutDegreeDistribution(oriFilePath, scaledFilePath,
            oriNodeSize, scaledNodeSize, 0);
    String inDegreePlotString = InOutDegreeDisCal.computeInOutDegreeDistribution(oriFilePath, scaledFilePath,
            oriNodeSize, scaledNodeSize, 1);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>In/Out Degree Distribution</title>
        <link rel="nus icon" type="image/vnd.microsoft.icon" href="favicon.ico"/>
        <style>
            body{text-align: center}
            .chartdiv{ margin:0 auto; width:400px; height:100px;} 
        </style>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['line']});
            google.charts.setOnLoadCallback(init);
            var rawinplotdata = <%=inDegreePlotString%>;
            var rawoutplotdata = <%=outDegreePlotString%>;
            function init(){
                drawChart(rawinplotdata, 'In');
                drawChart(rawoutplotdata, 'Out');
            }
            
            function drawChart(plotdata, inOrOut) {
                var data = new google.visualization.DataTable();
                data.addColumn('string', inOrOut.concat(' Degree'));
            
                data.addColumn('number', 'raw graph');
                data.addColumn('number', 'scaled graph');
                data.addRows(plotdata);
            
                var options = {
                    chart: {
                        title: inOrOut.concat('Degree Distribution')
                        },
                    width: 900,
                    height: 500
                };
                var chart = new google.charts.Line(document.getElementById(inOrOut));
                chart.draw(data, options);
            
            }
            function replot(inOrOut){
                var replotdata;
                var low = document.getElementById(inOrOut.concat('Low')).value;
                var high = document.getElementById(inOrOut.concat('High')).value;
                if(inOrOut === 'In'){
                    replotdata = truncateAxe(rawinplotdata, low, high);
                    console.log(replotdata);
                }else{
                    replotdata = truncateAxe(rawoutplotdata, low, high);
                }
                drawChart(replotdata, inOrOut);               
            }
                
            function truncateAxe(rawdata, low, high){
                var replotdata;
                console.log(low);
                console.log(high);
                console.log(rawdata.length);
                if (low < high && low >= 0 && high <= rawdata.length){
                    replotdata = rawdata.slice(low, high+1);
                    return replotdata;
                } else{
                    alert("Wrong Input!");
                }

            }
        </script>
    </head>
    <body>
        <div class="chartdiv" id="In" style="width: 900px; height: 500px"></div>
        <input id="InLow" name="name" type="number" required>
        <input id="InHigh" name="name" type="number" required>
        <button onclick="replot('In')">Replot</button>
        <br>
        <div class="chartdiv" id="Out" style="width: 900px; height: 500px"></div>
    </body>
</html>

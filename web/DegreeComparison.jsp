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
//    String outDegreePlotString = "[[0, 0, 0],[1, 0.8, 0.5],[2, 0, 0.2],[3, 0.2, 0.1]]";

%>
<!DOCTYPE html>
  <html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
            google.charts.load('current', {'packages':['line']});
      google.charts.setOnLoadCallback(drawChart);

    function drawChart() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', 'Degree');
      data.addColumn('number', 'raw graph');
      data.addColumn('number', 'scaled graph');

      data.addRows(<%=inDegreePlotString%>);

      var data1 = new google.visualization.DataTable();
      data1.addColumn('number', 'Degree');
      data1.addColumn('number', 'raw graph');
      data1.addColumn('number', 'scaled graph');

      data1.addRows(<%=outDegreePlotString%>);
      
      var options = {
        chart: {
          title: 'In Degree Distribution',         
        },
        width: 900,
        height: 500
      };
      
      var options1 = {
        chart: {
          title: 'Out Degree Distribution',
        },
        width: 900,
        height: 500
      };

      var chart = new google.charts.Line(document.getElementById('linechart_material'));

      chart.draw(data, options);
      
      var chart = new google.charts.Line(document.getElementById('linechart_material1'));

      chart.draw(data1, options1);
    }
    </script>
  </head>
  <body>
    <div id="linechart_material" style="width: 900px; height: 500px"></div>
    <br>
    <div id="linechart_material1" style="width: 900px; height: 500px"></div>
  </body>
</html>

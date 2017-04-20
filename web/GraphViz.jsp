<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="backtofront.*" %>
<!DOCTYPE>
<%
    String rawFilePath = session.getAttribute("uploadedFilePath").toString();
    int scaledNodeSize = Integer.parseInt(request.getParameter("snsize").toString());
    int rawNodeSize = Integer.parseInt(request.getParameter("onsize").toString());
    String[] rawNEJson = BasicStatisticCal.getGraphVizJson(rawFilePath, rawNodeSize);
    
    String scaledFilePath = session.getAttribute("uploadDir").toString().concat("scaled.txt");
    String[] scaledNEJson = BasicStatisticCal.getGraphVizJson(scaledFilePath, scaledNodeSize);  
%>
<html>

    <head>
        <title>Scaled Graph Visualization</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
        <link rel="nus icon" type="image/vnd.microsoft.icon" href="favicon.ico"/>
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/cytoscape.min.js"></script>
        <script src="js/dagre.min.js"></script>
        <script src="js/cytoscape-dagre.js"></script>
        <style>
            body {
                font-family: helvetica;
                font-size: 14px;
            }
            #cy {
                float:left; border:2px solid #334d66;
                width: 48.9%;
                height: 93%;
                left: 0;
                top: 0;
                z-index: 999;
            }
            #cx {
                float:right; border:2px solid #bf5a24;      
                width: 48.9%;
                height: 93%;
                left: 0;
                top: 0;
                z-index: 999;
            }
        </style>
        <script>
            $(function () {
                var nodejson = <%=rawNEJson[0]%>;
                console.log(nodejson);
                var edgejson = <%=rawNEJson[1]%>;
                var cy = window.cy = cytoscape({
                    container: document.getElementById('cy'),

                    boxSelectionEnabled: false,
                    autounselectify: true,

                    layout: {
                        name: 'dagre'
                    },

                    style: [
                        {
                            selector: 'node',
                            style: {
                                'content': 'data(id)',
                                'text-opacity': 0.5,
                                'text-valign': 'center',
                                'text-halign': 'right',
                                'background-color': '#334d66'
                            }
                        },

                        {
                            selector: 'edge',
                            style: {
                                'width': 4,
                                'target-arrow-shape': 'triangle',
                                'line-color': '#bf5a24',
                                'target-arrow-color': '#bf5a24',
                                'curve-style': 'bezier'
                            }
                        }
                    ],

                    elements: {
                        nodes: nodejson,
                        edges: edgejson
                    },
                });

            });
        </script> 
        <script>
            $(function () {
                var nodejson = <%=scaledNEJson[0]%>;
                var edgejson = <%=scaledNEJson[1]%>;
                var cx = window.cx = cytoscape({
                    container: document.getElementById('cx'),

                    boxSelectionEnabled: false,
                    autounselectify: true,

                    layout: {
                        name: 'dagre'
                    },

                    style: [
                        {
                            selector: 'node',
                            style: {
                                'content': 'data(id)',
                                'text-opacity': 0.5,
                                'text-valign': 'center',
                                'text-halign': 'right',
                                'background-color': '#bf5a24'
                            }
                        },

                        {
                            selector: 'edge',
                            style: {
                                'width': 4,
                                'target-arrow-shape': 'triangle',
                                'line-color': '#334d66',
                                'target-arrow-color': '#334d66',
                                'curve-style': 'bezier'
                            }
                        }
                    ],

                    elements: {
                        nodes: nodejson,
                        edges: edgejson
                    }
                });

            });
        </script> 
    </head>

    <body>
        <h1 style="color:#334d66; text-align:center; font-size: 20px">Original (Left) & Scaled (Right) Graph Visualization</h1>
        <div id="cy"></div>  
        <div id="cx"></div>
    </body>

</html>

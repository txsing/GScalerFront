<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<!DOCTYPE>
<%
    String scaledFilePath = session.getAttribute("uploadDir").toString().concat("scaled.txt");
    int scaledNodeSize = Integer.parseInt(session.getAttribute("scaledNodeSize").toString());
    String nodeJson = "[";
    for(int i = 0; i < scaledNodeSize - 1; i++){
        nodeJson = nodeJson + "{data: {id: \'" + i + "\'}},\n";
    }
    nodeJson = nodeJson.concat("{data: {id: \'" + (scaledNodeSize-1) + "\'}}]");
    BufferedReader br = new BufferedReader(new FileReader(scaledFilePath));
    
    String edgeJson = "";
    String line = "";
    while((line = br.readLine())!=null){
        String[] twoV = line.split("\\s+");
        edgeJson = edgeJson + ",\n{data: {source: \'" + twoV[0] + "\', target: \'"
                + twoV[1] + "\'}}";
    }
    edgeJson = "["+edgeJson.substring(2)+"]";
%>
<html>

    <head>
        <title>Scaled Graph Visualization</title>

        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">

        <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
        <script src="http://cytoscape.github.io/cytoscape.js/api/cytoscape.js-latest/cytoscape.min.js"></script>

        <!-- for testing with local version of cytoscape.js -->
        <!--<script src="../cytoscape.js/build/cytoscape.js"></script>-->

        <script src="https://cdn.rawgit.com/cpettitt/dagre/v0.7.4/dist/dagre.min.js"></script>
        <script src="https://cdn.rawgit.com/cytoscape/cytoscape.js-dagre/1.1.2/cytoscape-dagre.js"></script>

        <style>
            body {
                font-family: helvetica;
                font-size: 14px;
            }

            #cy {
                width: 100%;
                height: 100%;
                position: absolute;
                left: 0;
                top: 0;
                z-index: 999;
            }

            h1 {
                opacity: 0.5;
                font-size: 1em;
            }
        </style>

        <script>
            $(function () {
                var nodejson = <%=nodeJson%>;
                var edgejson = <%=edgeJson%>;
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
                    },
                });

            });
        </script>
        
            
            
    </head>

    <body>
        <h1>Scaled Graph Visualization</h1>
        <div id="cy"></div>
    </body>

</html>

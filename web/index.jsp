<%@page import="java.io.*"%>
<%@page import="propertycalculation.*" %>
<%@page import="backtofront.*" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html lang="en">
    <!-- header -->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>GScaler Cloud</title>
        <link rel="nus icon" type="image/vnd.microsoft.icon" href="favicon.ico"/>
        <link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
        <link href="fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/app.css">
        <link href="fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/base.css">
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="js/FileSaver.js" type="text/javascript"></script>
        <script src="fileinput/js/fileinput.js" type="text/javascript"></script>
        <script src="bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    </head>

    <!-- body -->
    <body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">
        <!-- Container (Whole Page) -->
        <div style="padding: 0px" class="container">
            <!-- Top -->
            <div class="top">
                <a href="#home"><img id="rotate" src="img/top.png" width="70" height="70" alt="top" /></a>
            </div>

            <!-- Nav Bar-->
            <nav class="navbar navbar-default navbar-fixed-top">
                <div style="margin-left: 20px" class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#myPage">
                        <p style="font-style:italic; font-weight: 700;">gScaler</p>
                    </a>
                </div>
                <div style="margin-right: 20px" class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#about">ABOUT</a>
                        </li>
                        <li>
                            <a href="#draw">DRAW</a>
                        </li>
                        <li>
                            <a href="#uploadAndScale">UPLOAD & SCALE</a>
                        </li>
                        <li>
                            <a href="#analysis">ANALYSIS</a>
                        </li>
                        <li>
                            <a href="#contact">CONTACT</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- Jumbotron -->
            <div style="border-radius: 0px;" id="home" class="jumbotron text-center">
                <h1>GScaler Cloud</h1>
                <p>GScaler is a tool to scale your graph up or down while guarantee the features of resulting graph <br> similar to original one.</p>
            </div>

            <!-- Container (About Section) -->
            <div id="about" class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <h2>About GScaler</h2><br>
                        <h4>
                            Enterprises and researchers often have datasets that can be represented as graphs (e.g. social networks).
                            The owner of such a graph may want to scale it down/up to a similar but smaller/larger version for some purposes
                            (e.g. for application development/system scalability test and so on). 
                        </h4><br>
                        <p>
                            Gscaler is the tool designed for such scaling, developed by students from National University of Singapore, School of Computing.<br> Related demo paper is accepted by VLDB 2017:
                            <a href="https://drive.google.com/file/d/0B8OtlmDMlRZnUllSTThPWm9kRm8/view?usp=sharing" target="_blank">
                                GscalerCloud: A Web-Based Graph Scaling Service
                            </a><br> Research paper is published in EDBT 2016:
                            <a href="https://openproceedings.org/2016/conf/edbt/paper-68.pdf" target="_blank">
                                GSCALER: Synthetically Scaling A Given Graph
                            </a>

                        </p>
                        <br>
                        <a class="btn btndow" href="#draw">Try It Now</a>
                    </div>
                    <div class="col-md-4">
                        <br><br><br>
                        <div align="center"><img src="img/logo.png"></div>
                    </div>
                </div>
            </div>

            <!-- Container (Draw Section) -->
            <div id="draw" class="container-fluid bg-grey">
                <div class="row">
                    <div class="col-md-7">
                        <div class="panel panel-default text-center">
                            <div class="panel-heading">
                                <h2 style="color: #FFFFFF; margin: 0px;">DRAW GRAPH NOW</h2>
                            </div>

                            <div id="drawarea" class="panel-body">
                                <script type="text/javascript" src="js/jquery.js "></script>
                                <script type="text/javascript" src="js/min.js "></script>
                                <p style="display:none; " id='main'></p>
                                <script type="text/javascript" src="js/app.js "></script>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5 text-left">
                        <h2 style="color:#334d66;"> How To Use </h2>
                        <ol class="notes">
                            <li>The graph is <strong>DIRECTED</strong>, bidirectional arrow can not be used to represent
                                undirected relation.</li>
                            <br>
                            <li>Click in the open space to add a node, <strong>Ctrl-Drag</strong> a node to move to adjust graph layout.</li>
                            <br>
                            <li>When a node is selected: press <strong>'Ctrl-Del'</strong> to remove it.</li>
                            <br>
                            <li>When an edge is selected: press <strong>'Ctrl-L'</strong>(eft), <strong>'Ctrl-R'</strong>(ight) to change direction, press <strong>'Ctrl-Del'</strong> removes the edge.</li>
                            <br>
                            <li>Click Download button to download the graph your drew as text file.</li>
                            <br>
                            <li>You can also directly upload your own graph file without drawing one, just follow the file iformat instructions given in 
                                <strong>UPLOAD</strong> session.</li>
                        </ol>
                        <br>
                        <div class="row">
                            <div class="col-md-5">
                                <button style="padding: 8px 16px;" class="btn btndow btn-lg" id="downloadLink" onclick="downloadGraphFile()">
                                    Download Graph
                                </button>
                                <script>
                                    function downloadGraphFile() {
                                        testTry();
                                        loadInnerHtml('edgelist.txt', 'main', 'text/html');
                                    }

                                    function loadInnerHtml(filename, elId, mimeType) {
                                        var elHtml = document.getElementById(elId).innerHTML;
                                        var link = document.createElement('a');
                                        mimeType = mimeType || 'text/plain';
                                        link.setAttribute('download', filename);
                                        link.setAttribute('href', 'data:' + mimeType + ';charset=utf-8,' + encodeURIComponent(elHtml));
                                        link.click();
                                    }
                                </script>
                            </div>
                            <div class="col-md-7" align="left">
                                <form id="autoUploadForm" action="AutoUpload.jsp" method="post" style="display: none">
                                    <input id="GraphString" name="graphString" type="text">
                                </form>
                                <button style="padding: 8px 16px;" class="btn btndow btn-lg" id="uploadlink" onclick="autoUploadGrpahFile()">
                                    Upload &amp; Scale Graph
                                </button>
                                <script>
                                    function autoUploadGrpahFile() {
                                        testTry();
                                        var elHtml = document.getElementById("main").innerHTML;
                                        console.log(elHtml);
                                        document.getElementById("GraphString").value = elHtml;
                                        document.getElementById("autoUploadForm").submit();
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- (Java Code) Raw Graph Analysis -->
            <%
                Boolean isUploaded = false;
                String uploadedFilePath = "";
                String uploadDir = "";

                String edge = "N/A";
                String node = "N/A";

                String avpl_ori = "N/A";
                String dia_ori = "N/A";

                String coc_ori = "N/A";
                try {
                    uploadedFilePath = session.getAttribute("uploadedFilePath").toString();//gets the path of the file which was uploaded
                    if (!(uploadedFilePath == null || uploadedFilePath.equals(""))) {
                        int[] rawGNandEsize = NodeEdgeSizeCal.getNodeAndEdgeSize(uploadedFilePath);
                        if (rawGNandEsize != null) {
                            node = Integer.toString(rawGNandEsize[0]);
                            edge = Integer.toString(rawGNandEsize[1]);
                        }

                        session.setAttribute("originalnode", node);
                        session.setAttribute("originaledge", edge);

                        uploadDir = session.getAttribute("uploadDir").toString();
                        String oriFilPath = uploadDir.concat("ori.txt");

                        String[] rawAvplAndDia = BasicStatisticCal
                                .getEffectiveDiaAndAvgShortestPathLen(uploadedFilePath, oriFilPath);

                        avpl_ori = rawAvplAndDia[0];
                        dia_ori = rawAvplAndDia[1];

                        coc_ori = BasicStatisticCal.getAvgClusteringCof(uploadedFilePath, oriFilPath, uploadDir);
                        isUploaded = true;
                    }
                } catch (Exception e) {
                    System.err.println("EXP(rawAnls): " + e.getMessage());
                    System.err.println(e.getStackTrace()[0]);
                }
            %> 

            <!-- Container (File Upload & Scale Section) -->
            <div id="uploadAndScale" class="container-fluid">
                <div class="row ">
                    <div class="col-sm-7 ">
                        <h2>Upload &amp; Scale Graph</h2>
                        <form action="UploadGraph.jsp" method="post"                         
                              enctype="multipart/form-data">
                            <input id="input-1a" type="file" name="file" class="file" data-show-preview="false">                       
                        </form>
                        <br>
                        <form id="scaleForm" action="ScaleGraph.jsp" metho="post">
                            <span>Node size scale from &nbsp;&nbsp;<%=node%> &nbsp;&nbsp;to&nbsp;</span>
                            <input id="nodes" name="scaledNodeSize"
                                   onkeyup="if (this.value.length === 1) {
                                               this.value = this.value.replace(/[^1-9]/g, '')
                                           } else {
                                               this.value = this.value.replace(/\D/g, '');
                                           }"  
                                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'');}"
                                   placeholder=" &nbsp;&nbsp;&nbsp;Input Scaled Node Size"/>
                            <br>
                            <span>Edge size scale from &nbsp;&nbsp;&nbsp;<%=edge%> &nbsp;&nbsp;to&nbsp;</span>
                            <input id="edges" name="scaledEdgeSize"
                                   onkeyup="if (this.value.length === 1) {
                                               this.value = this.value.replace(/[^1-9]/g, '')
                                           } else {
                                               this.value = this.value.replace(/\D/g, '');
                                           }"  
                                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"
                                   placeholder=" &nbsp;&nbsp;&nbsp;Input Scaled Edge Size">
                            <br><br>
                            <a id="scaleBtn" style="padding: 8px 16px;" class="btn btndow btn-lg">Scale It Now</a>
                            <script>
                                function submitScaleForm() {
                                    var nodesize = document.getElementById('nodes').value;
                                    var edgesize = document.getElementById('edges').value;
                                    if (nodesize !== null && nodesize !== ""
                                            && edgesize !== null && edgesize !== "") {
                                        document.getElementById('scaleForm').submit();
                                        return false;
                                    } else {
                                        alert("Miaow~~~ Please Input Scaled Node/Edge Size!");
                                    }
                                }
                            </script>
                        </form>
                    </div>
                    <div class="col-sm-5 text-left">
                        <h2>FORMAT INSTRUCTIONS</h2>
                        <ol class="notes">
                            <li>Only <b>TEXT</b> files are supported, we recommend '.txt' file.</li>
                            <br>
                            <li>The format of each line: <strong>[number1][whitespace][number2]</strong></li>
                            <br>
                            <li>For example, <strong>"1&nbsp;&nbsp;&nbsp;&nbsp;2"</strong> 
                                represents an edge point from vertex 1 to vertex 2 (noted that the graph is 
                                <strong>DIRECTED</strong>).</li>
                            <br>
                            <li>Please <strong>UPLOAD FIRST</strong> and <strong>SCALE AFTER</strong>.</li>
                        </ol>
                    </div>
                </div>
            </div>

            <!-- (Java Code) Scaled Graph Analysis -->
            <%
                String avpl = "N/A";
                String coc = "N/A";
                String dia = "N/A";
                String scaledNodeSize = "N/A";
                String scaledEdgeSize = "N/A";
                Boolean isScaled = false;

                if (isUploaded) {
                    Object scaledNodeSizeObj = session.getAttribute("scaledNodeSize");
                    Object scaledEdgeSizeObj = session.getAttribute("scaledEdgeSize");
                    if (scaledNodeSizeObj != null && scaledEdgeSizeObj != null) {

                        scaledNodeSize = (String) scaledNodeSizeObj;
                        scaledEdgeSize = (String) scaledEdgeSizeObj;
                        try {
                            String scaledFilePath = uploadDir.concat("scaled.txt");
                            String t2FilePath = uploadDir.concat("t2.txt");

                            String[] avplAndDia = BasicStatisticCal
                                    .getEffectiveDiaAndAvgShortestPathLen(scaledFilePath, t2FilePath);

                            avpl = avplAndDia[0];
                            dia = avplAndDia[1];

                            coc = BasicStatisticCal.getAvgClusteringCof(uploadedFilePath, t2FilePath, uploadDir);

                            isScaled = true;
                        } catch (Exception e) {
                            System.err.println("EXP(ScaleAnls): " + e.getMessage());
                            System.err.println(e.getStackTrace()[0]);
                        }
                    }
                }
            %>

            <!-- Container (Analysis Section) -->
            <div id="analysis" class="container-fluid bg-grey ">
                <div class="row">
                    <div class="col-md-12">
                        <h2>Basic Statistics</h2>
                        <table id="figureTable" class="table table-bordered table-inverse">
                            <thead>
                                <tr>
                                    <th></th>
                                    <th>Node No.</th>
                                    <th>Edge No.</th>
                                    <th>Effective Diameter</th>
                                    <th>Average Shortest Path</th> 
                                    <th>Average Clustering Coefficient</th>                                                                                              
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">Original</th>
                                    <td><span> <%=node%> </span></td>
                                    <td><span> <%=edge%> </span></td>
                                    <td><span> <%=dia_ori%> </span></td>
                                    <td><span> <%=avpl_ori%> </span></td>
                                    <td><span> <%=coc_ori%> </span></td>
                                </tr>
                                <tr>
                                    <th scope="row">Scaled</th>
                                    <td><span> <%=scaledNodeSize%> </span></td>
                                    <td><span> <%=scaledEdgeSize%> </span></td>
                                    <td><span> <%=dia%> </span></td>
                                    <td><span> <%=avpl%> </span></td>
                                    <td><span> <%=coc%> </span></td>
                                </tr>
                            </tbody>
                        </table>                      
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-7" align="left">
                        <form id="downScaledForm" action="DownloadScaledGraph">  
                            <a id="downScaledBtn" style="padding: 8px 16px;" class="btn btndow btn-lg">Download Scaled Graph</a>
                            <script>
                                function submitDownScaledGraphForm() {
                                    document.getElementById('downScaledForm').submit();
                                    return false;
                                }
                            </script>
                        </form>

                    </div>
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-5">
                                <a id="gViz" style="padding: 8px 16px;" class="btn btndow btn-lg" target="_blank">Graph Visualization</a>
                            </div>
                            <div class="col-md-7" align="right">
                                <a id="dCmp" style="padding: 8px 16px;" class="btn btndow btn-lg" target="_blank">In/Out Degree Comparison</a>  
                            </div>                        
                        </div>
                    </div>
                </div>
            </div>

            <!-- Container (Contact Section) -->
            <div id ="contact" style="background-color: #334d66; color: white;" class="container-fluid ">
                <h2 style="color: #FFFFFF;" class="text-left ">CONTACT</h2>
                <div class="row ">
                    <div class="col-sm-5 ">
                        <p>Please leave your precious comments to help us make GScaler better!</p>
                        <p><span class="glyphicon glyphicon-map-marker "></span> School of Computing</p>
                        <p><span class="glyphicon glyphicon-map-marker "></span> National University of Singapore</p>
                        <p><span class="glyphicon glyphicon-map-marker "></span> 13 Computing Drive, Singapore, 117417</p>
                        <p><span class="glyphicon glyphicon-envelope "></span> a0054808@u.nus.edu</p>
                    </div>
                    <div class="col-sm-7">
                        <form id="contactForm" action="SendEmail"
                              target="rfFrame">
                            <div class="row ">
                                <div class="col-sm-6 form-group ">
                                    <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
                                </div>
                                <div class="col-sm-6 form-group ">
                                    <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
                                </div>
                            </div>
                            <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="5"></textarea><br>
                            <div class="row ">
                                <div class="col-sm-12 form-group ">
                                    <button class="btn btn-default pull-right" 
                                            type="button" onclick="submitContactForm()">
                                        Send Email
                                    </button>
                                </div>
                            </div>
                            <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe> 
                        </form>
                        <script>
                            function submitContactForm() {
                                var email = document.getElementById('email').value;
                                var comments = document.getElementById('comments').value;
                                if (email !== null && /\S/.test(email)
                                    && comments !== null && /\S/.test(comments)) {
                                        alert("Thanks for your precious comments!");
                                        var frm = document.getElementById('contactForm');
                                        frm.submit();
                                        frm.reset();
                                        return false;
                                }else{
                                    alert("Miaow~~~ Please fill the contact form!");
                                }
                            }
                        </script>
                    </div>
                </div>
            </div>

            <script>
                $(function () {
                    var isUploaded = <%=isUploaded%>;
                    var isScaled = <%=isScaled%>
                    
                    if (isUploaded === true) {
                        $('#scaleBtn').attr('onclick', 'submitScaleForm()');
                    }
                    if (isScaled === true) {
                        $('#gViz').attr('href', 'GraphViz.jsp');
                        $('#dCmp').attr('href', 'DegreeComparison.jsp');
                        $('#downScaledBtn').attr('onclick', 'submitDownScaledGraphForm()');
                    }
                });
            </script>
        </div>    
    </body>
</html>
<!DOCTYPE html>
<html lang="en">

    <%@page import="java.io.*"%>
    <%@page import="propertycalculation.*" %>
    <%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
    <%@ page import="javax.servlet.http.*" %>
    <head
        <title>Bootstrap Theme Company Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="bootstrap-3.3.7/css/bootstrap.min.css">
        <link href="img/favicon.ico" rel="nus icon" type="image/png" />
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
        <link href="fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/app.css">
        <link href="fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="css/base.css" />
        <script src="js/jquery-3.1.1.min.js"></script>
        <script src="fileinput/js/fileinput.js" type="text/javascript"></script>
        <script src="bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    </head>

    <body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">
        <div style="padding: 0px" class="container">
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
            <p>GScaler is a tool to scale your graph up or down while guarantee the topology of resulting graph <br> similar to orginal one.</p>
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
                        Gscaler is the tool designed for such scaling, developed by Jiangwei Zhang from National University of Singapore, School of Computing.<br> Related demo paper is accepted by VLDB 2017:
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
                    <div align="center"><img src="img/lo.png"></div>
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
                        <li>Click in the open space to add a node, Ctrl-drag a node to move to adjust graph layout.</li>
                        <br>
                        <li>When a node is selected: press <strong>'Ctrl-DELETE'</strong> to remove it.</li>
                        <br>
                        <li>When an edge is selected: press <strong>'Ctrl-L'</strong>(eft), <strong>'Ctrl-R'</strong>(ight) to change direction, press <strong>'DELETE'</strong> removes the edge.</li>
                        <br>
                        <li>Download the graph your draw as graph matrix file and then upload it for scaling.</li>
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
                            <a style="padding: 8px 16px;" class="btn btndow btn-lg" href="#uploadAndScale">Upload &amp; Scale Graph</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pre-Analysis uploaded graph-->
        <%
            //String isUploaded = "false";
            String rawFilePath = "";
            String uploadDir = "";

            String edge = "N/A";
            String node = "N/A";

            String avpl_ori = "N/A";
            String dia_ori = "N/A";

            String ori_coc = "N/A";
            try {
                rawFilePath = session.getAttribute("uploadedFilePath").toString();//gets the path of the file which was uploaded
                if (rawFilePath == null
                        || rawFilePath.length() == 0
                        || rawFilePath.equals("")) {//to check if there is any input. 
                    rawFilePath = "";
                }
                //Reads file from where it is locally stored and counts the number if edges and nodes in the original file.
                //When the file is stored in the server,the code to read the file will have to be modified.     
                BufferedReader br = new BufferedReader(new FileReader(rawFilePath));
                HashMap<Integer, Integer> uidCounts = new HashMap<Integer, Integer>();
                HashMap<Integer, Integer> fidCounts = new HashMap<Integer, Integer>();
                String line = br.readLine();
                int nodesize = 77360;
                int edgesize = 0;
                while (line != null) {
                    edgesize++;
                    String[] temp = line.split("\\s+");
                    int fid = Integer.parseInt(temp[0]);
                    int uid = Integer.parseInt(temp[1]);
                    if (fid != uid) {
                        if (uidCounts.containsKey(uid)) {
                            uidCounts.put(uid, uidCounts.get(uid) + 1);
                        } else {
                            uidCounts.put(uid, 1);
                        }
                        if (!uidCounts.containsKey(fid)) {
                            uidCounts.put(fid, 0);
                        }

                        if (fidCounts.containsKey(fid)) {
                            fidCounts.put(fid, fidCounts.get(fid) + 1);
                        } else {
                            fidCounts.put(fid, 1);
                        }
                        if (!fidCounts.containsKey(uid)) {
                            fidCounts.put(uid, 0);
                        }
                    }
                    line = br.readLine();
                }
                br.close();
                nodesize = fidCounts.size();
                edge = edgesize + "";
                node = nodesize + "";

                session.setAttribute("originalnode", node);
                session.setAttribute("originaledge", edge);

                uploadDir = session.getAttribute("uploadDir").toString();
                String oriFilPath = uploadDir.concat("ori.txt");

                String[] args1 = new String[8];
                args1[0] = "-i";
                args1[1] = rawFilePath;
                args1[2] = "-m";
                args1[3] = "hop";
                args1[4] = "-o";
                args1[5] = oriFilPath;
                args1[6] = "-s";
                args1[7] = "0";
                PropertyCalculation pc1 = new PropertyCalculation();
                pc1.doMain(args1);

                br = new BufferedReader(new FileReader(oriFilPath + "avpl.txt"));
                String oc1 = br.readLine();
                int c1 = 1;
                char ch11;
                avpl_ori = "";
                for (int i = 0; i < oc1.length(); i++) {
                    if (c1 > 3) {
                        break;
                    }
                    ch11 = oc1.charAt(i);
                    if (ch11 == '0') {
                        avpl_ori += ch11;
                    } else {
                        c1++;
                        avpl_ori += ch11;
                    }
                }
                br = new BufferedReader(new FileReader(oriFilPath + "effective-diameter.txt"));
                dia_ori = br.readLine();
                br.close();

                // Caculate Average Clustering Coefficient
                String e = rawFilePath;
                String[] args2 = new String[6];
                args2[0] = "-i";
                args2[1] = e;
                args2[2] = "-o";
                args2[3] = oriFilPath;
                args2[4] = "-m";
                args2[5] = "coc";
                pc1.doMain(args2);
                br = new BufferedReader(new FileReader(uploadDir.concat("ori.txt2COC.txt")));
                String oc = br.readLine();
                br.close();
                int c = 1;
                ori_coc = "";
                char ch1;
                for (int i = 0; i < oc.length(); i++) {
                    if (c > 3) {
                        break;
                    }
                    ch1 = oc.charAt(i);
                    if (ch1 == '0') {
                        ori_coc += ch1;
                    } else {
                        c++;
                        ori_coc += ch1;
                    }
                }
                //isUploaded = "true";
            } catch (Exception e) {
                System.err.println("EXP(PreAnls): " + e.getMessage());
                System.err.println(e.getStackTrace()[0]);
            }
        %> 

        <!-- Container (File Upload & Scale Section) -->
        <div id="uploadAndScale" class="container-fluid">
            <div class="row ">
                <div class="col-sm-7 text-left">
                    <h2>FORMAT INSTRUCTIONS</h2>
                    <ol class="notes">
                        <li>Only <strong>TEXT</strong> files are supported, we recommend '.txt' file.</li>
                        <br>
                        <li>The format of each line: <strong>[number1][whitespace][number2]</strong></li>
                        <br>
                        <li>For example, <strong>"1&nbsp;&nbsp;&nbsp;&nbsp;2"</strong> 
                            represents an edge point from vertex 1 to vertex 2. <br>(noted that the graph is 
                            <strong>DIRECTED</strong>).</li>
                        <br>
                        <li>Please <strong>UPLOAD FIRST</strong> and <strong>SCALE AFTER</strong>.</li>
                    </ol>
                </div>
                <div class="col-sm-5 ">
                    <h2>Upload Graph File</h2>
                    <form action="UploadGraph.jsp" method="post"                         
                          enctype="multipart/form-data">
                        <input id="input-1a" type="file" name="file" class="file" data-show-preview="false">                       
                    </form>

                    <br>
                    <form action="ScaleGraph.jsp" metho="post">
                        <span>Node size scale from &nbsp;&nbsp;<%=node%> &nbsp;&nbsp;to&nbsp;</span>
                        <input id="nodes" type="number"  name="scaledNodeSize"
                               onkeyup="if(this.value.length===1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'');}"  
                               onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'');}"
                               placeholder=" Node Size After Scale"/>
                        <br>
                        <span>Edge size scale from &nbsp;&nbsp;&nbsp;<%=edge%> &nbsp;&nbsp;to&nbsp;</span>
                        <input id="edges" type="number" name="scaledEdgeSize"
                               onkeyup="if(this.value.length===1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'');}"  
                               onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"
                               placeholder=" Edge Size After Scale">
                        <br><br>                       
                        <input id="scaleBtn" type="submit" class="btn btndow btn-lg"                               
                               value="Scale It Now" />
                    </form>
                </div>
            </div>
        </div>

        <!--Calculate Analysis Figures >
        <%
            String scaledNodeSize = "N/A";
            String scaledEdgeSize = "N/A";

            String dia = "N/A";
            String avpl = "N/A";

            String coc = "N/A";
            try {
                scaledNodeSize = session.getAttribute("scaledNodeSize").toString();
                scaledEdgeSize = session.getAttribute("scaledEdgeSize").toString();

                String scaledFilePath = uploadDir.concat("scaled.txt");
                String t2FilePath = uploadDir.concat("t2.txt");

                // Caculate Average Shortest Path & Effective Diameter
                String[] args1 = new String[8];
                args1[0] = "-i";
                args1[1] = scaledFilePath;
                args1[2] = "-m";
                args1[3] = "hop";
                args1[4] = "-o";
                args1[5] = t2FilePath;
                args1[6] = "-s";
                args1[7] = "0";
                PropertyCalculation pc = new PropertyCalculation();
                pc.doMain(args1);

                BufferedReader br1 = new BufferedReader(new FileReader(t2FilePath + "avpl.txt"));
                String oc = br1.readLine();
                br1.close();

                int c = 1;
                char ch1;
                avpl = "";
                for (int i = 0; i < oc.length(); i++) {
                    if (c > 3) {
                        break;
                    }
                    ch1 = oc.charAt(i);
                    if (ch1 == '0') {
                        avpl += ch1;
                    } else {
                        c++;
                        avpl += ch1;
                    }
                }
                br1 = new BufferedReader(new FileReader(t2FilePath + "effective-diameter.txt"));
                dia = br1.readLine();
                br1.close();

                // Caculate Average Clustering Coefficient
                String[] args = new String[6];
                args[0] = "-i";
                args[1] = scaledFilePath;
                args[2] = "-o";
                args[3] = t2FilePath;
                args[4] = "-m";
                args[5] = "coc";
                pc.doMain(args);

                br1 = new BufferedReader(new FileReader(uploadDir.concat("t2.txt2COC.txt")));
                oc = br1.readLine();
                br1.close();
                c = 1;
                coc = "";
                for (int i = 0; i < oc.length(); i++) {
                    if (c > 3) {
                        break;
                    }
                    ch1 = oc.charAt(i);
                    if (ch1 == '0') {
                        coc += ch1;
                    } else {
                        c++;
                        coc += ch1;
                    }
                }
            } catch (Exception e) {
                System.err.println("EXP(ScaleAnls): " + e.getMessage());
                System.err.println(e.getStackTrace()[0]);
            }
        %>
        
        <!-- Container (analysis Section) -->
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
                                <td><span> <%=ori_coc%> </span></td>
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
                    <form action="DownloadScaledGraph.jsp">                              
                        <input type="submit" value="Download Scaled Graph" 
                               style="padding: 8px 16px;" class="btn btndow btn-lg">
                    </form>

                </div>
                <div class="col-md-5">
                    <div class="row">
                        <div class="col-md-5">
                            <a style="padding: 8px 16px;" class="btn btndow btn-lg" target="_blank" href="GraphViz.jsp">Visualize Scaled Graph</a>
                        </div>
                        <div class="col-md-7" align="right">
                            <a style="padding: 8px 16px;" class="btn btndow btn-lg" target="_blank" href="DegreeComparison.jsp">In/Out Degree Comparison</a>  
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
                    <p>Please feel free to leave your precious comments to help us make GScaler better!</p>
                    <p><span class="glyphicon glyphicon-map-marker "></span> School of Computing</p>
                    <p><span class="glyphicon glyphicon-map-marker "></span> National University of Singapore</p>
                    <p><span class="glyphicon glyphicon-map-marker "></span> 13 Computing Drive, Singapore, 117417</p>
                    <p><span class="glyphicon glyphicon-envelope "></span> a0054808@u.nus.edu</p>
                </div>
                <div class="col-sm-7">
                    <form id="contactForm" action="SendMail.jsp"
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
                                        type="button" onclick="submitForm()">
                                    Send Email
                                </button>
                            </div>
                        </div>
                        <iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe> 
                    </form>
                    <script>
                        function submitForm() {
                            // Get the first form with the name
                            // Hopefully there is only one, but there are more, select the correct index
                            var frm = document.getElementById("contactForm");
                            alert("Thanks for your comments!");
                            frm.submit(); // Submit
                            frm.reset();  // Reset
                            return false; // Prevent page refresh
                        }
                    </script>
                </div>
            </div>
        </div>
    <div>
    </body>
</html>
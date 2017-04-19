var xmlhttp;
function loadXMLHttp(url, cfunc) {
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = cfunc;
    xmlhttp.open("POST", url, true);
    xmlhttp.send();
}
function getCoc(isScaled) {    
    var url = "CalStatistic?isScaled=" + isScaled + "&type=coc";
    var id = (isScaled == false) ? "o" : "s";
    document.getElementById(id + "coc").innerHTML = "Calculating......";
    loadXMLHttp(url, function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {         
            document.getElementById(id + "coc").innerHTML = xmlhttp.responseText;
        }
    });
}
function getSize(isScaled) {
    resetStatistc();
    var url = "CalStatistic?isScaled=" + isScaled + "&type=size";
    var id = (isScaled == false) ? "o" : "s";

    validation();
    loadXMLHttp(url, function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var array = xmlhttp.responseText.split(":");
            var nsize = array[0];
            var esize = array[1];
            document.getElementById(id + "_nsize").innerHTML = nsize;
            document.getElementById(id + "_esize").innerHTML = esize;
            document.getElementById(id + "nsize").innerHTML = nsize;
            document.getElementById(id + "esize").innerHTML = esize;
        }
    });
}
function getDiaAvp(isScaled) {
    var url = "CalStatistic?isScaled=" + isScaled + "&type=diaAndspl";
    var id = (isScaled == false) ? "o" : "s";
    
    document.getElementById(id + "dia").innerHTML = "Calculating......";
    document.getElementById(id + "avpl").innerHTML = "Calculating......";
            
    loadXMLHttp(url, function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var array = xmlhttp.responseText.split(":");
            var dia = array[0];
            var avpl = array[1];
            document.getElementById(id + "dia").innerHTML = dia;
            document.getElementById(id + "avpl").innerHTML = avpl;
        }
    });
}
function resetStatistc(){
    document.getElementById("isUploaded").value = true;
    document.getElementById("isScaled").value = false;
    resetOri();
    resetSca();
}

function resetOri(){
     document.getElementById("odia").innerHTML = "un-Calculated";
     document.getElementById("ococ").innerHTML = "un-Calculated";
     document.getElementById("oavpl").innerHTML = "un-Calculated";
     document.getElementById("snsize").innerHTML = "N/A";
     document.getElementById("sesize").innerHTML = "N/A";
}

function resetSca(){    
     document.getElementById("sdia").innerHTML = "un-Calculated";
     document.getElementById("scoc").innerHTML = "un-Calculated";
     document.getElementById("savpl").innerHTML = "un-Calculated";
}
//function getStatistic(isScaled, type) {
//    if (window.XMLHttpRequest)
//        xmlhttp = new XMLHttpRequest();
//    else
//        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
//    var url = "CalStatistic?isScaled=" + isScaled + "&type=" + type;
//    var id = (isScaled == false) ? "o" : "s";
//
//    if (type === 'coc') {
//        xmlhttp.onreadystatechange = function () {
//            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//                document.getElementById(id + "coc").innerHTML = xmlhttp.responseText;
//            }
//        };
//    } else if (type === 'size') {
//        xmlhttp.onreadystatechange = function () {
//            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//                var array = xmlhttp.responseText.split(":");
//                var nsize = array[0];
//                var esize = array[1];
//                document.getElementById(id + "nsize").innerHTML = array[0];
//                document.getElementById(id + "esize").innerHTML = array[1];
//            }
//        };
//    } else {
//        xmlhttp.onreadystatechange = function () {
//            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//                var array = xmlhttp.responseText.split(":");
//                var dia = array[0];
//                var avpl = array[1];
//                document.getElementById(id + "dia").innerHTML = dia;
//                document.getElementById(id + "avpl").innerHTML = avpl;
//            }
//        };
//    }
//    xmlhttp.open("POST", url, true);
//    xmlhttp.send();
//}



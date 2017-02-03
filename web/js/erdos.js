/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var options;
var options1;
function makeMeAGraph() {
    
    options = $('#options').serialize();
    
    console.log(options);
    src = "https://chart.googleapis.com/chart?"+options
    $('#graph').attr("src", src);
}

function makeMeAGraph1() {
    
    options1 = $('#options1').serialize();
    console.log(options1);
    src1 = "https://chart.googleapis.com/chart?"+options1
    $('#graph1').attr("src", src1);
}


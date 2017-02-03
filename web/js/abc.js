/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var options;

function makeMeAGraph1() {
    
    options = $('#options').serialize();

    src = "https://chart.googleapis.com/chart?"+options
    $('#graph').attr("src", src);
}




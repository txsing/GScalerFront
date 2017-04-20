/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package propertycalculation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import static org.kohsuke.args4j.ExampleMode.ALL;
import org.kohsuke.args4j.Option;
import java.io.FileNotFoundException;

/**
 *
 * @author workshop
 */
public class PropertyCalculation {

    /**
     * @param args the command line arguments
     */
    //String[] modes =;
   
    @Option(name="-i",usage="input of the file",metaVar="INPUT")
    private String inStr ="";
    
     @Option(name="-i2",usage="input of the file2",metaVar="INPUT2")
    private String inStr2 ="";
    
    @Option(name="-o",usage="ouput of the file",metaVar="OUTPUT")
    private String outStr ="";

    @Option(name="-m",usage="Mode Of The Run",metaVar="MODE")
    private String mode ="";
    
    private int number_thread =1;

    @Option(name="-s",usage="Start of the number",metaVar="Start")
    private int start =0;

  
    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();
    
    public static void main(String[] args) throws InterruptedException {
        
        try {
            new PropertyCalculation().doMain(args);
//            System.out.println(args[0]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertyCalculation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doMain(String[] args) throws FileNotFoundException, InterruptedException {
        for(String arg:args){
            System.out.println(arg);
        }
        CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);
         try {
            // parse the arguments.
            parser.parseArgument(args);
            System.out.println(inStr);
            if (inStr.equals("")){
            }
            // if enough arguments are given.
            
            
            if (mode.equals("degree")){
                DegreeCal dc = new DegreeCal(this.inStr,outStr);
                dc.cal();
            }
            if (mode.equals("coc")){
                
                COC_Cal coc = new COC_Cal(this.inStr,outStr,this.number_thread);
                coc.cal();
            }
            if (mode.equals("scc")){
                SCC_Cal scc = new SCC_Cal(this.inStr,this.outStr);
                scc.cal();
            } 
            if (mode.equals("hop")){
                Hop_Cal hop = new Hop_Cal(this.inStr,this.outStr, this.number_thread, this.start);
                hop.cal();
            }
            
            if (mode.equals("dst")){
                Dstatistics dst = new Dstatistics(this.inStr,this.outStr, this.inStr2);
                dst.cal();
            }
            
        } catch( CmdLineException e ) {
            // if there's a problem in the command line,
            // you'll get this exception. this will report
            // an error message.
//            System.err.println(e.getMessage());
            e.printStackTrace();
            System.err.println("java SampleMain [options...] arguments...");
            // print the list of available options
            parser.printUsage(System.err);
            System.err.println();

            // print option sample. This is useful some time
            System.err.println("  Example: java SampleMain"+parser.printExample(ALL));
        }

        // this will redirect the output to the specified output
       // System.out.println(out);

       
    }
    
}

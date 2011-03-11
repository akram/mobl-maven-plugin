package org.echosource;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

import trans.Main;
import trans.main_0_0;

/**
 * 
 * @goal compile
 * @phase compile
 */
public class MoblMavenPlugin extends AbstractMojo {

  /**
   * Location of the file.
   * 
   * @parameter expression="${project.build.directory}"
   * @required
   */
  private File outputDirectory;

  /**
   * @see org.apache.maven.plugin.Mojo#execute()
   */
  public void execute() throws MojoExecutionException {
    File f = outputDirectory;
    if ( !f.exists()) {
      f.mkdirs();
    }
    String[] args = { "-I", "d:/downloads/mobl-lib", "-i", "aaa.mobl", "-S" };
    try {
      main(args);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  private void main(String[] args) {
    Context context;
    context = Main.init();
    context.setStandAlone(true);
    IStrategoTerm result = context.invokeStrategyCLI(main_0_0.instance, "Main", args);
    context.getIOAgent().closeAllFiles();
    context.getIOAgent().closeAllFiles();
    if (result == null) {
      System.err.println("Main: rewriting failed, trace:");
      context.printStackTrace();
      context.setStandAlone(false);
    } else {
      System.out.println(result);
      context.setStandAlone(false);
    }
    context.setStandAlone(false);
  }
}

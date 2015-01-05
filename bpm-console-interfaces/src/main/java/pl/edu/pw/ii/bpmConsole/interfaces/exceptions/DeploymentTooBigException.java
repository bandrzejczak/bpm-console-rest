package pl.edu.pw.ii.bpmConsole.interfaces.exceptions;

import pl.edu.pw.ii.bpmConsole.interfaces.Deployment;

public class DeploymentTooBigException extends ProcessEngineException {
    public DeploymentTooBigException() {
        super("Deployment file exceeds maximum size of "+ Deployment.MAX_FILE_SIZE/(1024*1024) + "MB");
    }
}
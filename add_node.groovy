import hudson.plugins.sshslaves.SSHLauncher
import hudson.plugins.sshslaves.verifiers.NonVerifyingKeyVerificationStrategy
import hudson.model.Node
import hudson.slaves.DumbSlave
import hudson.slaves.RetentionStrategy
import jenkins.model.Jenkins

def host = "192.168.122.71"
def port = 22
def credentialsId = "jenkins-master-ssh"

def launcher = new SSHLauncher(
    host,
    port,
    credentialsId,
    "",   // jvmOptions
    "",   // javaPath
    "",   // prefixStartSlaveCmd
    "",   // suffixStartSlaveCmd
    0,    // launchTimeoutSeconds (0 = default)
    0,    // maxNumRetries (0 = default)
    0,    // retryWaitTime (0 = default)
    new NonVerifyingKeyVerificationStrategy()
)

def agent = new DumbSlave(
    "labvm2",                        // nom de l'agent
    "Description de l'agent labvm2",// description
    "/home/jenkins",                // remoteFS (répertoire jenkins sur l’agent)
    "1",                           // nombre d’exécuteurs
    Node.Mode.NORMAL,              // mode
    "label",                      // labels (modifier si besoin)
    launcher,
    new RetentionStrategy.Always(),
    []
)

Jenkins.instance.addNode(agent)
Jenkins.instance.save()
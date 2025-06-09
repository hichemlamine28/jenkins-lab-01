import jenkins.model.*
import hudson.slaves.*
import hudson.plugins.sshslaves.SSHLauncher
import hudson.slaves.RetentionStrategy

def instance = Jenkins.getInstance()

def agents = [
    [name: "labvm2", host: "labvm2", home: "/home/ubuntu"],
    [name: "labvm3", host: "labvm3", home: "/home/ubuntu"],
    [name: "labvm4", host: "labvm4", home: "/home/ubuntu"]
]

agents.each { agent ->
    if (instance.getNode(agent.name) == null) {
        println "Creating agent ${agent.name}"
        def launcher = new SSHLauncher(
            agent.host,
            22,
            "jenkins", // Nom de l'identité Jenkins stockée dans Jenkins
            null, null, "", "", 60, 5, 15
        )
        def node = new DumbSlave(
            agent.name,
            agent.home,
            launcher
        )
        node.setNumExecutors(2)
        node.setMode(Node.Mode.NORMAL)
        node.setRetentionStrategy(new RetentionStrategy.Always())
        instance.addNode(node)
    } else {
        println "Agent ${agent.name} already exists"
    }
}

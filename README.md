# 🧪 Jenkins Lab 01

[![Build with Jenkins](https://img.shields.io/badge/built%20with-Jenkins-blue?logo=jenkins)](https://www.jenkins.io/)
[![Shell Script](https://img.shields.io/badge/script-bash-1f425f.svg?logo=gnu-bash)](https://www.gnu.org/software/bash/)
[![Go Backend](https://img.shields.io/badge/backend-go-blue?logo=go)](https://golang.org/)
[![Echo Framework](https://img.shields.io/badge/framework-echo-009688)](https://echo.labstack.com/)
[![GitHub Repo stars](https://img.shields.io/github/stars/hichemlamine28/jenkins-lab-01?style=social)](https://github.com/hichemlamine28/jenkins-lab-01/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/hichemlamine28/jenkins-lab-01?style=social)](https://github.com/hichemlamine28/jenkins-lab-01/network)
[![GitHub last commit](https://img.shields.io/github/last-commit/hichemlamine28/jenkins-lab-01)](https://github.com/hichemlamine28/jenkins-lab-01/commits)
[![GitHub license](https://img.shields.io/github/license/hichemlamine28/jenkins-lab-01)](https://github.com/hichemlamine28/jenkins-lab-01/blob/main/LICENSE)

---

## 📦 Présentation

Ce dépôt est un **laboratoire Jenkins** pour automatiser 

## STEP 1 : setup #####

Now let us install Jenkins on the centos-host machine and configure it to run on port 8090 instead of the default port 8080.


You can refer to the Jenkins Installation Docs located here:


https://www.jenkins.io/doc/book/installing/linux/#red-hat-centos



# Ubuntu :
```bash
sudo wget -O /etc/apt/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian/jenkins.io-2023.key
echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins
```


Beginning with Jenkins 2.335 and Jenkins 2.332.1, the package is configured with systemd rather than the older System V init. More information is available in "Managing systemd services".

The package installation will:

Setup Jenkins as a daemon launched on start. Run systemctl cat jenkins for more details.

Create a ‘jenkins’ user to run this service.

Direct console log output to systemd-journald. Run journalctl -u jenkins.service if you are troubleshooting Jenkins.

Populate /lib/systemd/system/jenkins.service with configuration parameters for the launch, e.g JENKINS_HOME

Set Jenkins to listen on port 8080. Access this port with your browser to start configuration.

If Jenkins fails to start because a port is in use, run  

```bash
systemctl edit jenkins 
```

and add the following:


[Service]
Environment="JENKINS_PORT=8081"

Here, "8081" was chosen but you can put another port available.

# Installation of Java

Jenkins requires Java to run, yet not all Linux distributions include Java by default. Additionally, not all Java versions are compatible with Jenkins.

There are multiple Java implementations which you can use. OpenJDK is the most popular one at the moment, we will use it in this guide.

Update the Debian apt repositories, install OpenJDK 21, and check the installation with the commands:

```bash
sudo apt update
sudo apt install fontconfig openjdk-21-jre
java -version

```


openjdk version "21.0.3" 2024-04-16
OpenJDK Runtime Environment (build 21.0.3+11-Debian-2)
OpenJDK 64-Bit Server VM (build 21.0.3+11-Debian-2, mixed mode, sharing)


# Why use apt and not apt-get or another command? 
The apt command has been available since 2014. 
It has a command structure that is similar to apt-get but was created to be a more pleasant experience for typical users. 
Simple software management tasks like install, search and remove are easier with apt.


## ################################################


🛠 Solution avec Ansible (propre et sécurisée)
📁 Arborescence Ansible recommandée

```text

jenkins-lab/
├── inventory.ini
├── lab_jenkins.yml
├── roles/
│   |── common/       # Installer Java sur tous les nodes
│   |   ├── tasks/
│   │   |   └── main.yml
│   |── jenkins/      # Installer Jenkins sur labvm1
│   |   ├── tasks/
│   │   |   └── main.yml
│   |   ├── defaults/
│   │   |   ├── main.yml
│   |   ├── handlers/
│   │   |   ├── main.yml
│   |   ├── templates/
│   │   |   ├── 1_login.groovy.j2
│   │   |   ├── 2_configure.groovy.j2
│   │   |   ├── 3_disable_setup.groovy.j2
│   │   |   ├── 4_install_plugins.groovy.j2
│   │   |   ├── 5_add_jenkins_credential.groovy.j2
│   │   |   ├── 6_add_jenkins_agents.groovy.j2


```



# VENV

```bash
python3 -m venv venv
source venv/bin/activate
pip install ansible
pip install passlib
ansible-galaxy collection install community.libvirt
sudo apt install pkg-config libvirt-dev python3-dev -y
pip3 install libvirt-python
```

# Installer Le LAB Jenkins

## Lancer le playbook Ansible

```bash
ansible-playbook lab-jenkins.yml -i inventory.ini --ask-vault-pass
```


## Lancer le script 

```bash
./lab_setup.sh
```

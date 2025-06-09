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

Ce dépôt est un **laboratoire Jenkins** pour automatiser la CI/CD d'une petite application écrite en **Go** avec le framework **Echo**.  
Il inclut des scripts Bash pour le build, les tests, le déploiement, et l'intégration avec Jenkins.

---

## ⚙️ Technologies utilisées

- 🐧 **Bash** — pour les scripts d'automatisation
- 🐹 **Golang** — backend léger
- 🚀 **Echo** — framework web rapide pour Go
- ⚙️ **Jenkins** — pipeline CI/CD
- 🐙 **GitHub** — gestion de code source

---

## 📁 Structure du dépôt

```text
.
├── Jenkinsfile             # Pipeline Jenkins declaratif
├── build.sh                # Script de build Bash
├── test.sh                 # Script de test
├── deploy.sh              # Script de déploiement (optionnel)
├── main.go                 # Application Go
├── go.mod / go.sum         # Dépendances Go
└── README.md               # Documentation
```

🚀 Exécution

📌 Lancer l'application en local :

```bash
go run main.go
```

Accessible via http://localhost:1323

🔧 Jenkins Pipeline
Le fichier Jenkinsfile gère les étapes suivantes :

🔍 Checkout du code source

🧪 Tests automatiques

🔨 Build de l'application

🚀 Déploiement (optionnel ou à compléter)

📜 Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE.

🙌 Contribuer
Les contributions sont les bienvenues ! Forke le repo, crée une branche et soumets une PR.




## ###################################################


In this lab, we will deploy the go-webapp-sample app that we saw in the lecture.

This sample application uses Echo as web application framework and Gorm as the backend database.


Clone this git repository under /home/username:

```bash
git clone https://github.com/kodekloudhub/go-webapp-sample
```
Now let us deploy the sample app from the repository that we just cloned. 
Run the following commands:

```bash
cd /home/username/go-webapp-sample/
go run main.go &
```




   ____    __
  / __/___/ /  ___
 / _// __/ _ \/ _ \
/___/\__/_//_/\___/ v4.6.3
High performance, minimalist Go web framework
https://echo.labstack.com




You can now access the sample app using the Sample-App button located above the terminal.

You can login with the following username and password:


username: test

password: test


 See this for more details and options

https://github.com/kodekloudhub/go-webapp-sample

or this

https://github.com/ybkuroki/vuejs-webapp-sample




## STEP 2 #####

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

sudo apt update
sudo apt install fontconfig openjdk-21-jre
java -version

openjdk version "21.0.3" 2024-04-16
OpenJDK Runtime Environment (build 21.0.3+11-Debian-2)
OpenJDK 64-Bit Server VM (build 21.0.3+11-Debian-2, mixed mode, sharing)


# Why use apt and not apt-get or another command? 
The apt command has been available since 2014. 
It has a command structure that is similar to apt-get but was created to be a more pleasant experience for typical users. 
Simple software management tasks like install, search and remove are easier with apt.


## ################################################



on node agent :

sudo useradd -r -md /var/jenkins_home -s /bin/bash jenkins

cat /etc/passwd

ls -l /var

sudo mkdir -p /usr/local/jenkins-service

sudo mv agent.jar /usr/local/jenkins-service/

sudo chown jenkins:jenkins -R /usr/local/jenkins-service

sudo vi /usr/local/jenkins-service/start-agent.sh

```bash
#!/bin/bash
cd /usr/local/jenkins-service
curl -sO http://192.168.122.144:8080/jnlpJars/agent.jar
java -jar agent.jar -url http://192.168.122.144:8080/ -secret f9477b67f8f31457b8be68d2e8a4179e4a38eab96b37fc80fb1b6bd17dd913ed -name labvm2 -webSocket -workDir "/home/ubuntu/jenkins_home"

exit 0



```

```bash
sudo chmod +x /usr/local/jenkins-service/start-agent.sh
```

```bash
sudo vi /etc/systemd/system/jenkins-agent.service
```

```bash
[Unit]
Description=Jenkins Agent

[Service]
User=jenkins
WorkingDirectory=/home/jenkins/jenkins_home
ExecStart=/bin/bash /usr/local/jenkins-service/start-agent.sh
Restart=always

[Install]
WantedBy=multi-user.target

```

```bash
sudo systemctl daemon-reload
sudo systemctl enable jenkins-agent.service
sudo systemctl start jenkins-agent.service
sudo systemctl status jenkins-agent.service
```



add public ssh key to jenkins-user

curl -Lv http://localhost:8080/login 2>&1 | grep -i 'x-ssh-endpoint'


ssh -i /home/hichem/.ssh/private_key -l jenkins-user -p 22 jenkins-server help



## ###################################

🛠 Solution avec Ansible (propre et sécurisée)
📁 Arborescence Ansible recommandée

jenkins-lab/
├── inventory.ini
├── playbook.yml
├── roles/
│   |── common/       # Install Java
│   |   ├── tasks/
│   │   |   └── main.yml
│   |── jenkins/      # Installer Jenkins sur labvm1
│   |   ├── tasks/
│   │   |   └── main.yml
│   |   ├── files/
│   │   |   ├── basic-security.groovy
│   │   |   └── add-nodes.groovy
│   |   ├── handlers/
│   │   |   ├── main.yml
│   |── ssh_setup/    # Gérer les clés SSH et la connexion master <-> agents
│   |   ├── tasks/
│   │   |   └── main.yml


# VENV

python3 -m venv venv
source venv/bin/activate
pip install ansible
pip install passlib
ansible-galaxy collection install community.libvirt
sudo apt install pkg-config libvirt-dev python3-dev -y
pip3 install libvirt-python


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





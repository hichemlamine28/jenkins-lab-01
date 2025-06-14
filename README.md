# 🧪 Jenkins Lab 01 — Automatisation complète avec Ansible

[![Built with Jenkins](https://img.shields.io/badge/Built%20With-Jenkins-blue?logo=jenkins)](https://www.jenkins.io/)
[![Groovy](https://img.shields.io/badge/Script-Groovy-4298B8?logo=apache-groovy)](https://groovy-lang.org/)
[![Ansible](https://img.shields.io/badge/Automation-Ansible-EE0000?logo=ansible)](https://www.ansible.com/)
[![Ubuntu](https://img.shields.io/badge/OS-Ubuntu-E95420?logo=ubuntu)](https://ubuntu.com/)
[![Java](https://img.shields.io/badge/Java-OpenJDK%2021-blue?logo=java)](https://openjdk.org/)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-Automated-007ec6?logo=githubactions)](https://en.wikipedia.org/wiki/CI/CD)
[![SSH](https://img.shields.io/badge/Auth-SSH-2e9fff?logo=openssh)](https://www.openssh.com/)
[![Python](https://img.shields.io/badge/Python-3.10+-3670A0?logo=python)](https://python.org)
[![Shell](https://img.shields.io/badge/Shell-Bash-1f425f.svg?logo=gnubash)](https://www.gnu.org/software/bash/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/hichemlamine28/jenkins-lab-01?style=social)](https://github.com/hichemlamine28/jenkins-lab-01/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/hichemlamine28/jenkins-lab-01?style=social)](https://github.com/hichemlamine28/jenkins-lab-01/network)
[![Last commit](https://img.shields.io/github/last-commit/hichemlamine28/jenkins-lab-01)](https://github.com/hichemlamine28/jenkins-lab-01/commits)


---

## 🚀 Présentation du projet

Ce dépôt propose un **laboratoire Jenkins clé en main**, utilisant **Ansible** pour automatiser de bout en bout :

- la **préparation d'une infrastructure existante** (au moins 2 VM Ubuntu – testée avec 4),
- l'**installation de Java, Jenkins et tous les outils nécessaires**,
- la **configuration complète de Jenkins** (login, mot de passe, clé SSH, URL, agents, plugins...),
- la **génération automatique d'un inventaire dynamique** via `inventory_dynamic.py`.

💡 **Objectif** : Fournir un environnement Jenkins opérationnel **en quelques minutes** sur une infrastructure virtuelle locale ou cloud.

---

## 🧱 Prérequis

- 2+ VM Ubuntu (la première sera `master`, les autres seront des `agents`)
- Accès SSH fonctionnel
- Python3 + pip
- Ansible
- `libvirt` (si en local)

---

## 🛠️ Structure du projet

```text
jenkins-lab/
├── ansible.cfg                   # config ansible
├── inventory.ini                 # (temporaire) inventaire initial
├── inventory_dynamic.py          # Script Python pour générer dynamiquement l'inventaire
├── lab_jenkins.yml               # Playbook principal
├── lab_jenkins.sh                # Script de lancement complet
├── group_vars/
│   ├── all/                      # vars / vault chiffré contenant le login/password
│   |   └── main.yml
├── roles/
│   ├── common/                   # Installation Java
│   │   └── tasks/
│   │       └── main.yml
│   └── jenkins/                  # Installation & config Jenkins
│       ├── defaults/
│       │   └── main.yml
│       ├── handlers/
│       │   └── main.yml
│       ├── tasks/
│       │   └── main.yml
│       └── templates/
│           ├── 1_install_plugins.groovy.j2
│           ├── 2_login.groovy.j2
│           ├── 3_configure.groovy.j2
│           ├── 4_disable_setup.groovy.j2
│           ├── 5_add_jenkins_credential.groovy.j2
│           ├── 6_add_jenkins_agents.groovy.j2
│           ├── check_script_execution.groovy.j2
```

---

## ⚙️ Installation manuelle de Jenkins (Ubuntu)

### 🔧 Ajout du dépôt Jenkins

```bash
sudo wget -O /etc/apt/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian/jenkins.io-2023.key
echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian binary/" | sudo tee /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt update
sudo apt install jenkins
```

> Depuis Jenkins 2.335, systemd est utilisé au lieu de init.d.

### 🛠️ Configuration du port (si 8080 occupé)

```bash
sudo systemctl edit jenkins
```

Ajouter :

```ini
[Service]
Environment="JENKINS_PORT=8081"
```

---

## ☕ Installation de Java (OpenJDK 21 recommandé)

```bash
sudo apt update
sudo apt install fontconfig openjdk-21-jre
java -version
```

Exemple attendu :

```text
openjdk version "21.0.3" 2024-04-16
OpenJDK Runtime Environment (build 21.0.3+11-Debian-2)
OpenJDK 64-Bit Server VM (build 21.0.3+11-Debian-2, mixed mode, sharing)
```

ℹ️ `apt` est plus moderne et user-friendly que `apt-get`.

---

## 🧪 Déploiement automatisé avec Ansible

### 📦 Création de l’environnement Python

```bash
python3 -m venv venv
source venv/bin/activate
pip install ansible passlib
ansible-galaxy collection install community.libvirt
sudo apt install pkg-config libvirt-dev python3-dev -y
pip3 install libvirt-python
```

---

## 🚀 Déploiement du LAB Jenkins

### 🧰 Étapes du lab :

1. Génération de l’inventaire dynamique :
   ```bash
   ./inventory_dynamic.py
   ```

2. Lancement du playbook :
   ```bash
   ansible-playbook lab_jenkins.yml -i inventory.ini --ask-vault-pass
   ```

3. Ou exécution directe avec script :
   ```bash
   ./lab_jenkins.sh
   ```

---

## Jenkins CLI

depuis le node master:

```bash
wget http://localhost:8080/jnlpJars/jenkins-cli.jar

java -jar jenkins-cli.jar -s http://localhost:8080 -auth admin:password help

alias jenkins-cli='java -jar ~/jenkins-cli.jar -s http://localhost:8080 -auth admin:password'

jenkins-cli help

jenkins-version
```

Autre Solution: Utilisation du sshd + port



```bash

curl -Lv http://localhost:8080/login 2>&1 | grep -i 'x-ssh-endpoint'

ssh -p 2222 admin@localhost version

```



## 🧩 Fonctionnalités automatisées

✅ Détection automatique des VMs (1 master + N agents)  
✅ Préparation des VMs (update, Java, etc.)  
✅ Installation & configuration complète de Jenkins  
✅ Envoi de la **clé SSH publique vers les agents**  
✅ Ajout automatique des **credentials** sur Jenkins Master  
✅ Installation des **plugins essentiels Jenkins**  
✅ Configuration initiale (URL, sécurité, groovy scripts...)  
✅ Compatible avec toute infrastructure existante (local ou cloud , il faut generer l'invetaire à la main si vous avez une infra différente)

---

## ⚖️ Licence

Ce projet est sous licence **MIT**. Voir le fichier [LICENSE](./LICENSE).

---

## 🤝 Contributions

Les contributions sont les bienvenues !  
Forkez, améliorez, proposez des PRs 🙏

---

## 👤 Auteur

**Hichem Elamine**  
💼 DevSecOps | Cloud | Automation  
🌍 [LinkedIn](https://www.linkedin.com/in/hichemlamine/) | [GitHub](https://github.com/hichemlamine28)

---

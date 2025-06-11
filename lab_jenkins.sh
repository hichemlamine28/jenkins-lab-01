#!/bin/bash
ansible-playbook lab_jenkins.yml -i inventory.ini --ask-vault-pass

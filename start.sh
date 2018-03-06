#!/bin/bash

mvn package
sudo iptables -A PREROUTING -t nat -p tcp --dport 80 -j REDIRECT --to-ports 8080
sh target/bin/webapp

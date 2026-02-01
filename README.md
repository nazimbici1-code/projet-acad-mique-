# projet-acad-mique-
Ce projet consiste en le développement d'une application de messagerie instantanée en temps réel basée sur une architecture robuste Client-Serveur. Conçue en Java (ou le langage utilisé), l'application permet une communication fluide entre plusieurs utilisateurs via le protocole réseau TCP.

L'objectif principal est de démontrer la maîtrise de la communication réseau par Sockets, la gestion de la concurrence (Multithreading) et la synchronisation des données en temps réel.
# Fonctionnalités Clés
.Communication bidirectionnelle :Envoi et réception de messages instantanés grâce aux Sockets TCP garantissant la fiabilité des données.

.Gestion Multi-clients : Le serveur peut gérer simultanément plusieurs connexions entrantes grâce à l'implémentation de threads dédiés.

.Système de Présence : Gestion dynamique des connexions et déconnexions avec notification en temps réel pour les autres utilisateurs.

.Architecture Scalable : Séparation claire entre la logique de service (Server) et l'interface utilisateur (Client)

# Aspects Techniques
.Protocole : TCP/IP (pour une transmission sans perte de paquets).

.Architecture : Centralisée (Le serveur agit comme un hub distribuant les messages).

.Multithreading : Utilisation de threads pour éviter le blocage de l'interface et permettre des lectures/écritures parallèles.

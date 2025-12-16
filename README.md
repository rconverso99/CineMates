# CineMates20

CineMates20 è un **Sistema Informativo per la Gestione e Condivisione di Film**, sviluppato nell'ambito del corso di Ingegneria del Software 2020-2021. Il sistema include due applicazioni principali: un'applicazione **Android** per gli utenti e un'applicazione **Desktop** per i moderatori.

---

## Architettura e Stack Tecnologico

Il sistema è strutturato su **tre livelli**: Client, Server e Database, garantendo sicurezza e modularità.

### Server
- **Architettura Serverless** su **AWS Cloud**
  - **EC2 (Elastic Compute Cloud):** Server Apache su Linux che gestisce le richieste HTTP tramite script PHP.
  - **Amazon RDS (PostgreSQL):** Database relazionale per la gestione efficiente dei dati.
  - **Amazon Cognito:** Autenticazione utenti, inclusa integrazione con Facebook.
- **API Esterne:** **TMDB (The Movie Database)** per accedere a oltre 600.000 film.

### Client (Android)
- Basato sul **pattern MVC (Model–View–Controller)**
  - **View/Controller:** Activity Android
  - **Model:** Classi dedicate e interfaccia `ApiInterface` per la logica e gestione dati

---

## Funzionalità

### Applicativo Android (Utenti)
- **Autenticazione:** Login e registrazione, con supporto a Facebook.
- **Ricerca e Visualizzazione Film:**
  - Ricerca film per titolo
  - Categorie predefinite: Popolari, Top Rated, Upcoming
  - Filtraggio per genere
- **Playlist:**
  - Gestione di due playlist: **Preferiti** e **Da Vedere**
  - Aggiunta e rimozione di film dalle playlist
- **Funzionalità Sociali:**
  - Profilo utente con follower e seguiti
  - Segui/Unfollow utenti
  - Confronto film preferiti con altri utenti

### Applicativo Desktop (Moderatori)
- **Autenticazione:** Accesso all'area moderatori
- **Gestione Moderatori:** Creazione nuovi profili moderatore
- **Comunicazione:**
  - Invio di mail promozionali agli utenti
  - Consigli personalizzati di film tramite email



---

CineMates20 combina la gestione dei film con funzionalità sociali avanzate, fornendo agli utenti un'esperienza completa di scoperta, condivisione e confronto dei propri interessi cinematografici.

Demo Android:
<img width="275" height="574" alt="image" src="https://github.com/user-attachments/assets/e38ea408-0b41-4d6b-8356-3a789964e084" />

<img width="273" height="576" alt="image" src="https://github.com/user-attachments/assets/c9e250e5-8d55-4fb6-b37b-d049dfa95048" />

<img width="270" height="576" alt="image" src="https://github.com/user-attachments/assets/f0fbbf5e-1796-4351-bd57-c43827f3cbca" />

<img width="270" height="580" alt="image" src="https://github.com/user-attachments/assets/40cbdf16-88d2-4bb0-94ae-602f62ff69d0" />

<img width="274" height="576" alt="image" src="https://github.com/user-attachments/assets/ada41da8-1cfb-44a4-9e0e-0647e8293dd0" />



Demo Desktop:
<img width="451" height="327" alt="image" src="https://github.com/user-attachments/assets/241235c6-6a2e-4fe0-b76d-91e0773f91d6" />

<img width="454" height="327" alt="image" src="https://github.com/user-attachments/assets/b7104e35-448d-4257-9b12-1d619440cd18" />

<img width="453" height="326" alt="image" src="https://github.com/user-attachments/assets/963b2b30-ad3e-4d39-a2b5-be8a2520355a" />

<img width="452" height="327" alt="image" src="https://github.com/user-attachments/assets/066acc10-c703-4313-996f-fe6f1d8f906e" />

<img width="453" height="327" alt="image" src="https://github.com/user-attachments/assets/c657febb-7eab-4975-a9e7-83cffc926e9c" />

<img width="451" height="325" alt="image" src="https://github.com/user-attachments/assets/c5daf6b2-de94-463b-a954-ee5617fc9e7e" />












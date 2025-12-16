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

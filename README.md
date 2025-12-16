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
<img width="990" height="1883" alt="image" src="https://github.com/user-attachments/assets/97a859b2-33f0-4bad-8e38-6064fe9a8778" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/36b5e9f8-6323-4d7f-8755-caf0675b5987" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/6c4eebae-1e26-4a8b-9d2e-026fe340f45f" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/81b2e3dc-8853-494d-8904-ff07f2367a7e" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/c1eb2922-fc19-4ddd-9595-6821c7e51418" />

Demo Desktop:
<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/17d6dcad-6238-48ad-8167-e08e5e8242d1" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/05b07c97-a1ac-4e48-89bc-92a16b910eee" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/d329bcec-bf02-4dcb-ab35-7ab531d5727c" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/96947af1-60ae-474f-a481-ee969c560694" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/ac34b2de-9b73-474f-b2fd-a4cfa948806b" />

<img width="941" height="1889" alt="image" src="https://github.com/user-attachments/assets/f659147c-6ea4-43f1-8c40-a0813cbc1919" />











<?php
   $servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
   $usernamedb = "postgres";
   $passworddb = "postgres";
   $db = "cinemates20";

  $nome=$_POST["nome"];
  $cognome=$_POST["cognome"];
  $username=$_POST["username"];
  $pass=$_POST["pass"];
  $email=$_POST["email"];
  $fotoprofilo=$_POST["foto_profilo"];

   // Create connection
   
   try {
    $conn = new PDO('pgsql:host=progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com;dbname=cinemates20', $usernamedb, $passworddb);
    // set the PDO error mode to exception
       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
       echo "Connected successfully";
       $sql= "INSERT INTO utenti (nome_utente,cognome_utente,username,password_utente,email_utente,foto_profilo) VALUES ('$nome','$cognome','$username','$pass','$email','$fotoprofilo')";
       $conn->exec($sql);
       echo "New record created successfully";
       }
    catch(PDOException $e)
       {
       echo "Connection failed: " . $e->getMessage();
       echo $sql . "<br>" . $e->getMessage();
       }


  



?>
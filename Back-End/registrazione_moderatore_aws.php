<?php
   $servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
   $usernamedb = "postgres";
   $passworddb = "postgres";
   $db = "cinemates20";

  $nome_moderatore=$_POST["nome_moderatore"];
  $email_moderatore=$_POST["email_moderatore"];
  $codice_moderatore=$_POST["codice_moderatore"];
  $password_moderatore=$_POST["password_moderatore"];

  

   // Create connection
   
   try {
    $conn = new PDO('pgsql:host=progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com;dbname=cinemates20', $usernamedb, $passworddb);
    // set the PDO error mode to exception
       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
       echo "Connected successfully";
       $sql= "INSERT INTO moderatori (nome_moderatore,email_moderatore,codice_moderatore,password_moderatore) VALUES ('$nome_moderatore','$email_moderatore','$codice_moderatore','$password_moderatore')";
       $conn->exec($sql);
       echo "New record created successfully";
       }
    catch(PDOException $e)
       {
       echo "Connection failed: " . $e->getMessage();
       echo $sql . "<br>" . $e->getMessage();
       }


  



?>
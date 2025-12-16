<?php
   $servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
   $usernamedb = "postgres";
   $passworddb = "postgres";
   $db = "cinemates20";

  $nome=$_POST["nome"];
  $username=$_POST["username"];
  $cod_playlist=$_POST["cod_playlist"];
  $tipo_playlist=$_POST["tipo_playlist"];
  $cod_film=$_POST["cod_film"];
  

   // Create connection
   
   try {
    $conn = new PDO('pgsql:host=progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com;dbname=cinemates20', $usernamedb, $passworddb);
    // set the PDO error mode to exception
       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
       echo "Connected successfully";
       $sql= "INSERT INTO playlist (nome,username,cod_playlist,tipo_playlist,cod_film) VALUES ('$nome','$username','$cod_playlist','$tipo_playlist','$cod_film')";
       $conn->exec($sql);
       echo "New record created successfully";
       }
    catch(PDOException $e)
       {
       echo "Connection failed: " . $e->getMessage();
       echo $sql . "<br>" . $e->getMessage();
       }


  



?>
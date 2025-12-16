<?php
   $servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
   $usernamedb = "postgres";
   $passworddb = "postgres";
   $db = "cinemates20";

  $cod_film=$_POST["cod_film"];
  $username=$_POST["username"];
  $tipo_playlist=$_POST["tipo_playlist"];
  

  

   // Create connection
   
   try {
    $conn = new PDO('pgsql:host=progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com;dbname=cinemates20', $usernamedb, $passworddb);
    // set the PDO error mode to exception
       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

       $sql= "DELETE FROM playlist WHERE username='$username'AND tipo_playlist='$tipo_playlist' AND cod_film='$cod_film' ";
       $conn->exec($sql);
       echo "New record delete successfully";
       }
    catch(PDOException $e)
       {
       echo "Connection failed: " . $e->getMessage();
       echo $sql . "<br>" . $e->getMessage();
       }


  



?>
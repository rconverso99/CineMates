<?php
   $servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
   $usernamedb = "postgres";
   $passworddb = "postgres";
   $db = "cinemates20";

   $username=$_POST["username"];
   $segue_username=$_POST["segue_username"];

  

  

   // Create connection
   
   try {
    $conn = new PDO('pgsql:host=progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com;dbname=cinemates20', $usernamedb, $passworddb);
    // set the PDO error mode to exception
       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

       $sql= "DELETE FROM segue WHERE username='$username'AND segue_username='$segue_username' ";
       $conn->exec($sql);
       echo "New record delete successfully";
       }
    catch(PDOException $e)
       {
       echo "Connection failed: " . $e->getMessage();
       echo $sql . "<br>" . $e->getMessage();
       }


  



?>
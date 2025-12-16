<?php
$servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
$usernamedb = "postgres";
$passworddb = "postgres";
$db = "cinemates20";

$codice_moderatore=$_POST["codice_moderatore"];
$password_moderatore=$_POST["password_moderatore"];

$response["codice_moderatore"] = "$codice_moderatore";
$response2["msg"] = "false";

try {
    $conn = new PDO('pgsql:host=progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com;dbname=cinemates20', $usernamedb, $passworddb);
    // set the PDO error mode to exception
       $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
       //echo "Connected successfully";
   }catch(PDOException $e)
       {
       echo "Connection failed: " . $e->getMessage();
       echo $sql . "<br>" . $e->getMessage();
       }

try{
        $query = $conn->prepare( "SELECT * FROM moderatori WHERE  codice_moderatore ='$codice_moderatore' and password_moderatore='$password_moderatore' ");
        //$query->bindParam(':username', $username_utente);
        //$query->bindParam(':password', $password_utente);
        $query->execute();
       // $found = $query->fetch();
    if( $query->rowCount() > 0 ) { # If rows are found for query
        $results = $query->fetchAll(PDO::FETCH_ASSOC);
        $json = json_encode($results);
        echo ($json);
    }
    else {
        echo json_encode($response2);
    }
      }catch(PDOException $e){
      echo $e->getMessage();
      }

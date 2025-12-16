<?php
$servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
$usernamedb = "postgres";
$passworddb = "postgres";
$db = "cinemates20";

$username_utente=$_POST["username_utente"];
$password_utente=$_POST["password_utente"];

$response["nome_utente"] = "$username_utente";
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
        $query = $conn->prepare( "SELECT nome_utente,cognome_utente,username,email_utente,foto_profilo FROM utenti WHERE  username ='$username_utente' and password_utente='$password_utente' ");
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

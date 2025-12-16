<?php
  $servername = "progetto.ckkkzotwusp3.us-east-2.rds.amazonaws.com";
  $usernamedb = "postgres";
  $passworddb = "postgres";
  $db = "cinemates20";

$user=$_POST["user"];
$response["msg"] = "true";
$response2["msg"] = "false";

   // Create connection
   
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
        $query = $conn->prepare( "SELECT username FROM utenti WHERE  username = :user");
        $query->bindParam(':user', $user);
        $query->execute();
       // $found = $query->fetch();
       if( $query->rowCount() > 0 ) { # If rows are found for query
        echo json_encode($response);
    }
    else {
        echo json_encode($response2);
    }
      }catch(PDOException $e){
      echo $e->getMessage();
      }


?>
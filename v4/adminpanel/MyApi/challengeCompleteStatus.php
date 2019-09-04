<?php 
 
require_once '../db_Operations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){

if(isset($_POST['userchallenge_Status']) and isset($_POST['challenge_Id']) and isset($_POST['user_Id']))
        {
			 $db = new db_Operations();
			  
 			$User_ID = $_POST['user_Id'];
			$Challenge_ID = $_POST['challenge_Id'];
			$ChallengeComplete_Status = $_POST['userchallenge_Status'];
			
			$db->select("UPDATE `take_challenge` SET `userchallenge_Status`='Complete' WHERE user_Id = $User_ID and challenge_Id = $Challenge_ID");
			$response['error'] = false; 
            $response['message'] = "Challenge Complete Successfully :P";
		}else{
				$response['error'] = true; 
			    $response['message'] = "Invalid Params";
		
			}

}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
}
echo json_encode($response);
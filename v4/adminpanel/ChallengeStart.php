<?php 
 
require_once 'db_Operations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	if(isset($_POST['user_Id']) and
            isset($_POST['challenge_Id']))
        {
				
		//echo "done!!";
		
			 $db = new db_Operations();
			  
 			$User_ID = $_POST['user_Id'];
			$Challenge_ID = $_POST['challenge_Id'];
//			$pass = md5($_POST['user_password']);
//			$age = $_POST['user_age'];
//			if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
// 				 $emailErr = "Invalid email format"; 
//				 $response['error'] = true; 
  //          $response['message'] = "Invalid email format";
	//			}else{
			
			
			
			
	//		if($row = $db->select("SELECT * FROM login_tb where user_name = '$name' || user_email = '$email'")){
	//			if($row->num_rows > 0){
					
	//				$response['error'] = true; 
      //      $response['message'] = "UserName OR Email is Already Registered"; 
					
		//			}else{
			if($row = $db->select("SELECT * FROM `take_challenge` WHERE user_Id = $User_ID and challenge_Id = $Challenge_ID and `userchallenge_Status`='Complete'"))
			{
				if($row->num_rows > 0){
									$response['error'] = true; 
				   			        $response['message'] = "Challenge Completed Already";
					}else{
						
						if($db->insert( 
 "INSERT INTO `Take_Challenge`(`user_Id`, `challenge_Id`, `userchallenge_Status`) VALUES ($User_ID,$Challenge_ID,'UnComplete')"
					          )){
									$response['error'] = false; 
				   			        $response['message'] = "Challenge Start";
									}else{
										$response['error'] = true; 
            $response['message'] = "Some error occurred please try again"; 
										}
		
						
						
						
						}
				
				}
						
		//				}
		//		}
			
        		
			
		//		}
		//}else{
		//	$response['error'] = true; 
        //$response['message'] = "Required fields are missing";
		//	}
}
}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
}
 
echo json_encode($response);
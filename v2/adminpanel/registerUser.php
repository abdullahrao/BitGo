<?php 
 
require_once 'db_Operations.php';
 
$response = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	if(isset($_POST['user_name']) and
            isset($_POST['user_email']) and
                isset($_POST['user_password']) and
					isset($_POST['user_age']))
        {
				
		//echo "done!!";
		
			 $db = new db_Operations();
			  
 			$name = $_POST['user_name'];
			$email = $_POST['user_email'];
			$pass = md5($_POST['user_password']);
			$age = $_POST['user_age'];
			if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
// 				 $emailErr = "Invalid email format"; 
				 $response['error'] = true; 
            $response['message'] = "Invalid email format";
				}else{
			
			
			
			if($row = $db->select("SELECT * FROM login_tb where user_name = '$name' || user_email = '$email'")){
				if($row->num_rows > 0){
					
					$response['error'] = true; 
            $response['message'] = "UserName OR Email is Already Registered"; 
					
					}else{
						if($db->insert( 
 "INSERT INTO `login_tb`(`user_name`, `user_email`, `user_password`, `user_age`) VALUES ('$name','$email','$pass',$age)"
					          )){
									$response['error'] = false; 
            $response['message'] = "User registered successfully";
									}else{
										$response['error'] = true; 
            $response['message'] = "Some error occurred please try again"; 
										}

						}
				}
			
        		
			
				}
		}else{
			$response['error'] = true; 
        $response['message'] = "Required fields are missing";
			}

}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
}
 
echo json_encode($response);
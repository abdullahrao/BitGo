<?php 
 
require_once 'db_Operations.php';
 
$response = array(); 
//$user = array(); 
 
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	if(isset($_POST['user_name']) and isset($_POST['user_password'])){
		
		$name = $_POST['user_name'];
		$password = md5($_POST['user_password']);
		
		$obj = new db_Operations();
		$user = $obj->select("SELECT * FROM login_tb where user_name = '$name' and user_password = '$password' ");
			if($user->num_rows > 0){
	while($row = mysqli_fetch_assoc($user)){
	$response['error'] = false;
	$response['user_id'] = $row['user_id'];
	$response['user_name'] = $row['user_name'];
	$response['user_email'] = $row['user_email'];
	$response['user_age'] = $row['user_age'];
//	$response['user_id'] = true;  		
	}
			}else{
				$response['error'] = true; 
    $response['message'] = "Invalid username or password!";
				}
			
			
			
		
		
		
		}else{
    $response['error'] = true; 
    $response['message'] = "Required Fields are Missing";
		}
	
	
	
	}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
		}

echo json_encode($response);
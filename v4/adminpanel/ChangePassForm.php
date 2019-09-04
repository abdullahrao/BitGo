<?php
require('db_Operations.php');

$response = array(); 
if($_SERVER['REQUEST_METHOD']=='POST'){

    if(isset($_POST['user_id']) and isset($_POST['user_password']) and isset($_POST['New_password']) and isset($_POST['Conf_password']) ){

        $User_ID = $_POST['user_id'];
        $Oldpass = md5($_POST['user_password']);
        $Newpass = $_POST['New_password'];
        $Confpass = $_POST['Conf_password'];

        $db = new db_Operations();
        
        $fetchdata=$db->select("Select user_password from login_tb where user_id = '$User_ID'");
        $row = mysqli_fetch_assoc($fetchdata);
        //echo $row['user_password'];
        if ($row['user_password'] == $Oldpass) {
    
            if ($Newpass == $Confpass) {
                $UpdatedPass = md5($Newpass);
            $db->select("UPDATE `login_tb` SET `user_password`='$UpdatedPass' WHERE user_id = $User_ID");
			$response['error'] = false; 
            $response['message'] = "Password Changed Successfully :P";

            }else{
                $response['error'] = true; 
                $response['message'] = "Invalid New Password";    
            }

        }else{
                $response['error'] = true; 
                $response['message'] = "Invalid Old Password";    
            }
    }else{
        $response['error'] = true; 
        $response['message'] = "Invalid Params";
    }

}else{
    $response['error'] = true; 
    $response['message'] = "Invalid Request";
}
echo json_encode($response);
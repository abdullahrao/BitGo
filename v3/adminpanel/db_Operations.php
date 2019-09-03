<?php
require('db_Connection.php');
class db_Operations{
	
	private $con;
	
	function __construct(){

		$obj = new db_connection();
		$this->con = $obj->db_connect();
		date_default_timezone_set("Asia/Karachi");

		}
	//public function insert($From,$To,$Reward){
		
	//	$ins = $this->con->prepare("INSERT INTO `daily_challenge`(`Challenge_id`, `ageFrom`, `ageTo`, `Reward`) VALUES (NULL,?,?,?)");
	//	$ins->bind_param("iii",$From,$To,$Reward);
	//	$ins->execute();
	//	}	
    public function insert($qry){
		
			$ins = $this->con->query($qry);
			return $ins;
	}
	
	public function select($qry){
		
			$sel = $this->con->query($qry);
			return $sel;
	}
	//public function registerUser($qry){
//		$password = md5($pass);
		//if($this->insert(" INSERT INTO `login_tb`(`user_id`, `user_name`, `user_email`, `user_password`, `user_age`) VALUES (".$username.",".$email.",".$password.",$age)"))
		//{
		//	return true;
		//	}else{
		//		return false;
		//		}
		
	//	}
	
}
<?php
//require('../db_Connection.php');
require('../db_Operations.php');


	 class selectapi{
	 
	 function __construct(){
		 //$obj = new db_connection();
		// $con = $obj->db_connect();
		 $op = new db_Operations();
		 
		//  $obj1 = new db_Operations();
	$manualDate1 = date("m/d/Y");
	$res1 = $op->select("SELECT * From `daily_challenge` where Challenge_Status = 'A'");
	$rowup = $res1->fetch_assoc();
	//echo $manualDate1."\n".$rowup['Challenge_ExpireDate']; 
	if($manualDate1 == $rowup['Challenge_ExpireDate']){
		$op->select("Update `daily_challenge` SET `Challenge_Status` = 'X' where Challenge_ExpireDate='$manualDate1'");
		//	 echo json_encode("updated");
		} 
		// $stmt = $con->prepare("SELECT `Challenge_id`, `ageFrom`, `ageTo`, `Reward` FROM `daily_challenge`");
		 //$stmt->execute();
		 //$stmt->bind_result($Challenge_id,$ageFrom,$ageTo,$Reward);
		$fetchdata = $op->select("SELECT Challenge_id,Reward,No_Steps,Challenge_Status,Challenge_CreateDate,Challenge_ExpireDate FROM daily_challenge where Challenge_Status = 'A'");
		 
		
		 $dataitem = array();
		 
		 //while($stmt->fetch()){
			while($row = mysqli_fetch_assoc($fetchdata)){ 
			 $temp = array();
			 $temp['Challenge_id'] = $row['Challenge_id'];
			 $temp['Reward'] = $row['Reward'];
			 $temp['No_Steps'] = $row['No_Steps'];
             $temp['Challenge_Status'] = $row['Challenge_Status'];
			 $temp['Challenge_CreateDate'] = $row['Challenge_CreateDate'];
			 $temp['Challenge_ExpireDate'] = $row['Challenge_ExpireDate'];
			 
			// echo $row['Challenge_id'];
			 //			 echo $row['ageFrom'];
				//		 			 echo $row['ageTo'];
				//					 			 echo $row['Reward'];
			 
			 array_push($dataitem, $temp);
			 
			 }
		 
			 echo json_encode($dataitem);
		 
		 
		 				}
	  
		 }
		 
		 $selectapi = new selectapi();

 
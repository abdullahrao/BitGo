<?php require('db_Operations.php'); ?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Daily Challenge</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen" href="main.css">
    <script src="main.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
});
  </script>
</head>
<body>
    
<div class="container">
  <h2>Welcome Create Daily Challenge</h2>
  <form action="DailyChallenge.php" method="post">
  <table class="table table-hover">
 <!--   <thead>
      <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
      </tr>
    </thead> -->
    <tbody>
      <tr>
      </tr>
      <tr>
      <td>Reward</td> <td><input type="text" name="reward" placeholder="Reward" autocomplete="off"/></td><td>Enter No# Of Steps</td><td><input type="text" name="Noofsteps" placeholder="No# Of Steps" autocomplete="off"/></td>
      </tr>
      <tr>
      <td>Expire Date</td><td colspan="3"><input type="text" name="ExpireDate" id="datepicker" placeholder="Expire Date" autocomplete="off"/></td>
      </tr>
      <tr>
      <td colspan="4" align="center"><input type="submit" class="btn btn-success" name="submitChallenge" value="Create Challenge" /></td>
      </tr>
    </tbody>
  </table>
  </form>
</div>
</body>
</html>
<?php
	  $obj1 = new db_Operations();
$manualDate1 = date("m/d/Y");
	$res1 = $obj1->select("SELECT * From `daily_challenge` where Challenge_Status = 'A'");
	$rowup = $res1->fetch_assoc();
	//echo $manualDate1."\n".$rowup['Challenge_ExpireDate']; 
	if($manualDate1 == $rowup['Challenge_ExpireDate']){
		$obj1->select("Update `daily_challenge` SET `Challenge_Status` = 'X' where Challenge_ExpireDate='$manualDate1'");
		} 
  
  if(isset($_POST['submitChallenge'])){
	 // $from = $_POST['agefrom'];
	  //$to = $_POST['ageto'];
	  $reward = $_POST['reward'];
	  $No_Steps = $_POST['Noofsteps'];
	  $Expire_date = $_POST['ExpireDate'];
	  $todayDay = date("d");
	  $todayMonth = date("m");
	  $todayYear = date("Y");
	  $manualDate = $todayMonth.'/'.$todayDay.'/'.$todayYear;	  
	 // echo date("m/d/Y");
	// $manualDate1 = "03/24/2019";
	 
	  $obj = new db_Operations();
	  
	$res = $obj->select("SELECT * From `daily_challenge` where Challenge_Status = 'A'");
	$row = $res->fetch_assoc();
	if($row > 1){
	echo '<script>alert("Challenge is Not Expire Yet")</script>';		  
		  }else{
    //$obj->insert($from,$to,$reward);
   if( $obj->insert("INSERT INTO `daily_challenge`(  `Reward`,`No_Steps`,`Challenge_Status`,`Challenge_CreateDate`,`Challenge_ExpireDate`) VALUES ($reward,$No_Steps,'A','$manualDate','$Expire_date')")){
	  echo '<script>alert("New Challenge Created Successfully")</script>';	
   
   }
   else{
	   echo '<script>alert("New Challenge is Not Created Due to Some Error!!! Kindly Check the Code")</script>';	
	   }
		  }
		
	  }
  
  ?>
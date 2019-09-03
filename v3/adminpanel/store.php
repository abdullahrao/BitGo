<?php 
 
require_once 'db_Operations.php';
 
$dataitem = array();
 
if($_SERVER['REQUEST_METHOD']=='POST'){

    $db = new db_Operations();

    if($row = $db->select("SELECT `s_id`, `image`, `name`, `price` FROM `tbl_store`"))
    {
        if($row->num_rows > 0){

		 
		 //while($stmt->fetch()){
			while($row1 = mysqli_fetch_assoc($row)){ 
			 $temp = array();
			 $temp['s_id'] = $row1['s_id'];
			 $temp['image'] = $row1['image'];
			 $temp['name'] = $row1['name'];
             $temp['price'] = $row1['price'];
			 
			 
             array_push($dataitem, $temp);
            }

        }

    }

}
     
echo json_encode($dataitem);
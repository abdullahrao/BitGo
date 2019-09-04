<?php
require('db_Operations.php');


if($_SERVER['REQUEST_METHOD']=='POST'){

    if(isset($_POST['user_id']) and isset($_POST['address']) and isset($_POST['pname']) and isset($_POST['email']) ){

        $db = new db_Operations();

        $userid = $_POST['user_id'];
        $add = $_POST['address'];
        $proname = $_POST['pname'];
        $em = $_POST['email'];
        
        $db->insert("INSERT INTO `tbl_order`(`user_id`, `address`, `ProName`, `email`) VALUES ($userid,'$add','$proname','$em')");

    }
}
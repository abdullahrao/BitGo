<?php
require('Constant.php');
date_default_timezone_set("Asia/Karachi");

class db_connection{
	
	private $con;
	
	function __construct(){
		
		}
	function db_connect(){
		$this->con = new mysqli(server,user,pass,db_name);
		if($this->con->connect_error){
			die("Connection Failed".$this->con->Connect_error);
			}
		return $this->con;
		}	
	
	}
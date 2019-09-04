<?php require('db_Operations.php'); ?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Vendors</title>
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
 
</head>
<body>
    
<div class="container">
  <h2>Welcome Create New Product</h2>
  <form action="vendors.php" method="post" enctype="multipart/form-data">
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
      <td>P.Name</td> <td><input type="text" name="pname" placeholder="Product Name" required autocomplete="off" /></td>
      <td>Price in Coins</td><td><input type="number" name="price" placeholder="CryptoCurrency Coins" required autocomplete="off"/></td>
      </tr>
      <tr>
      <td>Image(URL)</td><td colspan="3"><input type="text" name="pimage" placeholder="Image Url" required autocomplete="off" /></td>
      </tr>
      <tr>
      <td colspan="4" align="center"><input type="submit" class="btn btn-success" name="submitProduct" value="Create Product" /></td>
      </tr>
    </tbody>
  </table>
  </form>
</div>
</body>
</html>
<?php
if(isset($_POST['submitProduct'])){
	    
       $PName = $_POST['pname'];
       $Pprice = $_POST['price'];
       $imgName =$_POST['pimage'];

      $obj = new db_Operations();

      if ($obj->insert("INSERT INTO `tbl_store`( `image`, `name`, `price`) VALUES ('$imgName','$PName','$Pprice')")) {
       // echo "";
        echo '<script>alert("Your Product was uploaded successfully.")</script>';		  
      }else{
        //echo "";
        echo '<script>alert("Some Error Occured!")</script>';		  
      }
  } 

  
?>
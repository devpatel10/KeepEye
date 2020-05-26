<?php
require "init.php";
$username=$_GET['username'];
$latLng=$_GET['latLng'];
$sql =" UPDATE userdb SET latlng='$latLng' WHERE username='$username'";
$result=mysqli_query($con,$sql);
$status="ok";
echo json_encode(array("response"=>$status));
mysqli_close($con);

?>
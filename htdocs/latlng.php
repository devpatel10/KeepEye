<?php
require "init.php";
$username=$_GET['username'];
$latlng=$_GET['latlng']
$sql =" UPDATE userdb SET latlng='$latlng' WHERE username='$username' ";
$result=mysqli_query($con,$sql);
$sql1 =" UPDATE userdb SET latlng='$latlng' WHERE related='$username' ";
$result1=mysqli_query($con,$sql1);
    $status="ok";
    echo json_encode(array("response"=>$status));
mysqli_close($con);

?>
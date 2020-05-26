<?php
require "init.php";
$username = $_GET["username"];
$password=$_GET["password"];

$sql =" select * from userdb where username='$username' and password='$password'";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_assoc($result);
    $name=$row['name'];
    $status="ok";
    echo json_encode(array("response"=>$status,"name"=>$name));
}
else{
    $status="failed";
    echo json_encode(array("response"=>$status));
}
mysqli_close($con);
?>
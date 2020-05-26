<?php
require "init.php";
$username=$_GET['username'];
$sql =" select * from userdb where username='$username'";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_assoc($result);
    $type=$row['type'];
    $status=$type;
    echo json_encode(array("response"=>$status));
}
mysqli_close($con);

?>
<?php

require "init.php";
$name=$_GET["name"];
$username=$_GET["username"];
$password=$_GET["password"];
$phone=$_GET["phone"];
$type=$_GET["type"];
$sql ="select * from userdb where username='$username'";
$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result)>0)
{
    $status = "exist";
}

else
{
    $sql="insert into userdb(name,phone,username,password,type) values('$name','$phone','$username','$password','$type');";
    if(mysqli_query($con,$sql))
    {
        $status = "ok";
    }
    else{
        $status="error";
    }
}
echo json_encode(array("response"=>$status));
mysqli_close($con);
?>
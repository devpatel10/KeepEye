<?php
require "init.php";
$username=$_GET['username'];
$sql =" select * from userdb where username='$username' && type='parent'";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_assoc($result);
    $name=$row['related'];
    if($name!=NULL)
    {
        $sql2 =" UPDATE userdb SET related=NULL WHERE username='$username'";
        $result2=mysqli_query($con,$sql2);
        $sql3 =" UPDATE userdb SET related=NULL WHERE username='$name'";
        $result3=mysqli_query($con,$sql3);
        $status="ok";
        echo json_encode(array("response"=>$status));
    }
    else
    {
        $status="no";
        echo json_encode(array("response"=>$status));   
    }
}
else{
    $status="failed";
    echo json_encode(array("response"=>$status));
}
mysqli_close($con);
?>
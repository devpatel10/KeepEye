<?php
require "init.php";
$username=$_GET['username'];
$sql =" select * from userdb where username='$username'";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_assoc($result);
    $related=$row['related'];
    $sql1 =" select * from userdb where username='$related'";
    $result1=mysqli_query($con,$sql1);
    if(mysqli_num_rows($result)>0)
    {
        $row1=mysqli_fetch_assoc($result1);
        $latlng=$row1['latlng'];
     
            $status=$latlng;
        echo json_encode(array("response"=>$status));
        
        
    }
    else
    {
        $status="error";
        echo json_encode(array("response"=>$status));
    }
    
}
mysqli_close($con);

?>
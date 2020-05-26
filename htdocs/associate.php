<?php
require "init.php";
$username=$_GET['username'];
$related=$_GET['relate'];
$sql99 =" select * from userdb where username='$related' ";
$result99=mysqli_query($con,$sql99);
if(mysqli_num_rows($result99)>0){
    $row99=mysqli_fetch_assoc($result99);
    $name99=$row99['related'];
    if($name99!=NULL)
    {
        $sql98 =" UPDATE userdb SET related=NULL WHERE username='$related'";
        $result98=mysqli_query($con,$sql98);
        $sql97 =" UPDATE userdb SET related=NULL WHERE username='$name99'";
        $result99=mysqli_query($con,$sql97);
    }
}

$sql =" select * from userdb where username='$related' && type='child' ";
$result=mysqli_query($con,$sql);
if(mysqli_num_rows($result)>0)
{
    $sql1 =" select * from userdb where username='$username' && type='parent'";
    $result1=mysqli_query($con,$sql1);
    if(mysqli_num_rows($result1)>0)
    {
        $row=mysqli_fetch_assoc($result1);
        $name=$row['related'];
        if($name==NULL)
        {
            $sql2 =" UPDATE userdb SET related='$related' WHERE username='$username'";
            $result2=mysqli_query($con,$sql2);
            $sql7 =" UPDATE userdb SET related='$username' WHERE username='$related'";
            $result7=mysqli_query($con,$sql7);
        }
        else
        {
            $sql10 =" UPDATE userdb SET related=NULL WHERE username='$name'";
            $result10=mysqli_query($con,$sql10);
            $sql8 =" UPDATE userdb SET related='$related' WHERE username='$username'";
            $result8=mysqli_query($con,$sql8);
            $sql9 =" UPDATE userdb SET related='$username' WHERE username='$related'";
            $result9=mysqli_query($con,$sql9);
            
        }
        $status="ok";
        echo json_encode(array("response"=>$status));
    }
    else{
        $status="failed";
        echo json_encode(array("response"=>$status));
    }
}
else{
    $sql3 =" select * from userdb where username='$related' && type='parent' ";
    $result3=mysqli_query($con,$sql3);
    if(mysqli_num_rows($result3)>0)
    {
        
        $sql4 =" select * from userdb where username='$username' && type='child'";
        $result4=mysqli_query($con,$sql4);
        if(mysqli_num_rows($result4)>0)
        {
            $row=mysqli_fetch_assoc($result4);
            $name=$row['related'];
            if($name==NULL)
            {
                $sql5 =" UPDATE userdb SET related='$related' WHERE username='$username'";
                $result5=mysqli_query($con,$sql5);
                $sql6 =" UPDATE userdb SET related='$username' WHERE username='$related'";
                $result6=mysqli_query($con,$sql6);
            }
            else
            {
                $sql13 =" UPDATE userdb SET related=NULL WHERE username='$name'";
                $result13=mysqli_query($con,$sql13);
                $sql11 =" UPDATE userdb SET related='$related' WHERE username='$username'";
                $result11=mysqli_query($con,$sql11);
                $sql12 =" UPDATE userdb SET related='$username' WHERE username='$related'";
                $result12=mysqli_query($con,$sql12);
                
            }
            $status="ok";
            echo json_encode(array("response"=>$status));
        }
        else
        {
            $status="failed1";
            echo json_encode(array("response"=>$status));
        }
    }
    else
{
    $status="unavailable";
    echo json_encode(array("response"=>$status));
}
   
}
mysqli_close($con);

?>
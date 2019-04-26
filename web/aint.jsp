<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}
.topnav {
  overflow: hidden;
  background-color: #ccccff;
}
.topnav1 {
  overflow: hidden;
  background-color:#9999ff ;
}
.topnav1 a {
  float: left;
  color: black;
  text-align: center;
  padding: 5px 15px;
  text-decoration: none;
  font-size: 15px;
}
.topnav1 a:hover {
  background-color:#bbb ;
  color: black;
}
.topnav1 a.active {
  background-color: #cc99ff;
  color: black;
}
.topnav a {
  float: right;
  color:black ;
  text-align: center;
  padding: 14px 36px;
  text-decoration: none;
  font-size: 17px;
}
.topnav a:hover {
    background-color: #cccccc;
    color: black;
}
.topnav a.active {
    background-color: #cc99ff;
    color: black;
}
</style>
</head>
<body>
   
<div class="topnav">
  
   <center>
       <h3 style="font-family:Lucida Calligraphy"> ${"Welcome"}
       ${NAME}</h3>
   </center>
  <a href="index.html" target="_top">Logout</a>
  <a href="passchange.html" target="stud">Password</a>
  <a class="active" href="stud1.html" target="stud">Home</a>
</div>
<div class="topnav1">
 <a href="comp.html" target="stud">Complaints</a>
 <a href="personal" target="stud">Personal</a>
 <a href="updateacc" target="stud">Update Account</a>
 
</div>
  <br><br><iframe name="stud" src="stud1.html" height="580" width="1550" frameborder="0"></iframe>   
</body>
</html>

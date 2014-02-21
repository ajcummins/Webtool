<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	
	
	<script>
			function addTextRegion(){
				document.getElementById("tmp").innerHTML+="<tr><td>Jill</td></tr><tr>";
			}
			function SaveTextRegion(){
				document.getElementById("tmp").write(document.getElementById("tmp").innerHTML);
			}
			<!--document.write("<p>This is a paragraph</p>");-->
		</script>
		<style>
    .black_overlay{
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background-color: black;
        z-index:1001;
        -moz-opacity: 0.8;
        opacity:.80;
        filter: alpha(opacity=80);
    }
    .white_content {
        display: none;
        position: absolute;
        top: 25%;
        left: 25%;
        width: 50%;
        height: 50%;
        padding: 16px;
        border: 16px solid orange;
        background-color: white;
        z-index:1002;
        overflow: auto;
    }
	</style>
	<body><center>

		<div id="container" style="width:inherit">

			<div id="header" style="background-color:#BBBBBB;">
			<h1 style="margin-bottom:0;">Edit WebPage</h1></div>

			<div id="menu" style="background-color:#BBBBBB;height:inherit;width:inherit;">
				<b>Menu</b>
				HTML
				CSS
				JavaScript
			</div>

			<div id="content" style="background-color:#EEEEEE;height:inherit;width:inherit;float:middle;">
				<table id="tmp">
				<p><a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">Insert here</a></p>
				<div id="light" class="white_content">This is the lightbox content. <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">Close</a></div>
				<div id="fade" class="black_overlay"></div>
				</table>
				<button type="button" onclick="addTextRegion()">Add Section</button>
				<button type="button" onclick="SaveTextRegion()">Save</button>
			Content goes here</div>


		</div>
	 </center></body>

</html>
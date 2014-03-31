<!DOCTYPE html>
<html>
	<script type="text/javascript">
			var i;
			var txtR;
			function addTextRegion(){
				
				
				/*document.getElementById("tmp").style.fontStyle=document.getElementById("temptxt0").style.fontStyle;
				*/
				//document.getElementById("temptext0").style.font=
				//document.getElementById("temptext0").value;
				document.getElementById("tmp").innerHTML+= document.getElementById("yeah").innerHTML;
				document.getElementById("tmp").innerHTML+="<br>";
				
				resetAndCloseDiv();
				i++;
			}
			function textChange(){
				if(document.getElementById("italVal").checked){
					document.getElementById(txtR).style.fontStyle="italic";
				}else{
					document.getElementById(txtR).style.fontStyle="normal";
				}
				
				if(document.getElementById("boldVal").checked){
					document.getElementById(txtR).style.fontWeight="bold";
				}else{
					document.getElementById(txtR).style.fontWeight="normal";
				}
				var ali=0;
				//var ali = document.getElementById(txtR).style.textAlign=document.getElementById("align").value;
				if(ali==1){
					document.getElementById(txtR).style.textAlign="Left";
				}
				else if(ali==2){
					document.getElementById(txtR).style.textAlign="Center";
				}
				else if(ali==3){
					document.getElementById(txtR).style.textAlign="Right";
				}
				
				document.getElementById(txtR).style.fontSize=document.getElementById("fontVal").value+"px";// Times New Roman,serif;";
				document.getElementById(txtR).innerHTML=document.getElementById("temptext0").value;
			}
			function picBox(){
				var index = document.getElementById('selection').value;  
				document.getElementById("picEntry").innerHTML="";
				if(index==1){//Text
					var texttt;
					document.getElementById("picEntry").innerHTML+="<textarea id=temptext0 onchange=textChange()>";
					document.getElementById("picEntry").innerHTML+="<br>";
					
					document.getElementById("picEntry").innerHTML+="<input id=boldVal onchange=textChange() type=checkbox name=bold value=Bold>Bold</input><br>";
					document.getElementById("picEntry").innerHTML+="<input id=italVal onchange=textChange() type=checkbox name=ital value=Italic>Italic</input><br>";
					/*document.getElementById("picEntry").innerHTML+="<select id=align onchange=textChange()> ";
					document.getElementById("picEntry").innerHTML+="<option value=0>--</option>";
					document.getElementById("picEntry").innerHTML+="<option value=1>Left</option>";
					document.getElementById("picEntry").innerHTML+="<option value=2>Center</option>";
					document.getElementById("picEntry").innerHTML+="<option value=3>Right</option>";
					document.getElementById("picEntry").innerHTML+="</select><br>";*/
					document.getElementById("picEntry").innerHTML+="Font: <input id=fontVal onchange=textChange() type=number name=font value=20><br><br>";
					
					document.getElementById("picEntry").innerHTML+="<br>Text: ";
					txtR="rdyText"+i+"";
					document.getElementById("picEntry").innerHTML+="<br><form id =yeah><p id="+txtR+" >-</p></form>";
					
					
					
					document.getElementById("picEntry").innerHTML+="<button type=button onclick=addTextRegion()>Done</button>";
				}
				else if(index==2){//Images
					document.getElementById("picEntry").innerHTML+="<form name=Upload enctype=multipart/form-data method=post>";
					document.getElementById("picEntry").innerHTML+="Filename: <INPUT type=file id=submit>";
						//document.getElementById("picEntry").innerHTML+="<INPUT type="button" id="send" value="Upload">"
					document.getElementById("picEntry").innerHTML+="</form>";
					document.getElementById("picEntry").innerHTML+="<p>Or Enter URL of image: <p><textarea id=test0>";
					
					
					document.getElementById("picEntry").innerHTML+="<button type=button onclick=addImg()>Update Image</button><br>";
					
					//document.getElementById("picEntry").innerHTML+="";
				}else{
					document.getElementById("picEntry").innerHTML="";
				}
			}
			function addImg(){
				document.getElementById("picEntry").innerHTML+="<img id=imgtest src="+ document.getElementById("test0").value +" height=100 width=100>";
				document.getElementById("picEntry").innerHTML+="<form>";
				document.getElementById("picEntry").innerHTML+="Height: <input id=h type=number name=Height value="+document.getElementById("imgtest").height+" onchange=updateHWImg()><br>";
				document.getElementById("picEntry").innerHTML+="Width: <input id=w type=number name=Width value="+document.getElementById("imgtest").width+" onchange=updateHWImg()><br></form>";
				//document.getElementById("picEntry").innerHTML+=<input type="submit" value="Submit">
				
				document.getElementById("picEntry").innerHTML+="<button type=button onclick=addImgToPage()>Done</button>"
				
			}
			function addImgToPage(){
				document.getElementById("tmp").innerHTML+="<img src="+ document.getElementById("imgtest").src +" height="+document.getElementById("imgtest").height+" width="+document.getElementById("imgtest").width+">";
				document.getElementById("tmp").innerHTML+="<br>";
				resetAndCloseDiv();
				i++;
			}
			/*function addTextRegionToPage(){
			
			}*/
			function updateHWImg(){
				document.getElementById("imgtest").height = document.getElementById("h").value;
				document.getElementById("imgtest").width = document.getElementById("w").value;
			}
			function SaveTextRegion(){
				//document.getElementById("tmp").write(document.getElementById("tmp").innerHTML);
				//alert(document.getElementById("test0").value);
			}
			/*function getValue(e){
				var index = document.getElementById('selection').value;  
				//var index = e.value;
				alert("value="+index);  
				//alert("text="+document.getElementById('selection').options[index].text);
				if(index==0){
					alert("Nothing has been selected"); 
				}
				if(index==1){
					addTextRegion();
					document.getElementById('selection').value = 0;
				}
				if(index==2){
					addTextRegion();
					document.getElementById('selection').value = 0;
				}
				
			}*/
			function resetAndCloseDiv(){
				document.getElementById('light').style.display='none';
				document.getElementById('fade').style.display='none'
				document.getElementById("picEntry").innerHTML="";
				document.getElementById('selection').value = 0;
			}
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
			</div>

			<div id="content" style="background-color:#EEEEEE;height:inherit;width:inherit;float:middle;">
				<table id="tmp">
					
					
					<div id="light" class="white_content">
						<select id="selection" onchange="picBox()"> 
						  <option value="0">--</option>
						  <option value="1">Text</option>
						  <option value="2">Picture</option>
						</select><br>
						
						<div id="picEntry"></div>
						<br>
						<a href="javascript:void(0)" onclick = resetAndCloseDiv()>Close</a>
					</div>
					
					<div id="fade" class="black_overlay"></div>
				</table>
				<p><a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'">Insert here</a></p>
				<!--button type="button" onclick="getValue()">Add Section</button-->
				<button type="button" onclick="SaveTextRegion()">Preview Page</button>
			</div>


		</div>
	 </center></body>
</html>
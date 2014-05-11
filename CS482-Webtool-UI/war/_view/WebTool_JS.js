var i=0;
var j = new Array();
j[0]=0;
var txtR;
var pageSave;

function PopUp(){
	document.getElementById('light').style.display='block';
	document.getElementById('fade').style.display='block';
	document.getElementById('light').innerHTML="<p>How many Division would you like in this section? </><br>";
	document.getElementById('light').innerHTML+="<select id=numDivision value=0 onchange=DivisionTypeEdit()>";
	document.getElementById('numDivision').innerHTML+="<option value=0>None</option>";
	document.getElementById('numDivision').innerHTML+="<option value=1>1</option>";
	document.getElementById('numDivision').innerHTML+="<option value=2>2</option>";
	document.getElementById('light').innerHTML+="</select><br>";
	document.getElementById('light').innerHTML+="<div id=canvasDiv> </div>";
	
	
	
	document.getElementById('light').innerHTML+="<br><button type=button id=Smitb onclick=addTemplateToPage(document.getElementById('numDivision').value)>Submit</button>";
	document.getElementById('light').innerHTML+="<br><a href=javascript:void(0) onclick = resetAndCloseDiv()>Exit</a>";
	
}

function DivisionTypeEdit(){
	var numDiv = document.getElementById('numDivision').value;
	var width = 600;//document.getElementById("light").style.width-50;
	var height = 150;
	document.getElementById("canvasDiv").innerHTML="";
	document.getElementById("canvasDiv").innerHTML+="<canvas id=myCanvas width="+width+" height="+height+" style=border:1px solid #d3d3d3>";
	
	var c=document.getElementById("myCanvas");
	var ctx=c.getContext("2d");
	var w = c.width-50;
	
	if(numDiv==0){
		ctx.rect(25,25,w,100);
	}
	else if(numDiv==1){
		ctx.rect(25,25,w/2,100);
		ctx.rect(w/2+25,25,w/2,100);
	}
	else if(numDiv==2){
		ctx.rect(25,25,w/3,100);
		ctx.rect(w/3+25,25,w/3,100);
		ctx.rect(2*w/3+25,25,w/3,100);
		
	}
	ctx.stroke();
}

function addTemplateToPage(x){
	i++;
	resetAndCloseDiv();
	if(x==0){
		document.getElementById("workingPage").innerHTML+="<select id=selection+ x + onchange=picBox()>"; 
		document.getElementById("selection").innerHTML+="<option value=0>--</option>";
		document.getElementById("selection").innerHTML+="<option value=1>Text</option>";
		document.getElementById("selection").innerHTML+="<option value=2>Picture</option>";
		document.getElementById("workingPage").innerHTML+="</select><br>";
		
		document.getElementById("workingPage").innerHTML+="<div id=picEntry+ x +></div>";
		document.getElementById("workingPage").innerHTML+="<br>";
	}
}










function addTextRegion(){
	/*document.getElementById("tmp").style.fontStyle=document.getElementById("temptxt0").style.fontStyle;
	*/
	//document.getElementById("temptext0").style.font=
	//document.getElementById("temptext0").value;
	var name = "row"+i;
	document.getElementById("tmp").innerHTML+="<span id="+name+">";
	document.getElementById("tmp").innerHTML+="</span>";
	var sTmpName = "sideButton"+i;
	document.getElementById("tmp").innerHTML+="<button id="+i+" type=button onclick=addSideRegion("+i+")>Add Side Region</button>";
	var tmpName = name+"col"+j[i];
	document.getElementById(name).innerHTML+="<span id="+tmpName+">";
	document.getElementById(name).innerHTML+="</span>";
	
	document.getElementById(tmpName).innerHTML+= document.getElementById("yeah").innerHTML;
	
	
	document.getElementById(tmpName).innerHTML+="<br>";
	
	resetAndCloseDiv();
	j[i]++;
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
		document.getElementById("picEntry").innerHTML+="Font: <input id=fontVal onchange=textChange() type=number name=font value=20>";
		
		document.getElementById("picEntry").innerHTML+="<br>Text: ";
		txtR="rdyText"+i+""+j[i]+"";
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
function updateHWImg(){
	document.getElementById("imgtest").height = document.getElementById("h").value;
	document.getElementById("imgtest").width = document.getElementById("w").value;
}
function addSideRegion(){
	document.getElementById('light').style.display='block';
	document.getElementById('fade').style.display='block';
}
function storePage(){
	pageSave = document.getElementById("tmp");
}
function resetAndCloseDiv(){
	document.getElementById('light').style.display='none';
	document.getElementById('fade').style.display='none'
	//document.getElementById("picEntry").innerHTML="";
	//document.getElementById('selection').value = 0;
}
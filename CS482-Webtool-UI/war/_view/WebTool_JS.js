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

	resetAndCloseDiv();
	if(x==0){
		var sel = "selection"+ i;
		var selEntryVar = "selEntry"+ i +"0";
		document.getElementById("workingPage").innerHTML+="<span id=selection"+i+">";
			document.getElementById("selection"+i).innerHTML+="<select id="+sel+" onchange=picBox("+sel+","+selEntryVar+","+i+","+0+")>"; 
				document.getElementById(sel).innerHTML+="<option value=0>--</option>";
				document.getElementById(sel).innerHTML+="<option value=1>Text</option>";
				document.getElementById(sel).innerHTML+="<option value=2>Picture</option>";
			document.getElementById("selection"+i).innerHTML+="</select>";
		document.getElementById("workingPage").innerHTML+="</span>";
		
		
		document.getElementById("selection"+i).innerHTML+="<div id="+selEntryVar+"></div>";
		document.getElementById("selection"+i).innerHTML+="<br>";
	}
	if(x==1){
		var sel = "selection"+ i + "0";
		var selEntryVar = "selEntry"+ i +"0";
		
		var selEntryVar2 = "selEntry"+ i +"1";
		var sel2 = "selection"+ i + "1";
		
		var spanVar = "span"+i+"0";
		//document.getElementById("selection"+i).innerHTML+="<tr><td><span id="+spanVar+"></span></td>";
		
		var spanVar2 = "span"+i+"1";
		//document.getElementById("selection"+i).innerHTML+="<td><span id="+spanVar2+"></span></td></tr>";
		
		document.getElementById("workingPage").innerHTML+="<table width=100% id=selection"+i+"><tr><td><center><span id="+spanVar+"></span><div id="+selEntryVar+"></div></center></td><td><center><span id="+spanVar2+"></span><div id="+selEntryVar2+"></div></center></td></tr></table>";
		
		//left side span
			document.getElementById(spanVar).innerHTML+="<select id="+sel+" onchange=picBox("+sel+","+selEntryVar+","+i+","+0+")></select>";
				document.getElementById(sel).innerHTML+="<option value=0>--</option>";
				document.getElementById(sel).innerHTML+="<option value=1>Text</option>";
				document.getElementById(sel).innerHTML+="<option value=2>Picture</option>";
				
		//document.getElementById("selection"+i).innerHTML+="<div id="+selEntryVar+"></div>";
				
		
		//right side span
			document.getElementById(spanVar2).innerHTML+="<select id="+sel2+" onchange=picBox("+sel2+","+selEntryVar2+","+i+","+1+")></select>";
				document.getElementById(sel2).innerHTML+="<option value=0>--</option>";
				document.getElementById(sel2).innerHTML+="<option value=1>Text</option>";
				document.getElementById(sel2).innerHTML+="<option value=2>Picture</option>";
		
		//document.getElementById("selection"+i).innerHTML+="<div id="+selEntryVar2+"></div>";
		document.getElementById("selection"+i).innerHTML+="<br>";
	}
	
	i++;
}




function picBox(select, selDiv, a, b){
	var index = select.value;//document.getElementById(select).value;  
	selDiv.innerHTML="";
	if(index==1){//Text
		
		var boldVal="Bold"+a+""+b+"";
		var italVal="Ital"+a+""+b+"";
		var txtR="Text"+a+""+b+"";
		var temptext="temptext"+a+""+b+"";
		var fontVal="Font"+a+""+b+"";
		
		selDiv.innerHTML+="<textarea id="+temptext+" onchange=textChange("+temptext+", "+txtR+", "+boldVal+", "+italVal+", "+fontVal+")>";
		selDiv.innerHTML+="<br>";
		
		
		
		
		
		selDiv.innerHTML+="<input id="+boldVal+" onchange=textChange("+temptext+", "+txtR+", "+boldVal+", "+italVal+", "+fontVal+") type=checkbox name=bold value=Bold>Bold</input><br>";
		selDiv.innerHTML+="<input id="+italVal+" onchange=textChange("+temptext+", "+txtR+", "+boldVal+", "+italVal+", "+fontVal+") type=checkbox name=ital value=Italic>Italic</input><br>";
		/*selDiv.innerHTML+="<select id=align onchange=textChange("+temptext+", "+txtR+", "+boldVal+", "+italVal+", "+fontVal+")> ";
		selDiv.innerHTML+="<option value=0>--</option>";
		selDiv.innerHTML+="<option value=1>Left</option>";
		selDiv.innerHTML+="<option value=2>Center</option>";
		selDiv.innerHTML+="<option value=3>Right</option>";
		selDiv.innerHTML+="</select><br>";*/
		selDiv.innerHTML+="Font: <input id=fontVal onchange=textChange("+temptext+", "+txtR+", "+boldVal+", "+italVal+", "+fontVal+") type=number name=font value=20>";
		
		selDiv.innerHTML+="<br>Text: ";
		
		selDiv.innerHTML+="<br><form><p id="+txtR+" >-</p></form>";
		
		
		
		//document.getElementById("picEntry").innerHTML+="<button type=button onclick=addTextRegion()>Done</button>";
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

function textChange(stxt, dtxt, bol, ita, fnt){//, ali){
	if(ita.checked){
		dtxt.style.fontStyle="italic";
	}else{
		dtxt.style.fontStyle="normal";
	}
	
	if(bol.checked){
		dtxt.style.fontWeight="bold";
	}else{
		dtxt.style.fontWeight="normal";
	}
	var ali=0;
	//var ali = dtxt.style.textAlign=document.getElementById("align").value;
	if(ali==1){
		dtxt.style.textAlign="Left";
	}
	else if(ali==2){
		dtxt.style.textAlign="Center";
	}
	else if(ali==3){
		dtxt.style.textAlign="Right";
	}
	
	dtxt.style.fontSize=document.getElementById(fnt).value+"px";// Times New Roman,serif;";
	dtxt.innerHTML=document.getElementById(stxt).value;
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
var i=0;
var j = new Array();
j[0]=0;
var txtR;
var pageSave;

function PopUp(){//opens div for section selection
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

function DivisionTypeEdit(){//used for showing picture in div for Divisions
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

function addTemplateToPage(x){// used to add chosen template to page

	resetAndCloseDiv();
	if(x==0){
		var sel = "selection"+i;
		var selEntryVar = "selEntry"+ i +"0";
		/*
		document.getElementById("workingPage").innerHTML+="<span id=span"+i+">";
			document.getElementById("span"+i).innerHTML+="<select id="+sel+" onchange=picBox("+sel+","+selEntryVar+","+i+","+0+")>"; 
				document.getElementById(sel).innerHTML="<option>--</option>";
				document.getElementById(sel).innerHTML+="<option value=text>Text</option>";
				document.getElementById(sel).innerHTML+="<option value=picture>Picture</option>";
				document.getElementById(sel).innerHTML+="<option value=link>Link</option>";
			document.getElementById("span"+i).innerHTML+="</select>";
			document.getElementById("span"+i).innerHTML+="<div id="+selEntryVar+"></div>";
		document.getElementById("workingPage").innerHTML+="</span>";
		*/
		document.getElementById("workingPage").innerHTML+="<div id=section"+i+" name=section"+i+"></div>";
			document.getElementById("section"+i).innerHTML+="<table id=table"+i+" name=table"+i+"></table>";
				document.getElementById("table"+i).innerHTML+="<tr><td><span id=Division0 name=Division0></span></tr></td>";
					document.getElementById("Division0").innerHTML+="<select id="+sel+" onchange=picBox("+sel+","+selEntryVar+","+i+","+0+")></select>"; 
						document.getElementById(sel).innerHTML+="<option >--</option>";
						document.getElementById(sel).innerHTML+="<option value=text>Text</option>";
						document.getElementById(sel).innerHTML+="<option value=picture>Picture</option>";
						document.getElementById(sel).innerHTML+="<option value=link>Link</option>";
					document.getElementById("Division0").innerHTML+="<div id="+selEntryVar+"></div>";
							
				
				
		
		//document.getElementById("selection"+i).innerHTML+="<br>";
	}
	if(x==1){
		/*var sel = "selection"+ i + "0";
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
				document.getElementById(sel).innerHTML+="<option>--</option>";
				document.getElementById(sel).innerHTML+="<option value=text>Text</option>";
				document.getElementById(sel).innerHTML+="<option value=picture>Picture</option>";
				document.getElementById(sel).innerHTML+="<option value=link>Link</option>";
				
		//document.getElementById("selection"+i).innerHTML+="<div id="+selEntryVar+"></div>";
				
		
		//right side span
			document.getElementById(spanVar2).innerHTML+="<select id="+sel2+" onchange=picBox("+sel2+","+selEntryVar2+","+i+","+1+")></select>";
				document.getElementById(sel2).innerHTML+="<option >--</option>";
				document.getElementById(sel2).innerHTML+="<option value=text>Text</option>";
				document.getElementById(sel2).innerHTML+="<option value=picture>Picture</option>";
				document.getElementById(sel2).innerHTML+="<option value=link>Link</option>";
		
		//document.getElementById("selection"+i).innerHTML+="<div id="+selEntryVar2+"></div>";
		document.getElementById("selection"+i).innerHTML+="<br>";
		*/
		
		document.getElementById("workingPage").innerHTML+="<div id=section"+i+" name=section"+i+"></div>";
			document.getElementById("section"+i).innerHTML+="<table id=table"+i+" name=table"+i+" width=100%></table>";
				document.getElementById("table"+i).innerHTML+="<tr><td><center><span id=Division0 name=Division0></span></center></td><td><center><span id=Division1 name=Division1></span></center><tr><td>";
			
		var sel = "selection"+ i + "0";	
		var selEntryVar = "selEntry"+ i +"0";
		var selEntryVar2 = "selEntry"+ i +"1";
		
		var sel2 = "selection"+ i + "1";
		var spanVar = "Division0";
		var spanVar2 = "Division1";
				
				
				//left side span
				document.getElementById(spanVar).innerHTML+="<select id="+sel+" onchange=picBox("+sel+","+spanVar+","+i+","+0+")></select>";
				document.getElementById(sel).innerHTML+="<option >--</option>";
				document.getElementById(sel).innerHTML+="<option value=text>Text</option>";
				document.getElementById(sel).innerHTML+="<option value=picture>Picture</option>";
				document.getElementById(sel).innerHTML+="<option value=link>Link</option>";
				
				
				
				
				
				//right side span
				document.getElementById(spanVar2).innerHTML+="<select id="+sel2+" onchange=picBox("+sel2+","+spanVar2+","+i+","+1+")></select>";
				document.getElementById(sel2).innerHTML+="<option >--</option>";
				document.getElementById(sel2).innerHTML+="<option value=text>Text</option>";
				document.getElementById(sel2).innerHTML+="<option value=picture>Picture</option>";
				document.getElementById(sel2).innerHTML+="<option value=link>Link</option>";
				
				//document.getElementById("selection"+i).innerHTML+="<br>";
				
	}
	
	i++;
}
function italChange(ita, dtxt){
	if(ita.checked){
		dtxt.style.fontStyle="italic";
	}else{
		dtxt.style.fontStyle="normal";
	}
}

function boldChange(bol, dtxt){
	if(bol.checked){
		dtxt.style.fontWeight="bold";
	}else{
		dtxt.style.fontWeight="normal";
	}
}
function fontChange(fnt, dtxt){
	dtxt.style.fontSize=fnt.value+"px";
}

function textChange(stxt, dtxt){
	//dtxt = stxt;
	dtxt.innerHTML=stxt.value;
}

function alignChange(ali, dtxt){//(stxt, dtxt, bol, ita, fnt){//, ali){
	if(ali==1){
		dtxt.style.textAlign="Left";
	}
	else if(ali==2){
		dtxt.style.textAlign="Center";
	}
	else if(ali==3){
		dtxt.style.textAlign="Right";
	}
}


function addImg(sImg, spanId, a , b){	

	imgN = "image" +a+""+b;
	spanId.innerHTML+="<img id="+imgN+"></img>";
	document.getElementById(imgN).src=sImg.value; 
	
	
	//document.getElementById("picEntry").innerHTML+=<input type="submit" value="Submit">
	
	//Div.innerHTML+="<button type=button onclick=addImgToPage()>Done</button>"
	
}


function picBox(select, selDiv, a, b){// used for showing edits made to text field
	var index = select.value;//document.getElementById(select).value;  
	selDiv.innerHTML="";
	if(index=='text'){//Text
		boldVal="Bold"+a+""+b+"";
		italVal="Ital"+a+""+b+"";
		aliVal="Align"+a+""+b+"";
		txtR="Text"+a+""+b+"";
		temptext="temptext"+a+""+b+"";
		fontVal="Font"+a+""+b+"";
		
		var arguments = [temptext, txtR];//, boldVal, italVal,fontVal];
		var argumentsB = [boldVal, txtR];//[ 'Bold'+a+''+b];
		var argumentsI = [ italVal, txtR];
		var argumentsF = [ fontVal, txtR];
		var argumentsA = [ aliVal, txtR];
		
		selDiv.innerHTML+="<textarea id="+temptext+" onchange=textChange("+arguments+");></textarea>";
		selDiv.innerHTML+="<br>";
		
		selDiv.innerHTML+="<input id="+boldVal+" onchange=boldChange("+argumentsB+") type=checkbox name=bold value=true>Bold</input><br>";
		selDiv.innerHTML+="<input id="+italVal+" onchange=italChange("+argumentsI+") type=checkbox name=ital value=true>Italic</input><br>";
		/*
		selDiv.innerHTML+="<select id="+aliVal+" onchange=alignChange("+argumentsA+")> ";
			document.getElementById(aliVal).innerHTML="<option value=0>--</option>";
			document.getElementById(aliVal).innerHTML+="<option value=1>Left</option>";
			document.getElementById(aliVal).innerHTML+="<option value=2>Center</option>";
			document.getElementById(aliVal).innerHTML+="<option value=3>Right</option>";
		selDiv.innerHTML+="</select><br>";*/
		selDiv.innerHTML+="Font: <input id="+fontVal+" onchange=fontChange("+argumentsF+") type=number name=font value=20>";
		
		selDiv.innerHTML+="<br>Text: ";
		//txtR="Text"+i+""+j[i]+"";
		selDiv.innerHTML+="<br><form id =yeah><p id="+txtR+" >-</p></form>";
		
		
		
		
		//document.getElementById("picEntry").innerHTML+="<button type=button onclick=addTextRegion()>Done</button>";
	}
	else if(index=='picture'){//Images
		selDiv.innerHTML+="<form name=Upload enctype=multipart/form-data method=post>";
		selDiv.innerHTML+="Filename: <INPUT type=file id=submit>";
			//document.getElementById("picEntry").innerHTML+="<INPUT type="button" id="send" value="Upload">"
		selDiv.innerHTML+="</form>";
		
		tmpImage = "tmpImage"+a+""+b+"";
		image = "image"+a+""+b+"";
		
		
		selDiv.innerHTML+="<p>Or Enter URL of image: </p><textarea id="+tmpImage+">";
		
		
		
		var spanId = "span"+a+""+b;
		
		selDiv.innerHTML+="<br><form id =yeah><span id="+spanId+"></span></form>";
		
		
		selDiv.innerHTML+="<form>";
		hId = "height" +a+""+b;
		wId = "width" +a+""+b;
		//selDiv.innerHTML+="Height: <input id="+hId+" type=number name=Height value="+100+" onchange=updateHImg("+hId+",image"+a+""+b+")><br>";
		//selDiv.innerHTML+="Width: <input id="+wId+" type=number name=Width value="+100+" onchange=updateWImg("+wId+",image"+a+""+b+")><br></form>";
		var args = [tmpImage, spanId, a, b ];
		
		selDiv.innerHTML+="<button type=button onclick=addImg("+args+")>Update Image</button><br>";
		
		//document.getElementById("picEntry").innerHTML+="";
	}else{
		selDiv.innerHTML="";
	}
}

function updateHImg(sor, des){
	des.height = sor.value;
}
function updateWImg(sor, des){
	des.width = sor.value;
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


function addImgToPage(){
	document.getElementById("tmp").innerHTML+="<img src="+ document.getElementById("imgtest").src +" height="+document.getElementById("imgtest").height+" width="+document.getElementById("imgtest").width+">";
	document.getElementById("tmp").innerHTML+="<br>";
	resetAndCloseDiv();
	i++;
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
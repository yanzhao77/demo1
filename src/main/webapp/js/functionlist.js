$().ready(function(){
	$("#saverolefunc").click(function(){
		var checklist = "";
		var cblist = $(".cb");
		var roleid = document.getElementById("roleid").value;
		for(var i = 0;i < cblist.length;i++){
			if(cblist[i].checked){
				checklist = checklist + cblist[i].value;
				checklist = checklist + ",";
			}
		}
		$.post("/sys/as-role-premission/saverolefunc",{'checkFuncList':checklist,'roleId':roleid},function(result){
			if("success" == result){
				alert("保存成功！");
				window.location.href="/sys/as-role-premission/funclist?roleId="+roleid;
			}else{
				alert("保存失败！");
			}
		},'html');	
	});
	
	$("#cball").change(function(){
		var checkList = "";
		var cblist = $(".cb");
		if(document.getElementById("cball").checked)
			for(var i=0;i<cblist.length;i++)
				cblist[i].checked ='checked';
		else
			for(var i=0;i<cblist.length;i++)
				cblist[i].checked ='';
	});
	
});
$().ready(function(){

	configtype = 0;
	$(".modifySystemBtn").click(function(){
		var obj = $(this);
		$("#sid").val(obj.attr("sid"));
		$("#configName").val(obj.attr("sname"));
		configtype = obj.attr("configtype");
		if(configtype == 7 || configtype == 2){
			$("#modifyConfigValue").val(obj.attr("configvalue"));
		}
		if(configtype == 7){
			$("#modifyConfigTypeValue").val(obj.attr("configtypevalue"));
		}
		
		var select;
		if(obj.attr("isstart") == 1){
			select = "<select id='isstartselect'><option value='1' selected='selected'>启用</option><option value='2'>不启用</option></select>";
		}else{
			select = "<select id='isstartselect'><option value='1'>启用</option><option value='2' selected='selected'>不启用</option></select>";
		}
		$("#isstart").html('');
		$("#isstart").html(select);
		$("#addSystemdiv").slideUp(500);
		$("#modifySystemdiv").slideUp(500);
		$("#modifySystemdiv").slideDown(500);
	});
	$("#modifySystemConfigBtn").click(function(){
		
		var id = $("#sid").val();
		var configType = $.trim($("#modifyConfigType").val());
		var configName = $.trim($("#configName").val());
		var isstart = $("#isstartselect").val();
		var configvalue;
		var configTypevalue;
		var params;
		var ispeat = "nopeat";
		if(configName == '') 
			alert("类型名称不能为空！");
		else{
			$.post("/sys/as-systemconfig/isPeatConfig.action",{
				'id':id,
				'configType':configType,
				'configTypeName':configName
			},function(result){
				
				if("peat" == result){ 
					ispeat = "peat";
					alert("对不起，该类型名称已存在。");
				}else if("nopeat" == result){
					if(configtype == 7 || configtype == 2){
						
						configvalue = $.trim($("#modifyConfigValue").val());
					
						/*if(configval== '') {
							alert("！!!");
						}
						else if(configtype == 7 && isInteger(configvalue) == false){
							alert("类型数值应为大于0的正整数！");
						}*/
						
						
					
							if(configtype == 7){
								
								configTypevalue = $.trim($("#modifyConfigTypeValue").val());
								params = {
										'id':id,
										'configTypeName':configName,
										'configValue':configvalue,
										'configTypeValue':configTypevalue,
										'isStart':isstart};
							}else{
								
								params = {
										'id':id,
										'configTypeName':configName,
										'configValue':configvalue,
										'isStart':isstart};
							}
							if(configTypevalue == '') 
								alert("实际数值不能为空！");
							else if(configtype == 7 && isInteger(configTypevalue) == false)
								alert("实际数值应为大于0的正整数！");
							else{
								   
								$.post("/sys/as-systemconfig/modifyconfig.action",params,function(result){
									if("success" == result){
										alert("恭喜您，修改成功。");
										window.location.reload(true);
									}else{
										humane.success("对不起，修改"+configName+"失败.");
									}
									
								},"html");
								
							}
						
						
						
					}else{
						$.post("/sys/as-systemconfig/modifyconfig.action",{
							
							'id':id,
							'configTypeName':configName,
							'isStart':isstart
						},function(result){
							
							if("success" == result){
								alert("恭喜您，修改成功。");
								window.location.reload(true);
							}else{
								humane.success("对不起，修改"+configName+"失败.");
							}
							
						},"html");
					}
				}
			},"html");
		}
	});
	$("#cancleModifySystemConfigBtn").click(function(){
		$("#modifySystemdiv").slideUp(500);
	});
	$(".deleteSystemBtn").click(function(){
	
		var obj = $(this);
		if(confirm("您确定要删除【"+obj.attr("sname")+"】配置吗？")){
			//alert("Obj :" + obj.attr("sid")); deleteconfig
			
			$.post("/sys/as-systemconfig/deleteconfig.action",{
				'id':obj.attr("sid")
			},function(result){
				
				if("success" == result){
					alert("恭喜您，删除成功。");
					window.location.reload(true);
				}else{
					humane.success("对不起，删除"+configName+"失败.");
				}
				
			},"html");
		}
	});
	$("#addsystemconfig").click(function(){
		$("#modifySystemdiv").slideUp(500);
		$("#addSystemdiv").slideDown(500);
	});
	$("#cancleAddSystemConfigBtn").click(function(){
		$("#addSystemdiv").slideUp(500);
	});
	$("#addSystemConfigBtn").click(function(){
		
		var configType = $.trim($("#addConfigType").val());
		var configName = $.trim($("#addConfigName").val());
		var isstart = $("#addIsStartSelect").val();
		var addParams;
		var ispeat = "nopeat";
		if(configType == '') 
			alert("配置类型不能为空！");
		else if(configName == '') 
			alert("类型名称不能为空！");
		else{
			$.post("/sys/as-systemconfig/isPeatConfig.action",{
				'configType':configType,
				'configTypeName':configName
			},function(result){
				if("peat" == result){ 
					ispeat = "peat";
					alert("对不起，该类型名称已存在。");
				}else if("nopeat" == result){
					if(configType == 7 || configType == 2){
						configvalue = $.trim($("#addConfigValue").val());
						if(configvalue == '') 
							alert("类型数值不能为空！");
						else if(configType == 7 && isInteger(configvalue) == false)
							alert("类型数值应为大于0的正整数！");
						else{
							if(configType == 7){
								var configTypevalue = $.trim($("#addConfigTypeValue").val());
								
								addParams = {
											'configType':configType,
											'configTypeName':configName,
											'configValue':configvalue,
											'configTypeValue':configTypevalue,
											'isStart':isstart
										};
							}else{
								addParams = {
										'configType':configType,
										'configTypeName':configName,
										'configValue':configvalue,
										'isStart':isstart
										};
							}
							if(configTypevalue == '') 
								alert("实际数值不能为空！");
							else if(configType == 7 && isInteger(configTypevalue) == false)
								alert("实际数值应为大于0的正整数！");
							else{
								$.post("/sys/as-systemconfig/addconfig.action",addParams,function(result){
									alert(111)
									if("success" == result){
										alert("恭喜您，添加成功。");
										window.location.reload(true);
									}else{
										humane.success("对不起，失败"+configName+"失败.");
									}
									
								},"html");
							}
						}
					}else{
						$.post("/sys/as-systemconfig/addconfig.action",{
							'configType':configType,
							'configTypeName':configName,
							'isStart':isstart
						},function(result){
							if("success" == result){
								alert("恭喜您，添加成功。");
								window.location.reload(true);
								
							}else{
								humane.success("对不起，失败"+configName+"失败.");
							}
							
						},"html");
					}
				}
			},"html");
		}
		
	});
	
	$("#simpleBtn").click(function(){
		alert(123);
		var simpTypeName = $.trim($("#simpTypeName").val());
		var simpConfigValue = $.trim($("#simpConfigValue").val()); 
		var configType = $.trim($("#configType").val()); 
		var simpleId = $("#simpleId").val();
		if(simpTypeName == '') 
			alert("配置名称不能为空！");
		else if(simpConfigValue == '') 
			alert("配置数值不能为空！");
		else if(configType == "4" && isURL(simpConfigValue) == false)
			alert("输入的URL格式不正确，请重新输入！");
		else if(configType == "3" && isInteger(simpConfigValue) == false)
			alert("输入的配置数值应为大于0的正整数！");
		else{
			alert(321);
			$.post("/sys/as-systemconfig/modifyconfig.action",{
				'id':simpleId,
				'configTypeName':simpTypeName,
				'configValue':simpConfigValue,
				'isStart':1
			},function(result){
				if("success" == result){
					alert("恭喜您，修改成功。");
					window.location.reload(true);
				}else{
					humane.success("对不起，修改"+configName+"失败.");
				}
				
			},"html");
		}
	});
	mover(5);
});

function isInteger(simpConfigValue){
	var strRegex = "^[0-9]*[1-9][0-9]*$"; //正整数
	var re = new RegExp(strRegex);
	if (re.test(simpConfigValue))
		return true;
	else 
		return false;
}

function isURL(url){
	var strRegex = "^((https|http|ftp|rtsp|mms)://)?[a-z0-9A-Z]{3}\.[a-z0-9A-Z][a-z0-9A-Z]{0,61}?[a-z0-9A-Z]\.com|net|cn|cc (:s[0-9]{1-4})?/$";
	var re = new RegExp(strRegex);
	if (re.test(url))
		return true;
	else 
		return false;
}

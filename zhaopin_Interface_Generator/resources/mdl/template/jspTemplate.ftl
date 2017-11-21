<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body class="easyui-layout">
 	 <!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="过滤条件" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
        <p class="ui-fields">
#foreach($po in $!{columnDatas})
#if  ($po.columnName !='id' && $velocityCount  < 4)
			<label class="ui-label">${po.columnComment}:</label><input name="${po.columnName}" class="easyui-box ui-text" style="width:100px;">
#end
#end
	    </p>
	    <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->

     <!-- Data List -->
     <div region="center" border="false" >
     <table id="data-list"></table>
	 </div>
	 
     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="Basic window" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
		     	   <div class="ftitle">${codeName}</div>
#foreach($po in $!{columnDatas})
#if  ($po.columnName !='id')     	   
					<div class="fitem">
						<label>${po.columnComment}</label>
						<input name="${po.columnName}" type="text" maxlength="${po.charmaxLength}" class="${po.classType}" data-options="${po.optionType}" missingMessage="请填写${po.columnComment}">
					</div>
#end
#end
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/${bussPackage}/${entityPackage}/page-${lowerName}.js"></script>
  </body>
</html>

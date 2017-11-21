$package('jeecg.${lowerName}');
jeecg.${lowerName} = function(){
	var _box = null;
	var _this = {
		config:{
			event:{
				add:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.add();
				},
				edit:function(){
					$('#typeIds_combobox').combobox('reload');
					_box.handler.edit();
				}
			},
  			dataGrid:{
  				title:'${codeName}',
	   			url:'dataList.do',
	   			columns:[[
					{field:'id',checkbox:true},
#foreach($po in $!{columnDatas})
#if  ($po.columnName !='id')
					{field:'${po.columnName}',title:'${po.columnComment}',align:'center',sortable:true,
							formatter:function(value,row,index){
								return row.${po.columnName};
							}
						},
#end
#end
					]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	jeecg.${lowerName}.init();
});
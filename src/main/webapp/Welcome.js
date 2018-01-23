$(window).resize(function(){
	var AAAHeight = $(".contentInner_tr").height();
	$("#jvmContainer3").height(AAAHeight);
	$("#sysContainer4").height(AAAHeight);
})

require.config({
	paths : {
		echarts : 'layout/lib/echarts-2.2.7/build/dist'
	}
});
require(
[ 'echarts',
  'echarts/chart/line', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
  'echarts/chart/bar',
  'echarts/chart/gauge' 
], 
function(ec) {
	var jvmChart = ec.init(document.getElementById('jvmContainer'));
	var sysChart = ec.init(document.getElementById('sysContainer'));
	var jvmChart3 = ec.init(document.getElementById('jvmContainer3'));
	var sysChart4 = ec.init(document.getElementById('sysContainer4'));
	var option = {
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			name : '业务指标',
			type : 'gauge',
			splitNumber : 10, // 分割段数，默认为5
			axisLine : { // 坐标轴线
				lineStyle : { // 属性lineStyle控制线条样式
					color : [ [ 0.2, '#228b22' ], [ 0.8, '#48b' ],[ 1, '#ff4500' ] ],
					width : 8
				}
			},
			axisTick : { // 坐标轴小标记
				splitNumber : 10, // 每份split细分多少段
				length : 12, // 属性length控制线长
				lineStyle : { // 属性lineStyle控制线条样式
					color : 'auto'
				}
			},
			axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto'
				}
			},
			splitLine : { // 分隔线
				show : true, // 默认显示，属性show控制显示与否
				length : 30, // 属性length控制线长
				lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
					color : 'auto'
				}
			},
			pointer : {
				width : 5
			},
			title : {
				show : true,
				offsetCenter : [ 0, '-40%' ], // x, y，单位px
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					fontWeight : 'bolder',
					color : "#fff"
				}
			},
			detail : {
				formatter : '{value}%',
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto',
					fontWeight : 'bolder'
				}
			},
			data : [ {
				value : 0,
				name : '使用率'
			} ]
		} ]
	};
	var option1 = {
		tooltip : {
			formatter : "{a} <br/>{b} : {c}%"
		},
		series : [ {
			name : '业务指标',
			type : 'gauge',
			splitNumber : 10, // 分割段数，默认为5
			axisLine : { // 坐标轴线
				lineStyle : { // 属性lineStyle控制线条样式
					color : [ [ 0.2, '#228b22' ], [ 0.8, '#48b' ],
							[ 1, '#ff4500' ] ],
					width : 8
				}
			},
			axisTick : { // 坐标轴小标记
				splitNumber : 10, // 每份split细分多少段
				length : 12, // 属性length控制线长
				lineStyle : { // 属性lineStyle控制线条样式
					color : 'auto'
				}
			},
			axisLabel : { // 坐标轴文本标签，详见axis.axisLabel
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto'
				}
			},
			splitLine : { // 分隔线
				show : true, // 默认显示，属性show控制显示与否
				length : 30, // 属性length控制线长
				lineStyle : { // 属性lineStyle（详见lineStyle）控制线条样式
					color : 'auto'
				}
			},
			pointer : {
				width : 5
			},
			title : {
				show : true,
				offsetCenter : [ 0, '-40%' ], // x, y，单位px
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					fontWeight : 'bolder',
					color : "#fff"
				}
			},
			detail : {
				formatter : '{value}%',
				textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE
					color : 'auto',
					fontWeight : 'bolder'
				}
			},
			data : [ {
				value : 0,
				name : '使用率'
			} ]
		} ]
	};
	// ---------------------------------------------------------------------------------------
	var option2 = {
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '内存使用率' ],
			textStyle : {
				color : '#fff'
			}
		},
		dataZoom : {
			show : false,
			start : 0,
			end : 100
		},
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			axisLabel : {
				textStyle : {
					color : '#fff',
				},
				interval : 0,// 横轴信息全部显示
				rotate : 45,// 60度角倾斜显示
			},
			data : (function() {
				var now = new Date();
				var res = [];
				var len = 20;
				while (len--) {
					res.unshift(now.toLocaleTimeString());
					now = new Date(now - 3000);
				}
				return res;
			})()
		} ],
		yAxis : [ {
			type : 'value',
			scale : true,
			name : '使用率%',
			splitNumber:10,
//			min: 30,
//			max: 100,
			axisLabel : {
				textStyle : {
					color : '#fff'
				}
			},
			boundaryGap : [ 0, 0 ]
		} ],
		series : [

		{
			name : '内存使用率',
			type : 'line',
			data : (function() {
				var res = [];
				var len = 20;
				while (len--) {
					// 初始化的数组 ，长度是10
					res.push(0);
				}
				return res;
			})()
		} ]
	};
	var lastData = 1;
	var lastData1 = 1;
	var axisData;
	jvmChart.setOption(option);
	sysChart.setOption(option1);
	jvmChart3.setOption(option2);
	sysChart4.setOption(option2);
	
	// 窗口改变大小后重新加载图表
	setTimeout(function() {
		window.onresize = function() {
			jvmChart3.resize();
			sysChart4.resize();
		}
	}, 200);

	clearInterval(timeTicket);
	var timeTicket = setInterval(function() {
		option.series[0].data[0].value = refreshData()[0];
		option1.series[0].data[0].value = refreshData()[1];
		jvmChart.setOption(option, true);
		sysChart.setOption(option1, true);

		lastData = refreshData()[0];
		lastData1 = refreshData()[1];
		axisData = (new Date()).toLocaleTimeString();

		// 动态数据接口 addData
		jvmChart3.addData([[ 0, // 系列索引
		lastData, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData // 坐标轴标签
		] ]);
		sysChart4.addData([ [ 0, // 系列索引
		lastData1, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData // 坐标轴标签
		] ]);
	}, 5000);
	// ---------------------------------------------------------------------------------------

});

// ajax请求获取数据
function refreshData() {
	var memoryUsedRatio = [];
	$.ajax({
		url : 'h3cWelcomeController.do?refreshData',
		type : 'post',
		async : false,
		datatype : 'json',
		success : function(rs) {
			if (rs.success) {
				var memoryInfo = rs.data;
				$("#jvm_totalMemory").text(memoryInfo.jvm_totalMemory);
				$("#jvm_usedMemory").text(memoryInfo.jvm_usedMemory);
				$("#jvm_freeMemory").text(memoryInfo.jvm_freeMemory);
				$("#sys_totalMemory").text(memoryInfo.sys_totalMemory);
				$("#sys_usedMemory").text(memoryInfo.sys_usedMemory);
				$("#sys_freeMemory").text(memoryInfo.sys_freeMemory);
				memoryUsedRatio[0] = memoryInfo.jvm_usedRatio;
				memoryUsedRatio[1] = memoryInfo.sys_usedRatio;
			}
		},
		error : function(res) {
			alert("Ajax请求异常");
		}
	})
	return memoryUsedRatio;
}
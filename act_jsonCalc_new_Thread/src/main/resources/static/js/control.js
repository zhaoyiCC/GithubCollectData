/*用户技能展示相关配置*/
var conOption = {
	backgroundColor: '#DBDBDB',
	grid: {
		x: 80,
		y: 10,
		x2: 40,
		y2: 15,
		borderWidth : 0
	},
	xAxis: {
		type: 'value',
		show: false
	},
	yAxis: {
		type: 'category',
		show: true,
		axisLine: {
			show: false
		},
		splitLine: {
			show: false
		},
		axisTick: {
			show: false
		},
		data: ["Questions","Answers","Badges","Reputation"]
	},
	series: [ {
			type: 'bar',
			barWidth: 20,
			itemStyle: {
				normal: {
					color: function(params) {
						var colorList = ['#ec9821','#37b3d8','#2bb22b','#e75724'];
						return colorList[params.dataIndex % 4]
					},
					label: {
						position: 'right',
						show: true,
						textStyle: {
							fontSize: 16,
						}
					}
				},
				emphasis: {
					color: function(params) {
						var colorList = ['#ec9821','#37b3d8','#2bb22b','#e75724'];
						return colorList[params.dataIndex % 4]
					},
				}
			},
			data: [0,2,2,19]
		} ]
};
var colOption = {
		backgroundColor: '#DBDBDB',
		grid: {
			left: '3%',
			right: '3%',
			bottom: '8%',
			containLabel: true
		},		
		xAxis : {
			axisLabel: {
	            interval: 0,
	            rotate: 0
	        },
			type: 'category',
			data: ['Co-Commentor','\nCommentor','Co-Editor','\nEditor','Co-Answer','\nAnswer','Asker']
		},
		yAxis : {
			type: 'value',
			show:false
		},
		series : [ {
			name: 'collaborations',
			type: 'bar',
			barWidth: 25,
			itemStyle: {
				normal: {
					color: function(params) {
						var colorList = ['#E87C25','#C1232B','#E87C25','#C1232B','#E87C25','#C1232B','#27727B'];
						return colorList[params.dataIndex % 7]
					},
					label: {
						position: 'top',
						show: true,
					}
				},
			},
			data: [10,9,1,3,10,9,4]
		} ]
};
var optionC = {
	    backgroundColor: '#DBDBDB',
	    title : {
	        text: '技能云（5）',
	        textStyle: {
	        	fontSize: 14,
	        	fontWeight: 600,
	        	color: '#0366d6'
	        },
	        x: 10,
	        y: 10
	    },
	    tooltip: {
	        show: true,
	        formatter: function (params) {              
                return params.name+' : '+params.value;
            }
	    },
	    series: [{
	    	name: '技能云',
	        type: 'wordCloud',
	        size: ['100%', '100%'],
	        textRotation : [0, 45, 90, -45],
	        autoSize: {
	            enable: true,
	            minSize: 14,
	        },
	        textPadding: 1,
	        data: [
	            {
	                name: "twitter-bootstrap",
	                value: 14,
	                itemStyle: createRandomItemStyle()
	            },
	            {
	                name: "css-animations",
	                value: 1,
	                itemStyle: createRandomItemStyle()
	            },
	            {
	                name: "skeleton-css-boilerplate",
	                value: 1,
	                itemStyle: createRandomItemStyle()
	            },
	            {
	                name: "asp.net",
	                value: 1,
	                itemStyle: createRandomItemStyle()
	            },
	            {
	                name: "javascript",
	                value: 1,
	                itemStyle: createRandomItemStyle()
	            }
	        ]
	    }]
	};		
var optionR = {
    backgroundColor: '#DBDBDB',
    title : {
        text: '技术雷达（5）',
        textStyle: {
        	fontSize: 14,
        	fontWeight: 600,
        	color: '#0366d6'
        },
        x: 10,
        y: 10
    },
    tooltip : {
        trigger: 'axis',
        formatter: '{c}'
    },
    calculable : true,
    polar : [
     	 {
            indicator : [
                {text : 'twitter-\nbootstrap\n', max  : 14},
                {text : 'css-\nanimations\n', max  : 14},
                {text : 'skeleton-css-\nboilerplate\n', max  : 14},
                {text : 'asp.net', max  : 14},
                {text : 'javascript', max  : 14}
            ],
			center: ['50%', '50%'],
			radius: '50%',
			splitNumber: 4,
			type: 'circle',
			name: {
				textStyle: {
					color:'#3192ee'
				}
			},
        }
    ],
    series : [
        {
            type: 'radar',
            data : [
                {
                    value : [14, 1, 1, 1, 1],
                }
            ]
        }
    ]
};
var optionB = {
		grid: {
			x: 100,
			y: 0,
			x2: 40,
			y2: 0,
			//borderWidth : 0
		},
		xAxis: {
			type: 'value',
			show: false,
			max: 10,
			min: 0,
		},
		yAxis: {
			type: 'category',
			show: true,
			axisLine: {
				show: false
			},
			splitLine: {
				show: false
			},
			axisTick: {
				show: false
			},
			axisLabel: {
				interval: 0
			},
			data: ["Questions","Answers","Badges","Reputation"]
		},
		series: [ {
				type: 'bar',
				barWidth: 20,
				itemStyle: {
					normal: {
						color: '#D7504B',
						label: {
							position: 'right',
							show: true,
							formatter: '{c}',
							textStyle: {
								fontSize: 16,
							}
						}
					},
					emphasis: {
						color: '#D7504B'
					}
				},
				data: [0,2,2,19]
			} ]
};
function lineFeed(name,m){
	var temp=new Array(), nameR="", count=0;
	temp = name.split(/_|-/ig);
	var l=0;
	for(var i in temp){
		if(l+temp[i].length<=m){
			l += temp[i].length;
			if(i>0) nameR += '-'+temp[i];
			else nameR += temp[i];
		}else if(temp[i].length<=m){
			l = temp[i].length;
			nameR += '-\n'+temp[i];
			count++;
		}else{
			nameR += temp[i].substring(0,m-l);
			var t=m-l;
			l = (temp[i]-t)%m;
			while(t+m<temp[i].length){
				nameR += '\n'+temp[i].substring(t,m);
				t += m;
    			count++;
			}
			if(t<temp[i].length) nameR += '\n'+temp[i].substring(t);
			count++;
		}
	}
	return {result: nameR, count : count};
}

var style = [[{ normal: { color: '#ff0000' } },{ normal: { color: '#00A1CB' } },{ normal: { color: '#D0D102' } },
			  { normal: { color: '#61AE24' } },{ normal: { color: '#5182ab' } },{ normal: { color: '#ff7f50' } },
			  { normal: { color: '#6f57bc' } },{ normal: { color: '#dda0dd' } }],
			 [{ normal: { color: '#7b7f81' } },{ normal: { color: 'red',width: 2 } },{ normal: { color: '#bb69f2' } }]];
var optionGraph = {
    backgroundColor: '#DBDBDB',
    tooltip : {
        trigger: 'item',
        formatter: '{b}'
    },
    legend: {
        x: 20,
        y: 10,
        data:['家人','朋友']
    },
    series : [
        {
            type:'force',
            name : "人物关系",
            ribbonType: false,
            categories : [
                {
                    name: '人物'
                },
                {
                    name: '家人',
                    symbol: 'diamond'
                },
                {
                    name:'朋友'
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        textStyle: {
                            color: '#333',
                            fontSize: 16
                        }
                    },
                    nodeStyle : {
                    	brushType: 'both',
                        borderWidth : 1
                    },
					linkStyle : {
						type: 'line',
						width: 2,
                        opacity : 0.5,
						color: '#7b7f81'
					}
                },
				emphasis : {
                    nodeStyle : {
                        borderWidth : 1
                    }
				}
            },
            minRadius : 20,
            maxRadius : 40,
            gravity: 1.1,
            step: 20,
            draggable: true,
            linkSymbol: 'arrow',
			linkSymbolSize: [10,8],
            nodes:[
                {category:0, name: '乔布斯', value : 10, itemStyle: style[0][0]},
                {category:1, name: '丽萨-乔布斯',value : 2},
                {category:1, name: '保罗-乔布斯',value : 3},
                {category:1, name: '克拉拉-乔布斯',value : 3},
                {category:1, name: '劳伦-鲍威尔',value : 7},
                {category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
                {category:2, name: '奥巴马',value : 8},
                {category:2, name: '比尔-盖茨',value : 9},
                {category:2, name: '乔纳森-艾夫',value : 4},
                {category:2, name: '蒂姆-库克',value : 4},
                {category:2, name: '龙-韦恩',value : 1},
            ],
            links : [
                {source : 1, target : 0, weight : 1, name: '女儿', itemStyle: style[1][0]},
                {source : 0, target : 1, weight : 1, name: '父亲', itemStyle: style[1][0]},
                {source :2, target : 0, weight : 10, name: '父亲'},
                {source : 3, target : 0, weight : 1, name: '母亲'},
                {source : 4, target : 0, weight : 2},
                {source : 5, target : 0, weight : 3, name: '合伙人'},
                {source : 6, target : 0, weight : 1},
                {source : 7, target : 0, weight : 6, name: '竞争对手',history: ["电脑","手机"]},
                {source : 8, target : 0, weight : 1, name: '爱将'},
                {source : 9, target : 0, weight : 1},
                {source : 10, target : 0, weight : 1},
                {source : 3, target : 2, weight : 1},
                {source : 6, target : 2, weight : 1},
                {source : 6, target : 3, weight : 1},
                {source : 6, target : 4, weight : 1},
                {source : 6, target : 5, weight : 1},
                {source : 7, target : 6, weight : 6},
                {source : 7, target : 3, weight : 1},
                {source : 9, target : 6, weight : 1}
            ]
        }
    ]		
};

String.prototype.temp = function(obj) {
    return this.replace(/\$\w+\$/gi, function(matchs) {
        var returns = obj[matchs.replace(/\$/g, "")];		
        return (returns + "") == "undefined"? "": returns;
    });
};

function createRandomItemStyle() {
	return {
		normal: {
			color: 'rgb(' + [
				Math.round(Math.random() * 160),
				Math.round(Math.random() * 160),
				Math.round(Math.random() * 160)
			].join(',') + ')'
		},
        emphasis: {
        	color: 'red'
        }
	};
};

function addAbility(){
	var a =$('#ability').val();
	if(a.length==0) return;
	$('#ability').val("");
	for(var i in abilities){
		if(abilities[i] == a ){
			alert("该能力已选择！请选择其他能力。");
			return;
		}
	}
	abilities.push(a);
	var obj = {name:''},  html = tagBox.innerHTML;
	obj.name = a;	
	var htmlTag = tagTemp.temp(obj);
	tagBox.innerHTML = html + htmlTag;
};

function addTech(){
	var tech =$('#tech').val();
	if(tech.length==0) return;
	$('#tech').val("");
	for(var i in techs){
		if(techs[i] == tech ){
			alert("该技术已选择！请选择其他技术。");
			return;
		}
	}
	techs.push(tech);
	var obj = {name:''},  html = techBox.innerHTML;
	obj.name = tech;	
	var htmlTag = htmlTemp.temp(obj);
	techBox.innerHTML = html + htmlTag;
};

function addPlat(){
	var plat =$('#plat').val();
	if(plat.length==0) return;
	$('#plat').val("");
	for(var i in plats){
		if(plats[i] == plat ){
			alert("该平台已选择！请选择其他平台。");
			return;
		}
	}
	plats.push(plat);
	var obj = {name:''},  html = platBox.innerHTML;
	obj.name = plat;	
	var htmlTag = htmlTemp.temp(obj);
	platBox.innerHTML = html + htmlTag;
};
	
$(function(){
	$('#post').click(function(){
		$('.spinner').show();
		document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight
		var results = document.getElementsByName('result[]');
		for (var i = 0; i < 20; i++) {
			results[i].style.display = 'none';
		}
		document.getElementById('show-add').style.display = 'none';
		var title = document.getElementById('title').value;
		var type = document.getElementById('type').value;
		var postingDate = document.getElementById('posting-date').value;
		var submissionEndDate = document.getElementById('submission-end-date').value;
		var duration = (new Date(submissionEndDate) - new Date(postingDate)) / 1000 / 60 / 60 / 24;
		var prize = document.getElementById('prize').value;
		var techs = document.getElementsByName('tech-input[]');
		var techsStr = "";
		for (var i = 0; i < techs.length; i++) {
			techsStr = techsStr + techs[i].value + ",";
		}
		var plats = document.getElementsByName('plat-input[]');
		var platsStr = "";
		for (var i = 0; i < plats.length; i++) {
			platsStr = platsStr + plats[i].value + ",";
		}
		var description = document.getElementById('description').value;
		var url = '../RecommendService';
		$.post(url,{
			'title':title,
			'type':type,
			'postingDate':postingDate,
			'duration':duration,
			'prize':prize,
			'technologys':techsStr,
			'platforms':platsStr,
			'description':description
		},function(data,status){
			var $json = eval("("+data+")");
			var results = document.getElementsByName('result[]');
			for (var i = 0; i < 5; i++) {
				var children = results[i].getElementsByTagName('span');
				children[0].innerHTML = $json[i].handle;
				children[0].parentNode.href = 'https://www.topcoder.com/members/' + $json[i].handle;
				var imgSrc = './img/photos/' + $json[i].handle;
				results[i].getElementsByTagName('img')[0].src = imgSrc;
				children[1].innerHTML = $json[i].country;
				children[2].innerHTML = new Date($json[i].memberSince).toLocaleDateString();
				var atitles = results[i].getElementsByTagName('a');
				var quote = $json[i].quote ;
				if (quote !== null && quote !== undefined && quote != ""){
					children[3].innerHTML = '\"' + quote + '\"';
					atitles[1].title = '\"' + quote + '\"';
				}
				var skills = $json[i].skills;
				if (skills !== null && skills !== undefined && skills != ""){
					children[5].innerHTML = skills;
					atitles[2].title = skills;
				}
			}
			$('.spinner').hide();
			for (var i = 0; i < 5; i++) {
				results[i].style.display = 'block';
			}
			$('#show-add').show();
			for (var i = 5; i < results.length; i++) {
				var children = results[i].getElementsByTagName('span');
				children[0].innerHTML = $json[i].handle;
				children[0].parentNode.href = 'https://www.topcoder.com/members/' + $json[i].handle;
				var imgSrc = './img/photos/' + $json[i].handle;
				results[i].getElementsByTagName('img')[0].src = imgSrc;
				children[1].innerHTML = $json[i].country;
				children[2].innerHTML = new Date($json[i].memberSince).toLocaleDateString();
				var atitles = results[i].getElementsByTagName('a');
				var quote = $json[i].quote ;
				if (quote !== null && quote !== undefined && quote != ""){
					children[3].innerHTML = '\"' + quote + '\"';
					atitles[1].title = '\"' + quote + '\"';
				}
				var skills = $json[i].skills;
				if (skills !== null && skills !== undefined && skills != ""){
					children[5].innerHTML = skills;
					atitles[2].title = skills;
				}
			}
		});
	});
});
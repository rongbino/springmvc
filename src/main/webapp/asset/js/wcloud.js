var myChart;
var domMain = document.getElementById('main');
var needRefresh = false;

var enVersion = location.hash.indexOf('-en') != -1;
var hash = location.hash.replace('-en','');
hash = hash.replace('#','') || 'macarons';
hash += enVersion ? '-en' : '';

var curTheme = "macarons";
function requireCallback (ec, defaultTheme) {
    echarts = ec;
    refresh();
    window.onresize = myChart.resize;
}

function refresh(){
    needRefresh = false;
    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    myChart = echarts.init(domMain, curTheme);
    window.onresize = myChart.resize;
    myChart.setOption(option, true)
}

function search() {
    userid = $('#username').val();
    ulll = '/wordcloud/rest/username/'+userid;
    // alert(ulll);
    $.ajax({
        url: ulll,
        type:'GET', //GET
        async:false,    //或false,是否异步
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend:function(xhr){
            console.log(xhr)
            console.log('发送前')
        },
        success:function(data,textStatus,jqXHR){
            console.log(data)
            console.log(textStatus)
            console.log(jqXHR)
            updateOption(data)
        },
        error:function(xhr,textStatus){
            console.log('错误')
            console.log(xhr)
            console.log(textStatus)
        },
        complete:function(){
            console.log('结束')
        }
    })

    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    myChart = echarts.init(domMain, curTheme);
    window.onresize = myChart.resize;
    myChart.setOption(option, true)
}

function updateOption(data) {
    console.log(data);
    if (data) {
        if (data.username != "") {
            option.title.text = data.username;
        }
        option.series[0].data = [];
        $.each(data.taglist, function (idx, item) {
            console.log("id:" + idx);
            console.log("node:" + item.name);
            console.log("value:" + item.value);

            val = item.value * 100;
            val = (Math.floor(val)/100)*45;
            var obj = {
                name: item.name,
                value: Math.floor(val),
                itemStyle: createRandomItemStyle()
            };

            if (idx >= 50) { // 最大显示50个tag
                return;
            }
            option.series[0].data.push(obj);
        });
    }
}

var echarts;
var developMode = false;

if (developMode) {
    window.esl = null;
    window.define = null;
    window.require = null;
    (function () {
        var script = document.createElement('script');
        script.async = true;

        var pathname = location.pathname;

        var pathSegs = pathname.slice(pathname.indexOf('doc')).split('/');
        var pathLevelArr = new Array(pathSegs.length - 1);
        script.src = pathLevelArr.join('../') + 'asset/js/esl/esl.js';
        if (script.readyState) {
            script.onreadystatechange = fireLoad;
        }
        else {
            script.onload = fireLoad;
        }
        (document.body).appendChild(script);
        
        function fireLoad() {
            script.onload = script.onreadystatechange = null;
            setTimeout(loadedListener,100);
        }
        function loadedListener() {
            // for develop
            require.config({
                packages: [
                    {
                        name: 'echarts',
                        location: '../../src',
                        main: 'echarts'
                    },
                    {
                        name: 'zrender',
                        // location: 'http://ecomfe.github.io/zrender/src',
                        location: '../../../zrender/src',
                        main: 'zrender'
                    }
                ]
            });
            launchExample();
        }
    })();
}
else {
    // for echarts online home page
    require.config({
        paths: {
            echarts: './www/js'
        }
    });
    launchExample();
}

var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    // 按需加载
    isExampleLaunched = 1;
    require(
        [
            'echarts',
            'theme/' + hash.replace('-en', ''),
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/eventRiver',
            'echarts/chart/venn',
            'echarts/chart/treemap',
            'echarts/chart/tree',
            'echarts/chart/wordCloud',
            'echarts/chart/heatmap'
        ],
        requireCallback
    );
}

function createRandomItemStyle() {
    return {
        normal: {
            color: 'rgb(' + [
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160),
                Math.round(Math.random() * 160)
            ].join(',') + ')'
        }
    };
}

option = {
    title: {
        text: 'User Tag',
        link: 'http://www.google.com/trends/hottrends'
    },
    tooltip: {
        show: true
    },
    series: [{
        name: 'User Tag',
        type: 'wordCloud',
        size: ['80%', '80%'],
        textRotation : [0, 0, 0, 0],
        textPadding: 2,
        autoSize: {
            enable: false,
            minSize: 8
        },
        data: [
            {
                name: "Sam S Club",
                value: 10000,
                itemStyle: {
                    normal: {
                        color: 'black'
                    }
                }
            },
            {
                name: "Macys",
                value: 6181,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Amy Schumer",
                value: 4386,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Jurassic World",
                value: 4055,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Charter Communications",
                value: 2467,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Chick Fil A",
                value: 2244,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Planet Fitness",
                value: 1898,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Pitch Perfect",
                value: 1484,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Express",
                value: 1112,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Home",
                value: 965,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Johnny Depp",
                value: 847,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Lena Dunham",
                value: 582,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Lewis Hamilton",
                value: 555,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "KXAN",
                value: 550,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Mary Ellen Mark",
                value: 462,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Farrah Abraham",
                value: 366,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Rita Ora",
                value: 360,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Serena Williams",
                value: 282,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "NCAA baseball tournament",
                value: 273,
                itemStyle: createRandomItemStyle()
            },
            {
                name: "Point Break",
                value: 265,
                itemStyle: createRandomItemStyle()
            }
        ]
    }]
};


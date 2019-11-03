function writeCurrentDate() {

    var now = new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth();//得到月份
    // var date = now.getDate();//得到日期
    // var day = now.getDay();//得到周几
    // var hour = now.getHours();//得到小时
    // var minu = now.getMinutes();//得到分钟
    // var sec = now.getSeconds();//得到秒
    // var MS = now.getMilliseconds();//获取毫秒
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    // if (date < 10) date = "0" + date;
    // if (hour < 10) hour = "0" + hour;
    // if (minu < 10) minu = "0" + minu;
    // if (sec < 10) sec = "0" + sec;
    // if (MS < 100) MS = "0" + MS;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    // week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" ;
    //当前日期赋值给当前日期输入框中
    $("#currentDate").html(time);
    $("#currentDate_two").html(time);
    //设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。

    //ajax 月计划
    $(function () {
        $.ajax({
            url:"/system/monthly/getMonthlyPlan",
            data:"GET",
            datatype:"json",
            contentType : "application/json;charset=UTF-8",
            success:function (res) {
                if (res.monthlyPlan1.monthly_plan_state==2||res.monthlyPlan1.monthly_plan_state==4){
                    $("#addPlan").removeAttr('onclick').attr("onclick","invalid("+res.monthlyPlan1.monthly_plan_state+")");
                    $("#savaPlan").removeAttr('onclick').attr("onclick","invalid("+res.monthlyPlan1.monthly_plan_state+")");
                    $("#updatePlan").removeAttr('onclick').attr("onclick","invalid("+res.monthlyPlan1.monthly_plan_state+")");
                }
                text_I.value = res.monthlyPlan1.monthly_plan_content;
            }
        })
    })

    //ajax 月总结
    $(function () {
        $.ajax({
            url:"/system/monthlySummary/getMonthlySummary",
            data:"GET",
            datatype:"json",
            contentType : "application/json;charset=UTF-8",
            success:function (res) {
                if (res.monthlySummary.monthly_summary_state==2||res.monthlySummary.monthly_summary_state==4){
                    $("#addSummary").removeAttr('onclick').attr("onclick","invalidSum("+res.monthlySummary.monthly_summary_state+")");
                    $("#saveSummary").removeAttr('onclick').attr("onclick","invalidSum("+res.monthlySummary.monthly_summary_state+")");
                    $("#updateSummary").removeAttr('onclick').attr("onclick","invalidSum("+res.monthlySummary.monthly_summary_state+")");
                }
                text_I_two.value = res.monthlySummary.monthly_summary_content;
            }
        })
    })

}
//计划的按钮失效
function invalid(state) {
    if (state==2){
        alert("您的月计划正在等待审核不能修改呦！")
    }else if (state==4){
        alert("您的月计划已经审核成功不能修改呦！")
    }
}
//总结的按钮失效
function invalidSum(state) {
    if (state==2){
        alert("您的月总结正在等待审核不能修改呦！")
    }else if (state==4){
        alert("您的月总结已经审核成功不能修改呦！")
    }
}
//修改
function editEnable(obj) {
    var preInputObj = $("#text_I");
    $(preInputObj).removeAttr("readonly");
    $(preInputObj).focus();
}
//修改twice
function editEnable_Twice(obj) {
    var preInputObj_Twice = $("#text_I_two");
    $(preInputObj_Twice).removeAttr("readonly");
    $(preInputObj_Twice).focus();
}
//保存计划
function saveOne(re) {
    var monthly_plan_content = document.getElementById("text_I").value;//计划内容
    var monthly_plan_state;
    if (re=="保存"){
        monthly_plan_state=1;
    }else if (re=="提交"){
        monthly_plan_state=2;
    }else if (re=="修改"){
        monthly_plan_state=3;
    }else {
        alert("系统异常！")
    }
    var category={"monthly_plan_content":monthly_plan_content,"monthly_plan_state":monthly_plan_state};
    var jsonData = JSON.stringify(category);
    $.ajax({
        type:"post",
        url: "/system/monthly/addMonthlyPlan",
        data:jsonData,
        dataType:"json",
        contentType : "application/json;charset=UTF-8",
        success: function(result){
            if(result.code==0){
                if(monthly_plan_state==1){
                    alert("保存成功！");
                    location.reload();
                }else if (monthly_plan_state==2){
                    alert("提交成功！");
                    location.reload();
                }else {
                    alert("系统异常！")
                }
            }else {
                alert("保存失败！")
            }
        }
    });

    $("textarea[id='text_I']").each(function (index,element) {
        $(element).attr("readonly","readonly");
    })
}
//保存总结
function saveTwice(re) {

    var monthly_summary_content = document.getElementById("text_I_two").value;//计划内容
    var monthly_summary_state;
    if (re=="保存"){
        monthly_summary_state=1;
    }else if (re=="提交"){
        monthly_summary_state=2;
    }else if (re=="修改"){
        monthly_summary_state=3;
    }else {
        alert("系统异常！")
    }
    var category={"monthly_summary_content":monthly_summary_content,"monthly_summary_state":monthly_summary_state};
    var jsonData = JSON.stringify(category);
    $.ajax({
        type:"post",
        url: "/system/monthlySummary/addMonthlySummary",
        data:jsonData,
        dataType:"json",
        contentType : "application/json;charset=UTF-8",
        success: function(result){
            if(result.code==0){
                if(monthly_summary_state==1){
                    alert("保存成功！");
                    location.reload();
                }else if (monthly_summary_state==2){
                    alert("提交成功！");
                    location.reload();
                }else {
                    alert("系统异常！")
                }
            }else {
                alert(result.msg)
            }
        }
    });

    $("textarea[id='text_I_two']").each(function (i,element) {
        $(element).attr("readonly","readonly");
    })
}


//月计划显示动画
function showSign(){
    $(".signWrap_two").toggle();
    $(".btn_a").attr("status", function (i, origValue) {
        if (origValue === "off") {
            origValue = "on";
            $(".btn_a").attr("contchar", " Yes");
            $(document).scrollTop($("fieldset").height()*1/3);
            $(".sign_two").children().each(function (index, element) {
                $(element).addClass("animated fadeInRight");
            })
        }
        else {
            origValue = "off";
            $(".btn_a").attr("contchar", " No");
        }
        return origValue;
    })
}

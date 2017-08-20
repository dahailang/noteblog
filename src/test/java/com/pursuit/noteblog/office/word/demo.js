<button type="button" onclick="toInsuranceExcel()" class="button button-primary button-small">导出Word </button>  

/* 导出Word */  
function toInsuranceExcel(){  
    var curpagenum = $('#contentGroupOrderTable').getGridParam('page');  
    $("#page").val(curpagenum);  
    var startTime=$('#startTime').val();  
    var endTime=$('#endTime').val();  
    var receiveMode=$('#receiveMode').val();  
    var groupCode=$('#groupCode').val();  
    var supplierName=$('#supplierName').val();  
  
    var orgIds=$('#orgIds').val();  
    var orgNames=$('#orgNames').val();  
    var operType=$('#operType').val();  
    var saleOperatorIds=$('#saleOperatorIds').val();  
    var saleOperatorName=$('#saleOperatorName').val();  
  
    var orderMode=$('#dicIds').val();  
    var remark=$('#remark').val();  
    var page=$('#page').val();  
    var pageSize=$('#pageSize').val();  
    var userRightType=$('#userRightType').val();  
  
    var guestName=$('#guestName').val();  
    var gender=$('#gender').val()  
    var ageFirst=$('#ageFirst').val()  
    var ageSecond=$('#ageSecond').val()  
    var nativePlace=$('#nativePlace').val()  
  
    window.location ='../taobao/downloadInsure.htm?startTime='+startTime  
        +"&endTime="+endTime  
        +"&receiveMode="+receiveMode  
        +"&groupCode="+groupCode  
        +"&supplierName="+supplierName  
        +"&orgIds="+orgIds  
        +"&orgNames="+orgNames  
        +"&operType="+operType  
        +"&saleOperatorIds="+saleOperatorIds  
        +"&saleOperatorName="+saleOperatorName  
        +"&orderMode="+orderMode  
        +"&remark="+remark  
        +"&page="+page  
        +"&pageSize="+pageSize  
        +"&userRightType="+userRightType  
        +"&guestName="+guestName  
        +"&gender="+gender  
        +"&ageFirst="+ageFirst  
        +"&ageSecond="+ageSecond  
        +"&nativePlace="+nativePlace;  
}  
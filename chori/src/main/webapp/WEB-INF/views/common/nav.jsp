<div class="navbar">
   	<div class="navbar-inner">
        <ul class="breadcrumb">
            <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
            <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
            <li>
                <a id="a1" href="#"><span id="span1"></span></a> <span class="divider">/</span>	
            </li>
            <li>
                <a id="a2" href="#"><span id="span2"></span></a> <span class="divider">/</span>	
            </li>
            <li>
                <a id="a3" href="#"><span id="span3"></span></a>
            </li>
            <!-- <li class="active">Tools</li>  -->
        </ul>
   	</div>
</div>

<%-- 
$(document).ready(function() {
var pathname = window.location.pathname;
var splitPathName = pathname.split("/");
var idOfSpanTag;
var idOfATag;
var mark;
//var strForLv2; //Configuration, Operation, Shipment, Billing
for(var i =1; i<splitPathName.length; i++)
{
	idOfATag = "#a" + i;
	$(idOfATag).attr("href", pathname);
	idOfSpanTag = "#span" + i;
	idOfSpanTag2 = "#span" + i++;
	if(checkStrForLv2(pathname)!== "")
		{
			$(idOfSpanTag2).text(checkStrForLv2(splitPathName[i]));
			mark = idOfSpanTag2;
			pathname = "";
		}
	else 
		if(idOfSpanTag !== mark)
			$(idOfSpanTag).text( splitPathName[i] );
}


function checkStrForLv2(strInput) {
	var result;
	switch (strInput) {
	case "/Chori/":   
	case "/Chori/listCustomer": 
	case "/Chori/listaccessorysupplier":
	case "/Chori/listFactory": 
	case "/Chori/listFabricSupplier":
	case "/Chori/listShippingline": 
	case "/Chori/listctnrtype": 
	case "/Chori/listdestination": 
	case "/Chori/listgarmentkind": 
	case "/Chori/listAccessory":
	case "/Chori/listAccessoryConsumption": 
	case "/Chori/listcolor":
	case "/Chori/listunit":
	case "/Chori/listEstimatetime":
	case "/Chori/listUser":
	case "/Chori/listRole":
		result = "Configuration"; break; 
	default: 
		result = "";
	} 
	return result;
};
});
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- include js that check if user is logged in -->


<div class="navbar-inner">
	<div class="container-fluid">
		<a class="btn btn-navbar" data-toggle="collapse"
			data-target=".nav-collapse"> <span class="icon-bar"></span> <span
			class="icon-bar"></span> <span class="icon-bar"></span>
		</a> <a class="brand" href="<c:url value='/'/>">Chori</a>
		<div class="nav-collapse collapse">
			<ul class="nav pull-right" id="LogInLogOut">
			<!-- 
				<li class="dropdown"><a href="#" role="button"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="icon-user"></i> Vincent Gabriel <i class="caret"></i>

				</a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#">Profile</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="login.html">Logout</a></li>
					</ul></li>
					 -->
			</ul>
			<ul class="nav" id="mainMenu">
				<!--   <li class="active"><a href="#">Dashboard | <spring:message code="label.home" /></a></li> -->

				<%--
				<li class="dropdown">
				    <a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown"><spring:message code="label.configuration"/> <b class="caret"></b></a>
				    <ul class="dropdown-menu">
				    	<!-- Partner Information -->
				        <li role="presentation" class="dropdown-header custom-dropdown-header"><spring:message code="label.partnerInformation"/></li>
				        <li><a href="#"><spring:message code="label.partnerInformation.customer"/></a></li>
				        <li><a href="#"><spring:message code="label.partnerInformation.factory"/></a></li>
				        <li><a href="#"><spring:message code="label.partnerInformation.fabricSupplier"/></a></li>
				        <li><a href="#"><spring:message code="label.partnerInformation.accessorySupplier"/></a></li>
				        <li role="presentation" class="divider"></li>
				        <!-- Shipment -->
				        <li role="presentation" class="dropdown-header custom-dropdown-header"><spring:message code="label.shipment"/></li>
				        <li><a href="<c:url value='/listShippingline'/>"><spring:message code="label.shipment.shippingLine"/></a></li>
				        <li><a href="#"><spring:message code="label.shipment.containerType"/></a></li>
				        <li><a href="#"><spring:message code="label.shipment.destination"/></a></li>
				        <!-- Garment -->
						<li role="presentation" class="divider"></li>
				        <li role="presentation" class="dropdown-header custom-dropdown-header"><spring:message code="label.garment"/></li>
				        <li><a href="<c:url value='/listShippingline'/>"><spring:message code="label.garment.garmentStyle"/></a></li>
				        <li><a href="#"><spring:message code="label.garment.garmentStyle"/></a></li>
				        <li><a href="#"><spring:message code="label.garment.garmentConsumption"/></a></li>
				        <!-- Accessory -->
				        <li role="presentation" class="dropdown-header custom-dropdown-header"><spring:message code="label.accessory"/></li>
				        <li><a href="#"><spring:message code="label.accessory.accessoryInformation"/></a></li>
				        <li><a href="#"><spring:message code="label.accessory.accessoryForm"/></a></li>
				        <li><a href="#"><spring:message code="label.accessory.accessoryConsumptionByFactory"/></a></li>
				        <li><a href="#"><spring:message code="label.accessory.accessoryOrderSignature"/></a></li>
				        <li role="presentation" class="divider"></li>
				        <!-- General Information -->
				        <li role="presentation" class="dropdown-header custom-dropdown-header"><spring:message code="label.generalInformation"/></li>
				        <li><a href="#"><spring:message code="label.generalInformation.color"/></a></li>
				        <li><a href="#"><spring:message code="label.generalInformation.type"/></a></li>
				        <li><a href="#"><spring:message code="label.generalInformation.unit"/></a></li>
				        <li><a href="#"><spring:message code="label.generalInformation.width"/></a></li>
				        <li><a href="#"><spring:message code="label.generalInformation.currency"/></a></li>
				        <li><a href="#"><spring:message code="label.generalInformation.currencyExchange"/></a></li>
				        <li><a href="#"><spring:message code="label.generalInformation.estimateTimeOfCompletion"/></a></li>
				        <li role="presentation" class="divider"></li>
				    </ul>
				</li>
				--%> 
				
				<!-- Configuration -->
				<li class="dropdown">
				    <a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown"><spring:message code="label.configuration"/> <b class="caret"></b></a>
					<ul class="dropdown-menu">
					
					<!-- Partner Information -->
					  <li class="dropdown-submenu">					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.partnerInformation"/></a>
						<ul class="dropdown-menu">					    
							<li><a href="<c:url value='/listCustomer'/>"><spring:message code="label.partnerInformation.customer"/></a></li>
							<li><a href="<c:url value='/listFactory'/>"><spring:message code="label.partnerInformation.factory"/></a></li>
							<li><a href="<c:url value='/listFabricSupplier'/>"><spring:message code="label.partnerInformation.fabricSupplier"/></a></li>					
							<li><a href="<c:url value='/listaccessorysupplier'/>"><spring:message code="label.partnerInformation.accessorySupplier"/></a></li>	
							<li><a href="<c:url value='/Shippingline'/>"><spring:message code="label.partnerInformation.shippingLine"/></a></li>	
							<li><a href="<c:url value='/Office'/>"><spring:message code="label.generalInformation.agent"/></a></li>			       
					      <!--  For menu level 3
					      <li class="dropdown-submenu">
					        <a class="test" href="#">Another dropdown <span class="caret"></span></a>
					        <ul class="dropdown-menu">
					          <li><a href="#">3rd level dropdown</a></li>
					          <li><a href="#">3rd level dropdown</a></li>
					        </ul>
					      </li>
					       -->
					    </ul>
					  </li>
					  
					<!-- Shipment -->
					   <li class="dropdown-submenu">				   					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.shipment"/></a>
						<ul class="dropdown-menu">					    
					        <li><a href="<c:url value='/listctnrtype'/>"><spring:message code="label.shipment.containerType"/></a></li>
					        <li><a href="<c:url value='/listdestination'/>"><spring:message code="label.shipment.destination"/></a></li>			       
					    </ul>
					  </li>
					  
					<!-- Garment -->
					   <li class="dropdown-submenu">				   					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.garment"/></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/Size'/>"><spring:message code="label.garment.size"/></a></li>				    
					        <li><a href="<c:url value='/listGarmentstyle'/>"><spring:message code="label.garment.garmentStyle"/></a></li>
					        <li><a href="<c:url value='/listgarmentkind'/>"><spring:message code="label.garment.garmentKind"/></a></li>
					        <li><a href="<c:url value='/GarmentConsumption'/>"><spring:message code="label.garment.garmentConsumption"/></a></li>			       
					    </ul>
					  </li>					  
				        
					<!-- Accessory -->
					   <li class="dropdown-submenu">				   					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.accessory"/></a>
						<ul class="dropdown-menu">				    
					        <li><a href="<c:url value='/listAccessory'/>"><spring:message code="label.accessory.accessoryInformation"/></a></li>
					        <li><a href="<c:url value='/listAccessoryPrice'/>"><spring:message code="label.accessory.accessoryPrice"/></a></li>
					        <li><a href="<c:url value='/listWastedPercentage'/>"><spring:message code="label.accessory.accessoryConsumptionByFactory"/></a></li>
					        <!-- <li><a href="#"><spring:message code="label.accessory.accessoryForm"/></a></li>		-->			        
					        <li><a href="<c:url value='/listAccessoryGroup'/>"><spring:message code="label.accessory.accessoryGroup"/></a></li>
					        <li><a href="<c:url value='/listSignature'/>"><spring:message code="label.accessory.accessoryOrderSignature"/></a></li>		       
					    </ul>
					  </li>					  					 				      			        
				        
					<!-- General Information -->
					   <li class="dropdown-submenu">				   					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.generalInformation"/></a>
						<ul class="dropdown-menu">				    
					        <li><a href="<c:url value='/listcolor'/>"><spring:message code="label.generalInformation.color"/></a></li>
					        <li><a href="<c:url value='/listSizeGroup'/>"><spring:message code="label.generalInformation.type"/></a></li>
					        <li><a href="<c:url value='/listunit'/>"><spring:message code="label.generalInformation.unit"/></a></li>
					        <li><a href="<c:url value='/listwidth'/>"><spring:message code="label.generalInformation.width"/></a></li>				        
					        <li><a href="<c:url value='/listCurrency'/>"><spring:message code="label.generalInformation.currency"/></a></li>
					        <li><a href="<c:url value='/listCurrencyexchange'/>"><spring:message code="label.generalInformation.currencyExchange"/></a></li>
					        <li><a href="<c:url value='/listEstimatetime'/>"><spring:message code="label.generalInformation.estimateTimeOfCompletion"/></a></li>
					        <li><a href="<c:url value='/listpackingguide'/>"><spring:message code="label.generalInformation.packingGuide"/></a></li>
					        <li><a href="<c:url value='/listUser'/>"><spring:message code="label.generalInformation.userManagement"/></a></li>
					        <li><a href="<c:url value='/listRole'/>"><spring:message code="label.generalInformation.groupAndFunctions"/></a></li>		       
					    </ul>
					  </li>
					</ul>
    		</li>
        
        <!-- Operation -->
        	<li class="dropdown" style="display: none;">
				    <a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown"><spring:message code="label.operation"/> <b class="caret"></b></a>
					<ul class="dropdown-menu">
					
					<!-- Fabric Info -->
					  <li>					  	
						<a class="test" tabindex="-1" href="<c:url value='/listFabricinformation'/>"><spring:message code="label.operation.fabricInformation"/></a>
					  </li>
					  
					  <!-- Accessories -->
					 <li class="dropdown-submenu">					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.operation.accessories"/></a>
						<ul class="dropdown-menu">					    
							<li><a href="<c:url value='/listorderinternalaccessory'/>"><spring:message code="label.operation.accessories.orderInternalAccessories"/></a></li>
							<li><a href="<c:url value='/orderexternalaccessory'/>"><spring:message code="label.operation.accessories.orderExternalAccessories"/></a></li>				       
					      <!--  For menu level 3
					      <li class="dropdown-submenu">
					        <a class="test" href="#">Another dropdown <span class="caret"></span></a>
					        <ul class="dropdown-menu">
					          <li><a href="#">3rd level dropdown</a></li>
					          <li><a href="#">3rd level dropdown</a></li>
					        </ul>
					      </li>
					       -->
					    </ul>
					  </li>
					  
				<!-- Management -->
					 <li class="dropdown-submenu">					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.operation.management"/></a>
						<ul class="dropdown-menu">					    
							<li><a href="<c:url value='/listpi'/>"><spring:message code="label.operation.management.pi"/></a></li>
							<li><a href="<c:url value='#'/>"><spring:message code="label.operation.management.fpi"/></a></li>
							<li><a href="<c:url value='#'/>"><spring:message code="label.operation.management.rfpi"/></a></li>				       
					    </ul>
					  </li>	
					  
				<!-- Lot No Search -->
					  <li>					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.operation.lotNoSearch"/></a>
					  </li>		        
					</ul>
    		</li>
    		
    		
    	<!-- Shipment/Payment -->
        	<li class="dropdown" style="display: none;">
				    <a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown"><spring:message code="label.shipmentPayment"/> <b class="caret"></b></a>
					<ul class="dropdown-menu">	
					
				<!-- Shipment -->
					 <li class="dropdown-submenu">					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.shipmentPayment.shipment"/></a>
						<ul class="dropdown-menu">					    
							<li><a href="<c:url value='#'/>"><spring:message code="label.shipmentPayment.shipment.shipmentInformation"/></a></li>
							<li><a href="<c:url value='#'/>"><spring:message code="label.shipmentPayment.shipment.cancelShipment"/></a></li>			       
					    </ul>
					  </li>	  
					  			  
				<!-- Payment -->
					 <li class="dropdown-submenu">					  	
						<a class="test" tabindex="-1" href="#"><spring:message code="label.shipmentPayment.payment"/></a>
						<ul class="dropdown-menu">					    
							<li><a href="<c:url value='#'/>"><spring:message code="label.shipmentPayment.payment.searchAccessoriesOrderSheet"/></a></li>
							<li><a href="<c:url value='#'/>"><spring:message code="label.shipmentPayment.payment.searchShipment"/></a></li>			       
					    </ul>
					  </li>	      
					</ul>
    		</li>
    		
    	<!-- Billing -->
        	<li class="dropdown" style="display: none;">
				    <a href="#" class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown"><spring:message code="label.billing"/> <b class="caret"></b></a>
    		</li>
            <%--            
				<li class="dropdown">
				<a href="#" data-toggle="dropdown" class="dropdown-toggle">Configuration <b class="caret"></b>
				</a>
					<ul class="dropdown-menu" id="menu11">
						<li><a href="QuayCrane">Quay Crane</a></li>
						<li><a href="/Chori/liststatus">Status</a></li>
					</ul>
				</li>

				<li class="dropdown"><a href="#" data-toggle="dropdown"
					class="dropdown-toggle">Master <b class="caret"></b>
				</a>
					<ul class="dropdown-menu" id="menu11">
						<li><a href="<c:url value='/VesselBargeInfo/list'/>">Vessel
								Barge Info List</a></li>
						<li><a href="<c:url value='/PartnerInfo/list' />">Partner
								Info List</a></li>
						<li><a href="<c:url value='/CargoInfo/list' />">Cargo
								Info List</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" data-toggle="dropdown"
					class="dropdown-toggle">Voyage <b class="caret"></b>
				</a>
					<ul class="dropdown-menu" id="menu11">
						<li><a href="listVoyageCargoInfo">Voyage Cargo Info List</a></li>
						<li><a href="<c:url value='/BillOfLading/list' />">Voyage
								BL Info List</a></li>
						<li><a href="listVoyageHoldInfo">Voyage Hold Info List</a></li>
						<li><a href="listVoyageBargeOperation">Voyage Barge
								Operation List</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" data-toggle="dropdown"
					class="dropdown-toggle">Manual <b class="caret"></b>
				</a>
					<ul class="dropdown-menu" id="menu11">
						<li><a href="listManualBargeOperation">Manual Barge
								Operation List</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" role="button"
					class="dropdown-toggle" data-toggle="dropdown">Users <i
						class="caret"></i>

				</a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="#">User List</a></li>
						<li><a tabindex="-1" href="#">Search</a></li>
						<li><a tabindex="-1" href="#">Permissions</a></li>
					</ul></li>
			--%>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</div>

<style>
.custom-dropdown-header{
display: block;padding: 3px 20px;font-size: 12px;line-height: 1.428571429;color: #999999;
}

.dropdown-submenu {
    position: relative;
}

.dropdown-submenu .dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -1px;
}
</style>

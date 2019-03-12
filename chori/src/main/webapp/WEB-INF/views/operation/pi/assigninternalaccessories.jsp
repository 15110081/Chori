<script src="${pageContext.request.contextPath}/resources/userJs/operation/assigninternalaccessories/assigninternalaccessories.js"></script>

<button type="button" class="btn btn-info" id="btnAssignInternalAccessories"  style="margin-bottom:20px" 
name="btnAssignInternalAccessories">Assign General Accessories</button>

<div class="form-group" style="display: none;margin-bottom:10px" style="display: none;">
	<label for="txtLotNumberInPi" class="col-sm-3 control-label">Lot Number &nbsp;</label>
	<div class="col-sm-9">					
		<input type="text" class="form-control" id="txtLotNumberInPi" value="" name="txtLotNumberInPi" disabled>	
	</div>			
</div>	

<div class="form-group" style="display: none;margin-bottom:10px">
	<label for="txtLotNumberInPi" class="col-sm-3 control-label">Factory Code &nbsp;</label>
	<div class="col-sm-9">					
		<input type="text" class="form-control" id="txtFactoryCodeInPi" value="" name="txtFactoryCodeInPi" disabled>	
	</div>			
</div>	

<div class="form-group">
		<div id="listInternalAccessoryCoverInPi">
		<!-- 
			<table class="table table-striped table-bordered display responsive"
				id="listInternalAccessoryInPi">
				<thead>
					<tr>
						<th>Name</th>
						<th>Color</th>
						<th>Dimension</th>
						<th>Code</th>
						<th>Group Name</th>						
						<th>Kind</th>						
						<th>Mode</th>
						<th>Inventory Qty</th>
						<th>Available Qty</th>
						<th>Action</th>
						<th>Assign For</th>
						<th>Assign Qty</th>
						<th>Size</th>
						<th>Images</th>						
					</tr>
				</thead>
			</table>
			 -->
			</div>
	</div>
	
<div id="dialogAssignInternalAccessories" style="display: none;" class="form-horizontal">
		<div class="form-group" style="margin-bottom:10px;display: none;">
			<label for="txtLotNo" class="col-sm-3 control-label">Lot Number &nbsp;</label>
			<div class="col-sm-9">					
				<input type="text" class="form-control" id="txtLotNumberDialogAssignInternalAccessories" value="" name="txtLotNumber" disabled>	
			</div>			
		</div>
		
		<div class="form-group" style="margin-bottom:10px;display: none;">
			<label for="txtFactory" class="col-sm-3 control-label">Factory &nbsp;</label>
			<div class="col-sm-9">			
				<input type="text" class="form-control" id="txtFactoryDialogAssignInternalAccessories" value="" name="txtFactory" disabled>	
			</div>			
		</div>		
	<div class="form-group">
		<div class="span12">
		<div id="listInternalAccessoryCover">
			<table class="table table-striped table-bordered display responsive"
				id="listInternalAccessory">
				<thead>
					<tr>
						<th></th>
						<th>Name</th>
						<th>Color</th>
						<th>Dimension</th>
						<th>Code</th>
						<th>Group Name</th>						
						<th>Kind</th>						
						<th>Mode</th>
						<th>Inventory Qty</th>
						<th>Available Qty</th>
						<th>Images</th>
						<th>Assign For</th>
						<th>Size</th>
						<th>Assign Qty</th>						
						<!-- <th></th> -->
					</tr>
				</thead>
			</table>
			</div>
		</div>
	</div>
</div>

<div id="dialogEditAssignInternalAccessories" style="display: none;" class="form-horizontal">
	<div class="form-group" style="margin-bottom:10px;">
		<label class="col-sm-1 control-label">Lot Number: &nbsp;</label>				
		<label class="col-sm-1 control-label" id="lblEditLotNumber"></label>			
	</div>
	<br/>
	<div class="form-group" style="margin-bottom:10px">
		<label class="col-sm-1 control-label">Accessory: &nbsp;</label>		
		<label class="col-sm-1 control-label" id="lblEditAccessory"></label>		
	</div>
	<br/>
	<div class="form-group" style="margin-bottom:10px">
		<label class="col-sm-1 control-label">Inventory Quantity: &nbsp;</label>		
		<label class="col-sm-1 control-label" id="lblEditInventoryQty"></label>		
	</div>
	<br/>
	<div class="form-group" style="margin-bottom:10px">
		<label class="col-sm-1 control-label">Available Stock Qty : &nbsp;</label>		
		<label class="col-sm-1 control-label" id="lblEditAvailableQtyFromStock"></label>		
	</div>
	<br/>
	<div class="form-group" style="margin-bottom:10px">
		<label class="col-sm-5 control-label">Available Order Choose Qty: &nbsp;</label>		
		<label class="col-sm-1 control-label" id="lblEditAvailableQtyByChoosing"></label>		
	</div>
	<br/>
	<br/>
	<div class="form-group" style="margin-bottom:10px;margin-top:10px">
		<button class="btn btn-info btnChooseOrder" type="button" name="chooseOrder" >Choose Order</button>	
	</div>	
	<br/>	
	<div class="form-group" style="margin-bottom:10px">
		<label style="font-weight:bold; font-size:16px;">List Order: </label>	
	</div>			
	<div class="form-group" style="margin-bottom:20px">
		<div class="span6" style="margin-bottom:20px">
			<div id="listOrderIsAssignedCover" style="margin-bottom:30px">
				<table class="table table-striped table-bordered display responsive"
					id="listOrderIsAssigned">
					<thead>
						<tr>
							<th>Order Sheet No</th>
							<th>Order Assign Qty</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<hr width=100% align=left style="margin: 20px" noshade>		
	<div class="form-group" style="margin-top:20px">
		<label style="font-weight:bold; font-size:16px;">Assign Details: </label>	
	</div>	
	
	<div class="form-group" style="margin-bottom:10px;margin-top:10px">
		<button class="btn btn-primary btnUseRecommendQty" type="button" name="btnUseRecommendQty" >Use Recommend Qty</button>	
	</div>	
	
	<div class="form-group">
		<div class="span8">
			<div id="listEditAssignInternalAccessoryQtyCover">
				<table class="table table-striped table-bordered display responsive"
					id="listEditAssignInternalAccessoryQty">
					<thead>
						<tr>
							<th>Assign For</th>
							<th>Size</th>
							<th>Used Value</th>
							<th>Pcs</th>
							<th>Recommend Qty</th>
							<th>Assign Qty</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<br/>
	<div class="form-group" style="margin-top:20px; float=:right">
       	<label style="margin-top:20px" for="txtViewDetailFactory" class="col-sm-3 control-label">Total Assign: &nbsp;</label>
		<div class="col-sm-4">
		    <input class="col-sm-4" type="number" step="any" class="form-control" id="txtTotalAssign" value="" name="txtTotalAssign" style="margin-top:20px">
		</div>
   	</div>
</div>

<div id="dialogListOrderInternalAccessoriesForAssign" style="display: none;" class="form-horizontal">
	<div class="form-group" style="margin-bottom:10px">
		<label for="txtAccessoryCode" class="col-sm-3 control-label">Accessory Code &nbsp;</label>
		<div class="col-sm-9">					
			<input type="text" class="form-control" id="txtAccessoryCode" value="" name="txtAccessoryCode" disabled>	
		</div>			
	</div>		
	<div class="form-group">
		<div class="span8">
			<div id="listOrderForAssignCover">
				<table class="table table-striped table-bordered display responsive"
					id="listOrderForAssign">
					<thead>
						<tr>
							<th>Order Sheet No</th>
							<th>Order Qty</th>
							<th>Actual Delv Qty</th>
							<th>Price Qty</th>
							<th>Available Qty</th>
							<th>Order Assign Qty</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="dialogAvailableQtyNotEnough" style="display: none;" class="form-horizontal">
	<span>Not enough quantity for assign</span>
</div>

<div id="dialogOrderQtyNotValid" style="display: none;" class="form-horizontal">
	<span>Order Chose Quantity is not valid! (Order Quantity is less than Assigned Quantity)</span>
</div>
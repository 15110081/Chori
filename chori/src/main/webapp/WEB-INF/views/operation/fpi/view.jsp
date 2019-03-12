<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>FPI</title>
</head>
<div>
		<div  style="width: 70%; float: left">
			<div class="form-group">
				<label for="noneorderaccessories" class="col-sm-6 control-label">None
					order accessories </label>
				<div class="col-sm-6">
					<input type="checkbox" class="form-control" id="noneorderaccessories" path="noneorderaccessories"
						name="noneorderaccessories" value="false"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="customer1" class="col-sm-3 control-label">Customer
					Name 1 <span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="customer1" name="customer1" >
						<option value="none">Select a Name</option>
					</select>
					<span style="font-size: 11px; color: red" id="errorCustomer1"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="customer2" class="col-sm-3 control-label">Customer
					Name 2 <span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="customer2" name="customer2" >
						<option value="none">Select a Name</option>
					</select>
					<span style="font-size: 11px; color: red" id="errorCustomer2"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="pireceiveddate" class="col-sm-3 control-label">PI
					Received Date</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="pireceiveddate"
						 value=""
						name="pireceiveddate"  />
					<span style="font-size: 11px; color: red" id="errorPireceiveddate"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="piestshipdate" class="col-sm-3 control-label">PI
					Est Ship Date</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="piestshipdate"
						 value="" name="piestshipdate"
						/>
					<span style="font-size: 11px; color: red" id="errorPiestshipdate"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="piattachedFile" class="col-sm-3 control-label">Pi
					Attached File</label>
				<div class="col-sm-9">
					<input type='file' class="form-control" 
						name='piattached' id='piattached' />
				</div>
			</div>
			<div class="form-group">
				<label for="btnLogofChange" class="col-sm-3 control-label"></label>
				<div class="col-sm-9">
					<input class="btn btn-primary" type="button" id="btnLogofChange" name="btnLogofChange"
						value="Log Of Change">
				</div>
			</div>
			
			<div class="form-group">
				<label for="importFPI" class="col-sm-3 control-label">Import FPI
					</label>
				<div class="col-sm-9">
					<input  type='file' class="form-control" 
						name='importFPI' id='importFPI' />
				</div>
			</div>
	</div>

		<div class="right" style="width: 30%; float: right">
		<div class="form-group">
				<label for="destinationcode" class="col-sm-3 control-label">Destination
				</label>
				<div class="col-sm-9">
					<select id="destinationcode" name="destinationcode">
						<option value="none">Select A Destination</option>
						</select>
					<span style="font-size: 11px; color: red" id="errorDestinationcode"></span>
				</div>
			</div>
		
			<div class="form-group">
				<label for="factorycode" class="col-sm-3 control-label">Factory
					Name </label>
				<div class="col-sm-9">
				<select id="factorycode" name="factorycode">
					<option value="none">Select A Factory</option>
					</select>
					<span style="font-size: 11px; color: red" id="errorFactorycode"></span>
				</div>
			</div>

			

			<div class="form-group">
				<label for="status" class="col-sm-3 control-label">Status </label>
				<div class="col-sm-9">
					<select id="status" name="status" >
						<option value="Normal">Normal</option>
						<option value="Urgent">Urgent</option>
						<option value="Pending">Pending</option>
					</select>
					<span style="font-size: 11px; color: red" id="errorStatus"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="manufactureguideFile" class="col-sm-3 control-label">Sewing
					Guide</label>
				<div class="col-sm-9">
					<input type='file' class="form-control"
						 name='sewingguide'
						id='sewingguide' />
				</div>
			</div>
			<div class="form-group">
				<label for="packingguideFile" class="col-sm-3 control-label">Packing
					Guide</label>
				<div class="col-sm-9">
					<input type='file' class="form-control"
						 name='packingguide' id='packingguide' />
				</div>
			</div>
			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label">Remark</label>
				<div class="col-sm-9">
					<textarea id="areaRemak" rows="5" cols="5"></textarea>
				</div>
			</div>
		</div>

</div>
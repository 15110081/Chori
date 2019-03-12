                      <script src="${pageContext.request.contextPath}/resources/userJs/warningmessage/warningmessage.js"></script>     
                            <!-- PI warning message-->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left" style="color: red">PI Completion Date Warning</div>
                                    <div class="pull-right"><span class="badge badge-info" id="txtTotalPI"></span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped table-bordered display responsive" id="tblWarningMessageForPI">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Lot Number</th>
                                                <th>Customer</th>
                                                <th>Delivery Date</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                            <!-- Packing guide order external accessory warning message -->
                           <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left" style="color: red">Order Accessory For Packing Accessory Warning</div>
                                    <div class="pull-right"><span class="badge badge-info" id="txtTotalPackingGuideOrderExtAcc"></span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped table-bordered display responsive" id="tblWarningMessageForPackingGuideOrderExtAcc">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Order Sheet No</th>
                                                <th>Accessory Code</th>
                                                <th>Factory Name</th>
                                                <th>Order Date</th>
                                                <th>Order Quantity</th>
                                                <th>Estimate Delv Date</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>

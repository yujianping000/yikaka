
                       		<div class="row">
                       	            <input type="hidden" id="passId" name="passId" value="${offWork.id}">
	                                <div class="col-sm-9">
	                                		 	<p class="form-control-static">开始时间:</p>	<p class="form-control-static">${offWork.start?string('yyyy-MM-dd HH:mm')}</p>
	                                </div>
                       		
                       			  </div>
                       			  
                       				<div class="row">

	                                <div class="col-sm-9">
	                               		<p class="form-control-static">结束时间:</p>	<p class="form-control-static">[#if offWork.end??]${offWork.end?string('yyyy-MM-dd HH:mm')}[/#if]</p>
	                                </div>
                       			         </div>  
                       			         
                       			         
                       			        	<div class="row">

	                                <div class="col-sm-9">
	                                <p class="form-control-static">请假天数:</p>		<p class="form-control-static"> <input id="dayNum" name="dayNum"  value="${offWork.dayNum}"></p>
	                                </div>
							</div> 
                       			         
                    
							<!--/row-->



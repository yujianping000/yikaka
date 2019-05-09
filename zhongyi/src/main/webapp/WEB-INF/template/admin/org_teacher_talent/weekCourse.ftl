
			                    <input type="hidden" value="" id="teacherId" name="teacherId" />
			                    <div class="dataTables_wrapper form-inline">
						   
								   <style>
										th,td {text-align:center}
									</style>
			                        <table class="table table-striped table-bordered table-hover dataTables-example dataTable">
			                            <thead>
			                                <tr>
			                                	<th></th>
			                                    <th>星期一</th>
			                                    <th>星期二</th>
			                                    <th>星期三</th>
			                                	<th>星期四</th>
			                                	<th>星期五</th>
			                                    <th>星期六</th>
			                                    <th>星期日</th>
			                                </tr>
			                            </thead>
	                             		<tbody>
		                             		[#list weekCourseList as week]
				                             	<tr>
				                             		<td>
				                             			[#if week.type = 1]
				                             				上午
				                             			[/#if]
				                             			[#if week.type = 2]
				                             				下午
				                             			[/#if]
				                             			[#if week.type = 3]
				                             				晚上
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.monday = 1]
				                             				
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.tuesday = 1]
				                             				
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.wednesday = 1]
				                             			
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.thursday = 1]
				                             				
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.friday = 1]
				                             				
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.saturday = 1]
				                             				
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             		<td>
				                             			[#if week.sunday = 1]
				                             				
				                             			[#else]
				                             				有空
				                             			[/#if]
				                             		</td>
				                             	</tr>
				                             [/#list]
			                             	
		                                </tbody>
		                             </table>
		                      </div>
		                  
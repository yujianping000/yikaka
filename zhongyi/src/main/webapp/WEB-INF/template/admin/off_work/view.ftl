                           							<table class="table table-striped" style="border:0">
														<tbody>
															<tr>
																<td style="width:20%">请假类型:</td>
																<td>${typeName}</td>
															</tr>
															<tr>
																<td>课程名称:</td>
																<td>${courseName}</td>
															</tr>
															<tr>
																<td>课程课时:</td>
																<td> [#list coursetineTchs as coursetine] ${(coursetine)!''}</br>
																      [/#list] </td>
															</tr>
																<tr>
																<td>开始时间:</td>
																<td>[#if offWork.start??]${offWork.start?string('yyyy-MM-dd HH:mm')}[/#if]</td>
															</tr>
																<tr>
																<td>结束时间:</td>
																<td>[#if offWork.end??]${offWork.end?string('yyyy-MM-dd HH:mm')}[/#if]</td>
															</tr>
																<tr>
																<td>请假天数:</td>
																<td>${offWork.dayNum}</td>
															</tr>
																<tr>
																<td>请假事由:</td>
																<td>${offWork.description}</td>
															</tr>
																<tr>
																<td>上传图片:</td>
																<td>
																[#if offWork.image??]
																 [#list offWork.image?split(",") as s]
																<img src="${s}" style="width:50px;height:50px">
																[/#list]
																[/#if]
																</td>
													     	</tr>
																<tr>
																<td>拒绝原因:</td>
																<td>${offWork.reason}</td>
															</tr>
														</tbody>
													</table>

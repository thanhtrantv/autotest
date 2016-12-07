jQuery(document).ready(
		function($) {
			var lstTestCase;
			
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			loadTestCase();
			function loadTestCase(){
				$.ajax({
					url : "loadTestcase",
					type : "GET",
					contentType : "application/json",
					success : function(obj) {
						lstTestCase=JSON.parse(obj);
						console.log(lstTestCase);
						$.each(lstTestCase,function(key,value){
							var statusTC=value.testcaseStatus;
							$('#list-testcase').append("<li><div class='alert alert-info'> <strong><a href='#' idTC="+value.testcaseId+">"+value.testcaseName+"</a></strong> <button type='button' class='btn btn-info'>Run</button></div></li>");
							testId++;
						});
						$('#name-testcase').attr('testcaseid',lstTestCase[0].testcaseId);
						loadDetailTestCase();
						$('.sidenav ul li>div').removeClass('active');
						$('.sidenav ul li:first-child>div').addClass('active');	
					}
				});
			}
			function loadDetailTestCase(){
				$("#sortable").empty();
				$("#sortable").append('<li class="ui-state-default add-button"><div class="btn btn-default"><span class="glyphicon glyphicon-plus"></span></div></li>');
				$.ajax({
					url : "loadTestcaseDetail",
					type : "GET",
					data: {
						id:$('#name-testcase').attr('testcaseid')
					},
					contentType : "application/json",
					success : function(obj) {
						var testCaseDetail=JSON.parse(obj);
						//console.log(testCaseDetail);
						$('#name-testcase').text(testCaseDetail.testcaseName);
						$('#testcase-desc').text(testCaseDetail.testcaseDesc);
						$('#url-web').text(testCaseDetail.testcaseUrl);
						$('#ip-computer').text(testCaseDetail.testcaseIp);
						$('#browser-tc').find('.'+testCaseDetail.testcaseBrowser).prop('checked',true);
						$('#testcase-status').text(testCaseDetail.testcaseStatus);
						//console.log(JSON.parse(testCaseDetail.lstStep));
						
						if(testCaseDetail.lstStep!=null ){
							var lsttc=JSON.parse(testCaseDetail.lstStep);
							console.log(lsttc);
							$.each(lsttc,function(key,value){
								if(key==lsttc.length-1)return;
								//var li='<li class="ui-state-default"><div class="form-group header-step-label">	<label class="control-label">Step</label><span class="ui-state-default sortable-number" >1</span><span class="glyphicon glyphicon-remove"></span></div>	<div class="col-md-12 show-grid" >			  <div class="header-step">
								var liStep=$("#data-clone li").clone();
								$( liStep ).insertBefore($("#sortable li:last-child"));
								liStep.find('.header-step textarea').text("123");
								liStep.find('.select-action select').val(value.action);
								liStep.find('.element-type-action select').val(value.elementType);
								liStep.find('.element-define-action input').val(value.elementDefine);
								liStep.find('.value-enter-form input').val(value.valueEnter);
								
								$(document).find( "#sortable" ).sortable( "refreshPositions" );
								var $lis = $(document).find( "#sortable" ).children('li');
								$lis.each(function() {
									var $li = $(this);
									var newVal = $(this).index() + 1;
									//console.log($(this).children('.sortable-number'));
									$(this).find('.sortable-number').text(newVal);
									$(this).find('#item_display_order').val(newVal);
								});
							});
						}
						
					}
				});
			}
			$("#sortable").sortable(
					{
						placeholder : "ui-state-highlight",
						helper : 'clone',
						sort : function(e, ui) {
							$(ui.placeholder).html(
									Number($("#sortable > li:visible").index(
											ui.placeholder)) + 1);
						},
						update : function(event, ui) {
							console.log('duplicate!!!!!!')
							var $lis = $(this).children('li');
							$lis
									.each(function() {
										var $li = $(this);
										var newVal = $(this).index() + 1;
										console.log($(this).children(
												'.sortable-number'));
										$(this).find('.sortable-number').text(
												newVal);
										$(this).find('#item_display_order')
												.val(newVal);
									});
						}
					});
			$(document).find("#sortable").disableSelection();
			$(document).on('click','.header-step-label',function(e) {
				$(this).next('.show-grid').find('.content-step').toggle('fast');
			});
			$(document).on('click','.ui-state-default .glyphicon-remove',function(){
				$(this).closest('li').remove();
				var $lis = $( "#sortable" ).children('li');
				$lis.each(function() {
					var $li = $(this);
					var newVal = $(this).index() + 1;
					console.log($(this).children('.sortable-number'));
					$(this).find('.sortable-number').text(newVal);
					$(this).find('#item_display_order').val(newVal);
				});
			});
			$(document).on('click','#sortable .add-button',function(){
				var liStep=$("#data-clone li").clone();
				$( liStep ).insertBefore($(this).closest('li'));
				$(document).find( "#sortable" ).sortable( "refreshPositions" );
				var $lis = $(document).find( "#sortable" ).children('li');
				$lis.each(function() {
					var $li = $(this);
					var newVal = $(this).index() + 1;
					console.log($(this).children('.sortable-number'));
					$(this).find('.sortable-number').text(newVal);
					$(this).find('#item_display_order').val(newVal);
				});
				
			});
			var testId=1;
			$(document).on('click','.sidenav .btn-default',function(){
				$('#list-testcase').append("<li><div class='alert alert-info'> <strong><a href='#' idTC="+testId+">Test case "+testId+"</a></strong> <button type='button' class='btn btn-info'>Run</button></div></li>");
				testId++;
			});
			$(document).on('click','.sidenav ul li>div',function(){
				$('#name-testcase').text($(this).find('a').text());
				$('#name-testcase').attr('testcaseId',$(this).find('a').attr('idtc'));
				$('.sidenav ul li>div').removeClass('active');
				$(this).addClass('active');	
				loadDetailTestCase();
			});
			$(document).on('click','#save-testcase',function(){
				var listStep = [];
						var $lis = $(document).find('ul#sortable li');
						var i = 0;
						$lis
								.each(function() {
									var $li = $(this);
									var step = {};
									step.action = $li.find('.action-form')
											.find('select').val();
									step.elementType = $li.find(
											'.element-type-form')
											.find('select').val();
									step.elementDefine =$li.find(
											'.element-define-form').find(
											'input').val();
									step.valueEnter = $li.find(
											'.value-enter-form').find('input')
											.val();
									step.combineMultiAction='N';
									step.desc=$li.find('.header-step textarea').val();
									if(i==($lis.length-1))
										step.combineMultiAction='N';
									console.log(step);
									listStep[i] = step;
									i++;
								});
						console.log(listStep);
						var send = {};
						//send.lstStep = listStep;
						 send.lstStep=JSON.stringify(listStep);
						 //send.url="https://www.youtube.com/";
						// send.url="https://www.google.com/";
						 send.url=$('#url-web').val();
						 send.ip=$('#ip-computer').val();
						 send.id=$('#name-testcase').attr('testcaseid');
						 send.browser=$('input[name=browser]:checked').val();
						 send.desc=$('#testcase-desc').val();
						 send.name=$('#name-testcase').val();
						send.statusTC=$('#testcase-status').val();
						console.log(send)
						$.ajax({
							url : "saveTestcase",
							type : "GET",
							contentType : "application/json",
							data : send,

							success : function(msg) {
								$('.answer').html(msg);
							}
						});
			});
			$(document).find("#run-testcase").click(
					function(event) {
						var listStep = [];
						var $lis = $(document).find('ul#sortable li');
						var i = 0;
						$lis
								.each(function() {
									var $li = $(this);
									var step = {};
									step.action = $li.find('.action-form')
											.find('select').val();
									step.elementType = $li.find(
											'.element-type-form')
											.find('select').val();
									step.elementDefine =$li.find(
											'.element-define-form').find(
											'input').val();
									step.valueEnter = $li.find(
											'.value-enter-form').find('input')
											.val();
									step.combineMultiAction='N';
									if(i==($lis.length-1))
										step.combineMultiAction='N';
									console.log(step);
									listStep[i] = step;
									i++;
								});
						console.log(listStep);
						var send = {};
						//send.lstStep = listStep;
						 send.lstStep=JSON.stringify(listStep);
						 //send.url="https://www.youtube.com/";
						// send.url="https://www.google.com/";
						 send.url=$('#url-web').val();
						 send.ip=$('#ip-computer').val();
						 send.browser=$('input[name=browser]:checked').val();
						// console.log(JSON.parse(listStep))
						console.log(send)
						$.ajax({
							url : "runTestcase",
							type : "GET",
							contentType : "application/json",
							data : send,

							success : function(msg) {
								if(msg=='pass'){
									$('#testcase-status').text("Pass");
									$('#testcase-status').addClass("pass");
								}else{
									$('#testcase-status').text("Fail");
									$('#testcase-status').removeClass("pass");
								}
							}
						});
					});

		});
